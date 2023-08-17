import React from "react";
import Stack from "@mui/material/Stack";

function TableAddCreditCard({arr},{customer}){
    
    if(arr && arr.length !== 0){
    return(
        <Stack spacing={3} direction="column" sx={{ width: 500 }}>
        <div>Credit Cards</div>
        <div>
        
        <div id="transactions" class="table-responsive">
            <table id="items" class="table table-hover table-bordered table-info">
                <thead class="table-dark">
                    <tr >
                        <th>CreditCard Number</th>
                        <th>Description</th>
                        <th>Current Status</th>
                    </tr>
                </thead>
                <tbody id="tableItems" >
                    {arr && arr.map(option => {
                    return (
                        <tr>
         					<td>{option.cardNumber}</td>
         					<td>{option.description}</td>
                             <td>{option.status}</td>
 					    </tr>
                    );
                    })}
                </tbody>
    </table>
    
    </div>
    </div>
    </Stack>)
    }
    else{
        return (<div>
        <h3>No Credit Cards found</h3>
        </div>
        )
    }
}
export default TableAddCreditCard;