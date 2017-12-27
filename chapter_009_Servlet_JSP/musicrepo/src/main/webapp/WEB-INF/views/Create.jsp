<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Creation</title>
    </head>
    <body>
        <div align="center">
            <h2>Create user</h2>
            <form name="createForm" action=createuser method="POST">
                <table>
                    <tr>
                        <td>Login</td>
                        <td>
                            <input type="text" name="login" required >
                        </td>
                    </tr>
                    <tr>
                        <td>Password</td>
                        <td>
                            <input type="text" name="password" required >
                        </td>
                    </tr>
                    <tr>
                        <td>Name</td>
                        <td>
                            <input type="text" name="name" >
                        </td>
                    </tr>
                    <tr>
                        <td>Role</td>
                        <td>
                            <select name="roles">
                                <option value="user" selected>User</option>
                                <option value="mandator">Mandator</option>
                                <option value="admin">Admin</option>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td>City</td>
                        <td>
                            <input type="text" name="city" >
                        </td>
                    </tr>
                    <tr>
                        <td>Street</td>
                        <td>
                            <input type="text" name="street" >
                        </td>
                    </tr>
                    <tr>
                        <td>House</td>
                        <td>
                            <input type="number" min="0" name="house" >
                        </td>
                    </tr>
                    <c:forEach var="type" items="${musictypes}" >
                        <tr>
                            <td>Music style ${type.type}</td>
                            <td>
                                <input type="checkbox" name="musictypes" value="${type.type}">
                            </td>
                        </tr>
                    </c:forEach>
                    <tr>
                        <td colspan="2" align="center">
                            <input type="submit" value="create">
                        </td>
                    </tr>
                </table>
            </form>
        </div>
    </body>
</html>
