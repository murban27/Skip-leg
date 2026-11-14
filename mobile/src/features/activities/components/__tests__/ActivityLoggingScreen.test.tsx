import React from 'react';
import { render, fireEvent } from '@testing-library/react-native';
import ActivityLoggingScreen from '../ActivityLoggingScreen';
import { Provider } from 'react-redux';
import configureStore from 'redux-mock-store';

const mockStore = configureStore([]);

describe('ActivityLoggingScreen', () => {
  it('renders correctly', async () => {
    const store = mockStore({
      activities: { activities: [] },
    });

    const { getByPlaceholderText, getByText } = await render(
      <Provider store={store}>
        <ActivityLoggingScreen />
      </Provider>
    );

    expect(getByPlaceholderText('Activity Type')).toBeTruthy();
    expect(getByPlaceholderText('Duration (minutes)')).toBeTruthy();
    expect(getByPlaceholderText('Intensity')).toBeTruthy();
    expect(getByText('Log Activity')).toBeTruthy();
  });

  it('dispatches logActivity action on button press', async () => {
    const store = mockStore({
      activities: { activities: [] },
    });

    const { getByPlaceholderText, getByText } = await render(
      <Provider store={store}>
        <ActivityLoggingScreen />
      </Provider>
    );

    await fireEvent.changeText(getByPlaceholderText('Activity Type'), 'Running');
    await fireEvent.changeText(getByPlaceholderText('Duration (minutes)'), '30');
    await fireEvent.changeText(getByPlaceholderText('Intensity'), 'High');
    await fireEvent.press(getByText('Log Activity'));

    const actions = store.getActions();
    expect(actions).toEqual([
      {
        type: 'activities/logActivity',
        payload: { type: 'Running', duration: 30, intensity: 'High' },
      },
    ]);
  });
});
