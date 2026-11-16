import React from 'react';
import { Text } from 'react-native';
import { SafeAreaProvider } from 'react-native-safe-area-context';
import { render } from '@testing-library/react-native';
import Screen from '../Screen';

const renderWithProvider = (ui: React.ReactElement) =>
  render(
    <SafeAreaProvider
      initialMetrics={{
        frame: { x: 0, y: 0, width: 0, height: 0 },
        insets: { top: 0, left: 0, right: 0, bottom: 0 },
      }}
    >
      {ui}
    </SafeAreaProvider>
  );

describe('Screen', () => {
  it('should render children', async () => {
    const { getByText } = await renderWithProvider(
      <Screen>
        <Text>Test Screen</Text>
      </Screen>
    );
    expect(getByText('Test Screen')).toBeDefined();
  });

  it('should render scrollable content', async () => {
    const { getByText } = await renderWithProvider(
      <Screen scrollable>
        <Text>Scrollable Screen</Text>
      </Screen>
    );
    expect(getByText('Scrollable Screen')).toBeDefined();
  });
});
