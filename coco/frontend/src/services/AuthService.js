import axios from "axios";
axios.defaults.baseURL = "http:localhost:8080";

function register(fullName, email, password) {
  return axios.post("/api/auth/signup", {
    fullName,
    email,
    password,
  });
}
function login(email, password) {
  return axios
    .post("/api/auth/signin", {
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
