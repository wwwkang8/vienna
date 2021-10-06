<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"  %>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Web page view JSP</title>
</head>
<body>
    <h2>Hello world!</h2>
    <div>JSP List Test</div>
    <c:forEach var="item" items="${list}" varStatus="idx">
        ${idx.index}, ${item} <br/>
    </c:forEach>
</body>
</html>