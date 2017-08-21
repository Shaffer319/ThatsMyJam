<%-- 
    Document   : Account.jsp
    Created on : Jul 19, 2017, 1:42:50 PM
    Author     : mshaffer
--%>

<%@page import="com.thatsmyjam.constants.Action, com.thatsmyjam.constants.URL" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/includes/header.jsp" />

<div class="container" >
    <jsp:include page="includes/navbar.jsp" />

    <h1>Hello World!</h1>

    <p>
        Display user account info and allow then to change it.
    </p>
    <a href="<c:url value='/logout'/>">Logout <c:url value="/logout"/></a>

    <form action="createAccount" >
        <div>
            <label>First Name:</label>
            <input type="fname" name="fname" value="${user.firstName}"/>
        </div>
        <div>
            <label>Last Name:</label>
            <input type="lname" name="lname" value="${user.lastName}"/>
        </div>
        <div>
            <label>Email:</label>
            <input type="email" name="email" value="${user.email}"/>
        </div>
        <div>
            <label>Confirm Email:</label>
            <input type="email" name="cemail" value="${user.email}"/>
        </div>
        <input type="submit" value="Change"/>
    </form>
</div>
<jsp:include page="/includes/footer.jsp" />
