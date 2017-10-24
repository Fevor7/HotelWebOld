<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<fmt:setLocale value="${language}" scope="session" />

<fmt:bundle basename="pagecontent">
	<div class="personalPage">
		<div align="center"><h3><fmt:message key="personalPage.head"/></h3></div>
		<div class="personalInfo" >
			<div class="signUpPart1 textPersonInfo">
				<span><fmt:message key="signUp.login"/>:</span> <br> 
				<span><fmt:message key="signUp.name"/>:</span><br> 
				<span><fmt:message key="signUp.surname"/>:</span><br> 
				<span><fmt:message key="signUp.email"/>:</span><br> 
				<span><fmt:message key="personalPage.access"/>:</span><br>
			</div>
			<div class="signUpPart2">
				<span>${user.login}</span> <br> 
				<span class="userName">${user.name}</span> <br> 
				<span class="userSurname">${user.surname} </span> <br> 
				<span>${user.email}</span> <br> 
				<span class="roleLine"></span><br> 
				<input type="hidden" value="<fmt:message key="personalPage.noOrderFound"/>" class="noOrderFound" /> 
				<input type="hidden" value="<fmt:message key="personalPage.administrator"/>" class="roleAdmin" /> 
				<input type="hidden" value="<fmt:message key="personalPage.user"/>" class="roleUser"/> 
				<input type="hidden" class="role" value="${user.role}" />
			</div>
		</div>
		<div align="center" class="orderListText"><hr>
			<h4><span><fmt:message key="personalPage.order"/></span></h4>
		</div>
		<div class="insertOrderList" align="center"></div>
	</div>
</fmt:bundle>