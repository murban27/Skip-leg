import React from 'react';
import { render, fireEvent } from '@testing-library/react-native';
import LoginScreen from '../LoginScreen';
import { Provider } from 'react-redux';
import configureStore from 'redux-mock-store';

const mockStore = configureStore([]);

describe('LoginScreen', () => {
  it('renders correctly', async () => {
    const store = mockStore({
      auth: { isAuthenticated: false, token: null },
    });

    const { getByPlaceholderText, getByText } = await render(
      <Provider store={store}>
        <LoginScreen />
      </Provider>
    );

    expect(getByPlaceholderText('Email')).toBeTruthy();
    expect(getByPlaceholderText('Password')).toBeTruthy();
    expect(getByText('Login')).toBeTruthy();
  });

  it('dispatches login action on button press', async () => {
    const store = mockStore({
      auth: { isAuthenticated: false, token: null },
    });

    const { getByPlaceholderText, getByText } = await render(
      <Provider store={store}>
        <LoginScreen />
      </Provider>
    );

    await fireEvent.changeText(getByPlaceholderText('Email'), 'test@example.com');
    await fireEvent.changeText(getByPlaceholderText('Password'), 'password');
    await fireEvent.press(getByText('Login'));

    const actions = store.getActions();
    expect(actions).toEqual([
      {
        type: 'auth/login',
        payload: { email: 'test@example.com', password: 'password' },
      },
    ]);
  });
});
