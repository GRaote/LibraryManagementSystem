function validateModify() {
	var count1 = document.forms["modify1"]["bookCount"].value;

	var count2 = document.forms["modify1"]["copyCount"].value;

	if (count1 < count2) {
		alert("You can't reduce the number of copies");
		return false;
	}
}
function onDeleteBook() {
	var book = document.forms
}
function validateEmail() {
	var str = document.forms["IssueForm"]["emailID"].value;
	var r = /^([A-Za-z0-9_\-\.])+\@([A-Za-z0-9_\-\.])+\.([A-Za-z]{2,4})$/;
	if (r.test(str) == false) {
		alert("Please Enter an Valid EmailID (Eg:sam@xxx.com)");
	}
}
function nospaces(t) {

	if (t.value.match(/\s/g)) {

		alert('Sorry, you are not allowed to enter any spaces');

		t.value = t.value.replace(/\s/g, '');

	} else if (t.value == null) {
		alert('Please fill the text box');

	}

}
//
// function validateDropdown() {
//
// var e = document.getElementById("ddlView");
// var e1 = document.getElementById("ddlstate");
// var e2 = document.getElementById("ddlcity");
// var strUser = e.options[e.selectedIndex].value;
// var strUser2 = e1.options[e1.selectedIndex].value;
// var strUser4 = e2.options[e2.selectedIndex].value;
// // if you need text to be compared then use
// var strUser1 = e.options[e.selectedIndex].text;
// var strUser3 = e1.options[e1.selectedIndex].text;
// if (strUser == 0 || strUser2 == 0 || strUser4 == 0) // for text use
// // if(strUser1=="Select")
// {
// alert("Please select the value from dropdown");
// }
// }

function limitText(limitField, limitCount, limitNum) {
	if (limitField.value.length > limitNum) {
		limitField.value = limitField.value.substring(0, limitNum);
	} else {
		limitCount.value = limitNum - limitField.value.length;
	}
}
function resetFunction1() {

	document.getElementById("currentForm1").reset();
}
function resetFunction() {
	document.getElementById("currentForm").reset();
}

function validateEmail() {
	var str = document.forms["adduser"]["emailID"].value;
	var r = /^([A-Za-z0-9_\-\.])+\@([A-Za-z0-9_\-\.])+\.([A-Za-z]{2,4})$/;
	if (r.test(str) == false) {
		alert("Please Enter an Valid EmailID (Eg:sam@xxx.com)");
	}
}
