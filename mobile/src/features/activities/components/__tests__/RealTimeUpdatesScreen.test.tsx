import React from 'react';
import { render, act } from '@testing-library/react-native';
import RealTimeUpdatesScreen from '../RealTimeUpdatesScreen';

// Mock WebSocket
class MockWebSocket {
  static lastInstance: MockWebSocket | null = null;
  url: string;
  onmessage: ((event: { data: string }) => void) | null = null;

  constructor(url: string) {
    this.url = url;
    MockWebSocket.lastInstance = this;
  }
  close() {}
}

// Replace the global WebSocket with the mock
(global as any).WebSocket = MockWebSocket;

describe('RealTimeUpdatesScreen', () => {
  it('renders correctly', async () => {
    const { toJSON } = await render(<RealTimeUpdatesScreen />);
    expect(toJSON()).toBeTruthy();
  });

  it('handles WebSocket messages', async () => {
    const { getByText } = await render(<RealTimeUpdatesScreen />);
    const ws = MockWebSocket.lastInstance!;
    await act(async () => {
      ws.onmessage!({ data: JSON.stringify({ message: 'New update' }) });
    });
    expect(getByText('Update: New update')).toBeTruthy();
  });
});
