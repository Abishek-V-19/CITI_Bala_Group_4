import React, { useState } from "react";
import CircularProgress from "@mui/material/CircularProgress";
import TextField from "@mui/material/TextField";
import Stack from "@mui/material/Stack";
import Box from "@mui/material/Box";
import Button from "@mui/material/Button";
import InputLabel from '@mui/material/InputLabel';
import MenuItem from '@mui/material/MenuItem';
import FormHelperText from '@mui/material/FormHelperText';
import FormControl from '@mui/material/FormControl';
import Select, { SelectChangeEvent } from '@mui/material/Select';
import CustomerDetailsDisplay from './CustomerDetailsDisplay';

import axios from "axios";
import TableAddCreditCard from "./TableAddCreditCard";



function DeleteCreditCard() {
  const [putData, setPutData] = useState({
    // customerId: 0,
    // cardNumber: "cardnumberhere",
  });
  const [cardsFound, setCardsFound] = useState(false);
  const [customerId, setCustomerId] = useState(0);
  const [customerDetails, setCustomerDetails] = useState([]);
  const [customerFound, setCustomerFound] = useState(0);
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
  const handleSubmit2 = async (event) => {
    event.preventDefault();
    const url = `http://localhost:8080/CreditCard?customerId=${putData.customerId}`;
    setRenderResponse(false);
    setRenderError(false);
    try {
      const response = await fetch(url);
      const data = await response.json();
      console.log(data);
      if (data.creditCards.length === 0) {
        //setCustomerFound(3);
        setCardsFound(false);
      } else {
        setCardsFound(true);
      }
      setCustomerFound(1);
      setCustomerId(putData.customerId);
      setCustomerDetails(data);
    } catch (error) {
      console.log(error);
      setCustomerFound(2);
    }
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
      <h2 className="heading">VIEW CREDIT CARD</h2>
      <Box display="flex" justifyContent="center" alignItems="center" mt={5}>
        <form onSubmit={handleSubmit2}>
          <Stack spacing={3} direction="column" sx={{ width: 400 }}>
            <div>
              <TextField
                type="number"
                label="Customer id"
                name="customerId"
                value={putData.customerId}
                onChange={handleInputChange}
                sx={{ width: "90%" }}
                required
              />
            </div>
            
            <div>
              <Button
                type="submit"
                variant="contained"
                class="btn btn-outline-primary"
              >
                SEARCH
              </Button>
            </div>
          </Stack>
        </form>
      </Box>
      {customerFound === 1 ? (
        <Box mt={4} display="flex" justifyContent="center" alignItems="center">
          <Stack
            spacing={3}
            direction="column"
            justifyContent="center"
            alignItems="center"
            sx={{ width: 500}}
          >
            <CustomerDetailsDisplay customer={customerDetails.customer}></CustomerDetailsDisplay>
            <Stack spacing={3} direction="column" sx={{ width: 500 }}>
            <TableAddCreditCard arr = {customerDetails.creditCards} customer = {customerDetails.customer}></TableAddCreditCard>
            </Stack>
            {/* // saisandhyas code */}
            {/* {customerDetails.map((card, index) => {
              return <div key={index}>{card.cardNumber}</div>;
            })} */}
            {cardsFound &&
            <form onSubmit={handleSubmit}>
            <Stack spacing={3} direction="column" sx={{ width: 400 }}>
              <div>
                <TextField
                  type="text"
                  label="Enter card number to delete"
                  name="cardNumber"
                  value={putData.cardNumber}
                  onChange={handleInputChange}
                  sx={{ width: "100%" }}
                  required
                />
              </div>
              <div>
                <Button
                  sx={{ mt: 2 }}
                  variant="contained"
                  size="large"
                  class="btn btn-outline-primary"
                  type="submit"
                >
                  CANCEL CREDIT CARD
                </Button>
              </div>
              </Stack>
            </form>
            }
          </Stack>
        </Box>
      ) : customerFound === 2 ? (
        <div>Customer not found</div>
      ) : customerFound === 3 ? (
        <div>No card found for this id.</div>
      ) : (
        <div></div>
      )}
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
            sx={{ width: 400 }}
          >
            <div>
              <h3>Card Cancelled successfully.</h3>{" "}
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
            sx={{ width: 400 }}
          >
            <div>
              <h3>Please enter valid Credit Card Number.</h3>{" "}
            </div>
          </Stack>
        </Box>
      )}
    </div>
  );
}

export default DeleteCreditCard;
