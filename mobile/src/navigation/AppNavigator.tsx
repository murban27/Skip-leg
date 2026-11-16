import React from 'react';
import { createStackNavigator } from '@react-navigation/stack';
import { NavigationContainer } from '@react-navigation/native';
import LoginScreen from '../features/auth/components/LoginScreen';
import ActivityLoggingScreen from '../features/activities/components/ActivityLoggingScreen';
import ActivityRetrievalScreen from '../features/activities/components/ActivityRetrievalScreen';
import RealTimeUpdatesScreen from '../features/activities/components/RealTimeUpdatesScreen';
import WorkoutsHomeScreen from '../features/workouts/components/WorkoutsHomeScreen';

export type RootStackParamList = {
  Login: undefined;
  WorkoutsHome: undefined;
  ActivityLogging: undefined;
  ActivityRetrieval: undefined;
  RealTimeUpdates: undefined;
};

const Stack = createStackNavigator<RootStackParamList>();

const AppNavigator = () => {
  return (
    <NavigationContainer>
      <Stack.Navigator initialRouteName="Login">
        <Stack.Screen name="Login" component={LoginScreen} />
        <Stack.Screen name="WorkoutsHome" component={WorkoutsHomeScreen} />
        <Stack.Screen name="ActivityLogging" component={ActivityLoggingScreen} />
        <Stack.Screen name="ActivityRetrieval" component={ActivityRetrievalScreen} />
        <Stack.Screen name="RealTimeUpdates" component={RealTimeUpdatesScreen} />
      </Stack.Navigator>
    </NavigationContainer>
  );
};

export default AppNavigator;
