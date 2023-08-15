import React from "react";
import { Link } from "react-router-dom";
import Stack from "@mui/material/Stack";
import Button from "@mui/material/Button";
import Box from "@mui/material/Box";

function Home() {
  return (
    <Box display="flex" justifyContent="center" alignItems="center">
      <Stack spacing={3}>
        <div>
          <Link to="/transactions">
            <Button variant="outlined" size="medium">
              Transactions
            </Button>
          </Link>
        </div>
        <div>
          <Link to="/add">
            <Button variant="outlined" size="medium">
              Add credit card
            </Button>
          </Link>
        </div>
        <div>
          <Link to="/delete">
            <Button variant="outlined" size="medium">
              Delete credit card
            </Button>
          </Link>
        </div>
      </Stack>
    </Box>
  );
}

export default Home;
