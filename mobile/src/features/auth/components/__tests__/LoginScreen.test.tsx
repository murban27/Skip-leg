import React from 'react';
import { render, fireEvent } from '@testing-library/react-native';
import LoginScreen from '../LoginScreen';
import { Provider } from 'react-redux';
import configureStore from 'redux-mock-store';

const mockStore = configureStore([]);

describe('LoginScreen', () => {
  it('renders correctly', () => {
    const store = mockStore({
      auth: { isAuthenticated: false, token: null },
    });

    const { getByPlaceholderText, getByText } = render(
      <Provider store={store}>
        <LoginScreen />
      </Provider>
    );

    expect(getByPlaceholderText('Email')).toBeTruthy();
    expect(getByPlaceholderText('Password')).toBeTruthy();
    expect(getByText('Login')).toBeTruthy();
  });

  it('dispatches login action on button press', () => {
    const store = mockStore({
      auth: { isAuthenticated: false, token: null },
    });

    const { getByPlaceholderText, getByText } = render(
      <Provider store={store}>
        <LoginScreen />
      </Provider>
    );

    fireEvent.changeText(getByPlaceholderText('Email'), 'test@example.com');
    fireEvent.changeText(getByPlaceholderText('Password'), 'password');
    fireEvent.press(getByText('Login'));

    const actions = store.getActions();
    expect(actions).toEqual([
      {
        type: 'auth/login',
        payload: { email: 'test@example.com', password: 'password' },
      },
    ]);
  });
});