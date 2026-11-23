import React from 'react';
import { render, fireEvent } from '@testing-library/react-native';
import AppTextInput from '../AppTextInput';

describe('AppTextInput', () => {
  it('should reflect typing', async () => {
    const onChangeTextMock = jest.fn();
    const { getByPlaceholderText } = await render(
      <AppTextInput placeholder="Enter text" onChangeText={onChangeTextMock} />
    );
    const input = getByPlaceholderText('Enter text');
    fireEvent.changeText(input, 'Test Input');
    expect(onChangeTextMock).toHaveBeenCalledWith('Test Input');
  });
});
