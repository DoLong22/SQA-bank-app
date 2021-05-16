import axios from 'axios';

const baseUrl = process.env.VUE_APP_API_URL;
const options = {
  baseURL: baseUrl,
  headers: {},
};

const axiosInstance = axios.create(options);

axiosInstance.interceptors.request.use(
  async (request) => {
    return request;
  },
  (error) => {
    console.log(error);
    return Promise.reject(error);
  }
);

axiosInstance.interceptors.response.use(
  (response) => {
    return response.data;
  },
  (error) => {
    console.log(error);
    return Promise.reject(error);
  }
);

export default axiosInstance;
