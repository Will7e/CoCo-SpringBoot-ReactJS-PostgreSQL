import React from "react";
import ReactDOM from "react-dom";
import "./index.css";
import App from "./App";
import * as serviceWorker from "./serviceWorker";
import Avatar, { ConfigProvider } from "react-avatar";

ReactDOM.render(
  <React.StrictMode>
    <ConfigProvider
      colors={["black", "lightgrey", "PAPAYAWHIP", "DARKOLIVEGREEN"]}
    >
      <App />
    </ConfigProvider>
  </React.StrictMode>,

  document.getElementById("root")
);

serviceWorker.unregister();
