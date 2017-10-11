package ru.job4j.sqlruparser;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Класс, который парсит сайт SQL.ru, берет с него вакансии по Java и сохраняет их в базу данных.
 * Если в базе отсутствуют записи, то парсинг осуществляется с начала текущего года, иначе с момента
 * последнего запуска.
 *
 * @author Alexander Ivanov
 * @version 1.0
 * @since 11.10.2017
 */
public class JavaJobHunter implements Runnable {
    /**
     * Логгер.
     */
    private static final Logger LOGGER = LogManager.getLogger(JavaJobHunter.class.getName());

    /**
     * Время последнего запуска.
     */
    private Calendar lastStart;

    /**
     * Флаг для остановки парсинга. Переводится в положение True если время последнего комментария
     * к вакансии позже чем время последнего запуска или начала года в случае если это первый запуск.
     */
    private boolean stopParse = false;

    /**
     * XML документ, в котором хранятся все насторойки.
     */
    private org.w3c.dom.Document resource;

    /**
     * Соединение с базой данных.
     */
    private Connection connection;

    /**
     * Конструктор класса. Устанавливает переменную resource, connection, создает таблицы в базе данных если их там
     * еще нет. Определяет время последнего запуска, либо его отсутствия.
     */
    public JavaJobHunter() {
        setResource();
        setConnection();
        createTables();
        lastStart = Calendar.getInstance();
        if (isFirstStart()) {
            int currentYear = lastStart.get(Calendar.YEAR);
            lastStart.set(currentYear, 0, 1, 0, 0, 0);
        } else {
            lastStart.setTimeInMillis(getLastStart());
        }
    }

    /**
     * Геттер для Connection.
     *
     * @return объект соединения с базой данных.
     */
    public Connection getConnection() {
        return connection;
    }

    /**
     * Считывает из resource значение промежутка времени,
     * через которое будет производиться последующий парсинг вакансий.
     * (Решение о том в каких единицах времени будет выражаться данное значение принимается пользователем.)
     *
     * @return значение периода между работой потоков по парсингу SQL.ru.
     */
    public long getPeriod() {
        org.w3c.dom.Element root = resource.getDocumentElement();
        NodeList list = root.getElementsByTagName("period");
        org.w3c.dom.Element period = (org.w3c.dom.Element) list.item(0);
        return Long.parseLong(period.getTextContent());
    }

    /**
     * Метод для работы в потоке. Парсит все страницы с рубрики Вакансии сайта SQL.ru. Останавливается если флаг
     * выставлен в True. Затем добавляет все вакансии в базу данных. Устанавливает время работы данного метода, то есть
     * работы по парсингу сайта и записи данных. Перед завершением сбрасывает флаг stopParse.
     */
    @Override
    public void run() {
        int lastPage = Integer.parseInt(getLastPage());
        for (int i = 1; i <= lastPage; i++) {
            if (stopParse) {
                break;
            }
            addAllVacanciesToDatabase(parse(String.valueOf(i)));
            LOGGER.info(i + " page is parsed");
        }
        setLastStart();
        stopParse = false;
    }

    /**
     * Парсит страницу с сайта вакансий SQL.ru. Сохраняет записи с Java в виде объектов Vacancy.
     * Выбирает текст вакансии, адрес ссылки вакансии, имя топикстартера, ссылку на его профиль,
     * дату последнего коментария, статус объявления.
     * Парсинг останавливается если время последнего комментария окажется раньше последнего запуска
     * или времени начала текущего года. При этом флаг stopParse выставится в True и дальнейший парсинг прекращается.
     *
     * @param page номер страницы в рубрике Вакансии сайта SQL.ru.
     * @return список всех вакансий Java.
     */
    private List<Vacancy> parse(String page) {
        List<Vacancy> vacancies = new ArrayList<>();
        try {
            Document document = Jsoup.connect("http://www.sql.ru/forum/job-offers/" + page)
                    .userAgent("Mozilla").get();
            Elements rows = document.select("table.forumTable tr");
            for (Element e : rows) {
                Elements cells = e.children();
                boolean isClosed = false;
                String vacancy = "";
                String hrefVacancy = "";
                String topicStarter = "";
                String hrefTopicStarter = "";
                String lastComment = "";
                for (Element cell : cells) {
                    if (cell.hasClass("postslisttopic")) {
                        Elements cellChildren = cell.children();
                        for (Element cellChild : cellChildren) {
                            if (cellChild.hasClass("closedTopic")) {
                                isClosed = true;
                                break;
                            }
                        }
                        vacancy = cellChildren.first().text();
                        hrefVacancy = cellChildren.first().attributes().get("href");
                    }
                    if (cell.hasClass("altCol")) {
                        Elements cellChildren = cell.children();
                        for (Element cellChild : cellChildren) {
                            if (cellChild.is("a")) {
                                topicStarter = cellChild.text();
                                hrefTopicStarter = cellChild.attributes().get("href");
                            }
                        }
                    }
                    if (cell.hasClass("altCol") && cell.hasAttr("style")) {
                        lastComment = cell.text();
                    }
                }
                Calendar timeComment = null;
                if (!lastComment.isEmpty()) {
                    timeComment = parseDate(lastComment);
                    if (!hrefVacancy.equals("http://www.sql.ru/forum/485068/soobshheniya-ot-moderatorov-"
                            + "zdes-vy-mozhete-uznat-prichiny-udaleniya-topikov")
                            && !hrefVacancy.equals("http://www.sql.ru/forum/1196621/shpargalki")
                            && !hrefVacancy.equals("http://www.sql.ru/forum/484798/pravila-foruma")) {
                        if (timeComment.before(this.lastStart)) {
                            stopParse = true;
                            break;
                        }
                    }
                }
                Pattern pattern = Pattern.compile("java(\\W|$)(?!script)");
                Matcher matcher = pattern.matcher(vacancy.toLowerCase());
                if (matcher.find()) {
                    vacancies.add(new Vacancy(vacancy, hrefVacancy, topicStarter,
                            hrefTopicStarter, timeComment, isClosed));
                }
            }
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return vacancies;
    }

    /**
     * Парсит первую страницу рубрики Вакансии сайта SQL.ru и определяет последнюю странцу данной рубрики.
     *
     * @return запись с номером последней страницы.
     */
    private String getLastPage() {
        String maxPage = "";
        try {
            Document document = Jsoup.connect("http://www.sql.ru/forum/job-offers").userAgent("Mozilla").get();
            Elements numberOfPages = document.select("table.sort_options tr");
            for (Element rows : numberOfPages) {
                Elements cells = rows.children();
                for (Element cell : cells) {
                    if (cell.attributes().get("style").equals("text-align:left")) {
                        Elements pages = cell.children();
                        Element lastPage = pages.last();
                        maxPage = lastPage.text();
                    }
                }
            }
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return maxPage;
    }

    /**
     * Метод для парсинга даты последнего комментария. Парсинг фраз - сегодня, вчера и непосредственно
     * даты комментария если он был размещен на день раншье текущего времени.
     *
     * @param lastComment запись времени последнего комментария.
     * @return дату.
     */
    private Calendar parseDate(String lastComment) {
        Calendar date = Calendar.getInstance();
        String[] comment = lastComment.split(" ");
        String[] time = comment[comment.length - 1].split(":");
        int hour = Integer.parseInt(time[0]);
        int minute = Integer.parseInt((time[1]));
        int day;
        int month;
        int year;
        if (comment[0].equals("сегодня,")) {
            day = date.get(Calendar.DAY_OF_MONTH);
            month = date.get(Calendar.MONTH);
            year = date.get(Calendar.YEAR);
        } else if (comment[0].equals("вчера,")) {
            date.add(Calendar.DAY_OF_MONTH, -1);
            day = date.get(Calendar.DAY_OF_MONTH);
            month = date.get(Calendar.MONTH);
            year = date.get(Calendar.YEAR);
        } else {
            day = Integer.parseInt(comment[0]);
            month = getMonth(comment[1]);
            String curYear = 20 + comment[2].substring(0, comment[2].length() - 1);
            year = Integer.parseInt(curYear);
        }
        date.set(year, month, day, hour, minute);
        return date;
    }

    /**
     * Анализирует строку с названием месяца и передает число для получения объекта Calendar.
     *
     * @param mon обозначение месяца, использующееся на сайте SQL.ru.
     * @return номер месяца.
     */
    private int getMonth(String mon) {
        int month;
        switch (mon) {
            case "янв":
                month = 0;
                break;
            case "фев":
                month = 1;
                break;
            case "мар":
                month = 2;
                break;
            case "апр":
                month = 3;
                break;
            case "май":
                month = 4;
                break;
            case "июн":
                month = 5;
                break;
            case "июл":
                month = 6;
                break;
            case "авг":
                month = 7;
                break;
            case "сен":
                month = 8;
                break;
            case "окт":
                month = 9;
                break;
            case "ноя":
                month = 10;
                break;
            case "дек":
                month = 11;
                break;
            default:
                month = -1;
                break;
        }
        return month;
    }

    /**
     * Принимает список объектов Vacancy и сохраняет информацию о вакансиях и разместивших их пользователях
     * в две таблицы базу дынных. Информация о пользователях, ваканисии добавляется только если информации
     * о них еще нет в базе. Записи в базе могут обновляться, если был добавлен комментарий, и соотвественно время
     * комментария было изменено, также если объявление было закрыто или топикстартер изменил имя(если изменение
     * возможно). Уникальной записью выступают ссылки на пользователя и на размещенное объявление.
     *
     * @param vacancies список объектов Vacancy.
     */
    private void addAllVacanciesToDatabase(List<Vacancy> vacancies) {
        try {
            PreparedStatement userExistStatement = this.connection.
                    prepareStatement("SELECT name FROM users WHERE href = ?");
            PreparedStatement userInsertStatement = this.connection.
                    prepareStatement("INSERT INTO users (href, name) VALUES (?, ?)");
            PreparedStatement userUpdateStatement = this.connection.
                    prepareStatement("UPDATE users SET name = ? WHERE href = ?");
            PreparedStatement vacancyExistStatement = this.connection.
                    prepareStatement("SELECT lastcomment, isclosed FROM vacancies WHERE href = ?");
            PreparedStatement vacancyInsertStatement = this.connection.
                    prepareStatement("INSERT INTO vacancies "
                            + "(href, description, topicstarter_id, lastcomment, isclosed) VALUES "
                            + "(?, ?, (SELECT user_id FROM users u WHERE u.href = ?), ?, ?)");
            PreparedStatement vacancyUpdateStatement = this.connection.
                    prepareStatement("UPDATE vacancies SET lastcomment = ?, isclosed = ? WHERE href = ?");
            for (Vacancy vacancy : vacancies) {
                userExistStatement.setString(1, vacancy.getHrefTopicStarter());
                ResultSet userResultSet = userExistStatement.executeQuery();
                if (userResultSet.next()) {
                    String name = userResultSet.getString(1);
                    if (!name.equals(vacancy.getTopicStarter())) {
                        userUpdateStatement.setString(1, vacancy.getTopicStarter());
                        userUpdateStatement.setString(2, vacancy.getHrefTopicStarter());
                        userUpdateStatement.executeUpdate();
                    }
                } else {
                    userInsertStatement.setString(1, vacancy.getHrefTopicStarter());
                    userInsertStatement.setString(2, vacancy.getTopicStarter());
                    userInsertStatement.executeUpdate();
                }

                vacancyExistStatement.setString(1, vacancy.getHrefVacancy());
                ResultSet vacancyResultSet = vacancyExistStatement.executeQuery();
                if (vacancyResultSet.next()) {
                    java.sql.Date date = vacancyResultSet.getDate(1);
                    boolean isclosed = vacancyResultSet.getBoolean(2);
                    if (date.getTime() != vacancy.getLastComment().getTimeInMillis()
                            || isclosed != vacancy.isClosed()) {
                        vacancyUpdateStatement.setTimestamp(1,
                                new Timestamp(vacancy.getLastComment().getTimeInMillis()));
                        vacancyUpdateStatement.setBoolean(2, vacancy.isClosed());
                        vacancyUpdateStatement.setString(3, vacancy.getHrefVacancy());
                        vacancyUpdateStatement.executeUpdate();
                    }
                } else {
                    vacancyInsertStatement.setString(1, vacancy.getHrefVacancy());
                    vacancyInsertStatement.setString(2, vacancy.getDescription());
                    vacancyInsertStatement.setString(3, vacancy.getHrefTopicStarter());
                    vacancyInsertStatement.setTimestamp(4,
                            new Timestamp(vacancy.getLastComment().getTimeInMillis()));
                    vacancyInsertStatement.setBoolean(5, vacancy.isClosed());
                    vacancyInsertStatement.executeUpdate();
                }
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

    /**
     * Читает XML документ с настройками (jjhunter.xml), производится DOM-документ resource.
     */
    private void setResource() {
        try {
            DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = builderFactory.newDocumentBuilder();
            File file = new File("chapter_008_SQL_JDBC/src/main/resources/jjhunter.xml");
            this.resource = builder.parse(file);
            LOGGER.info("Getting resources");
        } catch (ParserConfigurationException | SAXException | IOException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

    /**
     * Устанавливает соединение к базе данных из resource на основании настроек, размещенных в XML-файле.
     */
    private void setConnection() {
        String database = "";
        String user = "";
        String password = "";
        org.w3c.dom.Element root = resource.getDocumentElement();
        NodeList children = root.getElementsByTagName("connect");
        NodeList connectChild = children.item(0).getChildNodes();
        for (int i = 0; i < connectChild.getLength(); i++) {
            Node node = connectChild.item(i);
            if (node instanceof org.w3c.dom.Element) {
                org.w3c.dom.Element element = (org.w3c.dom.Element) node;
                if (element.getTagName().equals("database")) {
                    Text text = (Text) element.getFirstChild();
                    database = text.getData().trim();
                } else if (element.getTagName().equals("user")) {
                    Text text = (Text) element.getFirstChild();
                    user = text.getData().trim();
                } else if (element.getTagName().equals("password")) {
                    Text text = (Text) element.getFirstChild();
                    password = text.getData().trim();
                }
            }
        }
        try {
            this.connection = DriverManager.
                    getConnection("jdbc:postgresql://localhost:5432/" + database, user, password);
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
        LOGGER.info("Getting connection to database");
    }

    /**
     * Читает из resource команды по созданию таблиц users и vacancies и создает их в базе данных.
     */
    private void createTables() {
        String users = "";
        String vacancies = "";
        org.w3c.dom.Element root = this.resource.getDocumentElement();
        NodeList children = root.getElementsByTagName("create");
        NodeList connectChild = children.item(0).getChildNodes();
        for (int i = 0; i < connectChild.getLength(); i++) {
            Node node = connectChild.item(i);
            if (node instanceof org.w3c.dom.Element) {
                org.w3c.dom.Element element = (org.w3c.dom.Element) node;
                if (element.getTagName().equals("users")) {
                    Text text = (Text) element.getFirstChild();
                    users = text.getData().trim();
                } else if (element.getTagName().equals("vacancies")) {
                    Text text = (Text) element.getFirstChild();
                    vacancies = text.getData().trim();
                }
            }
        }

        try {
            Statement statement = this.connection.createStatement();
            statement.executeUpdate(users);
            statement.executeUpdate(vacancies);
            LOGGER.info("Database is ready");
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

    /**
     * Считывает из resource значение времени последнего запуска в миллисекундах.
     *
     * @return время последнего запуска в миллисекундах.
     */
    private long getLastStart() {
        org.w3c.dom.Element root = resource.getDocumentElement();
        NodeList list = root.getElementsByTagName("laststart");
        org.w3c.dom.Element node = (org.w3c.dom.Element) list.item(0);
        return Long.parseLong(node.getTextContent());
    }

    /**
     * Определеят факт первого запуска приложения.
     *
     * @return True Если строки в базе данных отсутствуют.
     */
    private boolean isFirstStart() {
        boolean isFirst = true;
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT COUNT(*) FROM vacancies");
            if (rs.next() && rs.getInt(1) > 0) {
                isFirst = false;
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return isFirst;
    }

    /**
     * Сохраняет текущее время в переменную lastStart и записывает его в миллисекундах в DOM-объект resource.
     * Затем на основании resource перезаписывается файл jjhunter.xml.
     */
    private void setLastStart() {
        try {
            this.lastStart = Calendar.getInstance();
            org.w3c.dom.Element root = resource.getDocumentElement();
            NodeList list = root.getElementsByTagName("laststart");
            org.w3c.dom.Element node = (org.w3c.dom.Element) list.item(0);
            node.setTextContent(String.valueOf(this.lastStart.getTimeInMillis()));

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            DOMSource source = new DOMSource(resource);

            StreamResult streamResult =
                    new StreamResult(new File("chapter_008_SQL_JDBC/src/main/resources/jjhunter.xml"));
            transformer.transform(source, streamResult);
        } catch (TransformerException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }
}
