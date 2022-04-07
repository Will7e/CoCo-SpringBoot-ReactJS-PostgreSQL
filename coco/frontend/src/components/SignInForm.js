import React, { useState, useRef } from "react";
import { Link, useNavigate } from "react-router-dom";
import {
  FacebookLoginButton,
  InstagramLoginButton,
} from "react-social-login-buttons";
import CheckButton from "react-validation/build/button";

import AuthService from "../services/AuthService";

function SignInForm() {
  let navigate = useNavigate();
  const [loading, setLoading] = useState(false);
  const [message, setMessage] = useState("");
  const form = useRef();
  const checkBtn = useRef();

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
    setMessage("");
    setLoading(true);

    AuthService.login(signIn.email, signIn.password).then(
      () => {
        navigate("/profile");
        window.location.reload();
      },
      (error) => {
        const resMessage =
          (error.response &&
            error.response.data &&
            error.response.data.message) ||
          error.message ||
          error.toString();

        setLoading(false);
        setMessage(resMessage);
      }
    );
  };

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
          <button className="formFieldButton">Sign In</button>{" "}
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
