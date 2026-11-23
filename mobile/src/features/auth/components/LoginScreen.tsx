import React, { useState } from 'react';
import { StyleSheet } from 'react-native';
import { useDispatch } from 'react-redux';
import { Screen, AppText, AppTextInput, AppButton } from '../../../shared/ui';
import { spacing } from '../../../shared/theme';
import { login } from '../state/authSlice';
import { useNavigation } from '@react-navigation/native';
import type { StackNavigationProp } from '@react-navigation/stack';
import type { RootStackParamList } from '../../../navigation/AppNavigator';

const LoginScreen = () => {
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const dispatch = useDispatch();
  const navigation = useNavigation<StackNavigationProp<RootStackParamList>>();

  const handleLogin = () => {
    dispatch(login({ email, password }));
    navigation.replace('WorkoutsHome');
  };

  return (
    <Screen scrollable keyboardShouldPersistTaps="handled" style={styles.container}>
      <AppText variant="title" style={styles.title}>FitnessApp</AppText>
      <AppTextInput
        placeholder="Email"
        value={email}
        onChangeText={setEmail}
        autoCapitalize="none"
        style={styles.input}
      />
      <AppTextInput
        placeholder="Password"
        value={password}
        onChangeText={setPassword}
        secureTextEntry
        autoCapitalize="none"
        style={styles.input}
      />
      <AppButton
        label="Login"
        onPress={handleLogin}
        variant="primary"
        disabled={!email || !password}
      />
    </Screen>
  );
};

const styles = StyleSheet.create({
  container: {
    padding: spacing.md,
  },
  title: {
    marginBottom: spacing.lg,
    textAlign: 'center',
  },
  input: {
    marginBottom: spacing.md,
  },
});

export default LoginScreen;