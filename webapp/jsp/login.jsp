<jsp:directive.page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"/>
<html>
<head>
    <link rel="shortcut icon" href="images/favicon.ico" type="image/x-icon">
    <link type="text/css" href="css/general.css" rel="stylesheet">
    <title>Вход</title>
</head>
<body>
<div class="wrapper">
    <jsp:include page="header.jsp"/>
    <div class="main">
        <div class="content">
            <form name="loginForm" method="POST" action="login">

                <label for="login">Логин:</label>
                <div id="login_container">
                    <input type=login id="login" name="login" autofocus spellcheck="false" placeholder="логин">
                </div>

                <label for="password">Пароль:</label>
                <div id="password_container">
                    <input type=password id="password" name="password" spellcheck="false">
                </div>
                <div id="errorMsg">
                    ${errorLoginPassMessage}
                </div>
                <div id="submit">
                    <input type="submit" value="Войти"/>
                </div>
            </form>
            <script type="text/javascript" src="js/validate.js"></script>
        </div>
    </div>
    <jsp:include page="footer.jsp"/>
</div>
</body>
</html>
