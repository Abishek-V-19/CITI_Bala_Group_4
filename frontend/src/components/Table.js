import React from "react";

function Table({transactions}){
    console.log(transactions.transaction);
    const arr = transactions.transaction;
    return(
        <div id="transactions" class="table-responsive">
            <table id="items" class="table table-hover table-bordered table-info">
                <thead class="table-dark">
                    <tr >
                        <th>Transaction Id</th>
                        <th>TransactionDateTime</th>
                        <th>Transaction Amount</th>
                        <th>Name</th>
                        <th>Gender</th>
                        <th>Job</th>
                        <th>Category</th>
                        <th>Merchant</th>
                        <th>City</th>
                        <th>State</th>
                    </tr>
                </thead>
                <tbody id="tableItems" >
                    {arr && arr.map(option => {
                    return (
                        <tr>
         					<td>{option.transactionId}</td>
         					<td>{option.transactionDateTime}</td>
         					<td>{option.transactionAmount}</td>
         					<td>{option.firstName} {option.lastName}</td>
         					<td>{option.gender}</td>
         					<td>{option.job}</td>
         					<td>{option.category}</td>
         					<td>{option.merchant}</td>
         					<td>{option.city}</td>
         					<td>{option.state}</td>
 					    </tr>
                    );
                    })}
                </tbody>
    </table>
    </div>
    )
}
export default Table;