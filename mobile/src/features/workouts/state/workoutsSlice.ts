import { createSlice } from '@reduxjs/toolkit';
import { mockWorkouts } from '../data/mockWorkouts';
import { Workout } from '../types';

interface WorkoutsState {
  workouts: Workout[];
}

const initialState: WorkoutsState = {
  workouts: mockWorkouts,
};

const workoutsSlice = createSlice({
  name: 'workouts',
  initialState,
  reducers: {},
});

export const selectAllWorkouts = (state: { workouts: WorkoutsState }) => state.workouts.workouts;

export default workoutsSlice.reducer;