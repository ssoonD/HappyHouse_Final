<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="root" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html lang="ko">
<head>
  <title>SSAFY-Error page</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.6/umd/popper.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.2.1/js/bootstrap.min.js"></script>
  <style type="text/css">
  .err {
  	color: red;
  	font-size: 25px;
  	font-weight: bold;
  }
  </style>
</head>
<body>
<div class="container" align="center">
  <div class="jumbotron mt-3">
  	<%@ include file="/WEB-INF/views/include/header.jsp" %>
    <br><br><h2>Error Page T.T</h2><br>      
    <p class="err">${msg}</p>
  </div>  
  <p><a href="${root}">메인으로 가기</a></p>
</div>
</body>
</html>