import React, { useState } from "react";
import axios from "axios";

function DeleteCreditCard() {
  const [putData, setPutData] = useState({
    customerId: 0,
    cardNumber: "",
  });
  const [renderResponse, setRenderResponse] = useState(false);
  const [responseMessage, setResponseMessage] = useState("");
  const handleInputChange = (event) => {
    const { name, value } = event.target;
    setPutData((prevData) => ({
      ...prevData,
      [name]: value,
    }));
  };
  const handleSubmit = async (event) => {
    event.preventDefault();
    try {
      console.log(putData);
      let url = `http://localhost:8080/CreditCard/delete/${putData.cardNumber}/${putData.customerId}`;
      const response = await axios.put(url);
      console.log(response.data);
      setResponseMessage(response.data);
      setRenderResponse(true);
    } catch (error) {
      console.error("Error:", error);
    }
  };

  return (
    <div>
      <h1>Delete credit card</h1>
      <form onSubmit={handleSubmit}>
        <div>
          <label>Enter customer id:</label>
          <input
            type="number"
            name="customerId"
            value={putData.customerId}
            onChange={handleInputChange}
          />
        </div>
        <div>
          <label>Enter card number:</label>
          <input
            type="text"
            name="cardNumber"
            value={putData.cardNumber}
            onChange={handleInputChange}
          />
        </div>
        <button type="submit">Submit</button>
      </form>
      {renderResponse && <div>{responseMessage}</div>}
    </div>
  );
}

export default DeleteCreditCard;
