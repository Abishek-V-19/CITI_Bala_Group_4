import React, { useState } from "react";
import { baseUrl } from "../url";
import TextField from "@mui/material/TextField";
import CircularProgress from "@mui/material/CircularProgress";
import Stack from "@mui/material/Stack";
import Box from "@mui/material/Box";
import Button from "@mui/material/Button";
import Select, { SelectChangeEvent } from '@mui/material/Select';
import InputLabel from '@mui/material/InputLabel';
import MenuItem from '@mui/material/MenuItem';

function AddCustomer(){
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
        const url = `${baseUrl}/customer/add`;
    
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
    return(
        <div>
      <h2 className="heading">ADD CUSTOMER</h2>
      <Box display="flex" justifyContent="center" alignItems="center" mt={5}>
        <form onSubmit={handleSubmit}>
          <Stack
            spacing={3}
            direction="column"
            sx={{ width: 400, padding: "10px" }}
          >
            <div>
              <TextField
                type="text"
                label="First Name"
                name="firstName"
                value={postData.firstName}
                onChange={handleInputChange}
                sx={{ width: "90%" }}
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
                sx={{ width: "90%" }}
                required
              />
            </div>
            <div>
            <TextField
                label="Gender"
                name="gender"
                value={postData.gender}
                onChange={handleInputChange}
                sx={{ width: "90%" }}
                select
                required> 
                 <MenuItem value={"M"}>Male</MenuItem>
                  <MenuItem value={"F"}>Female</MenuItem>
              </TextField>
               
                 {/*
               <InputLabel id="gender-label">Gender</InputLabel>
                <Select
                  labelId="gender-label"
                  id="gender"
                  value={postData.gender}
                  label="Gender"
                  onChange={handleInputChange}
                  sx={{ width: "90%" }}
                >
                  <MenuItem value={"M"}>Male</MenuItem>
                  <MenuItem value={"F"}>Female</MenuItem>
                </Select> */}
           
            </div>
            <div>
              <TextField
                type="text"
                label="Profession"
                name="profession"
                value={postData.profession}
                onChange={handleInputChange}
                sx={{ width: "90%" }}
                required
              />
            </div>
            <div>
              <TextField
                type="date"
                label="Date of Birth"
                name="dob"
                value={postData.dob}
                onChange={handleInputChange}
                sx={{ width: "90%" }}
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
            alignItems="center"
            sx={{ width: 400 }}
          >
            <div>
              <h3>Customer Created successfully.</h3>{" "}
            </div>
            <div>
              <b>Customer id:</b> {renderData.customerId}
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
            alignItems="center"
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
export default AddCustomer;