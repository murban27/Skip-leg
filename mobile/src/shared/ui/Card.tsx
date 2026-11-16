import React from 'react';
import { Pressable, StyleSheet, ViewStyle, PressableProps } from 'react-native';
import { colors, radii, shadows } from '../theme';

interface CardProps extends PressableProps {
  style?: ViewStyle;
  children: React.ReactNode;
}

export const Card = ({ style, children, ...props }: CardProps) => {
  return (
    <Pressable style={[styles.card, style]} {...props}>
      {children}
    </Pressable>
  );
};

export default Card;

const styles = StyleSheet.create({
  card: {
    backgroundColor: colors.surface,
    borderRadius: radii.lg,
    borderCurve: 'continuous',
    borderWidth: 1,
    borderColor: colors.border,
    boxShadow: shadows.card,
  },
});