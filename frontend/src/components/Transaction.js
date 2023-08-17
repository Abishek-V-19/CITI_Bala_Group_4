import React, { useState } from "react";
import Filters from "./Filters";

function Transaction(){
    return(
        <div>
            <h2 className="heading">TRANSACTION HISTORY </h2>
            <Filters/>
        </div>
    )
}
export default Transaction;