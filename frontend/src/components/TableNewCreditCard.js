import React from "react";
import Stack from "@mui/material/Stack";

function TableNewCreditCard({customer}){
    
    if(customer !== null){
    return(
        <div>
        <div></div>
        <div id="transactions" class="table-responsive">
            <table id="items" class="table table-hover table-bordered table-info">
                <thead class="table-dark">
                    <tr>
                        <th>Details</th>
                        <th>Values</th>
                    </tr>
                </thead>
                <tbody id="tableItems" >
                        <tr>
         					<td>Customer id</td>
         					<td>{customer.customerId}</td>
                        </tr>
                        <tr>
         					<td>Credit card number</td>
         					<td>{customer.cardNumber}</td>
                        </tr>
                        <tr>
                            <td>Status</td>
         					<td>{customer.status}</td>
                        </tr>
                        <tr>
                            <td>Description</td>
         					<td>{customer.description}</td>
                        </tr>
                        
 					
                </tbody>
    </table>
    
    </div>
    </div>
    )
    }
    else{
        return (<div>
        <h3>Customer Not Found</h3>
        </div>
        )
    }
}
export default TableNewCreditCard;