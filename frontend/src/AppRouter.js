import React from "react";
import { Routes, Route } from "react-router-dom";
import Home from "./components/Home";
import AddCreditCard from "./components/AddCreditCard";
import DeleteCreditCard from "./components/DeleteCreditCard";
import Transaction from "./components/Transaction";

function AppRouter() {
  return (
    <Routes>
      <Route path="/" element={<Home />} />
      <Route path="/add" element={<AddCreditCard />} />
      <Route path="/delete" element={<DeleteCreditCard />} />
      <Route path="/transactions" element={<Transaction />} />
    </Routes>
  );
}

export default AppRouter;
