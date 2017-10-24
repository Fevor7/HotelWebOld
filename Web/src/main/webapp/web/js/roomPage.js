function showRoomPage(pageNumber) {
	var params = '?action=roompage';
	var url = 'Servlet' + params;
	var request = new XMLHttpRequest();
	request.onreadystatechange = function() {
		if (request.readyState == 4 && request.status == 200) {
			var response = request.responseText;

			document.querySelector('.insertPage').innerHTML = request.responseText;
			showRoomList(pageNumber);
		}
	}
	request.open('GET', url, true);
	request.setRequestHeader(securityHeaderName, securityHeaderValue);
	request.setRequestHeader('Content-Type',
			'application/x-www-form-urlencoded');
	request.send('');

}

function showRoomList(pageNumber) {
	var params = '?action=roomlist'+'&'+'pagenumber='+pageNumber;
	var url = 'Servlet' + params;
	var request = new XMLHttpRequest();
	request.onreadystatechange = function() {
		if (request.readyState == 4 && request.status == 200) {
			var response = request.responseText;
			document.querySelector('.insertRoomList').innerHTML = request.responseText;
		}
	}
	request.open('GET', url, true);
	request.setRequestHeader(securityHeaderName, securityHeaderValue);
	request.setRequestHeader('Content-Type',
			'application/x-www-form-urlencoded');
	request.send('');
}

function searchForRoom(pageNumber) {
	document.querySelector('.errorOrder').innerHTML = "";
	var dateStart = document.querySelector('.dateStart').value;
	var dateEnd = document.querySelector('.dateEnd').value;
	var todayDate = new Date();
	var curr_month = todayDate.getMonth() + 1;
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
	var dateOutput = ((String(todayDate.getFullYear()).length == 1) ? "0"
			+ todayDate.getFullYear() : todayDate.getFullYear())
			+ "-"
			+ ((String(curr_month).length == 1) ? "0" + curr_month : curr_month)
			+ "-"
			+ ((String(todayDate.getDate()).length == 1) ? "0"
					+ todayDate.getDate() : todayDate.getDate());

	var bed = document.querySelector('.bed').value;
	var person = document.querySelector('.person').value;
	var minPrice = document.querySelector('.inputMinPrice').value;
	var maxPrice = document.querySelector('.inputMaxPrice').value;
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

	var idTypeRoom = document.querySelector('.typeRoom').value;
	
	var params = '?action=roomsearch' + '&' + 'dateStart=' + dateOutputStart
			+ '&' + 'dateEnd=' + dateOutputEnd + '&' + 'bed=' + bed + '&'
			+ 'person=' + person + '&' + 'minPrice=' + minPrice + '&'
			+ 'maxPrice=' + maxPrice + '&' + 'idTypeRoom=' + idTypeRoom+'&'+'pagenumber='+pageNumber;

	ajaxPostSearchRoom(params);

}

function validateData(data) {
	return (/[0-9]{4}-(0[1-9]|1[012])-(0[1-9]|1[0-9]|2[0-9]|3[01])/.test(data)); 
}
function ajaxPostSearchRoom(params) {
	var url = 'Servlet' + params;
	var request = new XMLHttpRequest();
	request.onreadystatechange = function() {
		if (request.readyState == 4 && request.status == 200) {
			var response = request.responseText;
			switch (response) {
			case 'error': {
				var error = document.querySelector('.messageRoomNotFound').value;
				document.querySelector('.insertRoomList').style.color = "white";
				document.querySelector('.insertRoomList').innerHTML = error;
				break;
			}
			case 'errordata': {
				var error = document.querySelector('.incorrectDataError').value;
				document.querySelector('.errorOrder').innerHTML = error;
				break;
			}
			default: {
				var response = request.responseText;
				document.querySelector('.insertRoomList').style.color = "black";
				document.querySelector('.insertRoomList').innerHTML = request.responseText;
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
