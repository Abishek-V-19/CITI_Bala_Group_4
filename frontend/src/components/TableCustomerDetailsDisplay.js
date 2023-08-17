import React from "react";
import Stack from "@mui/material/Stack";

function TableCustomerDetailsDisplay({customer}){
    
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
         					<td>Customer Name</td>
         					<td>{customer.firstName + " "+customer.lastName}</td>
                        </tr>
                        <tr>
                            <td>Gender</td>
         					<td>{customer.gender}</td>
                        </tr>
                        <tr>
                            <td>Profession</td>
         					<td>{customer.job}</td>
                        </tr>
                        <tr>
                            <td>DOB</td>
         					<td>{customer.dob}</td>
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
export default TableCustomerDetailsDisplay;