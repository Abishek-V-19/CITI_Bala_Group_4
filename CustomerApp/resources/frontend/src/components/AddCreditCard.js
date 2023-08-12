import React, { useState } from "react";

function AddCreditCard() {
  const [postData, setPostData] = useState({
    customerId: 0,
  });

  const [renderResponse, setRenderResponse] = useState(false);
  const [renderData, setRenderData] = useState({});

  const handleInputChange = (event) => {
    const { name, value } = event.target;
    setPostData((prevData) => ({
      ...prevData,
      [name]: value,
    }));
  };

  const handleSubmit = async (event) => {
    event.preventDefault();

    const url = "http://localhost:8080/CreditCard/add";

    try {
      console.log(postData);
      const response = await fetch(url, {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify(postData),
      });

      const data = await response.json();
      console.log(data);
      setRenderResponse(true);
      setRenderData((prevData) => ({
        ...prevData,
        ...data,
      }));
    } catch (error) {
      console.error("Error:", error);
    }
  };

  return (
    <div>
      <h1>Add credit card</h1>
      <form onSubmit={handleSubmit}>
        <div>
          <label>Enter customer id:</label>
          <input
            type="number"
            name="customerId"
            value={postData.title}
            onChange={handleInputChange}
          />
        </div>
        <button type="submit">Submit</button>
      </form>
      {renderResponse && (
        <div>
          <div>
            Credit card added successfully for customer id:{" "}
            {renderData.customerId}
          </div>
          <div>Credit card number: {renderData.cardNumber} </div>
          <div>Status: {renderData.status} </div>
        </div>
      )}
    </div>
  );
}

export default AddCreditCard;
