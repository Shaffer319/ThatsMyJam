<%-- 
    Document   : index.jsp
    Created on : Jul 6, 2017, 9:12:34 PM
    Author     : bean51591
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="com.thatsmyjam.Action, com.thatsmyjam.URL" %>
<jsp:include page="/includes/header.jsp" />
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

    <div class="banner-text">
        <h2>That's My Jam!</h2>
        <p>Welcome to our music store</p>
    </div>	
    <div><a href="<c:url value="/admin/index.jsp"/>">Admin Site</a></div>
    <section class="login-block">
        <div class="container">
            <div class="row">
                <div class="col-md-4 login-sec">
                    <h2 class="text-center">Login</h2>
                    <!-- Special Container Security Login via form -->
                    <form action="ThatsMyJam/login" method="post" class="form-horizontal" role="form" align="center">
                        <div class="form-check">
                            <input type="submit" value="Login"  class="btn btn-login float-right">
                        </div>
                    </form>
                    
                    <form method="post" action="/createaccount"  class="form-horizontal" role="form" align="center">
                        <input type="hidden" name="action" value="<%= Action.ACTION_CREATE_ACCOUNT %>" />
                    </form>     
                </div>
               
                <div class="col-md-8 banner-sec">
                </div>
            </div>
        </div>
    </section>

 <jsp:include page="/includes/footer.jsp" />

