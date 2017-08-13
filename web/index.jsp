<%-- 
    Document   : index.jsp
    Created on : Jul 6, 2017, 9:12:34 PM
    Author     : bean51591
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="com.thatsmyjam.Action, com.thatsmyjam.URL" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">

        <title>That's My Jam</title>

        <!-- Bootstrap core CSS  -->
        <link href="css/bootstrap.min.css" rel="stylesheet">
        <link href="css/loginStyle.css" rel="stylesheet">
    </head>
    <body>
        <div class="banner-text">
            <h2>That's My Jam!</h2>
            <p>Welcome to our music store</p>
        </div>	
        <section class="login-block">
            <div class="container">
                <div class="row">
                    <div class="col-md-4 login-sec">
                        <h2 class="text-center">Login</h2>
                        
                        <form method="post" action="TestServlet"  class="form-horizontal" role="form" align="center">
                            <input type="hidden" name="action" value="<%= Action.ACTION_LOGIN %>" />

                            <div class="form-group">
                                <label for="username" class="text-uppercase">Username</label>
                                <input type="email" class="form-control" placeholder="Username">
                            </div>
                            <div class="form-group">
                                <label for="password" class="text-uppercase">Password</label>
                                <input type="password" class="form-control" placeholder="Password">
                            </div>
                            <div class="form-check">
                                <input type="submit" name="login" class="btn btn-login float-right" value="Login"/>
                                <!--<button type="submit" name="login" class="btn btn-login float-right">Login</button>-->
                            </div>
                        </form>
                            
                        <form method="post" action="TestServlet"  class="form-horizontal" role="form" align="center">
                            <input type="hidden" name="action" value="<%= Action.ACTION_SIGNUP %>" />
                            <div class="form-group">
                                <button type="submit" name="newAcct" class="btn btn-newAcct float-bottom">Create an Account</button>
                            </div>
                        </form>
                    </div>
                    <div class="col-md-8 banner-sec">
                    </div>
                </div>
            </div>
        </section>
        <!-- Bootstrap core Javascript and jQuery  -->
        <!-- Placed at the end for faster loading of pages -->
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
        <script src="js/bootstrap.min.js"></script>
    </body>
</html>
