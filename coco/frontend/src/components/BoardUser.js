import React, { useState, useEffect } from "react";
import UserService from "../services/user.service";
import EventBus from "../common/EventBus";
import AuthService from "../services/auth.service";
import UserList from "./UserList";
import "./UserList.css";

const BoardUser = () => {
  const [content, setContent] = useState([]);
  const currentUser = AuthService.getCurrentUser();

  const printUserList = (i) => {
    if (i === 1) {
      UserService.getUsersBySkill().then(
        (response) => {
          setContent(response.data);
          setContent((content) =>
            content.filter((x) => x.email != currentUser.email)
          );
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
    }
    if (i === 2) {
      UserService.getUsersBySkillJS().then(
        (response) => {
          setContent(response.data);
          setContent((content) =>
            content.filter((x) => x.email != currentUser.email)
          );
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
    }
    if (i === 3) {
      UserService.getUsersBySkillPy().then(
        (response) => {
          setContent(response.data);
          setContent((content) =>
            content.filter((x) => x.email != currentUser.email)
          );
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
    }
    if (i === 4) {
      UserService.getUsersBySkillHtlmCss().then(
        (response) => {
          setContent(response.data);
          setContent((content) =>
            content.filter((x) => x.email != currentUser.email)
          );
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
    }
    if (i === 5) {
      UserService.getUsersBySkillCS().then(
        (response) => {
          setContent(response.data);
          setContent((content) =>
            content.filter((x) => x.email != currentUser.email)
          );
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
    }
    if (i === 6) {
      UserService.getUsersBySkillSQL().then(
        (response) => {
          setContent(response.data);
          setContent((content) =>
            content.filter((x) => x.email != currentUser.email)
          );
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
    }
    if (i === 7) {
      UserService.getAllUser().then(
        (response) => {
          setContent(response.data);
          setContent((content) =>
            content.filter((x) => x.email != currentUser.email)
          );
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
    }
  };

  return (
    <div className="userlist-box">
      <div class="container">
        <div class="row">
          <div class="col-lg-10 mx-auto">
            <div class="career-search mb-60">
              <form action="#" class="career-form mb-60">
                <div class="row">
                  <div class="col-md-6 col-lg-3 my-3">
                    <button
                      type="button"
                      class="btn btn-lg btn-block btn-light btn-custom"
                      id="contact-submit"
                      onClick={() => {
                        printUserList(7);
                      }}
                    >
                      All
                    </button>
                  </div>
                  <div class="col-md-6 col-lg-3 my-3">
                    <button
                      type="button"
                      class="btn btn-lg btn-block btn-light btn-custom"
                      id="contact-submit"
                      onClick={() => {
                        printUserList(2);
                      }}
                    >
                      JavaScript
                    </button>
                  </div>
                  <div class="col-md-6 col-lg-3 my-3">
                    <button
                      type="button"
                      class="btn btn-lg btn-block btn-light btn-custom"
                      id="contact-submit"
                      onClick={() => {
                        printUserList(3);
                      }}
                    >
                      Python
                    </button>
                  </div>
                  <div class="col-md-6 col-lg-3 my-3">
                    <button
                      type="button"
                      class="btn btn-lg btn-block btn-light btn-custom"
                      id="contact-submit"
                      onClick={() => {
                        printUserList(4);
                      }}
                    >
                      HTML/CSS
                    </button>
                  </div>
                  <div class="col-md-6 col-lg-3 my-3">
                    <button
                      type="button"
                      class="btn btn-lg btn-block btn-light btn-custom"
                      id="contact-submit"
                      onClick={() => {
                        printUserList(5);
                      }}
                    >
                      C#
                    </button>
                  </div>
                  <div class="col-md-6 col-lg-3 my-3">
                    <button
                      type="button"
                      class="btn btn-lg btn-block btn-light btn-custom"
                      id="contact-submit"
                      onClick={() => {
                        printUserList(6);
                      }}
                    >
                      SQL
                    </button>
                  </div>
                  <div class="col-md-6 col-lg-3 my-3">
                    <button
                      type="button"
                      class="btn btn-lg btn-block btn-light btn-custom"
                      id="contact-submit"
                      onClick={() => {
                        printUserList(1);
                      }}
                    >
                      Java
                    </button>
                  </div>
                </div>
              </form>

              {content.map((n, i) => {
                return (
                  <UserList
                    key={i}
                    name={n.fullName}
                    location={n.city}
                    country={n.country}
                    contacts={n.contacts}
                    skill={n.skills}
                  >
                    {" "}
                  </UserList>
                );
              })}
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};

export default BoardUser;
