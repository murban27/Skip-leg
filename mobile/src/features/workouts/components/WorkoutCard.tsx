import React from 'react';
import { View, StyleSheet } from 'react-native';
import { Card, AppText } from '../../../shared/ui';
import { spacing, radii, shadows } from '../../../shared/theme';
import ExerciseRow from './ExerciseRow';
import { Workout } from '../types';

interface WorkoutCardProps {
  workout: Workout;
}

const WorkoutCard: React.FC<WorkoutCardProps> = ({ workout }) => {
  return (
    <Card style={styles.card}>
      <AppText variant="heading">{workout.name}</AppText>
      <View style={styles.exercisesContainer}>
        {workout.exercises.map((exercise) => (
          <ExerciseRow
            key={exercise.id}
            name={exercise.name}
            sets={exercise.sets}
            reps={exercise.reps}
            weight={exercise.weight}
          />
        ))}
      </View>
    </Card>
  );
};

const styles = StyleSheet.create({
  card: {
    marginBottom: spacing.md,
    padding: spacing.md,
    borderRadius: radii.md,
    boxShadow: shadows.card,
  },
  exercisesContainer: {
    marginTop: spacing.md,
  },
});

export default WorkoutCard;