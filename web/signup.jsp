<%-- 
    Document   : signup
    Created on : Jul 19, 2017, 1:44:57 PM
    Author     : mshaffer
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="com.thatsmyjam.Action, com.thatsmyjam.URL" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Sign Up</title>
        <!-- Bootstrap core CSS  -->
        <link href="css/bootstrap.min.css" rel="stylesheet">
        <link href="css/loginStyle.css" rel="stylesheet">
    </head>
    <body>
        <div class="banner-text">
            <h2>That's My Jam!</h2>
            <p>Create user</p>
        </div>	
        <section class="login-block">
            <div class="container">
                <!--<div class="row">-->
                    <div class="col-md-4 login-sec">
                        <h2 class="text-center">Create Account</h2>
                        <form method="post" action="TestServlet"  class="form-horizontal" role="form" align="center">
                            <input type="hidden" name="action" value="<%= Action.ACTION_SIGNUP %>" />

                            <div class="form-group">
                                <label for="fname" class="text-uppercase">First name: </label>
                                <input type="fname" class="form-control" name="fname" required><br>

                                <label for="lname" class="text-uppercase">Last name: </label>
                                <input type="lname" class="form-control" name="lname" required><br>
                            </div>
                            <div class="form-group">
                                <label for="password" class="text-uppercase">Password</label>
                                <input type="password" class="form-control" placeholder="Password" required>
                            </div>
                            <div class="form-group">
                                <label for="password" class="text-uppercase">Password</label>
                                <input type="password" class="form-control" placeholder="Password" required>
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
        <!-- Bootstrap core Javascript and jQuery  -->
        <!-- Placed at the end for faster loading of pages -->
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
        <script src="js/bootstrap.min.js"></script>
    </body>
</html>
