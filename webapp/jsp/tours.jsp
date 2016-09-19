<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:directive.page contentType="text/html;charset=UTF-8" language="java"/>
<html>
<head>
    <link rel="shortcut icon" href="images/favicon.ico" type="image/x-icon">
    <link type="text/css" href="css/general.css" rel="stylesheet">
    <title>Туры</title>
</head>
<body>
<div class="wrapper">
    <jsp:include page="header.jsp"/>
    <div class="main">
        <div class="content">
            <div id="exit">Здравствуйте, ${name}
                <form id="logoutForm" action="logout">
                    <button type="submit">Выйти</button>
                </form>
            </div>
            <br>
            <h2>Доступные туры</h2>
            <table class="tours" border="1" width="100%">
                <form name="buyForm" id="buyForm" method="POST" action="buy">
                    <tr>
                        <th>№</th>
                        <th>Страна</th>
                        <th>Тип тура</th>
                        <th>Стоимость</th>
                        <th>Горящий тур?</th>
                        <th>Купить</th>
                    </tr>
                    <c:forEach var="tour" items="${tours}">
                        <tr>
                            <td>
                                <c:out value="${ tour.id }"/>
                            </td>
                            <td>
                                <c:out value="${ tour.country }"/>
                            </td>
                            <td>
                                <c:out value="${ tour.type }"/>
                            </td>
                            <td>
                                <c:out value="${ tour.price }"/>
                            </td>
                            <th>
                                <c:if test="${tour.burning=='Да'}">
                                    <img src="images/burning.png"/>
                                </c:if>
                            </th>
                            <th><input name="tourId" type="radio" value="${ tour.id }"></th>
                        </tr>
                    </c:forEach>
                </form>
            </table>
            <br>
            <div id="submit"><input type="submit" form="buyForm" value="Купить выбранный тур"/></div>
            <br>
            <c:if test="${not empty orders}">
                <h2>Мои туры</h2>
                <table class="tours" border="1" width="100%">
                    <tr>
                        <th>Страна</th>
                        <th>Тип тура</th>
                        <th>Стоимость</th>
                    </tr>
                    <c:forEach var="order" items="${orders}">
                        <tr>
                            <td>
                                <c:out value="${ order.country }"/>
                            </td>
                            <td>
                                <c:out value="${ order.tourType }"/>
                            </td>
                            <td>
                                <c:out value="${ order.priceWithDiscount }"/>
                            </td>
                        </tr>
                    </c:forEach>
                </table>
            </c:if>
        </div>
    </div>
    <jsp:include page="footer.jsp"/>
</div>
</body>
</html>