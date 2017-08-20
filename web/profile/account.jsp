<%-- 
    Document   : Account.jsp
    Created on : Jul 19, 2017, 1:42:50 PM
    Author     : mshaffer
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="com.thatsmyjam.constants.Action, com.thatsmyjam.constants.URL" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>TODO<h1>
        <p>
            Display user account info and allow then to change it.
        </p>
        <form action="ThatsMyJam" >
            <input type="hidden" name="action" value="<%= Action.ACTION_UPDATE_USER %>"
            <input type="fname" />
            <input type="lname" />
            <input type="email" />
            <input type="email" />
            <input type="submit" value="change"/>
        </form>
    </body>
</html>
