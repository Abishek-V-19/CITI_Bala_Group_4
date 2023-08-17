import React, { useState } from "react";
import Chart from 'chart.js/auto';
import ChartValues from "./ChartValues";
 
let myChart = null;
function Charts({data}){
    console.log(data);
    const totalRecords = data.totalRecords == null?0:data.totalRecords;
    const [chart,setChart]=useState("");
    const clear =()=>{
        myChart.data.labels=[];
        myChart.data.datasets.data=[];
        myChart.update();
    };
    const updateChart =(event)=>{
        const {value} = event.target;
        if(value ==="null"){
            clear();
        }
        else if(Object.keys(data).length !== 0){
            const arr = data[value];
            setChart(value);
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
                legend: { display: true },
                title: {
                    display: true,
                    text: 'Country Sales'
                    }
                }
            })
            }
    }
        if(Object.keys(data).length !== 0){
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
        <div>
        <br></br>
        <h3>Total Records - {totalRecords}</h3> 
        <div class="chart-container">
            <br></br>
            <div id="charts">
                <h2>Data Visualisations</h2>
            <select id="chartOptions" onChange={updateChart}>
                <option value="null">Select an option</option>
                <option value="gender">Gender</option>
                <option value="category">Category</option>
                <option value="merchant">Merchant</option>
                <option value="city">City</option>
                <option value="state">State</option>
                <option value="profession">Profession</option>
            </select>
            </div>
            <canvas id="ordersChart"></canvas>
            {chart &&
            <h3>{chart}</h3>
            }
            
            <ChartValues prop={data[chart]}/>
        </div>
        </div>
    )
}
export default Charts;