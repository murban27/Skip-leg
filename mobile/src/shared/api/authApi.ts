import axios from 'axios';

const API_URL = 'http://localhost:8080/api/auth';

const authApi = {
  async login(email: string, password: string) {
    const response = await axios.post(`${API_URL}/mock-login`, { email, password });
    return response.data;
  },
};

export default authApi;