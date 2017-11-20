<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
    <head>
        <title>User create</title>
    </head>
    <body>
        <div align="center">
            <h2>Пользователь успешно создан</h2>
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
