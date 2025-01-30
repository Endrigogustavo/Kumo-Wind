import { StatusBar } from 'expo-status-bar';
import { StyleSheet, Text, View } from 'react-native';
import Home from './Components/HomeScreen'
import Login from './Components/Login'
import UserHome from './Components/UserPage'

import { NavigationContainer } from '@react-navigation/native';
import { createStackNavigator } from '@react-navigation/stack';

export default function App() {

  const Stack = createStackNavigator();

  return (
    <NavigationContainer>
      <Stack.Navigator>
        <Stack.Screen name="Home" component={Home} options={{
            title: '',  
            headerStyle: {
              backgroundColor: '#312C50',  
            },
            headerTintColor: '#fff', 
            headerTitleStyle: {
              fontWeight: 'bold',  
            },
          }} />
        <Stack.Screen name="Login" component={Login} options={{
            title: 'Login',  
            headerStyle: {
              backgroundColor: '#312C50',  
            },
            headerTintColor: '#fff', 
            headerTitleStyle: {
              fontWeight: 'bold',  
            },
          }} />

          <Stack.Screen name="User" component={UserHome} options={{
            title: '',  
            headerShown: false,
          }} />
      </Stack.Navigator>
    </NavigationContainer>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: '#312C50',
    alignItems: 'center',
    justifyContent: 'center',
  },
});
