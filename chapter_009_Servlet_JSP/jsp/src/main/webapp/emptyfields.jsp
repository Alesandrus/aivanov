<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Empty fields</title>
    </head>
    <body>
        <div align="center">
            <h2>Ни одно из полей пользвателя не было изменено</h2>
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
