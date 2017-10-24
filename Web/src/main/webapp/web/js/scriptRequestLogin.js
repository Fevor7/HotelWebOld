loginCallBack = '';

function pressEnter(event) {
	if (!event.shiftKey && event.keyCode == 13)
		logIn();
}
function logOut() {
	var hiddenField = document.querySelector('.fieldLOGIN').value;
	document.querySelector('.admin').style.display = "none";
	var params = 'action=logout';
	var request = new XMLHttpRequest();
	request.onreadystatechange = function() {
		if (request.readyState == 4 && request.status == 200) {
			if (request.responseText == "OK") {
				showFirstPage();
				loginStatus = "none";
				document.querySelector('.exit').style.display = "none";
				document.querySelector('.login').innerText = hiddenField;
				document.querySelector('.exit').style.display = "none";
				document.querySelector('.admin').style.display = "none";
			}
		}
	}
	request.open('POST', 'Servlet');
	request.setRequestHeader(securityHeaderName, securityHeaderValue);
	request.setRequestHeader('Content-Type',
			'application/x-www-form-urlencoded');
	request.send(params);
}
function logIn() {
	document.querySelector('.errorLogin').innerHTML = "";
	var hiddenField3 = document.querySelector('.fillAllFields').value;
	var log = document.querySelector('.loginLogIn');
	var pass = document.querySelector('.passLogIn');
	var params = 'action=login' + '&' + 'login=' + log.value + '&' + 'pass='
			+ pass.value;
	if (isEmpty(log.value, pass.value))
		document.querySelector('.errorLogin').innerHTML = hiddenField3;
	else {
		ajaxPost(params);
	}
	document.querySelector('.loginLogIn').value = "";
	document.querySelector('.passLogIn').value = "";
}

function isEmpty(str, str2) {
	return (str == null) || (str.length == 0) || (str2 == null)
			|| (str2.length == 0);
}

function ajaxPost(params) {
	var request = new XMLHttpRequest();

	request.onreadystatechange = function() {
		if (request.readyState == 4 && request.status == 200) {
			access(request.responseText);
		}
	}
	request.open('POST', 'Servlet');
	request.setRequestHeader(securityHeaderName, securityHeaderValue);
	request.setRequestHeader('Content-Type',
	'application/x-www-form-urlencoded');
	request.send(params);
}

function access(response) {
	var hiddenField2 = document.querySelector('.accessDenied').value;
	var attribute = response.substring(0, 5);
	var attributAdmin = response.substring(0, 4);
	if (attributAdmin == 'true') {
		loginStatus = response.substring(4);
		document.querySelector('.windowLogIn').style.display = "none";
		document.querySelector('.login').innerText = loginStatus;
		document.querySelector('.exit').style.display = "block";
		document.querySelector('.errorLogin').innerHTML = "";
		document.querySelector('.admin').style.display = "block";
		if (typeof loginCallBack == 'function') {
			loginCallBack();
		}
		;
		loginCallBack = '';

	}
	switch (attribute) {
	case 'error': {
		document.querySelector('.errorLogin').innerHTML = hiddenField2;
		break;
	}
	case 'false': {
		loginStatus = response.substring(5);
		document.querySelector('.windowLogIn').style.display = "none";
		document.querySelector('.login').innerText = loginStatus;
		document.querySelector('.exit').style.display = "block";
		document.querySelector('.errorLogin').innerHTML = "";

		if (typeof loginCallBack == 'function') {
			loginCallBack();
		}
		;
		loginCallBack = '';

		break;
	}

	default: {

	}
	}
}