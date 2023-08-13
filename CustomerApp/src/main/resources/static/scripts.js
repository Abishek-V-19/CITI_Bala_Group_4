function search(){
	document.getElementById("pageNo").textContent="1";
	getItems();
}
function getItems() {
	const url = getFilters();
	// const url = "/Transactions/pages?pageno=0&size=10&gender="+val["gender"]+"&category=null&merchant=null&city=null&state=null&transactionAmountLower=-1&transactionAmountUpper=-1&profession=null";
	fetch(url)//promise object to return data from Rest API
		.then(response => { return response.json(); }) //resolve , data from resolve is passed to next then
		.then(items => {
			if (items.transaction.length > 0) {
				var temp ="";
				items.transaction.forEach((itemData) => {
					temp += "<tr>";
					temp += "<td>" + itemData.transactionId + "</td>";
					temp += "<td>" + itemData.transactionDateTime + "</td>";
					temp += "<td>" + itemData.transactionAmount + "</td>";
					temp += "<td>" + itemData.firstName + " "+itemData.lastName + "</td>";
					temp += "<td>" + itemData.gender + "</td>";
					temp += "<td>" + itemData.job + "</td>";
					temp += "<td>" + itemData.category + "</td>";
					temp += "<td>" + itemData.merchant + "</td>";
					temp += "<td>" + itemData.city + "</td>";
					temp += "<td>" + itemData.state + "</td>";
					temp+= "</tr>"
				});
				document.getElementById('tableItems').innerHTML = temp;
			}
		});
}
function getFilters(){
	var url = "/Transactions/pages?";
	var val = {};
	val["pageno"] = (parseInt(document.getElementById("pageNo").textContent)-1).toString() ;
	val["pagesize"]=document.getElementById("pagesize").value;
	val["gender"]= document.getElementById("gender").value;
	val["category"]= document.getElementById("category").value;
	val["merchant"]= document.getElementById("merchant").value;
	val["city"]= document.getElementById("city").value;
	val["state"]= document.getElementById("state").value;
	val["lower"]= document.getElementById("lower").value;
	val["upper"]= document.getElementById("upper").value;
	val["profession"]= document.getElementById("profession").value;
	if(val["upper"]=="" || val["lower"]==""){
		val["upper"] = "-1";
		val["lower"] = "-1";
	}
	for(let key in val){
		if(val[key]==""){
			val[key] = "null"
		}
	}
	
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
function nextPage(){
	let pageno = parseInt(document.getElementById("pageNo").textContent);
	document.getElementById("pageNo").textContent = (parseInt(pageno)+1).toString();
	getItems();
}
function prevPage(){
	let pageno = parseInt(document.getElementById("pageNo").textContent);
	if(pageno>1){
		document.getElementById("pageNo").textContent = (parseInt(pageno)-1).toString();
		getItems();
	}
}
function test(){
	document.getElementById('transactions').innerHTML = "<h3>check</h3>";
}
