import React from "react";
import { Routes, Route, Link, BrowserRouter } from "react-router-dom";
import Navbar from "./components/Navbar";
import Register from "./components/Register";
import Login from "./components/Login";
import "./App.css";

import Home from "./components/Home";
import Profile from "./components/Profile";
import BoardUser from "./components/BoardUser";

const App = () => {
  return (
    <BrowserRouter>
      <div>
        <Navbar></Navbar>
      </div>
      <div className="App">
        <Routes>
          <Route path="/" element={<Home />} />
          <Route path="/register" element={<Register />} />
          <Route path="/home" element={<Home />} />
          <Route path="/login" element={<Login />} />
          <Route path="/profile" element={<Profile />} />
          <Route path="/user" element={<BoardUser />} />
        </Routes>
      </div>
    </BrowserRouter>
  );
};

export default App;
