import React from 'react';
import { render } from '@testing-library/react-native';
import { Provider } from 'react-redux';
import { SafeAreaProvider } from 'react-native-safe-area-context';
import WorkoutsHomeScreen from '../components/WorkoutsHomeScreen';
import store from '../../../shared/state/store';

const initialMetrics = {
  frame: { x: 0, y: 0, width: 0, height: 0 },
  insets: { top: 0, left: 0, right: 0, bottom: 0 }
};

describe('WorkoutsHomeScreen', () => {
  it('should render "My Workouts" and all workout names', async () => {
    const { getByText } = await render(
      <Provider store={store}>
        <SafeAreaProvider initialMetrics={initialMetrics}>
          <WorkoutsHomeScreen />
        </SafeAreaProvider>
      </Provider>
    );
    expect(getByText('My Workouts')).toBeTruthy();
    expect(getByText('Upper Body')).toBeTruthy();
    expect(getByText('Lower Body')).toBeTruthy();
    expect(getByText('Full Body')).toBeTruthy();
  });
});