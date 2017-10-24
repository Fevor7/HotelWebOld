<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<fmt:setLocale value="${sessionScope.language}" scope="session"/>

<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf8">
			
			<fmt:bundle basename="pagecontent" prefix="head.">
				<title><fmt:message key="hotel"/></title>
			</fmt:bundle>
		
		<link rel="shortcut icon" href="web/images/favicon.ico" type="image/x-icon">
		<link rel="stylesheet" type="text/css" href="web/css/styleMenu.css">
		<link rel="stylesheet" type="text/css" href="web/css/styleIndex.css">
		<link rel="stylesheet" type="text/css" href="web/css/windowLogIn.css">
		<link rel="stylesheet" type="text/css" href="web/css/windowSignUp.css">
		<link rel="stylesheet" type="text/css" href="web/css/personalPage.css">
		<link rel="stylesheet" type="text/css" href="web/css/firstPage.css">
		<link rel="stylesheet" type="text/css" href="web/css/orderList.css">
		<link rel="stylesheet" type="text/css" href="web/css/table.css">
		<link rel="stylesheet" type="text/css" href="web/css/styleSlider.css">
		<link rel="stylesheet" type="text/css" href="web/css/aboutPage.css">
		<link rel="stylesheet" type="text/css" href="web/css/roomPage.css">

		<script type="text/javascript" src="web/js/script.js"></script>
		<script type="text/javascript" src="web/js/scriptRequestLogin.js"></script>
		<script type="text/javascript" src="web/js/scriptRequestSignIn.js"></script>
		<script type="text/javascript" src="web/js/downloadPage.js"></script>
		<script type="text/javascript" src="web/js/personalPage.js"></script>
		<script type="text/javascript" src="web/js/firstPage.js"></script>
		<script type="text/javascript" src="web/js/orderList.js"></script>
		<script type="text/javascript" src="web/js/jquery-1.7.2.min.js"></script>
		<script type="text/javascript" src="web/js/scriptSlider.js"></script>
		<script type="text/javascript" src="web/js/aboutPage.js"></script>
		<script type="text/javascript" src="web/js/roomPage.js"></script>
	</head>
	<body onLoad="session('${user.name}','${user.surname}','${user.role}','${page}', '${orderStatus}')">
		<div class="noprint">
			<header Onclick="showFirstPage()"></header>
			
			<div class="language">
				<img src="web/images/RU.png" width="24" height="24" class="lang" onclick="switchLang('ru')">
				<img src="web/images/US.png" width="24" height="24"class="lang" onclick="switchLang('en')"> 
			</div>
			
			<fmt:bundle basename="pagecontent" prefix="menu.">
				<nav class="menu">
					<nav class="menuPart1">
						<div class="menuStyle">
							<ul>
								<li OnClick="showFirstPage()">
									<a href="#"><fmt:message key="home"/></a>
								</li>
								<li OnClick="showRoomPage(0)">
									<a href="#"><fmt:message key="rooms"/></a>
								</li>
								<li OnClick="showAboutPage()">
									<a href="#"><fmt:message key="about"/></a>
								</li>
								<li class="admin">
									<a href="#"><fmt:message key="administration" /></a>
									<ul>
								 		<li OnClick="showOrderListAdmin('1')">
											<a href="#"> <fmt:message key="order"/></a>
										</li>
									</ul>
								</li>
							</ul>
						</div>
					</nav>
					<nav class="menuPart2">
						<div class="menuStyle">
							<ul>
								<li class="exit" OnClick="logOut()">
									<a href="#"><fmt:message key="exit" /></a>
								</li>
								<li></li>
								<li OnClick="showWindowLogin()">
									<input type="hidden" value="<fmt:message key="login"/>" class="fieldLOGIN" /> 
									<a href="#" class="login"></a>
								</li>
							</ul>
						</div>
					</nav>
				</nav>
			</fmt:bundle>
			
			<fmt:bundle basename="pagecontent" prefix="login.">
				<div class="windowLogIn" align="center">
					<form onkeypress="pressEnter(event)">
						<h3><fmt:message key="authorization"/></h3>
						<input type="text" placeholder="<fmt:message key="login"/>" class="loginLogIn"/><br><br> 
						<input type="password" placeholder="<fmt:message key="password"/>" class="passLogIn" /><br> 
						<input type="hidden" value="<fmt:message key="accessDenied"/>" class="accessDenied" /> 
						<input type="hidden" value="<fmt:message key="fillAllFields"/>" class="fillAllFields" />
						<div class="errorLogin"></div>
						<input type="button" value="<fmt:message key="logInButton"/>" OnClick="logIn()" class="logInButton"/><br><br> 
						<a href="#" class="showWindowSignUp" OnClick="showWindowSignUp()"><fmt:message key="signUp" /></a> 
						<span class="closeWindowLogin" OnClick="closeWindowLogin()"></span>
					</form>
				</div>
			</fmt:bundle>
			
			<fmt:bundle basename="pagecontent" prefix="signUp.">
				<div class="windowSignUp" align="center">
					<h3><fmt:message key="windowSignUp" /></h3>
					<div class="signUpPart1">
						<span><fmt:message key="name"/></span><br><br> 
						<span><fmt:message key="surname"/></span><br><br> 
						<span><fmt:message key="email"/></span><br><br>
						<span><fmt:message key="login"/></span><br><br> 
						<span><fmt:message key="password"/></span><br><br> 
						<span><fmt:message key="RePassword"/></span><br><br>
					</div>
					<div class="signUpPart2" onkeypress="pressEnterSignUp(event)">
						<input type="text" placeholder="<fmt:message key="nameExample"/>" class="nameInputReg" /><br><br> 
						<input type="text"  placeholder="<fmt:message key="surnameExample"/>" class="surnameInputReg" /><br><br> 
						<input type="email" placeholder="<fmt:message key="emailExample"/>" class="emailInputReg"/><br><br> 
						<input type="text" placeholder="<fmt:message key="loginExample"/>" class="loginInputReg"/><br><br> 
						<input type="password" placeholder="<fmt:message key="symbolPoint"/>" class="passInputReg"/><br><br> 
						<input type="password" placeholder="<fmt:message key="symbolPoint"/>" class="repassInputReg"/><br>
						<br>
					</div>
					<div>
						<input type="button" value="<fmt:message key="signUpButton"/>" OnClick="signUp()" class="signUpButton" /><br> 
						<input type="hidden" value="<fmt:message key="passwordError"/>" class="passwordError" /> 
						<input type="hidden" value="<fmt:message key="shortPassword"/>." class="shortPassword" />
						<input type="hidden" value="<fmt:message key="incorrectEmail"/>" class="incorrectEmail" />
						<input type="hidden" value="<fmt:message key="incorrectName"/>" class="incorrectName" />
						<input type="hidden" value="<fmt:message key="incorrectPass"/>" class="incorrectPass" />
						<span class="errorSignUp"></span>
					</div>
					<div class="closeWindowSignUp">
						<span class="closechar" OnClick="closeWindowSignUp()"></span>
					</div>
				</div>
			</fmt:bundle>
			
			<fmt:bundle basename="pagecontent" prefix="windowMessage.">
				<div class="windowMessage" align="center">
					<div class="message"></div><br><br> 
					<input type="hidden" value="<fmt:message key="updateMessage"/>" class="updateMessage" /> 
					<input type="hidden" value="<fmt:message key="deleteOK"/>" class="deleteOK" /> 
					<input type="hidden" value="<fmt:message key="orderDeleteError"/>" class="orderDeleteError" /> 
					<input type="hidden" value="<fmt:message key="signUpOK"/>" class="signUpOK" /> 
					<input type="hidden" value="<fmt:message key="signUpErrorlogin"/>" class="signUpErrorlogin" /> 
					<input type="hidden" value="<fmt:message key="oderNotFound"/>" class="oderNotFound"> 
					<input type="hidden" value="<fmt:message key="incorrectDataError"/>" class="incorrectDataError">
					<input type="hidden" value="<fmt:message key="paymentOK"/>" class="paymentOK"> 
					<input type="hidden" value="<fmt:message key="errorEditOrder"/>" class="orderStatusMess"> 
					<input type="button" value="<fmt:message key="closeWindowMessage"/>" class="closeMessage" OnClick="closeMessage()" />
				</div>
			</fmt:bundle>
			<div class="insertPage"></div>
		</div>
		
		<div class="check">
			<fmt:bundle basename="pagecontent">
				<div align="center"><h3><fmt:message key="check.title" /></h3></div>
				<div class="checkPart1">
					<span><fmt:message key="roomList.numberOrder"/>:</span><br><br>
					<span><fmt:message key="signUp.name"/>:</span><br>
					<span><fmt:message key="signUp.surname"/>:</span><br> 
					<span><fmt:message key="newOrder.checkIn"/>:</span><br>
					<span><fmt:message key="newOrder.checkOut"/>:</span><br> 
					<span><fmt:message key="orderList.numberRoom"/>:</span><br> 
					<span><fmt:message key="orderList.typeRoom"/>:</span><br><br>
					<span><fmt:message key="orderEdit.toPay"/>:</span><br><br>
				</div>
				<div class="checkPart2">
					<span class="idOrderCheckInsert"></span><br><br>
					<span class="checkNameInsert"></span><br>
					<span class="checkSurnameInsert"></span><br> 
					<span class="checkInInsert"></span><br>
					<span class="checkOutInsert"></span><br>
					<span class="numberRoomInsert"></span><br>
					<span class="typeRoomRoomInsert"></span><br><br>
					<span class="totalInsert"></span><br><br>
				</div>
				<span class="closeWindowPayment" OnClick="closeWindowCheck()">&#10006;</span>
				<div align="center">
					<input type="button" value="<fmt:message key="check.print"/>" class="printCheck" OnClick="printCheck()" /><br>
				</div>
			</fmt:bundle>
		</div>
		
	</body>
</html>
