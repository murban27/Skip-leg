import React from 'react';
import { Text } from 'react-native';
import { render } from '@testing-library/react-native';
import Card from '../Card';

describe('Card', () => {
  it('should render children', async () => {
    const { getByText } = await render(
      <Card>
        <Text>Test Card</Text>
      </Card>
    );
    expect(getByText('Test Card')).toBeDefined();
  });
});
