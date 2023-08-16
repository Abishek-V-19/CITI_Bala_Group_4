import React, { useState } from "react";
import Chart from 'chart.js/auto';
import { List } from "@mui/material";
 
let myChart = null;
function Charts({data}){
    const clear =()=>{
        myChart.data.labels=[];
        myChart.data.datasets.data=[];
        myChart.update();
    };
    const updateChart =(event)=>{
        const {value} = event.target;
        if(Object.keys(data).length != 0){
            const arr = data[value];
            console.log(arr);
            let labels = [];
            let values = [];
            arr.forEach(value => {
                labels.push(value._id);                    
                values.push(value.amount); 
            });
            myChart.destroy();
            myChart = new Chart("ordersChart", {
                type: "bar",
                data: {
                    labels: labels,
                    datasets: [
                        {
                            label: "Total transaction amount",
                            data: values,
                    borderColor: '#36A2EB',
                    backgroundColor: '#3393FF'
                }
            ]
            },
            options: {
                legend: { display: false },
                title: {
                    display: true,
                    text: 'Country Sales'
                    }
                }
            })
            }
    }
        if(Object.keys(data).length != 0){
            const arr = data.gender;
            let labels = [];
            let values = [];
            arr.forEach(value => {
                labels.push(value._id);                    
                values.push(value.amount); 
            });
            if(!myChart){
                myChart = new Chart("ordersChart", {
                    type: "bar",
                    data: {
                        labels: labels,
                        datasets: [
                            {
                                label: "Total transaction amount",
                                data: values,
                        borderColor: '#36A2EB',
                        backgroundColor: '#3393FF'
                    }
                ]
                },
                options: {
                    legend: { display: false },
                    title: {
                        display: true,
                        text: 'Country Sales'
                        }
                    }
                })
            }
    }

    return(
        <div class="chart-container">
            <select id="chartOptions" onChange={updateChart}>
                <option value="gender">Gender</option>
                <option value="category">Category</option>
                <option value="merchant">Merchant</option>
                <option value="city">City</option>
                <option value="state">State</option>
                <option value="profession">Profession</option>
            </select>
            <canvas id="ordersChart"></canvas>
            <button onClick={clear}>clear</button>
        </div>
    )
}
export default Charts;