<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"  %>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Web page view JSP</title>
</head>
<body>
<script type="text/javascript">
    const button = document.querySelector('.btn')
    const form   = document.querySelector('.form')

    button.addEventListener('click', function() {
        form.classList.add('form--no')
    });
</script>
<div class="user">
    <header class="user__header">
        <img src="https://s3-us-west-2.amazonaws.com/s.cdpn.io/3219/logo.svg" alt="" />
        <h1 class="user__title">A lightweight and simple sign-up form</h1>
    </header>

    <form class="form" action="${pageContext.request.contextPath}/user/register" method="post">
        <div class="form__group">
            <input type="email" name="email" placeholder="Email" class="form__input" />
        </div>

        <div class="form__group">
            <input type="password" name="password" placeholder="Password" class="form__input" />
        </div>

        <button class="btn" type="submit">Register</button>
    </form>
</div>
</body>
</html>