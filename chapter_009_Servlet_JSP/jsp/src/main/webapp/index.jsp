<%@ page import="java.util.List" %>
<%@ page import="ru.job4j.UserStore" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%! private final UserStore users = UserStore.getInstance();%>
<!DOCTYPE html>
<html>
    <head>
        <title>CRUD-JSP</title>
    </head>
    <body>
        <div align="center">
            <h1>User's list</h1>
            <table border=1 cellspacing=0 cellpadding=1>
                <tr>
                    <th>№</th>
                    <th>User's login</th>
                    <th colspan=2>Action</th>
                </tr>
                <% List<String> usersList = users.getAll();
                for (int i = 0; i < usersList.size(); i++) {
                    String login = usersList.get(i); %>
                <tr>
                    <td><%= i + 1 %></td>
                    <td>
                        <a href=<%= request.getContextPath() + "/getuser?login="%><%=login%> ><%= login%></a>
                    </td>
                    <td>
                        <form action=update.jsp method=GET>
                            <input type=hidden name=login value=<%= login%>>
                            <input type=submit value=update>
                        </form>
                    </td>
                    <td>
                        <form action=deleteuser method=POST>
                            <input type=hidden name=login value=<%= login%>>
                            <input type=submit value=delete>
                        </form>
                    </td>
                </tr>
                <% } %>
            </table>
            <br>
            <h3><a href=<%= request.getContextPath() + "/create.jsp" %>>Создать нового пользователя</a></h3>
        </div>
    </body>
</html>
