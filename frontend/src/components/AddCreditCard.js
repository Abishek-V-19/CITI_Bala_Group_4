import React, { useState } from "react";
import TextField from "@mui/material/TextField";
import CircularProgress from "@mui/material/CircularProgress";
import Stack from "@mui/material/Stack";
import Box from "@mui/material/Box";
import Button from "@mui/material/Button";

function AddCreditCard() {
  const [postData, setPostData] = useState({
    // customerId: 0,
  });
  const [loader, setLoader] = useState(false);
  const [renderResponse, setRenderResponse] = useState(false);
  const [renderData, setRenderData] = useState({});
  const [renderError, setRenderError] = useState(false);

  const handleInputChange = (event) => {
    const { name, value } = event.target;
    setPostData((prevData) => ({
      ...prevData,
      [name]: value,
    }));
  };

  const handleSubmit = async (event) => {
    event.preventDefault();
    setRenderResponse(false);
    setLoader(true);
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
      setRenderError(false);
      setLoader(false);
      setRenderResponse(true);
      setRenderData((prevData) => ({
        ...prevData,
        ...data,
      }));
    } catch (error) {
      console.error("Error:", error);
      setRenderResponse(false);
      setLoader(false);
      setRenderError(true);
    }
  };

  return (
    <div>
      <h2>Add Credit Card</h2>
      <Box display="flex" justifyContent="center" alignItems="center" mt={5}>
        <form onSubmit={handleSubmit}>
          <Stack spacing={3} direction="column" sx={{ width: 400 }}>
            <div>
              <TextField
                type="number"
                label="Customer id"
                name="customerId"
                value={postData.customerId}
                onChange={handleInputChange}
                required
              />
            </div>
            <div>
              <TextField
                type="text"
                label="First Name"
                name="firstName"
                value={postData.firstName}
                onChange={handleInputChange}
                required
              />
            </div>
            <div>
              <TextField
                type="text"
                label="Last Name"
                name="lastName"
                value={postData.lastName}
                onChange={handleInputChange}
                required
              />
            </div>
            
            <div>
              <Button
                variant="contained"
                size="large"
                type="submit"
                class="btn btn-outline-primary"
              >
                ADD
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
            spacing={1}
            direction="column"
            justifyContent="center"
            alignItems="flex-start"
            sx={{ width: 400 }}
          >
            <div>
              <h3>Credit card added successfully.</h3>{" "}
            </div>
            <div>
              <b>Customer id:</b> {renderData.customerId}
            </div>
            <div>
              <b>Credit card number:</b> {renderData.cardNumber}{" "}
            </div>
            <div>
              <b>Status:</b> <span>{renderData.status}</span>{" "}
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
              <h4>Please enter valid customer details.</h4>
            </div>
          </Stack>
        </Box>
      )}
    </div>
  );
}

export default AddCreditCard;
