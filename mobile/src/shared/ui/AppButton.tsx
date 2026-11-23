import React from 'react';
import { Pressable, Text, StyleSheet, PressableProps } from 'react-native';
import { colors, radii, spacing, typography } from '../theme';

type ButtonVariant = 'primary' | 'secondary' | 'ghost';

interface AppButtonProps extends PressableProps {
  label: string;
  onPress: () => void;
  variant?: ButtonVariant;
  disabled?: boolean;
}

export const AppButton = ({ label, onPress, variant = 'primary', disabled = false, ...props }: AppButtonProps) => {
  const buttonStyle = [
    styles.base,
    styles[variant],
    disabled && styles.disabled,
  ];

  const textStyle = [
    styles.textBase,
    variant === 'ghost' && styles.ghostText,
    variant === 'primary' && styles.primaryText,
  ];

  return (
    <Pressable
      onPress={disabled ? undefined : onPress}
      style={({ pressed }) => [
        buttonStyle,
        pressed && styles.pressed,
      ]}
      disabled={disabled}
      {...props}
    >
      <Text style={textStyle}>{label}</Text>
    </Pressable>
  );
};

export default AppButton;

const styles = StyleSheet.create({
  base: {
    minHeight: 48,
    paddingHorizontal: spacing.lg,
    borderRadius: radii.pill,
    justifyContent: 'center',
    alignItems: 'center',
  },
  primary: {
    backgroundColor: colors.accent,
  },
  secondary: {
    borderWidth: 1,
    borderColor: colors.border,
  },
  ghost: {
    backgroundColor: 'transparent',
  },
  disabled: {
    opacity: 0.5,
  },
  pressed: {
    opacity: 0.8,
  },
  textBase: {
    ...typography.body,
    color: colors.textPrimary,
  },
  primaryText: {
    color: colors.background,
  },
  ghostText: {
    color: colors.accent,
  },
});