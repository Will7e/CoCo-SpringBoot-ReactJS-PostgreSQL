import React from "react";
import { Button } from "react-bootstrap";
import HomeImage from "../images/home.jpg";

import "./Home.css";
import TypeText from "./TypeText";

const Home = () => {
  return (
    <div className="home">
      <img className="home-image" src={HomeImage}></img>
      <div class="centered">
        <h1>
          <TypeText typing={1} text={"Coder Connect"} />{" "}
        </h1>
        <div class="center2">
          <h3>
            <TypeText typing={2} text={"Welcome to our community"} />{" "}
          </h3>
        </div>
      </div>
    </div>
  );
};

export default Home;
