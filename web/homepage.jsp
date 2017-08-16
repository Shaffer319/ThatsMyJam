<%-- 
    Document   : HomePage
    Created on : Jul 6, 2017, 11:13:33 PM
    Author     : bean51591
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <title>My Account</title>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <jsp:useBean id="infoBean" class="com.thatsmyjam.InfoBean" scope="request" />
        <link href="css/homepage.css" rel="stylesheet">

    </head>
    <body data-spy="scroll">
        <nav class="navbar navbar-inverse">
            <div class="container-fluid">
                <div class="navbar-header">
                    <a class="navbar-brand" href="#">That's My Jam</a>
                </div>
                <ul class="nav navbar-nav">
                    <li class="active"><a href="#">Home</a></li>
                    <li><a href="#">My Playlists</a></li>
                    <li><a href="#">Top Albums</a></li>
                </ul>
                <form class="navbar-form navbar-left">
                    <div class="input-group">
                        <input type="text" class="form-control" placeholder="Search">
                        <div class="input-group-btn">
                            <button class="btn btn-default" type="submit">
                                <i class="glyphicon glyphicon-search"></i>
                            </button>
                        </div>
                    </div>
                </form>
                <ul class="nav navbar-nav navbar-right">
                    <li><a href="#"><span class="glyphicon glyphicon-user"></span> Account</a></li>
                    <li><a href="#"><span class="glyphicon glyphicon-log-out"></span> Logout</a></li>
                </ul>
            </div>
        </nav>
        <div class="container">

            <div class="row">

                <div class="col-lg-12">
                    <h1 class="page-header">Album Gallery</h1>
                </div>

                <%=infoBean.getAlbumGallery()%>
                
                <p>Use the .navbar-form class to vertically align form elements (same padding as links) inside the navbar.</p>
                <p>The .input-group class is a container to enhance an input by adding an icon, text or a button in front or behind it as a "help text".</p>
                <p>The .input-group-btn class attaches a button next to an input field. This is often used as a search bar:</p>
            </div>
            <!-- Bootstrap core Javascript and jQuery  -->
            <!-- Placed at the end for faster loading of pages -->
            <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
            <script src="js/bootstrap.min.js"></script>
    </body>
</html>

