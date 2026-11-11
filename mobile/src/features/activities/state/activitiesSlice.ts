import { createSlice, PayloadAction } from '@reduxjs/toolkit';

interface Activity {
  type: string;
  duration: number;
  intensity: string;
}

interface ActivitiesState {
  activities: Activity[];
}

const initialState: ActivitiesState = {
  activities: [],
};

const activitiesSlice = createSlice({
  name: 'activities',
  initialState,
  reducers: {
    logActivity(state, action: PayloadAction<Activity>) {
      state.activities.push(action.payload);
    },
    fetchActivities(state) {
      // In a real app, you would fetch activities from an API here
      // For now, we'll just return the existing activities
    },
  },
});

export const { logActivity, fetchActivities } = activitiesSlice.actions;

export default activitiesSlice.reducer;