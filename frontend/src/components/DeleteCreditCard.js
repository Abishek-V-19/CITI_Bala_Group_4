import React, { useState } from "react";
import CircularProgress from "@mui/material/CircularProgress";
import TextField from "@mui/material/TextField";
import Stack from "@mui/material/Stack";
import Box from "@mui/material/Box";
import Button from "@mui/material/Button";

import axios from "axios";

function DeleteCreditCard() {
  const [putData, setPutData] = useState({
    customerId: 0,
    cardNumber: "cardnumberhere",
  });
  const [loader, setLoader] = useState(false);
  const [renderResponse, setRenderResponse] = useState(false);
  const [renderError, setRenderError] = useState(false);
  const handleInputChange = (event) => {
    const { name, value } = event.target;
    setPutData((prevData) => ({
      ...prevData,
      [name]: value,
    }));
  };
  const handleSubmit = async (event) => {
    event.preventDefault();
    setRenderResponse(false);
    setLoader(true);
    try {
      console.log(putData);
      let url = `http://localhost:8080/CreditCard/delete/${putData.cardNumber}/${putData.customerId}`;
      const response = await axios.put(url);
      console.log(response.data);
      setLoader(false);
      setRenderError(false);
      setRenderResponse(true);
    } catch (error) {
      setRenderResponse(false);
      setLoader(false);
      setRenderError(true);
      console.error("Error:", error);
    }
  };

  return (
    <div>
      <h2>Delete Credit Card</h2>
      <Box display="flex" justifyContent="center" alignItems="center" mt={5}>
        <form onSubmit={handleSubmit}>
          <Stack spacing={3} direction="row" sx={{ width: 600 }}>
            <div>
              <TextField
                type="number"
                label="Customer id"
                name="customerId"
                value={putData.customerId}
                onChange={handleInputChange}
              />
            </div>
            <div>
              <TextField
                type="text"
                label="Card Number"
                name="cardNumber"
                value={putData.cardNumber}
                onChange={handleInputChange}
              />
            </div>
            <div>
              <Button type="submit" variant="contained" color="error">
                DELETE
              </Button>
            </div>
          </Stack>
        </form>
      </Box>
      {loader && (
        <div>
          {" "}
          <CircularProgress />
        </div>
      )}
      {renderResponse && (
        <Box mt={4} display="flex" justifyContent="center" alignItems="center">
          <Stack
            direction="column"
            justifyContent="center"
            alignItems="flex-start"
            sx={{ width: 600 }}
          >
            <div>
              <h3>Card is deleted successfully.</h3>{" "}
            </div>
          </Stack>
        </Box>
      )}
      {renderError && (
        <Box mt={4} display="flex" justifyContent="center" alignItems="center">
          <Stack
            spacing={1}
            direction="column"
            justifyContent="center"
            alignItems="flex-start"
            sx={{ width: 600 }}
          >
            <div>
              <h3>Please enter a proper customer id and card number.</h3>{" "}
            </div>
          </Stack>
        </Box>
      )}
    </div>
  );
}

export default DeleteCreditCard;
