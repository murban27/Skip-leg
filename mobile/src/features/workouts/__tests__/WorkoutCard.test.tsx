import React from 'react';
import { render } from '@testing-library/react-native';
import WorkoutCard from '../components/WorkoutCard';
import { Workout } from '../types';

const mockWorkout: Workout = {
  id: 'test-workout',
  name: 'Test Workout',
  exercises: [
    {
      id: 'exercise-1',
      name: 'Test Exercise',
      sets: 3,
      reps: 10,
      weight: 20
    }
  ]
};

describe('WorkoutCard', () => {
  it('should render workout name and its exercises with sets × reps and weight', async () => {
    const { getByText } = await render(<WorkoutCard workout={mockWorkout} />);
    expect(getByText('Test Workout')).toBeTruthy();
    expect(getByText('Test Exercise')).toBeTruthy();
    expect(getByText('3 × 10 × 20 kg')).toBeTruthy();
  });
});