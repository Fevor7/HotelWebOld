idStatusOrderVar = '';
nameUser = '';
surnameUser = '';

function session(name, surname, role, page, orderStatus) {
	idStatusOrderVar = orderStatus;
	nameUser = name;
	surnameUser = surname;
	var hiddenField = document.querySelector('.fieldLOGIN').value;
	if (name == "") {
		document.querySelector('.login').innerText = hiddenField;
	} else {
		loginStatus = name;
		document.querySelector('.login').innerText = name;
		document.querySelector('.exit').style.display = "block";
		if (role == 'true') {
			document.querySelector('.admin').style.display = "block";
		}
	}
	switch (page) {
	case 'personalpage': {
		showPersonalPage();
		break;
	}
	case 'orderlist': {
		showOrderListPage();
		break;
	}
	case 'firstpage': {
		showFirstPage();
		break;
	}
	case 'aboutpage': {
		showAboutPage();
		break;
	}
	case 'roompage': {
		showRoomPage(0);
		break;
	}
	case 'orderlistadmin' : {
		if (!orderStatus=='') {
			showOrderListAdmin(orderStatus);
		} else {
			showOrderListAdmin('1');
		}
		
		break;
	}
	default: {
		showFirstPage();
		break;
	}
	}
	
	document.querySelector('.closechar').innerHTML = "&#10006";
	document.querySelector('.closeWindowLogin').innerHTML = "&#10006";
	
	var body = $(document);
	var menu = $(".menu");

	body.on("scroll", function(e) {
	    
	  if (this.scrollingElement.scrollTop > 90) {
		  menu.addClass("fixed");
	  } else {
		  menu.removeClass("fixed");
	  }
	  
	});
}

function showOrderListAdminEdit(){
	showOrderListAdmin(idStatusOrderVar);
}

function sessionDestoy() {
	var params = 'action=sessionDestoy';
	var request = new XMLHttpRequest();
	request.onreadystatechange = function() {
		if (request.readyState == 4 && request.status == 200) {			
		}
	}
	request.open('POST', 'Servlet');
	request.setRequestHeader(securityHeaderName,securityHeaderValue);
	request.setRequestHeader('Content-Type',
	'application/x-www-form-urlencoded');
	request.send(params);

}

function  selectPage(command, numberPage) {
	switch (command) {
	case 'roomList' : {
		showRoomPage(numberPage);
		break;
	}
	case 'roomsearch' : {
		searchForRoom(numberPage);
		break;
	}
	case 'roomsearchadmin' : {
		searchForRoomAdmin(numberPage);
		break;
	}
	case 'orderListUser' : {
		showUserOrderList(numberPage);
		break;
	}
	case 'orderList' : {
		showOrderListPage(orderStatus, numberPage);
		break;
	}
	
	
	}
}
