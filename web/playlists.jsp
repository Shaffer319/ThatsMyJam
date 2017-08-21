<%-- 
    Document   : HomePage
    Created on : Jul 6, 2017, 11:13:33 PM
    Author     : cpournaras11
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <jsp:useBean id="playlistBean" class="com.thatsmyjam.beans.InfoBean" scope="session" />
        <title>Playlists</title>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

        <link href="css/homepage.css" rel="stylesheet">
    </head>
    <body data-spy="scroll">
        <jsp:include page="includes/navbar.jsp" />

        <div class="container">
            <%=playlistBean.getPlaylistResults()%>
        </div>
        <!-- Bootstrap core Javascript and jQuery  -->
        <!-- Placed at the end for faster loading of pages -->
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
        <script src="js/bootstrap.min.js"></script>
    </body>
</html>

