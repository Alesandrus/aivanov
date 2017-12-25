<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <title>User info</title>
    </head>
    <body>
        <div align="center">
            <h2>Info about user</h2>
            <h2>${login}</h2>
            <table border=1 cellspacing=0 cellpadding=5>
                <c:if test="${sessionScope.role eq 'admin'}">
                    <tr>
                        <td>Password</td>
                        <td>${password}</td>
                    </tr>
                </c:if>
                <tr>
                    <td>Name</td>
                    <td>${name}</td>
                </tr>
                <tr>
                    <td>Role</td>
                    <td>${role}</td>
                </tr>
                <tr>
                    <td>City</td>
                    <td>${city}</td>
                </tr>
                <tr>
                    <td>Street</td>
                    <td>${street}</td>
                </tr>
                <tr>
                    <td>House</td>
                    <td>${house}</td>
                </tr>
                <c:forEach var="music" items="${musictypes}" varStatus="loopCount">
                    <tr>
                        <td>Music style # ${loopCount.count}</td>
                        <td>${music.type}</td>
                    </tr>
                </c:forEach>

            </table>
            <br>
            <br>
            <jsp:include page="BackToList.jsp" />
        </div>
    </body>
</html>
