import React from 'react';
import { View, StyleSheet } from 'react-native';
import { useSelector } from 'react-redux';
import { Screen, AppText } from '../../../shared/ui';
import { spacing } from '../../../shared/theme';
import WorkoutCard from './WorkoutCard';
import { selectAllWorkouts } from '../state/workoutsSlice';

const WorkoutsHomeScreen: React.FC = () => {
  const workouts = useSelector(selectAllWorkouts);

  return (
    <Screen scrollable style={styles.container}>
      <AppText variant="title" style={styles.title}>My Workouts</AppText>
      <View style={styles.workoutsContainer}>
        {workouts.map((workout) => (
          <WorkoutCard key={workout.id} workout={workout} />
        ))}
      </View>
    </Screen>
  );
};

const styles = StyleSheet.create({
  container: {
    padding: spacing.md,
  },
  title: {
    marginBottom: spacing.lg,
  },
  workoutsContainer: {
    marginTop: spacing.md,
  },
});

export default WorkoutsHomeScreen;