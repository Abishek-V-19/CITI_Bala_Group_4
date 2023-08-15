
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
