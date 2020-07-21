<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <link href="./css/loginpage.css" rel="stylesheet">
    <link href="./css/bootstrap.min.css" rel="stylesheet">
    <script src="js/jquery.min.js"></script>
    <script src="js/loginpage.js"></script>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
    <div class="container-fluid" id="logincenter">
        <div class="col-sm-2 col-md-3 col-lg-3"></div>
        <div class="col-xs-12 col-sm-8 col-md-6 col-lg-6" id="loginframe">
            <h3 class="text-center"><b>登录</b></h3>
            <form name="reg" action="/pmicu/LoginServlet" method="post">
                <b>用户名:</b><input name="username" class="form-control" type="text" placeholder="Enter Username..."/><br />
                <b>密码：</b><input name="password" class="form-control" type="password" placeholder="Enter Password"/><br />
                <button type="button" class="moreInfo">更多信息</button><br />
                <b class="ne">性别:</b><input class="ne" name="sex" type="radio">♂<br />
                <b class="ne">年龄：（可选）</b><input class="ne form-control" type="text" placeholder="Enter Anything..."/><br />
                <b class="ne">备注：（可选）</b><input class="ne form-control" type="text" placeholder="Enter Anything..."/><br />
                <div class="input-data">
                    <input type="text">
                    <label>Username</label>
                </div>

            <div class="row center-block"   style="margin-top: 5%">
                <div class="col-lg-1"></div>
                <button type="submit" class="col-lg-4 btn btn-primary" id="bt">登录</button>
                <div class="col-lg-2"></div>
                <button type="button" class="col-lg-4 btn btn-default" id="lo" disabled="disabled">注册</button>
                <div class="col-lg-1"></div>
                </div>
            </form>
        </div>
        <div class="col-sm-2 col-md-3 col-lg-3"></div>
<!--        <div class="row">1</div>-->
<!--        <div class="row">2</div>-->
<!--        <div class="row">3</div>-->
<!--        <div class="row">4</div>-->
<!--        <div class="row">5</div>-->
<!--        <div class="row">6</div>-->
<!--        <div class="row">1</div>-->
<!--        <div class="row">2</div>-->
<!--        <div class="row">3</div>-->
<!--        <div class="row">4</div>-->
<!--        <div class="row">5</div>-->
<!--        <div class="row">6</div>-->
<!--        <div class="row">1</div>-->
<!--        <div class="row">2</div>-->
<!--        <div class="row">3</div>-->
<!--        <div class="row">4</div>-->
<!--        <div class="row">5</div>-->
<!--        <div class="row">6</div>-->
<!--        <div class="row">1</div>-->
<!--        <div class="row">2</div>-->
<!--        <div class="row">3</div>-->
<!--        <div class="row">4</div>-->
<!--        <div class="row">5</div>-->
<!--        <div class="row">6</div>-->
    </div>
</body>
</html>