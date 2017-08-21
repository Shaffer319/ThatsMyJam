<%-- 
    Document   : navbar
    Created on : Aug 20, 2017, 10:22:01 PM
    Author     : mshaffer
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<nav class="navbar navbar-inverse">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand" href="#">That's My Jam</a>
        </div>
        <ul class="nav navbar-nav">
            <li class="active"><a href="/ThatsMyJam/homepage.jsp">Home</a></li>
            <li><a href="/ThatsMyJam/Playlists">My Playlists</a></li>
            <li><a href="/ThatsMyJam/mySongs.jsp">My Songs</a></li>
        </ul>
        <form class="navbar-form navbar-left" action=<%= response.encodeURL("Search")%> method=GET>>
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
            <li><a href="<c:url value="cart.jsp" />" ><span class= "glyphicon glyphicon-shopping-cart"></span> Cart</a></li>
            <li><a href="<c:url value="profile/account.jsp"/>" ><span class="glyphicon glyphicon-user"></span>Account</a></li>
            <li><a href="<c:url value="logout"/>"><span class="glyphicon glyphicon-log-out"></span>Logout</a></li>
        </ul>
    </div>
</nav>
