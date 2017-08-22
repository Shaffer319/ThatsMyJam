<%-- 
    Document   : HomePage
    Created on : Jul 6, 2017, 11:13:33 PM
    Author     : cpournaras11
--%>

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
    <%
        List<Song> myList = infoBean.getMySongsArray(user.getUserID());
        request.setAttribute("myList", myList);
    %>
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
            <c:forEach items="<%=myList%>" var="song" >
                <tr>
                    <td> <a target="_blank" href="http://www.google.com/search?q=youtube+${song.songName.replaceAll(" ", "+")}+on+album+${song.albumName.replaceAll(" ", "+")}+&m=0"   
                            >${song.songName}</a></td>
                    <td>${song.albumName}</td>
                    <td>${song.artistName}</td>
                    <td>   <button name="song" 
                                   value="${song.songName}_${song.songID}"
                                   title="Add to Playlist" 
                                   style="height:20px" 
                                   type="submit">
                            <span class="glyphicon glyphicon-plus-sign"/>
                        </button></td>
                </tr>
            </c:forEach>

            <!--        String targetLink = "<a target=\"_blank\" href=http://www.google.com/search?q=youtube+"
                          + songName.replaceAll(" ", "+")
                          + "+on+album+" + album.replaceAll(" ", "+") + "&m=0>";
                    htmlOutput += "<li><div class=\"col-xs-12 col-md-8\">"
                          + "<div style=\"float:left\">" + targetLink + songName + "</a> by " + artist + "</div>"
                          + "<div style=\"float:right\">"
                          + "<button name=\"song\" value=\"" + songName + "_" + songID + "\" title=\"Add to Playlist\" style=\"height:20px\" type=\"submit\">"
                          + "<span class=\"glyphicon glyphicon-plus-sign\"/>"
                          + "</button></div></div></li>";-->


            <%--<%=infoBean.getMySongs(user.getUserID())%>--%>
        </tbody>
    </table>
</div>

<jsp:include page="/includes/footer.jsp"/>

