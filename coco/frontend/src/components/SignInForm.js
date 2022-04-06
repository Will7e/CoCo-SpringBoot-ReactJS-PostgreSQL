import React, { useState, useRef } from "react";
import { Link, Navigate } from "react-router-dom";
import { Redirect } from "react-router-dom";
import { login } from "../actions/auth";
import { useDispatch, useSelector } from "react-redux";
import {
  FacebookLoginButton,
  InstagramLoginButton,
} from "react-social-login-buttons";

import AuthService from "../services/AuthService";

function SignInForm(props) {
  const form = useRef();
  const checkBtn = useRef();
  const [loading, setLoading] = useState(false);
  const { isLoggedIn } = useSelector((state) => state.auth);
  const { message } = useSelector((state) => state.message);
  const dispatch = useDispatch();
  const [signIn, setSignIn] = useState({
    email: "",
    password: "",
  });

  const handleChange = (event) => {
    const { name, value } = event.target;
    setSignIn((prevState) => {
      return {
        ...prevState,
        [name]: value,
      };
    });
  };

  const { email, password } = signIn;

  // Check on form submit
  function handleSubmit(event) {
    event.preventDefault();

    console.log("The form was submitted with the following data:");
    console.log(signIn);
  }

  const handleLogin = (e) => {
    e.preventDefault();
    setLoading(true);
    if (checkBtn.current.context._errors.length === 0) {
      dispatch(login(signIn.email, signIn.password))
        .then(() => {
          props.history.push("/home");
          window.location.reload();
        })
        .catch(() => {
          setLoading(false);
        });
    } else {
      setLoading(false);
    }
  };
  if (isLoggedIn) {
    return <Navigate to="/home" />;
  }

  return (
    <div className="formCenter">
      <form ref={form} className="formFields" onSubmit={handleLogin}>
        <div className="formField">
          <label className="formFieldLabel" htmlFor="email">
            E-Mail Address
          </label>
          <input
            type="email"
            id="email"
            className="formFieldInput"
            placeholder="Enter your email"
            name="email"
            value={email}
            onChange={handleChange}
          />
        </div>

        <div className="formField">
          <label className="formFieldLabel" htmlFor="password">
            Password
          </label>
          <input
            type="password"
            id="password"
            className="formFieldInput"
            placeholder="Enter your password"
            name="password"
            value={password}
            onChange={handleChange}
          />
        </div>

        <div className="formField">
          <button ref={checkBtn} className="formFieldButton">
            Sign In
          </button>{" "}
          <Link to="/" className="formFieldLink">
            Create an account
          </Link>
        </div>

        <div className="socialMediaButtons">
          <div className="facebookButton">
            <FacebookLoginButton onClick={() => alert("Hello")} />
          </div>

          <div className="instagramButton">
            <InstagramLoginButton onClick={() => alert("Hello")} />
          </div>
        </div>
      </form>
    </div>
  );
}

export default SignInForm;
