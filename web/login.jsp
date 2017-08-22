<%-- 
    Document   : login.jsp
    Created on : Aug 16, 2017, 1:19:02 PM
    Author     : mshaffer
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/includes/header.jsp" />
<jsp:include page="includes/navbar.jsp" />
<!--
    <div class="banner-text">
        <h2>That's My Jam!</h2>
        <p>Please Login</p>
    </div>
-->
    <section class="login-block">
        <div class="container">
            <div class="row">
                <div class="login-sec">
                    <h2 class="text-center">Login</h2>
                    <!-- Special Container Security Login via form -->
                    <form action="j_security_check" method="post" class="form-horizontal" role="form" align="center">
                        <div class="form-group">
                            <label for="username" class="text-uppercase">Username</label>
                            <input type="email" class="form-control" name="j_username" placeholder="Username">
                        </div>
                        <div class="form-group">
                            <label for="password" class="text-uppercase">Password</label>
                            <input type="password" class="form-control"  name="j_password" placeholder="Password">
                        </div>
                        <div class="form-check">
                            <input type="submit" value="Login"  class="btn btn-success float-right" style="width: 200px;">
                        </div>
                    </form>
                </div>
                <div>
                    <form method="post" action="<c:url value="/signup.jsp"/>"  class="form-horizontal" style="margin: 10px;" role="form" align="center">

                        <div class="form-group">
                            <button type="submit" name="newAcct" class="btn btn-newAcct float-bottom" style="width: 200px;" >Create an Account</button>
                        </div>
                    </form>
                </div>      
                <div class="col-md-8 banner-sec">
                </div>
            </div>
        </div>
    </section>

 <jsp:include page="/includes/footer.jsp" />

