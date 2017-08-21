<%-- 
    Document   : login.jsp
    Created on : Aug 16, 2017, 1:19:02 PM
    Author     : mshaffer
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="com.thatsmyjam.constants.Action, com.thatsmyjam.constants.URL" %>
<jsp:include page="/includes/header.jsp" />

    <div class="banner-text">
        <h2>That's My Jam!</h2>
        <p>Please Login</p>
    </div>
    <section class="login-block">
        <jsp:include page="includes/navbar.jsp" />
        <div class="container">
            <div class="row">
                <div class="col-md-4 login-sec">
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
                            <input type="submit" value="Login"  class="btn btn-login float-right">
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
    </section>

 <jsp:include page="/includes/footer.jsp" />

