import React from "react";
import { Routes, Route } from "react-router-dom";
import Home from "./components/Home";
import AddCreditCard from "./components/AddCreditCard";
import DeleteCreditCard from "./components/DeleteCreditCard";
import Transaction from "./components/Transaction";
import AddCustomer from "./components/AddCustomer";

function AppRouter() {
  return (
    <Routes>
      <Route path="/" element={<Home />} />
      <Route path="/add" element={<AddCreditCard />} />
      <Route path="/delete" element={<DeleteCreditCard />} />
      <Route path="/transactions" element={<Transaction />} />
      <Route path="/AddCustomer" element={<AddCustomer />} />
    </Routes>
  );
}

export default AppRouter;
