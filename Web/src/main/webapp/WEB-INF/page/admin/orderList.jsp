<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="r" uri="http://anydoby.com/simpletags"%>
<jsp:useBean id="now" class="java.util.Date" scope="page" />

<fmt:bundle basename="pagecontent" prefix="paging.">	
	<div align="center" class="paging" >
		<br>
		<r:pager page="${listWrapper}"  command="${listWrapper.command}" language="${language}"/>
	</div>
</fmt:bundle>

<fmt:bundle basename="pagecontent" prefix="orderList.">	
	<div class="orderList" align="center">
		<table class="myTable">
			<thead>
				<tr>
					<td>ID</td>
						<td><fmt:message key="numberRoom"/></td>
						<td><fmt:message key="userLogin"/></td>
						<td><fmt:message key="checkIn"/></td>
						<td><fmt:message key="checkOut"/></td>
						<td><fmt:message key="person"/></td>
						<td><fmt:message key="bed"/></td>
						<td><fmt:message key="typeRoom"/></td>
						<td><fmt:message key="total"/></td>
						<td>----</td>
						<td><fmt:message key="status"/></td>	
				</tr>
			</thead>
				<c:forEach items="${orderlist}" var="i">
						<tr>
							<td class="idOrderTable"><c:out value="${i.orderId}" /></td>
							<td class="roomIdOrderTable"><c:out value="${i.room.number}" /></td>
							<td class="userIdOrderTable"><c:out value="${i.user.login}" /></td>
							<td class="dataStartOrderTable"><c:out value="${i.dateStart}" /></td>
							<td class="dataEndOrderTable"><c:out value="${i.dateEnd}" /></td>
							<td class="personOrderTable"><c:out value="${i.personNumber}" /></td>
							<td class="roomOrderTable"><c:out value="${i.bedNumber}" /></td>
							<td class="typeRoomTable" data-type="<c:out value="${i.typeRoom.id}" />"><c:out value="${i.typeRoom.value}" /></td>
							<td class="totalAmountTable"><c:out value="$ ${i.totalAmount}" /></td>
							<td><input type="button" value="<fmt:message key="edit"/>" class="editOrderButton" OnClick="showAdminEditOrder(event)" /></td>
							<td data-status="<c:out value="${i.orderStatus.id}"/>" class="statusTable"><c:out value="${i.orderStatus.value}"/></td>
						</tr>
				</c:forEach>
		</table><br>
	</div>
</fmt:bundle>

<fmt:bundle basename="pagecontent" prefix="paging.">	
	<div align="center" class="paging" >
		<r:pager page="${listWrapper}"  command="${listWrapper.command}" language="${language}"/>
	</div>
</fmt:bundle>