<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE HTML>
<html>
<head>
    <title>人际树</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <!--工具-->
    <link rel="stylesheet" href="css/index/style.css">
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <script src="js/jquery.min.js"></script>
    <script type="text/javascript" src="js/index/RelationshipTree.js"></script>

</head>
<body>
<div class="message warning">
    <div id="inset_login">
        <div class="login-head">
            <h1>登陆</h1>
            <div class="alert-close"> </div>
        </div>
        <form id="login_submit" action="login" method="post">
            <li>
                <input type="text" name = "username" id="username_login" class="text" placeholder="Username"><img  src="./images/user.png" class="icon">
            </li>
            <div class="clear"> </div>
            <!--
            <li>
                <input type="password" name = "password" id="password_login" placeholder="Password" > <img src="./images/password.png" class="icon">
            </li>
            -->
            <div class="clear"> </div>
            <div class="submit">
                <!--
                <input type="button" class="submit" id="login"  value="密码登陆" >
                <input type="button" class="submit" id="gotoregister"  value="密码注册" >
                -->
                <input type="button" class="submit" id="login"  value="密像登陆" >
                <input type="button" class="submit" id="gotoregister"  value="密像注册" >
                <div class="clear">  </div>
            </div>
        </form>
    </div>
    <div id="inset_register">
        <div class="login-head">
            <h1>注册</h1>
            <div class="alert-close"> </div>
        </div>
        <form id="register_submit" action="register" method="post">
            <li>
                <input type="text" class="text" name = "username"placeholder="Username" ><img  src="./images/user.png" class="icon">
            </li>
            <div class="clear"> </div>
            <li>
                <input type="password" name = "password" placeholder="Password" > <img  src="./images/password.png" class="icon">
            </li>
            <div class="clear"> </div>
            <div class="clear"> </div>
            <div class="submit">
                <input type="button" class="submit" id="back"  value="返回" >
                <input type="button" class="submit" id="register"  value="完成注册" >
                <div class="clear">  </div>
            </div>
        </form>
    </div>
</div>
<div class="clear"> </div>

<!--- footer --->
</body>
</html>