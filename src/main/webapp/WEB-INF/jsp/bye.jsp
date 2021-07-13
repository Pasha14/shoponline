<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Bye!</title>
</head>
<body>
<h2>Students demo - bye page!</h2>
</body>

<div>
    <h1>System Users</h1>
</div>
<div>
    <table>
        <tr>
            <td>Buyer Id</td>
            <td>Buyer Login</td>
            <td>Buyer Password</td>
            <td>Buyer Name</td>
            <td>Buyer Surname</td>
            <td>Birth date</td>
            <td>Phone</td>
            <td>Email</td>
            <td>Post code</td>
            <td>City</td>
            <td>Address</td>
        </tr>
        <%--        Show all users from collection of elements by jstl--%>
        <c:forEach var="buyer" items="${buyers}">
            <tr>
                <td>${buyer.id}</td>
                <td>${buyer.login}</td>
                <td>${buyer.password}</td>
                <td>${buyer.name}</td>
                <td>${buyer.surname}</td>
                <td>${buyer.birthDate}</td>
                <td>${buyer.phone}</td>
                <td>${buyer.email}</td>
                <td>${buyer.postal_code}</td>
                <td>${buyer.city}</td>
                <td>${buyer.address}</td>
                <td>
                    <button>Edit</button>
                </td>
                <td>
                    <button>Delete</button>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>
<div>
    ${singleUser}
</div>
</html>