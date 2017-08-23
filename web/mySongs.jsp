<%-- 
    Document   : HomePage
    Created on : Jul 6, 2017, 11:13:33 PM
    Author     : cpournaras11
--%>

<%@page import="com.thatsmyjam.data.Playlist"%>
<%@page import="com.thatsmyjam.data.Song"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/includes/header.jsp"/>
<jsp:include page="/includes/navbar.jsp" />

<jsp:useBean id="infoBean" class="com.thatsmyjam.beans.InfoBean" scope="session" />
<jsp:useBean id="user" class="com.thatsmyjam.data.User" scope="session" />

<div class="container">

    <h1> Songs </h1>
    <button type="button" name="newPlaylist" class="btn btn-primary" data-toggle="collapse" data-target="#addPlaylist">
        Create New Playlist <span class="glyphicon glyphicon-plus-sign"></span>
    </button>
    <div id="addPlaylist" class="collapse">
        <form method="post" action="<c:url value="/Playlists/CreateNewPlaylist"/>" >
            <div class="row">
                <div class="col-lg-3">
                    <div class="input-group">
                        <span class="input-group-btn">
                            <button class="btn btn-secondary" type="submit" name="addPlaylist">Go!</button>
                        </span>
                        <input type="text" name="playlistName" class="form-control" placeholder="Playlist Name">
                    </div>
                </div>
            </div>
        </form>
    </div>


    <%
        List<Song> mySongList = infoBean.getMySongsArray(user.getUserID());
        List<Playlist> myPlaylistList = infoBean.getMyPlaylists(user.getUserID());

        request.setAttribute("mySongList", mySongList);
        request.setAttribute("myPlaylistList", myPlaylistList);

    %>
    <form action="<c:url value="/Playlist/AddSongToPlaylist"/>"> 
        <fieldset> <legend>Playlists</legend>
        <c:forEach items="${myPlaylistList}" var="playlist" >
            <input type="radio" name="playlistID" value="${playlist.playlistID}" />${playlist.playlistName}<br/>
        </c:forEach>
        </fieldset>
        <table class="table table-hover">
            <thead>
                <tr>
                    <th>Song</th>
                    <th>Album</th>
                    <th>Artist</th>
                    <th></th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${mySongList}" var="song" >
                    <tr>
                        <td> <a target="_blank" href="http://www.google.com/search?q=youtube+${song.songName.replaceAll(" ", "+")}+on+album+${song.albumName.replaceAll(" ", "+")}+&m=0"   
                                >${song.songName}</a></td>
                        <td>${song.albumName}</td>
                        <td>${song.artistName}</td>
                        <td>   <button name="song" 
                                       value="${song.songID}"
                                       title="Add to Playlist" 
                                       style="height:20px" 
                                       type="submit">
                                <span class="glyphicon glyphicon-plus-sign"/>
                            </button></td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </form>

</div>

<jsp:include page="/includes/footer.jsp"/>

