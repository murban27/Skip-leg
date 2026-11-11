import React from 'react';
import { render } from '@testing-library/react-native';
import ActivityRetrievalScreen from '../ActivityRetrievalScreen';
import { Provider } from 'react-redux';
import configureStore from 'redux-mock-store';

const mockStore = configureStore([]);

describe('ActivityRetrievalScreen', () => {
  it('renders correctly', () => {
    const store = mockStore({
      activities: {
        activities: [
          { type: 'Running', duration: 30, intensity: 'High' },
          { type: 'Cycling', duration: 45, intensity: 'Medium' },
        ],
      },
    });

    const { getByText } = render(
      <Provider store={store}>
        <ActivityRetrievalScreen />
      </Provider>
    );

    expect(getByText('Type: Running')).toBeTruthy();
    expect(getByText('Duration: 30 minutes')).toBeTruthy();
    expect(getByText('Intensity: High')).toBeTruthy();
    expect(getByText('Type: Cycling')).toBeTruthy();
    expect(getByText('Duration: 45 minutes')).toBeTruthy();
    expect(getByText('Intensity: Medium')).toBeTruthy();
  });
});