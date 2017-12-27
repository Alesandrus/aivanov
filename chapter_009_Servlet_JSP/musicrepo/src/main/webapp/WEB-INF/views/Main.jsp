<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <title>List of users</title>
    </head>
    <body>
        <div align="center">
            <h1>Hi ${sessionScope.login}</h1>
            <jsp:include page="Logout.jsp"/>
            <br>
            <h3><a href=createuser>Create user</a></h3>
            <table border=1 cellspacing=0 cellpadding=1>
                <tr>
                    <th>№</th>
                    <th>User's login</th>
                </tr>

                <c:forEach var="user" items="${users}" varStatus="loopCount" >
                    <c:if test="${sessionScope.login ne user}">
                        <tr>
                            <td>${loopCount.count}</td>
                            <td>
                                <a href=${pageContext.request.getContextPath()}/getuser?login=${user.login}>${user.login}</a>
                            </td>
                        </tr>
                    </c:if>
                </c:forEach>
            </table>
        </div>
    </body>
</html>
