import React, { useState, useEffect } from "react";
import UserService from "../services/user.service";
import EventBus from "../common/EventBus";

const BoardUser = () => {
  const [content, setContent] = useState([]);

  useEffect(() => {
    UserService.getUsersBySkill().then(
      (response) => {
        setContent(response.data);
      },
      (error) => {
        const _content =
          (error.response &&
            error.response.data &&
            error.response.data.message) ||
          error.message ||
          error.toString();

        setContent(_content);

        if (error.response && error.response.status === 401) {
          EventBus.dispatch("logout");
        }
      }
    );
  }, []);

  return (
    <div className="container">
      <header className="jumbotron">
        {content.map((n, i) => {
          return (
            <h3 key={i}>
              <span>{n.fullName}</span>
              <span>{n.email}</span>
              <span>{n.contacts}</span>
              <span>{n.city}</span>
            </h3>
          );
        })}
      </header>
    </div>
  );
};

export default BoardUser;
