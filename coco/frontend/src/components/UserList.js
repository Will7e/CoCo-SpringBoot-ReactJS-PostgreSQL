import React from "react";
import Avatar from "react-avatar";
import "./UserList.css";

function UserList({ name, location, country, contacts, skill }) {
  return (
    <div class="filter-result">
      <div class="job-box d-md-flex align-items-center justify-content-between mb-30">
        <div class="job-left my-4 d-md-flex align-items-center flex-wrap">
          <div class="img-holder mr-md-4 mb-md-0 mb-4 mx-auto mx-md-0 d-md-none d-lg-flex">
            <Avatar name={name} size="80" round={true} textSizeRatio={3} />{" "}
          </div>
          <div class="job-content">
            <h5 class="text-center text-md-left">{name}</h5>
            <ul class="d-md-flex flex-wrap text-capitalize ff-open-sans">
              <li class="mr-md-4">
                <i class="zmdi zmdi-pin mr-2"></i> {location}
              </li>
              <li class="mr-md-4">
                <i class="zmdi zmdi-money mr-2"></i> {country}
              </li>
              <li class="mr-md-4">
                <i class="zmdi zmdi-time mr-2"></i> {contacts}
              </li>

              <li class="mr-md-4">
                {skill.map((n, i) => {
                  return (
                    <i key={i} class="zmdi zmdi-time mr-2">
                      {n.name}
                    </i>
                  );
                })}
              </li>
            </ul>
          </div>
        </div>
        <div class="job-right my-4 flex-shrink-0">
          <a href="#" class="btn d-block w-100 d-sm-inline-block btn-light">
            Add Friend
          </a>
        </div>
      </div>
    </div>
  );
}

export default UserList;
