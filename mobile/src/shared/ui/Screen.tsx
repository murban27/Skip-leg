import React from 'react';
import { View, ScrollView, StyleSheet, ViewStyle, ScrollViewProps } from 'react-native';
import { useSafeAreaInsets } from 'react-native-safe-area-context';
import { spacing } from '../theme';

interface ScreenProps extends ScrollViewProps {
  scrollable?: boolean;
  style?: ViewStyle;
  children: React.ReactNode;
}

export const Screen = ({ scrollable = false, style, children, ...props }: ScreenProps) => {
  const insets = useSafeAreaInsets();

  const contentContainerStyle = {
    paddingTop: insets.top + spacing.lg,
    paddingBottom: insets.bottom + spacing.lg,
    paddingHorizontal: spacing.lg,
    gap: spacing.lg,
  };

  if (scrollable) {
    return (
      <ScrollView
        contentContainerStyle={[contentContainerStyle, style]}
        keyboardShouldPersistTaps="handled"
        {...props}
      >
        {children}
      </ScrollView>
    );
  }

  return (
    <View style={[styles.container, style]}>
      {children}
    </View>
  );
};

export default Screen;

const styles = StyleSheet.create({
  container: {
    flex: 1,
  },
});