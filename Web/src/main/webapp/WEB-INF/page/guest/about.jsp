<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="aboutPage" align="center">
	<div>
		<h3>
			<span>${hotel.name}</span> <span style="color: #FF7D08;">${hotel.starReting}</span>
		</h3>
	</div><br><hr><br>
	
	<div>${hotel.address}</div><br>
	<div>${hotel.location}</div><br><hr><br>
	<div><p>${hotel.about}</p></div><br><hr><br>
	
	<div>
		<c:forEach items="${listFacil}" var="i">
			<span> <span class="checkMark">&#10003;</span> <c:out
					value="${i.value}" />&emsp;
			</span>
		</c:forEach>
	</div><br><hr><br>
	
	<div>
		<c:forEach items="${listFoto}" var="i">
			<img src="<c:out value="${i.value}"/>">
		</c:forEach>
	</div><br><hr><br>
	
</div>