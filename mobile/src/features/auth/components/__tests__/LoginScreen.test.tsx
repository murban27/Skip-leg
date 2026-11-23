import React from 'react';
import { render, userEvent } from '@testing-library/react-native';
import { Provider } from 'react-redux';
import { SafeAreaProvider } from 'react-native-safe-area-context';
import { useNavigation } from '@react-navigation/native';
import LoginScreen from '../LoginScreen';
import store from '../../../../shared/state/store';
import { login } from '../../state/authSlice';

const mockDispatch = jest.fn();
const mockReplace = jest.fn();

jest.mock('@react-navigation/native', () => ({
  useNavigation: jest.fn(),
}));

jest.mock('react-redux', () => ({
  ...jest.requireActual('react-redux'),
  useDispatch: () => mockDispatch,
}));

beforeEach(() => {
  jest.clearAllMocks();
  (useNavigation as jest.Mock).mockReturnValue({ replace: mockReplace });
});

const renderLogin = () =>
  render(
    <SafeAreaProvider
      initialMetrics={{
        frame: { x: 0, y: 0, width: 0, height: 0 },
        insets: { top: 0, left: 0, right: 0, bottom: 0 },
      }}
    >
      <Provider store={store}>
        <LoginScreen />
      </Provider>
    </SafeAreaProvider>
  );

describe('LoginScreen', () => {
  it('should dispatch login with entered credentials', async () => {
    const user = userEvent.setup();
    const { getByPlaceholderText, getByText } = await renderLogin();

    await user.type(getByPlaceholderText('Email'), 'test@example.com');
    await user.type(getByPlaceholderText('Password'), 'password');
    await user.press(getByText('Login'));

    expect(mockDispatch).toHaveBeenCalledWith(
      login({ email: 'test@example.com', password: 'password' })
    );
  });

  it('should not dispatch login when fields are empty', async () => {
    const user = userEvent.setup();
    const { getByText } = await renderLogin();

    await user.press(getByText('Login'));

    expect(mockDispatch).not.toHaveBeenCalled();
  });

  it('should navigate to WorkoutsHome on submit', async () => {
    const user = userEvent.setup();
    const { getByPlaceholderText, getByText } = await renderLogin();

    await user.type(getByPlaceholderText('Email'), 'test@example.com');
    await user.type(getByPlaceholderText('Password'), 'password');
    await user.press(getByText('Login'));

    expect(mockReplace).toHaveBeenCalledWith('WorkoutsHome');
  });
});
