package ru.job4j.sqlruparser;

import java.util.Calendar;

/**
 * Класс, содержащий в себе информацию о вакансии.
 *
 * @author Alexander Ivanov
 * @version 1.0
 * @since 11.10.2017
 */
public class Vacancy {
    /**
     * Описание вакансии.
     */
    private String description;

    /**
     * Ссылка на вакансию.
     */
    private String hrefVacancy;

    /**
     * Имя пользователя, разместившего вакансию.
     */
    private String topicStarter;

    /**
     * Ссылка на профиль пользователя, разместившего вакансию.
     */
    private String hrefTopicStarter;

    /**
     * Время последнего комментария к вакансии.
     */
    private Calendar lastComment;

    /**
     * Статус вакансии (открыта или закрыта).
     */
    private boolean closed;

    /**
     * Конструктор класса.
     *
     * @param desc     описание.
     * @param hrefVac  ссылка на вакансию.
     * @param topStart имя топикстартера.
     * @param hrefTS   ссылка на профиль тс.
     * @param comment  время последенего комментария.
     * @param closed   статус вакансии.
     */
    public Vacancy(String desc, String hrefVac, String topStart, String hrefTS, Calendar comment, boolean closed) {
        this.description = desc;
        this.hrefVacancy = hrefVac;
        this.topicStarter = topStart;
        this.hrefTopicStarter = hrefTS;
        this.lastComment = comment;
        this.closed = closed;
    }

    /**
     * Геттер для описания вакансии.
     *
     * @return описание.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Сеттер для описания вакансии.
     *
     * @param description описание вакансии.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Геттер для ссылки на вакансию.
     *
     * @return ссылку.
     */
    public String getHrefVacancy() {
        return hrefVacancy;
    }

    /**
     * Сеттер для ссылки на вакансию.
     *
     * @param hrefVacancy ссылка на вакансию.
     */
    public void setHrefVacancy(String hrefVacancy) {
        this.hrefVacancy = hrefVacancy;
    }

    /**
     * Геттер для имени тс.
     *
     * @return имя тс.
     */
    public String getTopicStarter() {
        return topicStarter;
    }

    /**
     * Сеттер для имени тс.
     *
     * @param topicStarter имя тс.
     */
    public void setTopicStarter(String topicStarter) {
        this.topicStarter = topicStarter;
    }

    /**
     * Геттер для ссылки на профиль тс.
     *
     * @return ссылку на профиль тс.
     */
    public String getHrefTopicStarter() {
        return hrefTopicStarter;
    }

    /**
     * Сеттер для ссылки на профиль тс.
     *
     * @param hrefTopicStarter ссылка на профиль тс.
     */
    public void setHrefTopicStarter(String hrefTopicStarter) {
        this.hrefTopicStarter = hrefTopicStarter;
    }

    /**
     * Геттер на время последнего комментария к вакансии.
     *
     * @return время последнего комментария к вакансии.
     */
    public Calendar getLastComment() {
        return lastComment;
    }

    /**
     * Сеттер к времени последнего комментария к вакансии.
     *
     * @param lastComment время последнего комментария.
     */
    public void setLastComment(Calendar lastComment) {
        this.lastComment = lastComment;
    }

    /**
     * Геттер статуса вакансии.
     *
     * @return статус вакансии.
     */
    public boolean isClosed() {
        return closed;
    }

    /**
     * Сеттер статуса вакансии.
     *
     * @param closed статус вакансии.
     */
    public void setClosed(boolean closed) {
        this.closed = closed;
    }

    /**
     * Переопределенный метод equals(). Вакансии считается одинаковыми если ссылки у них одинаковы.
     *
     * @param o сравниваемый объект.
     * @return true если объекты идентичны.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Vacancy vacancy = (Vacancy) o;
        return hrefVacancy != null ? hrefVacancy.equals(vacancy.hrefVacancy) : vacancy.hrefVacancy == null;
    }

    /**
     * Переопределенный метод hashcode().
     *
     * @return целое значение хешкода ссылки на вакансию.
     */
    @Override
    public int hashCode() {
        return hrefVacancy != null ? hrefVacancy.hashCode() : 0;
    }

    /**
     * Переопреденный метод toString().
     *
     * @return подробное описание вакансии.
     */
    @Override
    public String toString() {
        return "Vacancy" + System.lineSeparator()
                + "description='" + description + '\'' + System.lineSeparator()
                + "hrefVacancy='" + hrefVacancy + '\'' + System.lineSeparator()
                + "topicStarter='" + topicStarter + '\'' + System.lineSeparator()
                + "hrefTopicStarter='" + hrefTopicStarter + '\'' + System.lineSeparator()
                + "lastComment='" + lastComment.getTime() + '\'' + System.lineSeparator()
                + "closed=" + (closed ? "Yes" : "No");
    }
}
