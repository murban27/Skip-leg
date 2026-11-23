import React from 'react';
import { TextInput, StyleSheet, TextInputProps } from 'react-native';
import { colors, radii, spacing, typography } from '../theme';

interface AppTextInputProps extends TextInputProps {}

export const AppTextInput = (props: AppTextInputProps) => {
  return <TextInput style={styles.input} placeholderTextColor={colors.textTertiary} {...props} />;
};

export default AppTextInput;

const styles = StyleSheet.create({
  input: {
    ...typography.body,
    backgroundColor: colors.surface,
    borderWidth: 1,
    borderColor: colors.border,
    borderRadius: radii.md,
    padding: spacing.md,
    color: colors.textPrimary,
  },
});