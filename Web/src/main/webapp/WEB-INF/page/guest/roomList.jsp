<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="r" uri="http://anydoby.com/simpletags"%>

<fmt:setLocale value="${language}" scope="session" />
<fmt:bundle basename="pagecontent" prefix="paging.">
	<div align="center" class="paging">
		<r:pager page="${listWrapper}" command="${listWrapper.command}" language="${language}" />
	</div>
</fmt:bundle>
	
<fmt:bundle basename="pagecontent" prefix="roomList.">
	<c:forEach items="${roomList}" var="i">
		<div class="windowRoom" align="center">
			<div class="winEqImg">
				<img src="${i.fotoAddress}" alt="test">
			</div>
			<div align="center">
				<span class="titleRoom">№: </span> 
				<span>${i.number}</span><br>
				<span class="titleRoom"><fmt:message key="type"/>:</span> 
				<span>${i.typeRoom.value}</span><br>
				<span class="titleRoom"><fmt:message key="size"/>:</span> 
				<span>${i.size}</span> <span>м²</span><br>
				<span class="titleRoom"><fmt:message key="person"/>:</span> 
				<span>${i.person}</span><br>
				<span class="titleRoom"><fmt:message key="bed"/>:</span> 
				<span>${i.bed}</span><br>
				<span class="titleRoom"><fmt:message key="price"/>:</span> 
				<span class="titleRoomPrice">$${i.price} <fmt:message key="term"/></span>
			</div>
		</div>
	</c:forEach>
</fmt:bundle>