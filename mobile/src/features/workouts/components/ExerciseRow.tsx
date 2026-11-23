import React from 'react';
import { View, StyleSheet } from 'react-native';
import { AppText } from '../../../shared/ui';
import { spacing } from '../../../shared/theme';

interface ExerciseRowProps {
  name: string;
  sets: number;
  reps: number;
  weight?: number;
}

const ExerciseRow: React.FC<ExerciseRowProps> = ({ name, sets, reps, weight }) => {
  return (
    <View style={styles.container}>
      <AppText variant="body">{name}</AppText>
      <AppText variant="numeric">
        {sets} × {reps}{weight ? ` × ${weight} kg` : ''}
      </AppText>
    </View>
  );
};

const styles = StyleSheet.create({
  container: {
    flexDirection: 'row',
    justifyContent: 'space-between',
    paddingVertical: spacing.sm,
  },
});

export default ExerciseRow;