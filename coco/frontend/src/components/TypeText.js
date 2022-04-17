import React from "react";
import TypeWriter from "react-typewriter";

function TypeText({ text, typing }) {
  return <TypeWriter typing={typing}>{text}</TypeWriter>;
}

export default TypeText;
