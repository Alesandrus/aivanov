<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Fail create</title>
    </head>
    <body>
        <div align="center">
            <h2>Пользователь с данным именем уже зарегистрирован</h2>
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
