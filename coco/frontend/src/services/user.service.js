import axios from "axios";
import authHeader from "./auth-header";
import AuthService from "./auth.service";

const API_URL = "http://localhost:8080/api/test/";
const API_URL2 = "http://localhost:8080/api/";

const getPublicContent = () => {
  return axios.get(API_URL + "all");
};

const getUserBoard = () => {
  return axios.get(API_URL + "user", { headers: authHeader() });
};

const getUserInfo = () => {
  return axios.get(API_URL2 + "user", { headers: authHeader() });
};
const getAllUser = () => {
  return axios.get("http://localhost:8080/api/user/all");
};

const getUsersBySkill = () => {
  return axios.get("http://localhost:8080/api/user/skills/user/1");
};
const getUsersBySkillJS = () => {
  return axios.get("http://localhost:8080/api/user/skills/user/2");
};
const getUsersBySkillPy = () => {
  return axios.get("http://localhost:8080/api/user/skills/user/3");
};
const getUsersBySkillHtlmCss = () => {
  return axios.get("http://localhost:8080/api/user/skills/user/4");
};
const getUsersBySkillCS = () => {
  return axios.get("http://localhost:8080/api/user/skills/user/5");
};
const getUsersBySkillSQL = () => {
  return axios.get("http://localhost:8080/api/user/skills/user/6");
};
const editUser = (data) => {
  return axios.put("http://localhost:8080/api/user/edit/user", data, {
    headers: authHeader(),
  });
};
const deleteSkill = (data) => {
  return axios.delete("http://localhost:8080/api/user/skills/remove", {
    headers: authHeader(),
    data,
  });
};
const addSkill = (data) => {
  return axios.put("http://localhost:8080/api/user/skills/add", data, {
    headers: authHeader(),
  });
};

const addFriend = (currentUserId, userToAddId) => {
  return axios.post("http://localhost:8080/api/user/friends/add", {
    headers: authHeader(),
    currentUserId,
    userToAddId,
  });
};
const getFriendList = () => {
  return axios.get("http://localhost:8080/api/user/friends/all");
};

const unFriend = (currentUserId, userToAddId) => {
  return axios.delete("http://localhost:8080/api/user/skills/remove", {
    headers: authHeader(),
    currentUserId,
    userToAddId,
  });
};

const UserService = {
  getUsersBySkill,
  getPublicContent,
  getUserBoard,
  getAllUser,
  getUserInfo,
  getUsersBySkillJS,
  getUsersBySkillPy,
  getUsersBySkillPy,
  getUsersBySkillHtlmCss,
  getUsersBySkillCS,
  getUsersBySkillSQL,
  editUser,
  deleteSkill,
  addSkill,
  addFriend,
  getFriendList,
  unFriend,
};

export default UserService;
