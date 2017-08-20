<%-- 
    Document   : HomePage
    Created on : Jul 6, 2017, 11:13:33 PM
    Author     : cpournaras11
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <jsp:useBean id="infoBean" class="com.thatsmyjam.beans.InfoBean" scope="session" />
        <title>
            <% if (request.getParameter("artist") != null) {%>
            <%=infoBean.getTitle(true, request.getParameter("artist"))%>
            <%} else {%>
            <%=infoBean.getTitle(false, request.getParameter("album"))%>
            <%}%>
        </title>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

        <link href="css/homepage.css" rel="stylesheet">
    </head>
    <body data-spy="scroll">
        <nav class="navbar navbar-inverse">
            <div class="container-fluid">
                <div class="navbar-header">
                    <a class="navbar-brand" href="#">That's My Jam</a>
                </div>
                <ul class="nav navbar-nav">
                    <li class="active"><a href="/ThatsMyJam/homepage.jsp">Home</a></li>
                    <li><a href="#">My Playlists</a></li>
                    <li><a href="#">Top Albums</a></li>
                </ul>
                <form class="navbar-form navbar-left" action="/ThatsMyJam/Search" method=GET>>
                    <div class="input-group">
                        <input type="text" name="search" class="form-control" placeholder="Search">
                        <div class="input-group-btn">
                            <button class="btn btn-default" type="submit">
                                <i class="glyphicon glyphicon-search"></i>
                            </button>
                        </div>
                    </div>
                </form>
                <ul class="nav navbar-nav navbar-right">
                    <li><a href="/ThatsMyJam/cart.jsp"><span class= "glyphicon glyphicon-shopping-cart"></span> Cart</a></li>
                    <li><a href="/ThatsMyJam/account.jsp"><span class="glyphicon glyphicon-user"></span>Account</a></li>
                    <li><a href="#"><span class="glyphicon glyphicon-log-out"></span>Logout</a></li>
                </ul>
            </div>
        </nav>
        <div class="container">
            <form method="post" action=<%= response.encodeURL("ShoppingCart")%> class="form-horizontal" role="form" align="center">
                <% if (request.getParameter("artist") != null) {%>
                <%=infoBean.getPage(true, request.getParameter("artist"))%>
                <%} else {%>
                <%=infoBean.getPage(false, request.getParameter("album"))%>
                <br></br>                     
                <button type="submit" name="addAlbumCart" class="btn btn-default">
                    Add Album to Cart <span class="glyphicon glyphicon-shopping-cart"></span>
                </button> 
                <%}%>
            </form>
        </div>
        <!-- Bootstrap core Javascript and jQuery  -->
        <!-- Placed at the end for faster loading of pages -->
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
        <script src="js/bootstrap.min.js"></script>
    </body>
</html>

