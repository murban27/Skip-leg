module.exports = {
  preset: '@react-native/jest-preset',
  transformIgnorePatterns: [
    'node_modules/(?!((jest-)?react-native|@react-native(-community)?|@react-navigation|expo(nent)?|@expo(nent)?/.*|react-redux|@reduxjs/toolkit|redux|redux-mock-store|immer|@testing-library/react-native|test-renderer)/)',
  ],
};
