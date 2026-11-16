import workoutsReducer, { selectAllWorkouts } from '../state/workoutsSlice';

describe('workoutsSlice', () => {
  it('should contain the 3 mock workouts', () => {
    const state = workoutsReducer(undefined, { type: 'unknown' });
    const workouts = selectAllWorkouts({ workouts: state });
    expect(workouts).toHaveLength(3);
    expect(workouts.map(w => w.name)).toEqual(['Upper Body', 'Lower Body', 'Full Body']);
  });
});