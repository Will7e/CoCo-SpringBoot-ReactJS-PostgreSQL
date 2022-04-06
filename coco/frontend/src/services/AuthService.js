import axios from "axios";
const API_URL = "http://localhost:8080/api/auth/";

function register(fullName, email, password) {
  return axios.post(API_URL + "signup", {
    fullName,
    email,
    password,
  });
}
function login(email, password) {
  return axios
    .post(API_URL + "signin", {
      email,
      password,
    })
    .then((response) => {
      if (response.data.accessToken) {
        localStorage.setItem("user", JSON.stringify(response.data));
      }
      return response.data;
    });
}
function logout() {
  localStorage.removeItem("user");
}
const getCurrentUser = () => {
  return JSON.parse(localStorage.getItem("user"));
};
export default {
  register,
  login,
  logout,
  getCurrentUser,
};
