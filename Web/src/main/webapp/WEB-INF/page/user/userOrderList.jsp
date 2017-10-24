<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="r" uri="http://anydoby.com/simpletags"%>
<jsp:useBean id="now" class="java.util.Date" scope="page" />

<fmt:bundle basename="pagecontent" prefix="paging.">
	<div align="center" class="pagingOrder" >
		<r:pager page="${listWrapper}"  command="${listWrapper.command}" language="${language}" />
	</div>
</fmt:bundle>

<fmt:bundle basename="pagecontent" prefix="orderList.">
	<div>
		<table class="myTable">
			<thead>
				<tr>
					<td>ID</td>
					<td><fmt:message key="numberRoom"/></td>
					<td><fmt:message key="checkIn"/></td>
					<td><fmt:message key="checkOut"/></td>
					<td><fmt:message key="person"/></td>
					<td><fmt:message key="bed"/></td>
					<td><fmt:message key="typeRoom"/></td>
					<td><fmt:message key="total"/></td>
					<td>-</td>
					<td><fmt:message key="status"/></td>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${orderlist}" var="i">
						<tr>
							<td class="idOrderTable"><c:out value="${i.orderId}" /></td>
							<td class="numberRoomOrderTable"><c:out value="${i.room.number}" /></td>
							<td class="dataStartOrderTable"><c:out value="${i.dateStart}" /></td>
							<td class="dataEndOrderTable"><c:out value="${i.dateEnd}" /></td>
							<td class="personOrderTable"><c:out value="${i.personNumber}" /></td>
							<td class="roomOrderTable"><c:out value="${i.bedNumber}" /></td>
							<td class="typeRoomTable" data-prop="<c:out value="${i.typeRoom.id}"/>"><c:out value="${i.typeRoom.value}"/></td>
							<td class="totalAmountTable"><c:out value="$ ${i.getTotalAmount()}" /></td>
							<td><input type="button" value="<fmt:message key="edit"/>" class="editOrderButton" OnClick="showEditOrderWindow(event)"/></td>
							<td data-status=<c:out value="${i.orderStatus.id}"/> class="statusTable"><c:out value="${i.orderStatus.value}"/></td>
						</tr>
				</c:forEach>
			</tbody>
		</table>
		<br>
	</div>
</fmt:bundle>

<fmt:bundle basename="pagecontent">

<fmt:bundle basename="pagecontent" prefix="paging.">
	<div align="center" class="pagingOrder" >
		<r:pager page="${listWrapper}"  command="${listWrapper.command}" language="${language}"/>
	</div>
</fmt:bundle>

	<div class="windowConfirmation" align="center"><br>
		<span><fmt:message key="orderEdit.deleteMes"/></span><br><br>
		<span><fmt:message key="orderEdit.id"/></span>
		<span class="idOrderSave"></span><br><br>
		<input class="closeMessage" type="button" value="<fmt:message key="windowMessage.yes"/>" OnClick="deleteUserOrder()"/>
		<span>&emsp;&emsp;&emsp;</span>
		<input class="closeMessage" type="button" value="<fmt:message key="windowMessage.no"/>" OnClick="closeMessageConfirmation()"/>
	</div>
	
	<div class="windowEditOrder" align="center">
	
		<div><h3><fmt:message key="orderEdit.header"/></h3></div>
		
		<input type="date" class="dateStart" min="<fmt:formatDate type="time" value="${now}" pattern="yyyy-MM-dd"/>" value="<fmt:formatDate type="time" value="${now}" pattern="yyyy-MM-dd"/>" OnClick="dateStartClick()" onblur="dateStartClick()">
		<span> - </span>
		<input type="date" class="dateEnd" min="<fmt:formatDate type="time" value="${now}" pattern="yyyy-MM-dd"/>" value="<fmt:formatDate type="time" value="${now}" pattern="yyyy-MM-dd"/>" OnClick="dateEndClick()" onblur="dateEndClick()" ><br><br>
		<span><fmt:message key="roomList.person"/> &emsp;&emsp;&emsp;&emsp;&emsp;</span>
		<span><fmt:message key="roomList.bed"/></span> <br>
		
		<select class="person">
			<option>1</option>
			<option>2</option>
			<option>3</option>
			<option>4</option>
			<option>5</option>
		</select>
		<span>&emsp;&emsp;&emsp;&emsp;&emsp; </span>
		
		<select class="bed">
			<option>1</option>
			<option>2</option>
			<option>3</option>
			<option>4</option>
			<option>5</option>
		</select><br><br>
		
		<span><fmt:message key="newOrder.typeRoom"/></span> <br>
		
		<select class="typeRoom">
			<c:forEach items="${typeRoomList}" var="i">
				<option class = "firstOptionType" value="<c:out value="${i.id}"/>"><c:out value="${i.value}"/></option>
			</c:forEach>
		</select><br><br>
		
		<input type="hidden" value="<fmt:message key="windowMessage.incorrectDataError"/>" class="messageErrorData">
		<input type="hidden" value="<fmt:message key="newOrder.errorCheckIn"/>" class="errorCheckIn"> 
		<input type="hidden" value="<fmt:message key="newOrder.errorCheckOut"/>" class="errorCheckOut"> 
		<input  type="button" value=<fmt:message key="orderEdit.butSave"/> class="sendApplocationButton" OnClick="updateUserOrder()"/>
		<span>&emsp;&emsp;</span>
		<input  type="button" value=<fmt:message key="orderEdit.butDelete"/> class="sendApplocationButton" OnClick="deleteUserOrder()"/><br><br>
		<span class="closeWindowLogin" OnClick="closeWindowEditOrder()">&#10006;</span>
		<span class="errorOrder"></span>
	</div>

<div class="windowPayment" align="center"><br>
	<span><fmt:message key="orderEdit.confirmed"/></span><br><br>
	<span><fmt:message key="orderEdit.toPay"/>:</span>
	<span class="totalPay"></span><br><br>
	<input type="button" value="<fmt:message key="orderEdit.pay"/>" OnClick="roomPayment()" class="closeMessage"/>
	<span class="closeWindowPayment" OnClick="closeWindowPayment()">&#10006;</span>
</div>

</fmt:bundle>