<%-- 
    Document   : HomePage
    Created on : Jul 6, 2017, 11:13:33 PM
    Author     : cpournaras11
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="com.thatsmyjam.constants.Action, com.thatsmyjam.constants.URL" %>
<jsp:include page="/includes/header.jsp" />
<jsp:include page="/includes/navbar.jsp" />

    <jsp:useBean id="searchBean" class="com.thatsmyjam.beans.InfoBean" scope="session" />

        <jsp:include page="includes/navbar.jsp" />

        <div class="container">
            <%=searchBean.getSearchResults()%>
        </div>

<jsp:include page="/includes/footer.jsp" />

