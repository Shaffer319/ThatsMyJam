<%-- 
    Document   : index.jsp
    Created on : Aug 15, 2017, 4:53:47 PM
    Author     : mshaffer
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="/includes/header.jsp" />
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="banner-text">
    <h2>That's My Jam!</h2>
    <p>WELCOME ADMIN!</p>
</div>	
<section style="float:left;
         width:100%;
         box-sizing:border-box;
         padding : 234px 0;">
    <div class="container" style="float: left;">
        <h3> This page can only be viewed by people with the admin role.</h3>
    </div>

    <a href="<c:url value="/logout"/>">Logout <c:url value="/logout"/></a>
    <!--        
    <form method="post" action="logout"  class="form-horizontal" role="form" align="center">=
                <div class="form-group">
                    <input type="submit" value="Logout" class="btn btn-newAcct float-bottom"/>
                </div>
            </form>
    -->
</section>

<jsp:include page="/includes/footer.jsp" />
