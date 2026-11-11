import axios from 'axios';

const API_URL = 'http://localhost:8080/api/activities';

const activitiesApi = {
  async logActivity(activity: { type: string; duration: number; intensity: string }) {
    const response = await axios.post(API_URL, activity);
    return response.data;
  },
  async getActivities() {
    const response = await axios.get(API_URL);
    return response.data;
  },
};

export default activitiesApi;