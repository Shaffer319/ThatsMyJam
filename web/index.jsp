<%-- 
    Document   : index.jsp
    Created on : Jul 6, 2017, 9:12:34 PM
    Author     : bean51591
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="com.thatsmyjam.Action, com.thatsmyjam.URL" %>
<jsp:include page="/includes/header.jsp" />

    <div class="banner-text">
        <h2>That's My Jam!</h2>
        <p>Welcome to our music store</p>
    </div>	
    <section class="login-block">
        <div class="container">
            <div class="row">
                <div class="col-md-4 login-sec">
                    <h2 class="text-center">Login</h2>


                    <form method="post" action="ThatsMyJam"  class="form-horizontal" role="form" align="center">
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
                            <input type="submit" name="login" value="Login"/>
                            <!--<button type="submit" name="login" class="btn btn-login float-right">Login</button>-->
                        </div>
                    </form>

                    <form method="post" action="ThatsMyJam"  class="form-horizontal" role="form" align="center">
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
        </div>
    </section>

 <jsp:include page="/includes/footer.jsp" />

