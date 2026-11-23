module.exports = {
  preset: '@react-native/jest-preset',
  setupFiles: ['<rootDir>/jest.setup.js'],
  testTimeout: 20000,
  transformIgnorePatterns: [
    'node_modules/(?!((jest-)?react-native|@react-native(-community)?|@react-navigation|expo(nent)?|@expo(nent)?/.*|react-native-gesture-handler|react-native-screens|react-native-safe-area-context|react-redux|@reduxjs/toolkit|redux|redux-mock-store|immer|@testing-library/react-native|test-renderer)/)',
  ],
};
