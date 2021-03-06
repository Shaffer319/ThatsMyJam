<%-- 
    Document   : HomePage
    Created on : Jul 6, 2017, 11:13:33 PM
    Author     : cpournaras11
--%>

<%@page import="com.thatsmyjam.beans.CartBean"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="/includes/header.jsp"/>
<jsp:include page="/includes/navbar.jsp"/>

<jsp:useBean id="infoBean" class="com.thatsmyjam.beans.InfoBean" scope="session" />
<jsp:useBean id="cartBean" class="com.thatsmyjam.beans.CartBean" scope="session" />
<jsp:useBean id="user" class="com.thatsmyjam.data.User" scope="session"/>

<%
    CartBean cart = (CartBean) session.getAttribute("cartBean");
    String message = cart.getMessage();
%>

<div class="container">
    <form method="post" action="<c:url value="/ShoppingCart"/>" class="form-horizontal" role="form" align="center">
        <legend>Shopping Cart</legend>
        <% if (message != null) {%>
        <p><i><font color= #B1351A><%= message%></font></i></p>
                <% }%>

        <% if (cart != null && cart.getNumItems() > 0) {%>
        <div class="row">
            <div class="col-sm-12 col-md-10 col-md-offset-1">
                <table class="table table-hover">
                    <thead>
                        <tr>
                            <th>Item</th>
                            <th class="text-center">Price</th>
                            <th> </th>
                        </tr>
                    </thead>
                    <tbody>

                        <%  for (int i = 0; i < cart.getNumItems(); ++i) {%>
                        <tr>
                            <td class="col-sm-8 col-md-6">
                                <div class="media">
                                    <a class="thumbnail pull-left" href="#"> <img class="media-object" src="${pageContext.request.contextPath}/images/<%=cart.getItems().get(i).getImage()%>" style="width: 50%; height: 50%;"> </a>
                                    <div class="media-body">
                                        <c:url value="/info.jsp" var="albumUrl">
                                            <c:param name="album" value="<%=String.valueOf(cart.getItems().get(i).getAlbumID())%>" />
                                        </c:url>		
                                        <c:url value="/info.jsp" var="artistUrl">		
                                            <c:param name="artist" value="<%=String.valueOf(cart.getItems().get(i).getArtistID())%>" />		
                                        </c:url>		
                                        </p>		
                                        <h4 class="media-heading">		
                                            <a href="${albumUrl}">		
                                                <%=cart.getItems().get(i).getItemTitle()%>		
                                            </a>		
                                        </h4>		
                                        <h5 class="media-heading"> by 		
                                            <a href="${artistUrl}">		
                                                <%=cart.getItems().get(i).getArtistName()%>		
                                            </a>		
                                        </h5>
                                    </div>
                                </div></td>                          
                            <td class="col-sm-1 col-md-1 text-center"><strong><%=cart.getItems().get(i).getFormattedPrice()%></strong></td>
                            <td class="col-sm-1 col-md-1">
                                <button type="submit" name="remove" value="<%=cart.getItems().get(i).getItemTitle()%>" class="btn btn-danger">
                                    <span class="glyphicon glyphicon-remove"></span> Remove
                                </button></td>
                        </tr>
                        <%}%>
                    </tbody>
                    <tfoot>
                        <tr>
                            <td>   </td>
                            <td>   </td>
                            <td><h3>Total</h3></td>
                            <td class="text-center"><h3><%=cart.getFormattedTotal()%></h3></td>
                        </tr>
                        <tr>
                            <td>   </td>
                            <td>   </td>
                            <td>
                                <button type="submit" name="continue_shop" class="btn btn-default">
                                    <span class="glyphicon glyphicon-shopping-cart"></span> Continue Shopping
                                </button></td>
                            <td>
                                <c:choose>
                                    <c:when test="${user.loaded}">
                                        <button type="button" name="payment" class="btn btn-primary" data-toggle="collapse" data-target="#payment">
                                            Add Payment <span class="glyphicon glyphicon-play"></span>
                                        </button>
                                    </c:when>
                                    <c:otherwise>
                                        <a href="<c:url value="/profileController/cart"/>">
                                        <div type="button" style="width: 100;" >
                                            Login <span class="glyphicon glyphicon-play"></span>
                                        </div> </a>
                                    </c:otherwise>
                                </c:choose>
                            </td>
                        </tr>
                    </tfoot>

                </table>
            </div>
        </div>
        <%   } else {%>
        <h3>No items in cart</h3>
        <br></br>
        <button type="submit" name="continue_shop" class="btn btn-default">
            <span class="glyphicon glyphicon-shopping-cart"></span> Continue Shopping
        </button>
        <%}%>

        <div id="payment" class="collapse">
            <form class="form-horizontal" role="form">
                <fieldset>
                    <legend>Payment</legend>
                    <div class="form-group">
                        <label class="col-sm-3 control-label" for="cardholder">Name on Card</label>
                        <div class="col-sm-9">
                            <input type="text" class="form-control" name="cardholder" id="cardholder" placeholder="Card Holder Name">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label" for="cardnumber">Card Number</label>
                        <div class="col-sm-9">
                            <input type="text" maxlength="16" class="form-control" name="cardnumber" id="cardnumber" placeholder="Credit Card Number">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label" for="expdate">Expiration Date</label>
                        <div class="col-sm-9">
                            <div class="row">
                                <div class="col-xs-3">
                                    <select class="form-control col-sm-2" name="expmon" id="expmon">
                                        <option>Month</option>
                                        <option value="01">01</option>
                                        <option value="02">02</option>
                                        <option value="03">03</option>
                                        <option value="04">04</option>
                                        <option value="05">05</option>
                                        <option value="06">06</option>
                                        <option value="07">07</option>
                                        <option value="08">08</option>
                                        <option value="09">09</option>
                                        <option value="10">10</option>
                                        <option value="11">11</option>
                                        <option value="12">12</option>
                                    </select>
                                </div>
                                <div class="col-xs-3">
                                    <select class="form-control" name="expyr">
                                        <option value="13">2017</option>
                                        <option value="14">2018</option>
                                        <option value="15">2019</option>
                                        <option value="16">2020</option>
                                        <option value="17">2021</option>
                                        <option value="18">2022</option>
                                        <option value="19">2023</option>
                                        <option value="20">2024</option>
                                    </select>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-xs-3 control-label" for="cvv">CVV</label>
                        <div class="col-xs-3">
                            <input type="text" class="form-control" minlength="3"  maxlength="3" name="cvv" id="cvv" placeholder="Security Code">
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-sm-offset-3 col-sm-9">
                            <form method="post" class="form-horizontal" role="form" align="center" action="<c:url value="/ShoppingCart"/>" >
                                <button type="submit" name="checkout" class="btn btn-success" data-toggle="modal" data-target="#confirmDialog">Confirm Payment</button>
                            </form>
                        </div>
                    </div>
                </fieldset>
            </form>
        </div>

        <!-- Modal -->
        <div class="modal fade" id="confirmDialog" role="dialog">
            <div class="modal-dialog">
                <!-- Modal content-->
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                        <h4 class="modal-title"><font color= #1E8D1E>Payment Processed</font></h4>
                    </div>
                    <div class="modal-body">
                        <p>Thank you for your purchase!</p>
                        <p>You can now add these songs to your playlists.</p>
                    </div>
                    <div class="form-group">
                        <div align="center">
                            <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </form>
</div>
<jsp:include page="/includes/footer.jsp"/>

