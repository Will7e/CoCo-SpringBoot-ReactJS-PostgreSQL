import React, { useState } from "react";
import { Link } from "react-router-dom";

function SignUpForm() {
  const [signUp, setSignUp] = useState({
    email: "",
    password: "",
    name: "",
    hasAgreed: false,
  });

  const handleChange = (event) => {
    const { name, value } = event.target;
    setSignUp((prevState) => {
      return {
        ...prevState,
        [name]: value,
      };
    });
  };

  const { email, password, name, hasAgreed } = signUp;

  function handleSubmit(e) {
    e.preventDefault();

    console.log("The form was submitted with the following data:");
    console.log(signUp);
  }

  return (
    <div className="formCenter">
      <form onSubmit={handleSubmit} className="formFields">
        <div className="formField">
          <label className="formFieldLabel" htmlFor="name">
            Full Name
          </label>
          <input
            type="text"
            id="name"
            className="formFieldInput"
            placeholder="Enter your full name"
            name="name"
            value={name}
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
          <label className="formFieldCheckboxLabel">
            <input
              className="formFieldCheckbox"
              type="checkbox"
              name="hasAgreed"
              value={hasAgreed}
              onChange={handleChange}
            />{" "}
            I agree all statements in{" "}
            <a href="null" className="formFieldTermsLink">
              terms of service
            </a>
          </label>
        </div>

        <div className="formField">
          <button className="formFieldButton">Sign Up</button>{" "}
          <Link to="/sign-in" className="formFieldLink">
            I'm already member
          </Link>
        </div>
      </form>
    </div>
  );
}
export default SignUpForm;
