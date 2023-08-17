import React from "react";
import Stack from "@mui/material/Stack";
import TableCustomerDetailsDisplay from "./TableCustomerDetailsDisplay";


function CustomerDetailsDisplay({customer}){
    if(customer !== null){
        return (
        <Stack spacing={3} direction="column" sx={{ width: 500 }}>
        <div>Customer Details</div>
            <div><TableCustomerDetailsDisplay customer={customer}></TableCustomerDetailsDisplay></div>
        </Stack>)
    }
    else{
        return <h3>Customer not found</h3>
    }
}

export default CustomerDetailsDisplay