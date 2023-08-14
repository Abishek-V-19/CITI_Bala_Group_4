import React, { useState } from "react";

const genderOptions = ["None", "Male", "Female"];
const categoryOptions = [
  "None",
  "gas_transport",
  "shopping_pos",
  "misc_pos",
  "shopping_net",
];
const professionOptions = [
  "None",
  "Media planner",
  "Ambulance person",
  "Podiatrist",
  "Architectural technologist",
  "Energy engineer",
];

function Transaction() {
  const [transaction, setTransaction] = useState({
    gender: "None",
    category: "None",
    profession: "None",
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
          <select name="gender" onChange={handleSelectChange}>
            {genderOptions.map((option, index) => {
              return <option key={index}>{option}</option>;
            })}
          </select>
        </div>
        <div>
          <select name="category" onChange={handleSelectChange}>
            {categoryOptions.map((option, index) => {
              return <option key={index}>{option}</option>;
            })}
          </select>
        </div>
        <div>
          <select name="profession" onChange={handleSelectChange}>
            {professionOptions.map((option, index) => {
              return <option key={index}>{option}</option>;
            })}
          </select>
        </div>
        <button type="submit">Search</button>
      </form>
    </div>
  );
}

export default Transaction;
