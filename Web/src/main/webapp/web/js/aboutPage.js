function showAboutPage(){
	var params = 'action=aboutpage';
	var url = 'Servlet?'+params;
	var request = new XMLHttpRequest();
	request.onreadystatechange = function() {
		if (request.readyState == 4 && request.status == 200) {
			var response = request.responseText;
			document.querySelector('.insertPage').innerHTML = request.responseText;
		}
	}
	request.open('GET', url, true);
	request.setRequestHeader(securityHeaderName,securityHeaderValue);
	request.setRequestHeader('Content-Type',
	'application/x-www-form-urlencoded');
	request.send('');
}