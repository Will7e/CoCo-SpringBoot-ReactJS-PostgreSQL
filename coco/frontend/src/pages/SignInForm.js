import React, { useState, useEffect } from "react";
import { Link } from "react-router-dom";
import {
  FacebookLoginButton,
  InstagramLoginButton,
} from "react-social-login-buttons";

function SignInForm() {
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

  function handleSubmit(event) {
    event.preventDefault();

    console.log("The form was submitted with the following data:");
    console.log(signIn);
  }

  return (
    <div className="formCenter">
      <form className="formFields" onSubmit={handleSubmit}>
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
