import React from 'react';
import { Text, TextProps, StyleSheet } from 'react-native';
import { typography } from '../theme';

type TextVariant = 'title' | 'heading' | 'body' | 'caption' | 'numeric';

interface AppTextProps extends TextProps {
  variant?: TextVariant;
}

export const AppText = ({ variant = 'body', style, ...props }: AppTextProps) => {
  return (
    <Text style={[styles[variant], style]} {...props} />
  );
};

export default AppText;

const styles = StyleSheet.create({
  title: typography.title,
  heading: typography.heading,
  body: typography.body,
  caption: typography.caption,
  numeric: typography.numeric,
});