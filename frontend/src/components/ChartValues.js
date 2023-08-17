import React, { useState } from "react";

function ChartValues({prop}){
    console.log(prop);
    if(prop && prop.length != 0){
return(
         <div id="summary" class="table-responsive">
            <table id="itemsC" class="table table-hover table-bordered table-info">
                <thead class="table-dark">
                    <tr >
                        <th>Type</th>
                        <th>Amount</th>
                    </tr>
                </thead>
                <tbody id="tableItemsC" >
                    {prop && prop.map(option => {
                    return (
                        <tr>
         					<td>{option._id}</td>
         					<td>{option.amount}</td>
 					    </tr>
                    );
                    })}
                </tbody>
    </table>
    </div>
)
}
}
export default ChartValues;

