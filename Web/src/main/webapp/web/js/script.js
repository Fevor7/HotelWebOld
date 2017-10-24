loginStatus = "none";
securityHeaderName = "SecurityHeader";
securityHeaderValue = "javathebest";

function closeWindowLogin() {
	document.querySelector('.windowLogIn').style.display = "none";
	document.querySelector('.errorLogin').innerHTML = "";
	document.querySelector('.loginLogIn').value = "";
	document.querySelector('.passLogIn').value = "";
	loginCallBack = '';
}

function showWindowLogin() {
	var hiddenField = document.querySelector('.fieldLOGIN').value;
	var login = document.querySelector('.login').innerText;
	if (document.querySelector('.windowLogIn').style.display != "block") {
		if (loginStatus == "none") {
			document.querySelector('.windowLogIn').style.display = "block";
		} else {
			showPersonalPage();
		}
	} else {
		document.querySelector('.windowLogIn').style.display = "none";
	}
	document.querySelector('.errorLogin').innerHTML = "";

}

function showWindowSignUp() {
	document.querySelector('.errorLogin').innerHTML = "";
	document.querySelector('.errorSignUp').innerHTML = "";
	document.querySelector('.windowSignUp').style.display = "block";
	document.querySelector('.loginLogIn').value = "";
	document.querySelector('.passLogIn').value = "";
}

function switchLang(leng) {
	var params = 'action=language' + '&' + 'value=' + leng;
	var request = new XMLHttpRequest();
	request.onreadystatechange = function() {
		if (request.readyState == 4 && request.status == 200) {
			location.reload();
		}
	}
	request.open('POST', 'Servlet');
	request.setRequestHeader(securityHeaderName, securityHeaderValue);
	request.setRequestHeader('Content-Type',
			'application/x-www-form-urlencoded');
	request.send(params);
}
