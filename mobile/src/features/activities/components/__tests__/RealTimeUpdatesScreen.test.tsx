import React from 'react';
import { render } from '@testing-library/react-native';
import RealTimeUpdatesScreen from '../RealTimeUpdatesScreen';

// Mock WebSocket
class MockWebSocket {
  constructor(url) {
    this.url = url;
    this.onmessage = jest.fn();
  }
  close() {}
}

// Replace the global WebSocket with the mock
global.WebSocket = MockWebSocket;

describe('RealTimeUpdatesScreen', () => {
  it('renders correctly', () => {
    const { getByText } = render(<RealTimeUpdatesScreen />);
    expect(getByText('Update:')).toBeTruthy();
  });

  it('handles WebSocket messages', () => {
    const { getByText } = render(<RealTimeUpdatesScreen />);
    const ws = new WebSocket('ws://localhost:8080/updates');
    ws.onmessage({ data: JSON.stringify({ message: 'New update' }) });
    expect(getByText('Update: New update')).toBeTruthy();
  });
});