import axios from "axios";
import authHeader from "./auth-header";

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

const UserService = {
  getPublicContent,
  getUserBoard,
  getUserInfo,
};

export default UserService;
