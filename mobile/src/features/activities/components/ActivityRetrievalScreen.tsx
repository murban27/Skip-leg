import React, { useEffect } from 'react';
import { View, Text, FlatList, StyleSheet } from 'react-native';
import { useDispatch, useSelector } from 'react-redux';
import { fetchActivities } from '../state/activitiesSlice';

const ActivityRetrievalScreen = () => {
  const dispatch = useDispatch();
  const activities = useSelector((state) => state.activities.activities);

  useEffect(() => {
    dispatch(fetchActivities());
  }, [dispatch]);

  return (
    <View style={styles.container}>
      <FlatList
        data={activities}
        keyExtractor={(item, index) => index.toString()}
        renderItem={({ item }) => (
          <View style={styles.activityItem}>
            <Text>Type: {item.type}</Text>
            <Text>Duration: {item.duration} minutes</Text>
            <Text>Intensity: {item.intensity}</Text>
          </View>
        )}
      />
    </View>
  );
};

const styles = StyleSheet.create({
  container: {
    flex: 1,
    padding: 16,
  },
  activityItem: {
    padding: 16,
    borderBottomWidth: 1,
    borderBottomColor: '#ccc',
  },
});

export default ActivityRetrievalScreen;