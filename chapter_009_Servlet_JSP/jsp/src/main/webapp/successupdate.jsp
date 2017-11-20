<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Success update</title>
    </head>
    <body>
        <div align="center">
            <h2>Информация о пользователе обновлена</h2>
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
