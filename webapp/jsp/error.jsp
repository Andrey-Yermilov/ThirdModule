<jsp:directive.page isErrorPage="true" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"/>
<html>
<head>
    <link rel="shortcut icon" href="images/favicon.ico" type="image/x-icon">
    <link type="text/css" href="css/general.css" rel="stylesheet">
    <title>Ошибка</title>
</head>
<body>
<div class="wrapper">
    <jsp:include page="header.jsp"/>
    <div class="main">
        <div class="error404">
            <h1>404</h1>
            Ошибка. Страница не найдена
        </div>
    </div>
    <jsp:include page="footer.jsp"/>
</div>
</body>
</html>
