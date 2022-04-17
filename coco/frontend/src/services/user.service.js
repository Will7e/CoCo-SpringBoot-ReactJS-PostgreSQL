import axios from "axios";
import authHeader from "./auth-header";

const API_URL = "http://localhost:8080/api/test/";
const API_URL2 = "http://localhost:8080/api/";
const API_URL3 = "http://localhost:8080/api/user/skills";

const getPublicContent = () => {
  return axios.get(API_URL + "all");
};

const getUserBoard = () => {
  return axios.get(API_URL + "user", { headers: authHeader() });
};

const getUserInfo = () => {
  return axios.get(API_URL2 + "user", { headers: authHeader() });
};
const putUserSkills = (skillId, userId) => {
  return axios.put(API_URL3 + "/add", {
    skillId,
    userId,
    headers: authHeader(),
  });
};

const UserService = {
  getPublicContent,
  getUserBoard,
  getUserInfo,
  putUserSkills,
};

export default UserService;
