import React, { useState } from "react";
import {categories,professions,merchants,city ,states} from "./filterOptions"

function Transaction() {
  const [transaction, setTransaction] = useState({
    pageno:0,
    pagesize:10,
    gender: "null",
    category: "null",
    merchant:"null",
    city:"null",
    state:"null",
    lower:-1,
    upper:-1,
    profession: "null"
  });
  const handleSubmit = async (event) => {
    event.preventDefault();
    try {
      console.log(transaction);
      //transaction object will have query details..
      //API query to view transactions goes here..

      //After query is successfully completed, transaction table will be rendered..
    } catch (error) {
      console.error("Error:", error);
    }
  };
  const handleSelectChange = (event) => {
    const { name, value } = event.target;
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
          <input name="pagesize" type="number" key={10}></input>
          <p>entries</p>
        </div>
        <div>
          <p>Gender</p>
          <select name="gender" onChange={handleSelectChange}>
          <option value= "null">None</option>
            <option value="M">Male</option>
            <option value="F">Female</option>
          </select>
        </div>
        <div>
        <p>Category</p>
          <select name="category" onChange={handleSelectChange}>
            <option key = "null">None</option>
            {categories.map((option, index) => {
              return <option key={index}>{option}</option>;
            })}
          </select>
        </div>
        <div>
        <p>Merchant</p>
          <select name="merchant" onChange={handleSelectChange}>
            <option key = "null">None</option>
            {merchants.map((option, index) => {
              return <option key={index}>{option}</option>;
            })}
          </select>
        </div>
        <div>
        <p>City</p>
          <select name="city" onChange={handleSelectChange}>
            <option key = "null">None</option>
            {city.map((option, index) => {
              return <option key={index}>{option}</option>;
            })}
          </select>
        </div>
        <div>
        <p>State</p>
          <select name="state" onChange={handleSelectChange}>
            <option key = "null">None</option>
            {states.map((option, index) => {
              return <option key={index}>{option}</option>;
            })}
          </select>
        </div>
        <div>
        <p>Profession</p>
          <select name="profession" onChange={handleSelectChange}>
          <option key = "null">None</option>
            {professions.map((option, index) => {
              return <option key={index}>{option}</option>;
            })}
          </select>
        </div>
        <div>
        <p>Limit</p>
          <input name="lower" type="text" placeholder="Lower limit"></input>
          <input name="upper" type="text" placeholder="Upper limit"></input>
        </div>
        <button type="submit">Search</button>
      </form>
    </div>
  );
}

export default Transaction;
