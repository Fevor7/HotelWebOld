idOrderVar = '';
numberRoomVar = '';
dateStartVar = '';
dateEndVar = '';
personVar = '';
roomVar = '';
minPriceVar = '';
maxPriceVar = '';
statusVar = '';
totalVar = '';
typeRoomVar = '';
typeRoomValue = '';
relogin = true;

function closeWindowCheck(){
	document.querySelector('.check').style.display = "none";
}

function showPersonalPage() {
	var params = 'action=personalpage';
	var url = 'Servlet?' + params;
	var request = new XMLHttpRequest();
	request.onreadystatechange = function() {
		if (request.readyState == 4 && request.status == 200) {
			var response = request.responseText;
			if (response == "error") {
				logOut();
				document.querySelector('.windowLogIn').style.display = "block";
				loginCallBack = function() {
					showPersonalPage();
				}
			} else {
				document.querySelector('.insertPage').innerHTML = request.responseText;
				nameUser = document.querySelector('.userName').innerHTML;
				surnameUser = document.querySelector('.userSurname').innerHTML;
				showUserOrderList(0);
				var role = document.querySelector('.role').value;
				var roleLine = document.querySelector('.roleUser').value;
				if (role == "true") {
					roleLine = document.querySelector('.roleAdmin').value;
				}
				document.querySelector('.roleLine').innerHTML = roleLine;

			}
		}
	}
	request.open('GET', url, true);
	request.setRequestHeader(securityHeaderName, securityHeaderValue);
	request.setRequestHeader('Content-Type',
			'application/x-www-form-urlencoded');
	request.send('');
}

function showUserOrderList(pageNumber) {
	var params = 'action=orderlistuser'+'&'+'pagenumber='+pageNumber;
	var url = 'Servlet?' + params;
	var request = new XMLHttpRequest();
	request.onreadystatechange = function() {
		if (request.readyState == 4 && request.status == 200) {
			var response = request.responseText;
			if (response == "error") {
				var errorMessage = document.querySelector('.noOrderFound').value;
				document.querySelector('.insertOrderList').innerHTML = errorMessage;
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

function showEditOrderWindow(event) {
	document.querySelector('.windowEditOrder').style.display = "none";
	document.querySelector('.windowMessage').style.display = "none";
	document.querySelector('.check').style.display = "none";
	var target = $(event.target);
	idOrderVar = target.closest('tr').find('.idOrderTable').text();
	dateStartVar = target.closest('tr').find('.dataStartOrderTable').text();
	dateEndVar = target.closest('tr').find('.dataEndOrderTable').text();
	personVar = target.closest('tr').find('.personOrderTable').text();
	roomVar = target.closest('tr').find('.roomOrderTable').text();
	typeRoomVar = target.closest('tr').find('.typeRoomTable').data('prop');
	typeRoomValue = target.closest('tr').find('.typeRoomTable').text();
	statusVar = target.closest('tr').find('.statusTable').data('status');
	totalVar = target.closest('tr').find('.totalAmountTable').text();
	numberRoomVar = target.closest('tr').find('.numberRoomOrderTable').text();

	document.querySelector('.dateStart').value = dateStartVar;
	document.querySelector('.dateEnd').value = dateEndVar;
	document.querySelector('.bed').value = roomVar;
	document.querySelector('.person').value = personVar;
	document.querySelector('.totalPay').innerHTML = totalVar;
	document.querySelector('.typeRoom').value = typeRoomVar;
	
	switch (statusVar) {
	case 1: {
		document.querySelector('.windowEditOrder').style.display = "block";
		break;
	}
	case 2: {
		document.querySelector('.check').style.display = "block";
		insertDataCheck();
		break;
	}
	case 3: {
		var message = document.querySelector('.orderStatusMess').value;
		document.querySelector('.message').style.weight= "200px";
		document.querySelector('.message').innerHTML = message;
		document.querySelector('.windowMessage').style.display = "block";
		break;
	}
	case 4: {
		var message = document.querySelector('.orderStatusMess').value;
		document.querySelector('.message').innerHTML = message;
		document.querySelector('.windowMessage').style.display = "block";
		break;
	}
	}

}

function insertDataCheck() {
	document.querySelector('.checkNameInsert').innerHTML = nameUser;
	document.querySelector('.checkSurnameInsert').innerHTML = surnameUser;
	document.querySelector('.checkInInsert').innerHTML = dateStartVar;
	document.querySelector('.checkOutInsert').innerHTML = dateEndVar;
	document.querySelector('.numberRoomInsert').innerHTML = numberRoomVar;
	document.querySelector('.typeRoomRoomInsert').innerHTML = typeRoomValue;
	document.querySelector('.totalInsert').innerHTML = totalVar;
	document.querySelector('.idOrderCheckInsert').innerHTML = idOrderVar;
	
}

function deleteUserOrder() {
	var errorMessage = document.querySelector('.orderDeleteError').value;
	var deleteOK = document.querySelector('.deleteOK').value;
	var params = 'action=orderdelete' + '&' + 'id=' + idOrderVar;
	var request = new XMLHttpRequest();
	request.onreadystatechange = function() {
		if (request.readyState == 4 && request.status == 200) {
			if (request.responseText == "OK") {
				showPersonalPage();
				document.querySelector('.message').innerHTML = deleteOK;
				document.querySelector('.windowMessage').style.display = "block";
			}
			if (request.responseText == "error") {
				logOut();
				document.querySelector('.windowLogIn').style.display = "block";
				loginCallBack = function() {
					deleteUserOrder();
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

function closeMessageConfirmation() {
	document.querySelector('.windowConfirmation').style.display = "none";
}

function closeWindowEditOrder() {
	document.querySelector('.windowEditOrder').style.display = "none";

}

function updateUserOrder() {
	if (relogin) {
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
		dateStartVar = document.querySelector('.dateStart').value;
		dateEndVar = document.querySelector('.dateEnd').value;
		roomVar = document.querySelector('.bed').value;
		personVar = document.querySelector('.person').value;
		typeRoomVar = document.querySelector('.typeRoom').value;
		var date = new Date(dateStartVar);
		var curr_month2 = date.getMonth() + 1;
		dateStartVar = ((String(date.getFullYear()).length == 1) ? "0"
				+ date.getFullYear() : date.getFullYear())
				+ "-"
				+ ((String(curr_month2).length == 1) ? "0" + curr_month2
						: curr_month2)
				+ "-"
				+ ((String((date.getDate())).length == 1) ? "0"
						+ (date.getDate()) : (date.getDate()));
		date = new Date(dateEndVar);
		curr_month2 = date.getMonth() + 1;
		dateEndVar = ((String(date.getFullYear()).length == 1) ? "0"
				+ date.getFullYear() : date.getFullYear())
				+ "-"
				+ ((String(curr_month2).length == 1) ? "0" + curr_month2
						: curr_month2)
				+ "-"
				+ ((String((date.getDate())).length == 1) ? "0"
						+ (date.getDate()) : (date.getDate()));
	}
	var update = document.querySelector('.updateMessage').value;

	var params = 'action=orderupdate' + '&' + 'id=' + idOrderVar + '&'
			+ 'dateStart=' + dateStartVar + '&' + 'dateEnd=' + dateEndVar + '&'
			+ 'bed=' + roomVar + '&' + 'person=' + personVar + '&'
			+ 'idTypeRoom=' + typeRoomVar;
	var request = new XMLHttpRequest();
	request.onreadystatechange = function() {
		if (request.readyState == 4 && request.status == 200) {
			if (request.responseText == "OK") {
				relogin = true;
				showPersonalPage();
				document.querySelector('.message').innerHTML = update;
				document.querySelector('.windowMessage').style.display = "block";
			}
			if (request.responseText == "error") {
				relogin = false;
				logOut();
				document.querySelector('.windowLogIn').style.display = "block";
				loginCallBack = function() {
					updateUserOrder();
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

function closeWindowPayment(){
	document.querySelector('.windowPayment').style.display = "none";
}

function roomPayment(){
	var params = 'action=payment' + '&' + 'id=' + idOrderVar;
	var request = new XMLHttpRequest();
	var paymantOK =  document.querySelector('.paymentOK').value;
	request.onreadystatechange = function() {
		if (request.readyState == 4 && request.status == 200) {
			if (request.responseText == "OK") {
				showPersonalPage();
				document.querySelector('.message').innerHTML = paymantOK;
				document.querySelector('.windowMessage').style.display = "block";
			}
			if (request.responseText == "error") {
				logOut();
				document.querySelector('.windowLogIn').style.display = "block";
				loginCallBack = function() {
					roomPayment();
				}
			}
		}
	}
	request.open('POST', 'Servlet');
	request.setRequestHeader(securityHeaderName, securityHeaderValue);
	request.setRequestHeader('Content-Type',
			'application/x-www-form-urlencoded');
	request.send(params);
}

function printCheck() {
	window.print();
}