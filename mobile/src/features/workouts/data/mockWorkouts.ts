import { Workout } from '../types';

export const mockWorkouts: Workout[] = [
  {
    id: 'upper-body',
    name: 'Upper Body',
    exercises: [
      {
        id: 'bench-press',
        name: 'Bench Press',
        sets: 4,
        reps: 8,
        weight: 60
      },
      {
        id: 'pull-ups',
        name: 'Pull-Ups',
        sets: 3,
        reps: 6
      },
      {
        id: 'shoulder-press',
        name: 'Shoulder Press',
        sets: 3,
        reps: 10,
        weight: 20
      },
      {
        id: 'bicep-curls',
        name: 'Bicep Curls',
        sets: 3,
        reps: 12,
        weight: 10
      }
    ]
  },
  {
    id: 'lower-body',
    name: 'Lower Body',
    exercises: [
      {
        id: 'squats',
        name: 'Squats',
        sets: 4,
        reps: 10,
        weight: 80
      },
      {
        id: 'deadlifts',
        name: 'Deadlifts',
        sets: 3,
        reps: 6,
        weight: 100
      },
      {
        id: 'lunges',
        name: 'Lunges',
        sets: 3,
        reps: 12,
        weight: 20
      },
      {
        id: 'calf-raises',
        name: 'Calf Raises',
        sets: 3,
        reps: 15,
        weight: 30
      }
    ]
  },
  {
    id: 'full-body',
    name: 'Full Body',
    exercises: [
      {
        id: 'burpees',
        name: 'Burpees',
        sets: 3,
        reps: 10
      },
      {
        id: 'push-ups',
        name: 'Push-Ups',
        sets: 3,
        reps: 15
      },
      {
        id: 'plank',
        name: 'Plank',
        sets: 3,
        reps: 60
      },
      {
        id: 'squat-jumps',
        name: 'Squat Jumps',
        sets: 3,
        reps: 12
      }
    ]
  }
];