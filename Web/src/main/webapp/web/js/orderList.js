securityHeaderName = "SecurityHeader";
securityHeaderValue = "javathebest";
idUserVarAdmin = '';
idOrderVarAdmin = '';
idRoomVarAdmin = '';
dateStartVarAdmin = '';
dateEndVarAdmin = '';
personVarAdmin = '';
roomVarAdmin = '';
typeRoomAdmin = '';
statusAdmin = '';
totalAmountAdmin = '';
reloginAdmin = true;
orderStatus = '';

function showOrderListPage(type, pageNumber) {
	idStatusOrderVar = type;
	orderStatus = type;
	var params = '?action=orderlist' + '&' + 'type=' + type+'&'+'pagenumber='+pageNumber;
	if (type == '5') {
		params = '?action=allorderlist'+'&'+'pagenumber='+pageNumber;
	}
	var url = 'Servlet' + params;
	var request = new XMLHttpRequest();
	request.onreadystatechange = function() {
		if (request.readyState == 4 && request.status == 200) {
			var response = request.responseText;
			document.querySelector('.orderListChoose').selectedIndex=type-1;
			if (response == "error") {
				var errorOrder = document.querySelector('.oderNotFound').value;
				document.querySelector('.insertOrderList').style.color = 'white';
				document.querySelector('.insertOrderList').innerHTML = '<br>'
						+ errorOrder;
			} else {
				document.querySelector('.insertOrderList').innerHTML = request.responseText;
			}

		}
	}
	request.open('GET', url, true);
	request.setRequestHeader(securityHeaderName, securityHeaderValue);
	request.setRequestHeader('Content-Type',
			'application/x-www-form-urlencoded');
	request.send('');
}
function showAdminEditOrder(event) {
	var target = $(event.target);
	idUserVarAdmin = target.closest('tr').find('.userIdOrderTable').text();
	idOrderVarAdmin = target.closest('tr').find('.idOrderTable').text();
	idRoomVarAdmin = target.closest('tr').find('.roomIdOrderTable').text();
	dateStartVarAdmin = target.closest('tr').find('.dataStartOrderTable')
			.text();
	dateEndVarAdmin = target.closest('tr').find('.dataEndOrderTable').text();
	personVarAdmin = target.closest('tr').find('.personOrderTable').text();
	roomVarAdmin = target.closest('tr').find('.roomOrderTable').text();
	typeRoomAdmin = target.closest('tr').find('.typeRoomTable').data('type');
	statusAdmin = target.closest('tr').find('.statusTable').data('status');
	totalAmountAdmin = target.closest('tr').find('.totalAmountTable').text();
	totalAmountAdmin = totalAmountAdmin.substr(2, 4);
	if (totalAmountAdmin == '') {
		totalAmountAdmin = 0;
	}
	

	document.querySelector('.roomIdEditOrder').value = idRoomVarAdmin;
	document.querySelector('.orderIdAdminEdit').innerText = idOrderVarAdmin;
	document.querySelector('.userIdEditOrder').innerHTML = idUserVarAdmin;
	document.querySelector('.dateStart').value = dateStartVarAdmin;
	document.querySelector('.dateEnd').value = dateEndVarAdmin;
	document.querySelector('.bed').value = roomVarAdmin;
	document.querySelector('.person').value = personVarAdmin;
	document.querySelector('.typeRoom').value = typeRoomAdmin;
	document.querySelector('.statusOrder').value = statusAdmin;
	document.querySelector('.totalEditOrder').value = totalAmountAdmin;
	
	document.querySelector('.editOrderAdminButton').style.display = "inline-block";
	document.querySelector('.save').style.display = "inline-block";
	document.querySelector('.delete').style.display = "inline-block";
	document.querySelector('.chooseRoom').style.display = "inline-block";
	document.querySelector('.orderlistFilter').style.display = "inline-block";
	document.querySelector('.Revert').style.display = "inline-block";

}

function updateUserOrderAdmin() {
	totalAmountAdmin = document.querySelector('.totalEditOrder').value; 
	document.querySelector('.errorOrder').innerHTML = "";
	if (reloginAdmin) {
		if(!validateData(document.querySelector('.dateStart').value)) {
			var incorrectName = document.querySelector('.errorCheckIn').value;
			document.querySelector('.errorOrder').innerHTML = incorrectName;
			return;
		} 
		if(!validateData(document.querySelector('.dateEnd').value)) {
			var incorrectName = document.querySelector('.errorCheckOut').value;
			document.querySelector('.errorOrder').innerHTML = incorrectName;
			return;
		} 
		
		if(!validateNumber(document.querySelector('.roomIdEditOrder').value)) {
			var incorrectRoom = document.querySelector('.errorRoomId').value;
			document.querySelector('.errorOrder').innerHTML = incorrectRoom;
			return;
		}
		if(!validateNumber(document.querySelector('.totalEditOrder').value)) {
			var incorrectRoom = document.querySelector('.errorTotal').value;
			document.querySelector('.errorOrder').innerHTML = incorrectRoom;
			return;
		}
		dateStartVarAdmin = document.querySelector('.dateStart').value;
		dateEndVarAdmin = document.querySelector('.dateEnd').value;
		roomVarAdmin = document.querySelector('.bed').value;
		personVarAdmin = document.querySelector('.person').value;
		idRoomVarAdmin = document.querySelector('.roomIdEditOrder').value;
		typeRoomAdmin = document.querySelector('.typeRoom').value;
		statusAdmin = document.querySelector('.statusOrder').value;
		var date = new Date(dateStartVarAdmin);
		var curr_month2 = date.getMonth() + 1;
		dateStartVarAdmin = ((String(date.getFullYear()).length == 1) ? "0"
				+ date.getFullYear() : date.getFullYear())
				+ "-"
				+ ((String(curr_month2).length == 1) ? "0" + curr_month2
						: curr_month2)
				+ "-"
				+ ((String((date.getDate())).length == 1) ? "0"
						+ (date.getDate()) : (date.getDate()));
		date = new Date(dateEndVarAdmin);
		curr_month2 = date.getMonth() + 1;
		dateEndVarAdmin = ((String(date.getFullYear()).length == 1) ? "0"
				+ date.getFullYear() : date.getFullYear())
				+ "-"
				+ ((String(curr_month2).length == 1) ? "0" + curr_month2
						: curr_month2)
				+ "-"
				+ ((String((date.getDate())).length == 1) ? "0"
						+ (date.getDate()) : (date.getDate()));
	}
	var update = document.querySelector('.updateMessage').value;

	var params = 'action=orderupdateadmin' + '&' + 'id=' + idOrderVarAdmin
			+ '&' + 'dateStart=' + dateStartVarAdmin + '&' + 'dateEnd='
			+ dateEndVarAdmin + '&' + 'bed=' + roomVarAdmin + '&' + 'person='
			+ personVarAdmin + '&' + 'idTypeRoom=' + typeRoomAdmin + '&'
			+ 'roomId=' + idRoomVarAdmin + '&' + 'status=' + statusAdmin + '&'
			+ 'totalAmount=' + totalAmountAdmin;
	var request = new XMLHttpRequest();
	request.onreadystatechange = function() {
		if (request.readyState == 4 && request.status == 200) {
			if (request.responseText == "OK") {
				reloginAdmin = true;
				showOrderListAdmin(idStatusOrderVar);
				document.querySelector('.message').innerHTML = update;
				document.querySelector('.windowMessage').style.display = "block";
			}
			if (request.responseText == "error") {
				reloginAdmin = false;
				logOut();
				document.querySelector('.windowLogIn').style.display = "block";
				loginCallBack = function() {
					updateUserOrderAdmin();
				}
			}
			if (request.responseText == "errordata") {
				var error = document.querySelector('.messageErrorData').value;
				document.querySelector('.errorOrder').innerHTML = error;
			}

		}
	}
	request.open('POST', 'Servlet');
	request.setRequestHeader(securityHeaderName, securityHeaderValue);
	request.setRequestHeader('Content-Type',
			'application/x-www-form-urlencoded');
	request.send(params);
}

function deleteUserOrderAdmin() {
	var errorMessage = document.querySelector('.orderDeleteError').value;
	var deleteOK = document.querySelector('.deleteOK').value;
	var params = 'action=orderdelete' + '&' + 'id=' + idOrderVarAdmin;
	var request = new XMLHttpRequest();
	request.onreadystatechange = function() {
		if (request.readyState == 4 && request.status == 200) {
			if (request.responseText == "OK") {
				showOrderListAdmin(idStatusOrderVar);
				document.querySelector('.message').innerHTML = deleteOK;
				document.querySelector('.windowMessage').style.display = "block";
			}
			if (request.responseText == "error") {
				logOut();
				document.querySelector('.windowLogIn').style.display = "block";
				loginCallBack = function() {
					deleteUserOrderAdmin();
				}
			}
			if (request.responseText == "errordelete") {
				document.querySelector('.message').innerHTML = errorMessage;
				document.querySelector('.windowConfirmation').style.display = "none";
				document.querySelector('.windowMessage').style.display = "block";
			}
		}
	}
	request.open('POST', 'Servlet');
	request.setRequestHeader(securityHeaderName, securityHeaderValue);
	request.setRequestHeader('Content-Type',
			'application/x-www-form-urlencoded');
	request.send(params);
}

function revertValueFilterOrderAdmin() {
	document.querySelector('.errorOrder').innerHTML = "";
	document.querySelector('.roomIdEditOrder').value = idRoomVarAdmin;
	document.querySelector('.orderIdAdminEdit').innerText = idOrderVarAdmin;
	document.querySelector('.userIdEditOrder').value = idUserVarAdmin;
	document.querySelector('.dateStart').value = dateStartVarAdmin;
	document.querySelector('.dateEnd').value = dateEndVarAdmin;
	document.querySelector('.bed').value = roomVarAdmin;
	document.querySelector('.person').value = personVarAdmin;
	document.querySelector('.typeRoom').value = typeRoomAdmin;
	document.querySelector('.statusOrder').value = statusAdmin;

}
function searchForRoomAdmin(pageNumber) {
	document.querySelector('.errorOrder').innerHTML = "";
	if(!validateData(document.querySelector('.dateStart').value)) {
		var incorrectName = document.querySelector('.errorCheckIn').value;
		document.querySelector('.errorOrder').innerHTML = incorrectName;
		return;
	} 
	if(!validateData(document.querySelector('.dateEnd').value)) {
		var incorrectName = document.querySelector('.errorCheckOut').value;
		document.querySelector('.errorOrder').innerHTML = incorrectName;
		return;
	}
	dateStartVarAdminSend = document.querySelector('.dateStart').value;
	dateEndVarAdminSend = document.querySelector('.dateEnd').value;
	roomVarAdminSend = document.querySelector('.bed').value;
	personVarAdminSend = document.querySelector('.person').value;
	typeRoomAdminSend = document.querySelector('.typeRoom').value;
	var date = new Date(dateStartVarAdmin);
	var curr_month2 = date.getMonth() + 1;
	dateStartVarAdminSend = ((String(date.getFullYear()).length == 1) ? "0"
			+ date.getFullYear() : date.getFullYear())
			+ "-"
			+ ((String(curr_month2).length == 1) ? "0" + curr_month2
					: curr_month2)
			+ "-"
			+ ((String((date.getDate())).length == 1) ? "0"
					+ (date.getDate()) : (date.getDate()));
	date = new Date(dateEndVarAdmin);
	curr_month2 = date.getMonth() + 1;
	dateEndVarAdminSend = ((String(date.getFullYear()).length == 1) ? "0"
			+ date.getFullYear() : date.getFullYear())
			+ "-"
			+ ((String(curr_month2).length == 1) ? "0" + curr_month2
					: curr_month2)
			+ "-"
			+ ((String((date.getDate())).length == 1) ? "0"
					+ (date.getDate()) : (date.getDate()));
	var update = document.querySelector('.updateMessage').value;

	var params = '?action=roomsearchadmin' + '&' + 'id=' + idOrderVarAdmin
			+ '&' + 'dateStart=' + dateStartVarAdminSend + '&' + 'dateEnd='
			+ dateEndVarAdminSend + '&' + 'bed=' + roomVarAdminSend + '&'
			+ 'person=' + personVarAdminSend + '&' + 'idTypeRoom='
			+ typeRoomAdminSend+'&'+'pagenumber='+pageNumber;
	var url = 'Servlet' + params;
	var request = new XMLHttpRequest();
	request.onreadystatechange = function() {
		if (request.readyState == 4 && request.status == 200) {
			var response = request.responseText;
			switch (response) {
			case 'error': {
				reloginAdmin = false;
				var error = document.querySelector('.messageRoomNotFound').value;
				document.querySelector('.insertChooseTypeOrder').style.color = "white";
				document.querySelector('.insertChooseTypeOrder').innerHTML = error;
				break;
			}
			case 'errordata': {
				var error = document.querySelector('.messageErrorData').value;
				document.querySelector('.errorOrder').innerHTML = error;
				break;
			}
			default: {
				reloginAdmin = true;
				document.querySelector('.insertChooseTypeOrder').style.color = "black";
				document.querySelector('.insertChooseTypeOrder').innerHTML = request.responseText;
				break;
			}
			}

		}
	}
	request.open('GET', url, true);
	request.setRequestHeader(securityHeaderName, securityHeaderValue);
	request.setRequestHeader('Content-Type',
			'application/x-www-form-urlencoded');
	request.send('');

}

function validateData(data) {
	return (/[0-9]{4}-(0[1-9]|1[012])-(0[1-9]|1[0-9]|2[0-9]|3[01])/.test(data)); 
}

function validateNumber(number) {
	return (/[0-9]/.test(number)); 
}

function selectRoom(roomId) {
	document.querySelector('.roomIdEditOrder').value = roomId;
}

function showOrderListAdmin(type) {
	orderStatus = '1';
	var params = '?action=orderlistadmin';
	var url = 'Servlet' + params;
	var request = new XMLHttpRequest();
	request.onreadystatechange = function() {
		if (request.readyState == 4 && request.status == 200) {
			var response = request.responseText;
			if (response == "error") {
				logOut();
				document.querySelector('.windowLogIn').style.display = "block";
				loginCallBack = function() {
					showOrderListAdmin(type);
				}
			} else {
				document.querySelector('.insertPage').innerHTML = request.responseText;
				showPageChooseTypeOrder(type);
			}
		}
	}
	request.open('GET', url, true);
	request.setRequestHeader(securityHeaderName, securityHeaderValue);
	request.setRequestHeader('Content-Type',
			'application/x-www-form-urlencoded');
	request.send('');
}

function showPageChooseTypeOrder(type) {
	var params = '?action=pagechoosetypeorder';
	var url = 'Servlet' + params;
	var request = new XMLHttpRequest();
	request.onreadystatechange = function() {
		if (request.readyState == 4 && request.status == 200) {
			var response = request.responseText;
			document.querySelector('.insertChooseTypeOrder').innerHTML = request.responseText;
			showOrderListPage(type, 0);
		}
	}
	request.open('GET', url, true);
	request.setRequestHeader(securityHeaderName, securityHeaderValue);
	request.setRequestHeader('Content-Type',
			'application/x-www-form-urlencoded');
	request.send('');

}