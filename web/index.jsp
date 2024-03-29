<%@ page import="cn.mariozzj.sp2.bean.User"  %>
<%@ page import="javax.servlet.http.HttpSession"  %>
<%--
  Created by IntelliJ IDEA.
  User: PlumberMario
  Date: 2020/7/1
  Time: 22:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="zh-CN">
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
  <title>PMISeeU -- Personal Webpage of Plumbermario</title>

  <!-- Bootstrap -->
  <link href="css/bootstrap.min.css" rel="stylesheet">
  <link href="css/carousel.css" rel="stylesheet">
  <link href="css/frontpage.css" rel="stylesheet">
  <!--    &lt;!&ndash; jQuery (Bootstrap 的所有 JavaScript 插件都依赖 jQuery，所以必须放在前边) &ndash;&gt;-->
  <!--    <script src="js/jquery.min.js"></script>-->
  <!-- 加载 Bootstrap 的所有 JavaScript 插件。你也可以根据需要只加载单个插件。 -->
  <script src="js/bootstrap.min.js"></script>
  <script src="js/frontpage.js"></script>
</head>
<body>
<div class="navbar-wrapper">
  <div class="container">

    <nav class="navbar navbar-inverse navbar-static-top">
      <div class="container">
        <div class="navbar-header">
          <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
            <!--菜单三根横线 -->
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <a class="navbar-brand" href="#" onclick="">PMISeeU</a>
        </div>
        <nav id="navbar" class="navbar-collapse collapse">
          <ul class="nav navbar-nav">
            <li class="active"><a href="#">Home</a></li>
            <li><a href="#about">About</a></li>
            <li><a href="#" onclick="undermaintainceNotification()">Contact</a></li>
            <li class="dropdown" onclick="undermaintainceNotification()">
              <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Dropdown <span class="caret"></span></a>
              <!--                            <ul class="dropdown-menu">-->
              <!--                                <li><a href="#">Action</a></li>-->
              <!--                                <li><a href="#">Another action</a></li>-->
              <!--                                <li><a href="#">Something else here</a></li>-->
              <!--                                <li role="separator" class="divider"></li>-->
              <!--                                <li class="dropdown-header">Nav header</li>-->
              <!--                                <li><a href="#">Separated link</a></li>-->
              <!--                                <li><a href="#">One more separated link</a></li>-->
              <!--                            </ul>-->
            </li>
            </ul>
          <ul class="navbar-right">
            <%
              String username;
              String logout;
              HttpSession indexsession = request.getSession();
                User user = (User) indexsession.getAttribute("user");
              if(user != null){
                username = ("".equals(user.username)) ? "Login" : user.username;
                logout = ("".equals(user.username)) ? "" : " | Log Out";
              }else{
                username="Login";
                logout = "";
              }
            %>
              <button class="btn btn-default navbar-btn" style="margin-right: 10px"><a href='login.html'><%=username%></a><a href="/pmicu/LogoutServletB"><%=logout%>></a></button>
          </ul>
        </nav>
      </div>
    </nav>

  </div>
</div>


<!-- Carousel
================================================== -->
<div id="myCarousel" class="carousel slide" data-ride="carousel">
  <!-- Indicators -->
  <ol class="carousel-indicators">
    <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
    <li data-target="#myCarousel" data-slide-to="1"></li>
    <li data-target="#myCarousel" data-slide-to="2"></li>
  </ol>
  <div class="carousel-inner" role="listbox">
    <div class="item active">
      <img class="first-slide img-responsive" src="image/bg_1.png" alt="First slide">
      <div class="container">
        <div class="carousel-caption">
          <h1>Hello World</h1>
          <p>Each man is the architect of his own fate. </p>
          <p><a class="btn btn-lg btn-primary" href="#" role="button" onclick="undermaintainceNotification()">More>></a></p>
        </div>
      </div>
    </div>
    <div class="item">
      <img class="second-slide" src="image/bg_2.png" alt="Second slide">
      <div class="container">
        <div class="carousel-caption">
          <h1>C'est la vie</h1>
          <p>Peace of mind generates firm steps.</p>
          <p><a class="btn btn-lg btn-primary" href="#" role="button" onclick="undermaintainceNotification()" >More>></a></p>
        </div>
      </div>
    </div>
    <div class="item">
      <img class="third-slide" src="image/bg_3.png" alt="Third slide">
      <div class="container">
        <div class="carousel-caption">
          <h1>Cherish everyday</h1>
          <p>Yesterday is history, tomorrow is mystery, but today is a gift.</p>
          <p><a class="btn btn-lg btn-primary" href="#" role="button" onclick="undermaintainceNotification()">More on the way</a></p>
        </div>
      </div>
    </div>
  </div>
  <a class="left carousel-control" href="#myCarousel" role="button" data-slide="prev">
    <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
    <span class="sr-only">Previous</span>
  </a>
  <a class="right carousel-control" href="#myCarousel" role="button" data-slide="next">
    <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
    <span class="sr-only">Next</span>
  </a>
</div><!-- /.carousel -->
<div>
  <div class="container marketing">
    <!-- Three columns of text below the carousel -->
    <div class="col-lg-12 subtitle">
      <div id="about"><b>About Myself</b></div>
    </div>
    <div class="row">
      <div class="col-lg-4">
        <img class="img-circle" src="image/book.svg" alt="Generic placeholder image" width="140" height="140">
        <h2>Studying</h2>
        <p id="desc1">Real name Zhejun Zheng. I am currently a sophomore in Information Management Science Dept., School of Information Management, Wuhan University. </p>
        <p><a class="btn btn-default" id="btn01" role="button" >View details &raquo;</a></p>
      </div><!-- /.col-lg-4 -->
      <div class="col-lg-4">
        <img class="img-circle" src="image/coding.svg" alt="studying" width="140" height="140">
        <h2>Crafting</h2>
        <p id="desc2">I'm a little bit interested in programming and I like to craft sth on my own. I'm an amateur programmer in <code>BOP</code>/<code>COP</code> (<a href="https://www.baidu.com/s?wd=helloworld%E6%80%8E%E4%B9%88%E5%86%99">Baidu</a>/<a href="https://so.csdn.net/so/search/s.do?q=hello%20world&t=&u=">CSDN</a> Oriented Programming). </p>
        <p><a class="btn btn-default" href="#" id="btn02" role="button" onclick="undermaintainceNotification(2)">View details &raquo;</a></p>
      </div><!-- /.col-lg-4 -->
      <div class="col-lg-4">
        <img class="img-circle" src="image/sparkling.svg" alt="coding" width="140" height="140">
        <h2>Sparkling</h2>
        <p id="desc3">I'm a friend always there to help, love to cooperate and get improved together. I always try my best, not fear to face the difficulties and never shy to show myself out.</p>
        <p><a class="btn btn-default" href="#" id="btn03" role="button" onclick="undermaintainceNotification(3)">View details &raquo;</a></p>
      </div><!-- /.col-lg-4 -->
    </div><!-- /.row -->
  </div>
</div>

<%@include file="footer.jsp"%>


<script src="js/jquery.min.js"></script>
<!--<script>window.jQuery || document.write('<script src="../../assets/js/vendor/jquery.min.js"><\/script>')</script>-->
<script src="js/bootstrap.min.js"></script>
</body>
</html>