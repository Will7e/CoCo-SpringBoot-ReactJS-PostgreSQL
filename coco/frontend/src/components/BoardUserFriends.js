import React, { useState, useEffect } from "react";
import UserService from "../services/user.service";
import EventBus from "../common/EventBus";
import AuthService from "../services/auth.service";
import UserList from "./UserList";
import "./UserList.css";

function BoardUserFriends() {
  return (
    <div>
      <div className="userlist-box">
        <div class="container">
          <div class="row">
            <div class="col-lg-10 mx-auto">
              <div class="career-search mb-60">
                <form action="#" class="career-form mb-60">
                  <div class="row">
                    <div class="col-md-6 col-lg-3 my-3">
                      <h1>Friends</h1>
                    </div>
                  </div>
                </form>

                <h1>Right here</h1>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
}

export default BoardUserFriends;
