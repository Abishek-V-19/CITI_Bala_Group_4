import React, { useState } from "react";
import {categories,professions,merchants,city ,states} from "./filterOptions";
import {getFilters} from './scripts';
function Filters() {
  const [transaction, setTransaction] = useState({
    pageno:"0",
    pagesize:"10",
    gender: "null",
    category: "null",
    merchant:"null",
    city:"null",
    state:"null",
    lower:"",
    upper:"",
    profession: "null"
  });
  const handleSubmit = async (event) => {
    event.preventDefault();
    const url = getFilters(transaction);
    console.log(url);
    try {
      console.log(transaction);
      //transaction object will have query details..
      //API query to view transactions goes here..
      const response = await fetch(url).then(response => { return response.json(); }) 
      //After query is successfully completed, transaction table will be rendered..
      console.log(response);
    } catch (error) {
      console.error("Error:", error);
    }
  };
  const handleChange = (event) => {
    const { name, value } = event.target;
    console.log(name,value);
    setTransaction((prevData) => ({
      ...prevData,
      [name]: value,
    }));
  };
  return (
    <div>
      <form onSubmit={handleSubmit}>
      <div>
        <p>Show</p>
          <input name="pagesize" type="number" value={transaction.pagesize} onChange={handleChange}></input>
          <p>entries</p>
        </div>
        <div>
          <p>Gender</p>
          <select name="gender" onChange={handleChange}>
          <option value= "null">None</option>
            <option value="M">Male</option>
            <option value="F">Female</option>
          </select>
        </div>
        <div>
        <p>Category</p>
          <select name="category" onChange={handleChange}>
            <option value = "null">None</option>
            {categories.map((option, index) => {
              return <option key={index}>{option}</option>;
            })}
          </select>
        </div>
        <div>
        <p>Merchant</p>
          <select name="merchant" onChange={handleChange}>
            <option value = "null">None</option>
            {merchants.map((option, index) => {
              return <option key={index}>{option}</option>;
            })}
          </select>
        </div>
        <div>
        <p>City</p>
          <select name="city" onChange={handleChange}>
            <option value = "null">None</option>
            {city.map((option, index) => {
              return <option key={index}>{option}</option>;
            })}
          </select>
        </div>
        <div>
        <p>State</p>
          <select name="state" onChange={handleChange}>
            <option value = "null">None</option>
            {states.map((option, index) => {
              return <option key={index}>{option}</option>;
            })}
          </select>
        </div>
        <div>
        <p>Profession</p>
          <select name="profession" onChange={handleChange}>
          <option value = "null">None</option>
            {professions.map((option, index) => {
              return <option key={index}>{option}</option>;
            })}
          </select>
        </div>
        <div>
        <p>Limit</p>
          <input name="lower" type="text" placeholder="Lower limit" value={transaction.lower} onChange={handleChange}></input>
          <input name="upper" type="text" placeholder="Upper limit" value={transaction.upper} onChange={handleChange}></input>
        </div>
        <button type="submit">Search</button>
      </form>
    </div>
  );
}

export default Filters;
