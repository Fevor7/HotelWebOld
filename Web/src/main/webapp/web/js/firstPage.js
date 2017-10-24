securityHeaderName = "SecurityHeader";
securityHeaderValue = "javathebest";
function showFirstPage() {

	var params = '?action=firstpage';
	var url = 'Servlet' + params;
	var request = new XMLHttpRequest();
	request.onreadystatechange = function() {
		if (request.readyState == 4 && request.status == 200) {
			document.querySelector('.insertPage').innerHTML = request.responseText;
			var name = document.querySelector('.nameUser').value;
			var role = document.querySelector('.roleUser').value;
			var hiddenField = document.querySelector('.fieldLOGIN').value;
			if (name == "") {
				loginStatus = "none";
				document.querySelector('.exit').style.display = "none";
				document.querySelector('.login').innerText = hiddenField;
				document.querySelector('.exit').style.display = "none";
				document.querySelector('.admin').style.display = "none";
			} else {
				loginStatus = name;
				document.querySelector('.login').innerText = name;
				document.querySelector('.exit').style.display = "block";
				if (role == 'true') {
					document.querySelector('.admin').style.display = "block";
				}
			}
			animateSlider();
		}
	}
	request.open('GET', url, true);
	request.setRequestHeader(securityHeaderName, securityHeaderValue);
	request.setRequestHeader('Content-Type',
			'application/x-www-form-urlencoded');
	request.send('');
}

function enterPressMin(e) {
	if (e.keyCode == 13) {
		onfocusMin();
	}
}

function onfocusMin() {
	var valueInput = document.querySelector('.inputMinPrice').value;
	var valueInputMax = document.querySelector('.inputMaxPrice').value;
	if ((valueInput >= 20) && (valueInput <= 2000)) {
		document.querySelector('.minPrice').value = valueInput;
		document.querySelector('.maxPrice').min = valueInput;
		if (valueInput > valueInputMax) {
			document.querySelector('.maxPrice').min = valueInput;
			document.querySelector('.maxPrice').value = valueInput;
			document.querySelector('.inputMaxPrice').value = valueInput;
		}
	} else {
		var min = 20;
		document.querySelector('.inputMinPrice').value = min;
		document.querySelector('.minPrice').value = min;
		document.querySelector('.maxPrice').min = min;
	}
}

function enterPressMax(e) {
	if (e.keyCode == 13) {
		onfocusMax();
	}
}

function onfocusMax() {
	var valueInput = document.querySelector('.inputMaxPrice').value;
	var valueInputMin = document.querySelector('.inputMinPrice').value;
	if ((valueInput >= 20) && (valueInput <= 2000)) {
		document.querySelector('.maxPrice').value = valueInput;
		document.querySelector('.minPrice').max = valueInput;
		if (valueInput < valueInputMin) {
			document.querySelector('.minPrice').max = valueInput;
			document.querySelector('.minPrice').value = valueInput;
			document.querySelector('.inputMinPrice').value = valueInput;
		}
	} else {
		var max = 2000;
		var min = 20;
		document.querySelector('.inputMaxPrice').value = max;
		document.querySelector('.maxPrice').value = max;
		document.querySelector('.minPrice').min = min;
	}
}

function priceMin() {
	var valuerate = document.querySelector('.minPrice').value;
	document.querySelector('.inputMinPrice').value = valuerate;
	document.querySelector('.maxPrice').min = valuerate;
}

function priceMax() {
	var valuerate = document.querySelector('.maxPrice').value;
	document.querySelector('.inputMaxPrice').value = valuerate;
	document.querySelector('.minPrice').max = valuerate;
}

function dateStartClick() {
	var dateStart = document.querySelector('.dateStart').value;
	var dateEnd = document.querySelector('.dateEnd').value;
	var todayDate = new Date();
	var date = new Date(dateStart);
	var dateE = new Date(dateEnd);
	var curr_month = todayDate.getMonth() + 1;

	var curr_date2 = date.getDate();
	var curr_month2 = date.getMonth() + 1;
	var curr_year2 = date.getFullYear();

	var today = new Date(todayDate.getFullYear(), todayDate.getMonth(),
			todayDate.getDate()).valueOf();
	var dateOutput = ((String(todayDate.getFullYear()).length == 1) ? "0"
			+ todayDate.getFullYear() : todayDate.getFullYear())
			+ "-"
			+ ((String(curr_month).length == 1) ? "0" + curr_month : curr_month)
			+ "-"
			+ ((String(todayDate.getDate()).length == 1) ? "0"
					+ todayDate.getDate() : todayDate.getDate());
	var dateOutputEnd = ((String(date.getFullYear()).length == 1) ? "0"
			+ date.getFullYear() : date.getFullYear())
			+ "-"
			+ ((String(curr_month2).length == 1) ? "0" + curr_month2
					: curr_month2)
			+ "-"
			+ ((String(date.getDate()).length == 1) ? "0" + date.getDate()
					: date.getDate());

	if (date.valueOf() < today) {
		document.querySelector('.dateStart').value = dateOutput;
	} else {
		document.querySelector('.dateEnd').min = dateOutputEnd;
	}
	if (date.valueOf() >= dateE.valueOf()) {
		document.querySelector('.dateEnd').value = dateOutputEnd;
	}
}

function dateEndClick() {
	var dateStart = document.querySelector('.dateStart').value;
	var dateEnd = document.querySelector('.dateEnd').value;
	var dataS = new Date(dateStart);
	var dataE = new Date(dateEnd);
	var todayDate = new Date();
	var curr_month = dataS.getMonth() + 1;
	var todayStart = new Date(dataS.getFullYear(), dataS.getMonth(), dataS
			.getDate()).valueOf();
	var dateOutput = dataS.getFullYear()
			+ "-"
			+ ((String(curr_month).length == 1) ? "0" + curr_month : curr_month)
			+ "-"
			+ ((String(dataS.getDate()).length == 1) ? "0" + dataS.getDate()
					: dataS.getDate());
	if (dataE.valueOf() < todayStart.valueOf()) {
		document.querySelector('.dateEnd').value = dateOutput;
	}
}

function sendNewOrder() {
	var dateStart = document.querySelector('.dateStart').value;
	var dateEnd = document.querySelector('.dateEnd').value;
	if(!validateData(dateStart)) {
		var incorrectName = document.querySelector('.errorCheckIn').value;
		document.querySelector('.errorOrder').innerHTML = incorrectName;
		return;
	} 
	if(!validateData(dateEnd)) {
		var incorrectName = document.querySelector('.errorCheckOut').value;
		document.querySelector('.errorOrder').innerHTML = incorrectName;
		return;
	} 
	var todayDate = new Date();
	var curr_month = todayDate.getMonth() + 1;

	var dateOutput = ((String(todayDate.getFullYear()).length == 1) ? "0"
			+ todayDate.getFullYear() : todayDate.getFullYear())
			+ "-"
			+ ((String(curr_month).length == 1) ? "0" + curr_month : curr_month)
			+ "-"
			+ ((String(todayDate.getDate()).length == 1) ? "0"
					+ todayDate.getDate() : todayDate.getDate());

	var bed = document.querySelector('.bed').value;
	var person = document.querySelector('.person').value;
	var idTypeRoom = document.querySelector('.typeRoom').value;
	var date = new Date(dateStart);
	var curr_month2 = date.getMonth() + 1;
	var dateOutputStart = ((String(date.getFullYear()).length == 1) ? "0"
			+ date.getFullYear() : date.getFullYear())
			+ "-"
			+ ((String(curr_month2).length == 1) ? "0" + curr_month2
					: curr_month2)
			+ "-"
			+ ((String((date.getDate())).length == 1) ? "0"
					+ (date.getDate()) : (date.getDate()));
	var dateE = new Date(dateEnd);
	var curr_month3 = dateE.getMonth() + 1;
	var dateOutputEnd = ((String(dateE.getFullYear()).length == 1) ? "0"
			+ dateE.getFullYear() : dateE.getFullYear())
			+ "-"
			+ ((String(curr_month3).length == 1) ? "0" + curr_month3
					: curr_month3)
			+ "-"
			+ ((String((dateE.getDate())).length == 1) ? "0"
					+ (dateE.getDate()) : (dateE.getDate()));

	var params = 'action=createorder' + '&' + 'dateStart=' + dateOutputStart
			+ '&' + 'dateEnd=' + dateOutputEnd + '&' + 'bed=' + bed + '&'
			+ 'person=' + person + '&' + 'idTypeRoom=' + idTypeRoom;

	var messageErrorBed = document.querySelector('.messageErrorBed').value;
	var messageErrorPerson = document.querySelector('.messageErrorPerson').value;
	var firstOption = document.querySelector('.firstOption').value;
	var firstOptionType = document.querySelector('.firstOptionType').value;

	if (person == firstOption) {
		document.querySelector('.errorOrder').innerHTML = messageErrorPerson;
	} else {
		if (bed == firstOption) {
			document.querySelector('.errorOrder').innerHTML = messageErrorBed;
		} else {
			ajaxPostOrder(params);
			document.querySelector('.errorOrder').innerHTML = "";
			document.querySelector('.dateStart').value = dateOutput;
			document.querySelector('.dateEnd').value = dateOutput;
			document.querySelector('.bed').value = firstOption;
			document.querySelector('.person').value = firstOption;
			document.querySelector('.typeRoom').value = firstOptionType;
		}
	}

}

function validateData(data) {
	return (/[0-9]{4}-(0[1-9]|1[012])-(0[1-9]|1[0-9]|2[0-9]|3[01])/.test(data)); 
}

function ajaxPostOrder(params) {

	var request = new XMLHttpRequest();
	request.onreadystatechange = function() {
		if (request.readyState == 4 && request.status == 200) {
			var response = request.responseText;
			switch (response) {
			case 'error': {
				logOut();
				document.querySelector('.windowLogIn').style.display = "block";
				loginCallBack = function() {
					ajaxPostOrder(params);
				}
				break;
			}
			case 'errordata': {
				var error = document.querySelector('.messageErrorData').value;
				document.querySelector('.errorOrder').innerHTML = error;
				break;
			}
			case 'OK': {
				document.querySelector('.message').innerHTML = document
						.querySelector('.orderOk').value;
				document.querySelector('.windowMessage').style.display = "block";
				break;
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

