// function addOptions(arr,opt){
// 	temp = '<option value="null">None</option>';
// 	for(item of arr){
// 		temp+=`<option value="${item}">${item}</option>`;
// 	}
// 	document.getElementById(opt).innerHTML = temp;
// }
// function loadFilterOptions(){
// 	addOptions(categories,"category");
// 	addOptions(merchants,"merchant");
// 	addOptions(city,"city");
// 	addOptions(states,"state");
// 	addOptions(professions,"profession");
// }
// loadFilterOptions();
// function search(){
// 	document.getElementById("pageNo").textContent="1";
// 	getItems();
// }
// function getItems() {
// 	const url = getFilters();
// 	// const url = "/Transactions/pages?pageno=0&size=10&gender="+val["gender"]+"&category=null&merchant=null&city=null&state=null&transactionAmountLower=-1&transactionAmountUpper=-1&profession=null";
// 	fetch(url)//promise object to return data from Rest API
// 		.then(response => { return response.json(); }) //resolve , data from resolve is passed to next then
// 		.then(items => {
// 			var temp ="";
// 			if (items.transaction.length > 0) {
// 				items.transaction.forEach((itemData) => {
// 					temp += "<tr>";
// 					temp += "<td>" + itemData.transactionId + "</td>";
// 					temp += "<td>" + itemData.transactionDateTime + "</td>";
// 					temp += "<td>" + itemData.transactionAmount + "</td>";
// 					temp += "<td>" + itemData.firstName + " "+itemData.lastName + "</td>";
// 					temp += "<td>" + itemData.gender + "</td>";
// 					temp += "<td>" + itemData.job + "</td>";
// 					temp += "<td>" + itemData.category + "</td>";
// 					temp += "<td>" + itemData.merchant + "</td>";
// 					temp += "<td>" + itemData.city + "</td>";
// 					temp += "<td>" + itemData.state + "</td>";
// 					temp+= "</tr>"
// 				});
// 			}
// 			document.getElementById('tableItems').innerHTML = temp;
// 		});
// }
export function getFilters(val){
	let url = "http://localhost:8080/Transactions/pages?";
	if(val.upper=="" || val.lower==""){
		val.upper = "-1";
		val.lower = "-1";
	}
	// for(let key in val){
	// 	if(val.key==""){
	// 		val.key= "null"
	// 	}
	// }
	console.log(val.merchant);
	url+="pageno="+val["pageno"];
	url+="&size="+val["pagesize"];
	url+="&gender="+ val["gender"];
	url+="&category="+val["category"];
	url+="&merchant="+val["merchant"];
	url+="&city="+val["city"];
	url+="&state="+val["state"];
	url+="&transactionAmountLower="+val["lower"];
	url+="&transactionAmountUpper="+val["upper"];
	url+="&profession="+val["profession"];
	return url;
}
// function nextPage(){
// 	let pageno = parseInt(document.getElementById("pageNo").textContent);
// 	document.getElementById("pageNo").textContent = (parseInt(pageno)+1).toString();
// 	getItems();
// }
// function prevPage(){
// 	let pageno = parseInt(document.getElementById("pageNo").textContent);
// 	if(pageno>1){
// 		document.getElementById("pageNo").textContent = (parseInt(pageno)-1).toString();
// 		getItems();
// 	}
// }
// function test(){
// 	document.getElementById('transactions').innerHTML = "<h3>check</h3>";
// }
