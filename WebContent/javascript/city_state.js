var states = Object();

states['Andhra Pradesh'] = 'Visakhapatnam|Vijayawada|Guntur|Nellore';
states['Punjab'] = 'Ludhiana|Amritsar|Patiala|Chandigarh';
states['Gujarat'] = 'Ahmedabad|Surat|Vadodara|Rajkot';
states['Maharashtra'] = 'Mumbai |Pune|Nagpur|Thane';

// //////////////////////////////////////////////////////////////////////////

var cityDropDown = Object();

// india
cityDropDown['Visakhapatnam'] = '531022|531032|531036|531072|531052|535103';
cityDropDown['Vijayawada'] = '520021|520325|520434|520061|520098|520675';
cityDropDown['Guntur'] = '522101|522111|522132|522154|522173|522184';
cityDropDown['Nellore'] = '522345|522366|522312|522315|522310' | '522319';
cityDropDown['Ludhiana'] = '141109|141190|141119|141129|141766|141187';
cityDropDown['Amritsar'] = '141023|141134|141145|141156|141165|141182';
cityDropDown['Patiala'] = '141122|141133|141144|141155|141188|141118';
cityDropDown['Chandigarh'] = '411047|411048|411049|411017|411016|411019';
cityDropDown['Ahmedabad'] = '382120|382112|382113|382114|382115|382116';
cityDropDown['Surat'] = '382145|382121|382122|382123|382124|382126';
cityDropDown['Vadodara'] = '382131|382132|382133|382134|382135|382136';
cityDropDown['Rajkot'] = '382133|382134|382153|382137|382139|382199';
cityDropDown['Mumbai'] = '440043|440054|440076|440003|440018|440026';
cityDropDown['Nagpur'] = '440021|440045|440009|440222|440333|440542';
cityDropDown['Pune'] = '411024|411234|411065|411054|411555|416543';
cityDropDown['Thane'] = '400708|400732|400766|400777|400798|400709';

// /////////////////

function setRegions() {
	for (region in states)
		document
				.write('<option value="' + region + '">' + region + '</option>');
}

function setState(oRegionSel, state, city) {
	var countryArr;
	state.length = 0;
	city.length = 0;
	var region = oRegionSel.options[oRegionSel.selectedIndex].text;
	if (states[region]) {
		state.disabled = false;
		city.disabled = true;
		state.options[0] = new Option('SELECT City', '');
		countryArr = states[region].split('|');
		for (var i = 0; i < countryArr.length; i++)
			state.options[i + 1] = new Option(countryArr[i], countryArr[i]);
		document.getElementById('txtregion').innerHTML = region;
		document.getElementById('txtplacename').innerHTML = '';
	} else
		state.disabled = true;
}

function zipCode(state, city) {
	var cityStateArr;
	city.length = 0;
	var states = state.options[state.selectedIndex].text;
	if (cityDropDown[states]) {
		city.disabled = false;
		city.options[0] = new Option('SELECT ZIP Code', '');
		cityStateArr = cityDropDown[states].split('|');
		for (var i = 0; i < cityStateArr.length; i++)
			city.options[i + 1] = new Option(cityStateArr[i], cityStateArr[i]);
		document.getElementById('txtplacename').innerHTML = states;
	} else
		city.disabled = true;
}
