<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<title>비엔나 뉴스</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Raleway">
<style>
    body,h1,h5 {font-family: "Raleway", sans-serif}
    body, html {height: 100%}
    .bgimg {
        background-color: black;
        min-height: 100%;
        background-position: center;
        background-size: cover;
    }
    .email_form {
        height: 50px;
        width: 600px;
        font-size: 30px;
    }
    .email_subscribe {
        height: 50px;
        width: 70px;
        font-size: 30px;
        background-color: lightgoldenrodyellow;
    }
</style>
<script type="text/javascript">
    let result = ${result};

    alert(result);

</script>
<body>

<div class="bgimg w3-display-container w3-text-white">
    <div class="w3-display-middle w3-jumbo">
        <p>비엔나 뉴스 구독</p>
        <form id="subscription" action="${pageContext.request.contextPath}/user/subscribe" method="post">
            <input type="email" name="email" placeholder="Email" class="email_form" />
            <input type="submit" name="subscribe" class="email_subscribe">
        </form>

    </div>

    <div class="w3-display-topleft w3-container w3-xlarge">
<%--        <p><button onclick="document.getElementById('menu').style.display='block'" class="w3-button w3-black">menu</button></p>--%>
<%--        <p><button onclick="document.getElementById('contact').style.display='block'" class="w3-button w3-black">contact</button></p>--%>
    </div>
    <div class="w3-display-bottomleft w3-container">
        <p class="w3-xlarge">아파트, 재건축/재개발, 토지 등 각종 부동산 뉴스를 제공해드립니다.</p>
        <p class="w3-large"> CEO 강정호</p>
        <p>powered by <a href="https://www.w3schools.com/w3css/default.asp" target="_blank">vienna-news</a></p>
    </div>
</div>

<!-- Menu Modal -->
<%--<div id="menu" class="w3-modal">--%>
<%--    <div class="w3-modal-content w3-animate-zoom">--%>
<%--        <div class="w3-container w3-black w3-display-container">--%>
<%--            <span onclick="document.getElementById('menu').style.display='none'" class="w3-button w3-display-topright w3-large">x</span>--%>
<%--            <h1>Starters</h1>--%>
<%--        </div>--%>
<%--        <div class="w3-container">--%>
<%--            <h5>Tomato Soup <b>$2.50</b></h5>--%>
<%--            <h5>Chicken Salad <b>$3.50</b></h5>--%>
<%--            <h5>Bread and Butter <b>$1.00</b></h5>--%>
<%--        </div>--%>
<%--        <div class="w3-container w3-black">--%>
<%--            <h1>Main Courses</h1>--%>
<%--        </div>--%>
<%--        <div class="w3-container">--%>
<%--            <h5>Grilled Fish and Potatoes <b>$8.50</b></h5>--%>
<%--            <h5>Italian Pizza <b>$5.50</b></h5>--%>
<%--            <h5>Veggie Pasta <b>$4.00</b></h5>--%>
<%--            <h5>Chicken and Potatoes <b>$6.50</b></h5>--%>
<%--            <h5>Deluxe Burger <b>$5.00</b></h5>--%>
<%--        </div>--%>
<%--        <div class="w3-container w3-black">--%>
<%--            <h1>Desserts</h1>--%>
<%--        </div>--%>
<%--        <div class="w3-container">--%>
<%--            <h5>Fruit Salad <b>$2.50</b></h5>--%>
<%--            <h5>Ice cream <b>$2.00</b></h5>--%>
<%--            <h5>Chocolate Cake <b>$4.00</b></h5>--%>
<%--            <h5>Cheese <b>$5.50</b></h5>--%>
<%--        </div>--%>
<%--    </div>--%>
<%--</div>--%>

<!-- Contact Modal -->
<%--<div id="contact" class="w3-modal">--%>
<%--    <div class="w3-modal-content w3-animate-zoom">--%>
<%--        <div class="w3-container w3-black">--%>
<%--            <span onclick="document.getElementById('contact').style.display='none'" class="w3-button w3-display-topright w3-large">x</span>--%>
<%--            <h1>Contact</h1>--%>
<%--        </div>--%>
<%--        <div class="w3-container">--%>
<%--            <p>Reserve a table, ask for today's special or just send us a message:</p>--%>
<%--            <form action="/action_page.php" target="_blank">--%>
<%--                <p><input class="w3-input w3-padding-16 w3-border" type="text" placeholder="Name" required name="Name"></p>--%>
<%--                <p><input class="w3-input w3-padding-16 w3-border" type="number" placeholder="How many people" required name="People"></p>--%>
<%--                <p><input class="w3-input w3-padding-16 w3-border" type="datetime-local" placeholder="Date and time" required name="date" value="2020-11-16T20:00"></p>--%>
<%--                <p><input class="w3-input w3-padding-16 w3-border" type="text" placeholder="Message \ Special requirements" required name="Message"></p>--%>
<%--                <p><button class="w3-button" type="submit">SEND MESSAGE</button></p>--%>
<%--            </form>--%>
<%--        </div>--%>
<%--    </div>--%>
<%--</div>--%>

</body>
</html>

