import React from 'react';
import { render } from '@testing-library/react-native';
import AppText from '../AppText';

describe('AppText', () => {
  it('should render with body variant by default', async () => {
    const { getByText } = await render(<AppText>Test Text</AppText>);
    expect(getByText('Test Text')).toBeDefined();
  });

  it('should render with title variant', async () => {
    const { getByText } = await render(
      <AppText variant="title">Title Text</AppText>
    );
    expect(getByText('Title Text')).toBeDefined();
  });
});
