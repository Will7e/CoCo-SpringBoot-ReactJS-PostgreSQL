import React, { useState, useEffect } from "react";
import { Routes, Route, Link } from "react-router-dom";
import "bootstrap/dist/css/bootstrap.min.css";
import "./Navbar.css";

import AuthService from "../services/auth.service";

import Login from "./Login";
import Register from "./Register";
import Home from "./Home";
import Profile from "./Profile";
import BoardUser from "./BoardUser";

import EventBus from "../common/EventBus";

function Navbar() {
  const [currentUser, setCurrentUser] = useState(undefined);

  useEffect(() => {
    const user = AuthService.getCurrentUser();

    if (user) {
      setCurrentUser(user);
    }

    EventBus.on("logout", () => {
      logOut();
    });

    return () => {
      EventBus.remove("logout");
    };
  }, []);

  const logOut = () => {
    AuthService.logout();

    setCurrentUser(undefined);
  };
  return (
    <div>
      {" "}
      <nav className="navbar navbar-expand navbar-dark bg-dark">
        <Link to={"/"} className="navbar-brand"></Link>
        <div className="navbar-nav mr-auto">
          <li className="company_name nav-item">
            <Link to={"/home"} className="nav-link">
              CODER CONNECT
            </Link>
          </li>

          {currentUser && (
            <li className="nav-page nav-item">
              <Link to={"/user"} className=" nav-link">
                Home
              </Link>
            </li>
          )}
        </div>

        {currentUser ? (
          <div className="navbar-nav ml-auto">
            <li className="nav__button nav-item">
              <Link to={"/profile"} className="nav-link">
                My Profile
              </Link>
            </li>
            <li className="nav__button nav-item">
              <a href="/login" className="nav-link" onClick={logOut}>
                Sign out
              </a>
            </li>
          </div>
        ) : (
          <div className="navbar-nav ml-auto">
            <li className="nav__button login_button nav-item">
              <Link to={"/login"} className="nav-link">
                Sign in
              </Link>
            </li>

            <li className="nav__button nav-item">
              <Link to={"/register"} className="nav-link">
                Sign Up
              </Link>
            </li>
          </div>
        )}
      </nav>
    </div>
  );
}

export default Navbar;
