import React from 'react';
import { render, fireEvent } from '@testing-library/react-native';
import AppButton from '../AppButton';

describe('AppButton', () => {
  it('should fire onPress when not disabled', async () => {
    const onPressMock = jest.fn();
    const { getByText } = await render(
      <AppButton label="Test Button" onPress={onPressMock} />
    );
    fireEvent.press(getByText('Test Button'));
    expect(onPressMock).toHaveBeenCalled();
  });

  it('should not fire onPress when disabled', async () => {
    const onPressMock = jest.fn();
    const { getByText } = await render(
      <AppButton label="Test Button" onPress={onPressMock} disabled />
    );
    fireEvent.press(getByText('Test Button'));
    expect(onPressMock).not.toHaveBeenCalled();
  });
});
