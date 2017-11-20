<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Not found user</title>
    </head>
    <body>
        <div align="center">
            <h2>Пользователь не найден в базе данных</h2>
            <br>
            <br>
            <%
                String root = "/";
                if (!request.getContextPath().isEmpty()) {
                    root = request.getContextPath();
                }
            %>
            <a href=<%=root%>>К списку пользователей</a>
        </div>
    </body>
</html>
