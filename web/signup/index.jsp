<%-- 
    Document   : signup
    Created on : Jul 19, 2017, 1:44:57 PM
    Author     : mshaffer
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="com.thatsmyjam.constants.Action, com.thatsmyjam.constants.URL" %>

<jsp:include page="/includes/header.jsp" />
<jsp:include page="/includes/navbar.jsp" />

        <section class="login-block">
            <div class="container">
                <!--<div class="row">-->
                    <div class="login-sec">
                        <h2 class="text-center">Create Account</h2>
                        <!--<p><%= request.getAttribute("message") %></p>-->
                        <center><span style="color:red;">${message}</span></center><br/>
                        <form method="post" action="createAccount"  class="form-horizontal" role="form" align="center" autocomplete="on">
                            <div class="form-group">
                                <label for="fname" class="text-uppercase">First name </label>
                                <input type="fname" id="fname" value="" class="form-control" name="fname" id="fname" placeholder="Enter First Name" autocomplete="on" required><br>

                                <label for="lname" class="text-uppercase">Last name </label>
                                <input type="lname" id="lname" value="" class="form-control" name="lname" placeholder="Enter Last Name" required><br>

                                <label for="email" class="text-uppercase">Email</label>
                                <input type="email" id="email" value="" class="form-control" name="email" placeholder="Enter Email" required>
                            </div>
                            
                            <div class="form-group">
                                <label for="pass" class="text-uppercase">Password</label>
                                <input type="password" id="pass" class="form-control" placeholder="Password" name="pass" required>
                            </div>

                            <div class="form-group">
                                <label for="cpass" class="text-uppercase">Confirm Password</label>
                                <input type="password" id="cpass" class="form-control" placeholder="Confirm Password" name="cpass" required>
                            </div>
                            
                            <div class="form-check">
                                <input type="submit" class="btn btn-newAcct float-bottom" name="login" value="Sign Up"/>
                                <!--<button type="submit" name="login" class="btn btn-login float-right">Login</button>-->
                            </div>
                        </form>
                    </div>
                <!--               
                <div class="col-md-8 banner-sec">	   
                </div>
                -->
                <!--</div>-->
            </div>
        </section>

 <jsp:include page="/includes/footer.jsp" />