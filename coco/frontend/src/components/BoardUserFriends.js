import React, { useState, useEffect } from "react";
import UserService from "../services/user.service";
import EventBus from "../common/EventBus";
import AuthService from "../services/auth.service";
import UserList from "./UserList";
import "./UserList.css";

const BoardUserFriends = () => {
  const [content, setContent] = useState([]);
  const currentUser = AuthService.getCurrentUser();

  useEffect(() => {
    UserService.getFriendList(currentUser.id).then(
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
  }, [content]);

  return (
    <div className="userlist-box">
      <div class="container">
        <div class="row">
          <div class="col-lg-10 mx-auto">
            <div class="career-search mb-60">
              <form action="#" class="career-form mb-60">
                <div class="row">
                  <div className="friends-title">
                    <h1>My friends</h1>
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
                    userId={currentUser.id}
                    friendId={n.id}
                    a={2}
                    btntext="Unfriend"
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

export default BoardUserFriends;
