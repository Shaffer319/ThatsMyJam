<%-- 
    Document   : Account.jsp
    Created on : Jul 19, 2017, 1:42:50 PM
    Author     : mshaffer
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="com.thatsmyjam.constants.Action, com.thatsmyjam.constants.URL" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/includes/header.jsp" />
<jsp:include page="/includes/navbar.jsp" />

<div class="container" >
    <h1>Hello World!</h1>

    <p>
        Display user account info and allow then to change it.
    </p>
    
<!--    <form aciton="/logout" class="form-horizontal"  role="form" align="center" >
        <input class="btn btn-success" type="submit" value="Logout"/>
    </form>-->
    
    <form action="ProfileController/ChangeProfile" class="form-horizontal" role="form" align="center" method="post">
        <div class="form-group">
            <label class="col-sm-3 control-label">First Name:</label>
            <div class="col-sm-9">
                <input class="form-control" type="fname" name="fname" value="${user.firstName}"/>
            </div>
        </div>
        <div class="form-group">        
            <label class="col-sm-3 control-label">Last Name:</label>
            <div class="col-sm-9">
                <input class="form-control" type="lname" name="lname" value="${user.lastName}"/>
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-3 control-label">Email:</label>
            <div class="col-sm-9">
                <input class="form-control" type="email" name="email" value="${user.email}"/>
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-3 control-label">Confirm Email:</label>
            <div class="col-sm-9">
                <input class="form-control" type="email" name="cemail" value="${user.email}"/>
            </div>
        </div>
        <input class="btn btn-success" type="submit" value="Change"/>
    </form>
</div>
<jsp:include page="/includes/footer.jsp" />
