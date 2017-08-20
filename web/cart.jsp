<%-- 
    Document   : HomePage
    Created on : Jul 6, 2017, 11:13:33 PM
    Author     : cpournaras11
--%>

<%@page import="com.thatsmyjam.beans.CartBean"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <jsp:useBean id="infoBean" class="com.thatsmyjam.beans.InfoBean" scope="session" />
        <jsp:useBean id="cartBean" class="com.thatsmyjam.beans.CartBean" scope="session" />
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

        <link href="css/homepage.css" rel="stylesheet">
    </head>
    <body data-spy="scroll">
        <% CartBean cart = (CartBean) session.getAttribute("cartBean");%>
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
                    <li><a href="/ThatsMyJam/cart.jsp"><span class= "glyphicon glyphicon-shopping-cart"></span> Cart</a></li>
                    <li><a href="/ThatsMyJam/account.jsp"><span class="glyphicon glyphicon-user"></span>Account</a></li>
                    <li><a href="#"><span class="glyphicon glyphicon-log-out"></span>Logout</a></li>
                </ul>
            </div>
        </nav>
        <div class="container">
             <legend>Shopping Cart</legend>
            <form method="post" action=<%= response.encodeURL("ShoppingCart")%>  class="form-horizontal" role="form" align="center">
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
                                <% if (cart != null && cart.getNumItems() > 0) {
                                    for (int i = 0; i < cart.getNumItems(); ++i) {%>
                                <tr>
                                    <td class="col-sm-8 col-md-6">
                                        <div class="media">
                                            <a class="thumbnail pull-left" href="#"> <img class="media-object" src="images/<%=cart.getItems().get(i).getImage()%>" style="width: 50%; height: 50%;"> </a>
                                            <div class="media-body">
                                                <h4 class="media-heading"><a href="/ThatsMyJam/info.jsp?album=<%=cart.getItems().get(i).getAlbumID()%>"><%=cart.getItems().get(i).getItemTitle()%></a></h4>
                                                <h5 class="media-heading"> by  <a href="/ThatsMyJam/info.jsp?artist=<%=cart.getItems().get(i).getArtistID()%>"> <%=cart.getItems().get(i).getArtistName()%> </a></h5>
                                            </div>
                                        </div></td>                          
                                    <td class="col-sm-1 col-md-1 text-center"><strong>$<%=cart.getItems().get(i).getPrice()%></strong></td>
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
                                    <td class="text-center"><h3>$<%=cart.getTotal()%></h3></td>
                                </tr>
                                <tr>
                                    <td>   </td>
                                    <td>   </td>
                                    <td>
                                        <button type="submit" name="continue_shop" class="btn btn-default">
                                            <span class="glyphicon glyphicon-shopping-cart"></span> Continue Shopping
                                        </button></td>
                                    <td>
                                        <button type="button" name="payment" class="btn btn-primary" data-toggle="collapse" data-target="#payment">
                                            Add Payment <span class="glyphicon glyphicon-play"></span>
                                        </button></td>
                                </tr>
                            </tfoot>
                            <%   } else {%>
                            <h3>No items in cart</h3>
                            <%}%>
                        </table>
                    </div>
                </div>
            </form>
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
                                <input type="text" class="form-control" name="cardnumber" id="cardnumber" placeholder="Credit Card Number">
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
                                            <option value="02">02)</option>
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
                                            <option value="13">2016</option>
                                            <option value="14">2017</option>
                                            <option value="15">2018</option>
                                            <option value="16">2019</option>
                                            <option value="27">2020</option>
                                            <option value="18">2021</option>
                                            <option value="19">2022</option>
                                            <option value="20">2023</option>
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
                                <button type="submit" name="checkout" class="btn btn-success">Confim Payment</button>
                            </div>
                        </div>
                    </fieldset>
                </form>
            </div>
        </div>
        <!-- Bootstrap core Javascript and jQuery  -->
        <!-- Placed at the end for faster loading of pages -->
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
        <script src="js/bootstrap.min.js"></script>
    </body>
</html>

