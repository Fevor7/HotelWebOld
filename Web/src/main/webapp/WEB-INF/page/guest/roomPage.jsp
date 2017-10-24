<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:useBean id="now" class="java.util.Date" scope="page" />
<fmt:setLocale value="${language}" scope="session" />

<fmt:bundle basename="pagecontent">
	<div class="filterRoom" align="center">
	
		<div><h3><fmt:message key="roomFilter.head"/></h3></div>
		
		<span><fmt:message key="newOrder.checkIn"/></span><br> 
		<input type="date" class="dateStart" min="<fmt:formatDate type="time" value="${now}" pattern="yyyy-MM-dd"/>" value="<fmt:formatDate type="time" value="${now}" pattern="yyyy-MM-dd"/>" OnClick="dateStartClick()" onblur="dateStartClick()"><br>
		<span><fmt:message key="newOrder.checkOut"/></span><br> 
		<input type="date" class="dateEnd" min="<fmt:formatDate type="time" value="${now}" pattern="yyyy-MM-dd"/>" value="<fmt:formatDate type="time" value="${now}" pattern="yyyy-MM-dd"/>" OnClick="dateEndClick()" onblur="dateEndClick()"><br><br>
		<span><fmt:message key="roomList.person"/></span><span>&emsp;&emsp;</span> 
		<span><fmt:message key="roomList.bed"/></span><br>
		<select class="person">
			<option>1</option>
			<option>2</option>
			<option>3</option>
			<option>4</option>
			<option>5</option>
		</select> 
		<span>&emsp;&emsp;&emsp;</span> 
		<select class="bed">
			<option>1</option>
			<option>2</option>
			<option>3</option>
			<option>4</option>
			<option>5</option>
		</select><br><br> 
		<span><fmt:message key="newOrder.typeRoom"/></span><br> 
		<select class="typeRoom">
			<c:forEach items="${typeRoomList}" var="i">
				<option class="firstOptionType" value="<c:out value="${i.id}"/>"><c:out value="${i.value}"/></option>
			</c:forEach>
		</select><br><br> 
		
		<span><fmt:message key="roomList.price"/></span><br> 
		<span><fmt:message key="roomFilter.min"/> $</span> 
		<input type="text" class="inputMinPrice" style="width: 30px;" value="20" onkeypress="enterPressMin(event)" onblur="onfocusMin()"><br>
		<input class="minPrice" oninput="priceMin()" name="range" type="range" min="20" max="2000" step="1" value="20"><br> 
		<span><fmt:message key="roomFilter.max"/> $</span> 
		<input type="text" style="width: 30px;" class="inputMaxPrice" value="2000" onkeypress="enterPressMax(event)" onblur="onfocusMax()"><br>
		<input  type="range"class="maxPrice" oninput="priceMax()" name="range" min="20" max="2000" step="1" value="2000"><br><br>
		<input type="button" value="<fmt:message key="roomFilter.revert"/>" class="sendApplocationButton" OnClick="showRoomPage(0)" /> 
		<span>&emsp;</span> 
		<input type="button" value="<fmt:message key="roomFilter.apply"/>" class="sendApplocationButton" OnClick="searchForRoom(0)" /><br><br> 
		<input type="hidden" value="<fmt:message key="windowMessage.nothingFound"/>" class="messageRoomNotFound"> 
		<input type="hidden" value="<fmt:message key="incorrectDataError"/>" class="incorrectDataError">
		<input type="hidden" value="<fmt:message key="newOrder.errorCheckIn"/>" class="errorCheckIn"> 
		<input type="hidden" value="<fmt:message key="newOrder.errorCheckOut"/>" class="errorCheckOut"> 
		<span class="errorOrder"></span> <span class="errorOrderRoom"></span>
	</div>
</fmt:bundle>
<div class="insertRoomList"></div>