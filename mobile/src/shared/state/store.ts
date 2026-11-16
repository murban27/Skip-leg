import { configureStore } from '@reduxjs/toolkit';
import authReducer from '../../features/auth/state/authSlice';
import activitiesReducer from '../../features/activities/state/activitiesSlice';
import workoutsReducer from '../../features/workouts/state/workoutsSlice';

const store = configureStore({
  reducer: {
    auth: authReducer,
    activities: activitiesReducer,
    workouts: workoutsReducer,
  },
});

export type RootState = ReturnType<typeof store.getState>;
export type AppDispatch = typeof store.dispatch;

export default store;