import React, { useEffect, useState } from 'react';
import { View, Text, FlatList, StyleSheet } from 'react-native';

const RealTimeUpdatesScreen = () => {
  const [updates, setUpdates] = useState<{ message: string }[]>([]);

  useEffect(() => {
    const ws = new WebSocket('wss://api.fitnessapp.com/updates');

    ws.onmessage = (event) => {
      const newUpdate = JSON.parse(event.data);
      setUpdates((prevUpdates) => [...prevUpdates, newUpdate]);
    };

    return () => {
      ws.close();
    };
  }, []);

  return (
    <View style={styles.container}>
      <FlatList
        data={updates}
        keyExtractor={(item, index) => index.toString()}
        renderItem={({ item }) => (
          <View style={styles.updateItem}>
            <Text>Update: {item.message}</Text>
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
  updateItem: {
    padding: 16,
    borderBottomWidth: 1,
    borderBottomColor: '#ccc',
  },
});

export default RealTimeUpdatesScreen;