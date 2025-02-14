import { StyleSheet } from 'react-native';
import Home from './Screens/HomeScreen';
import Login from './Screens/Login';
import UserHome from './Screens/ArtGalery';
import CreateArt from './Screens/CreateArt';
import Register from './Screens/RegisterUser';
import MyArts from './Screens/MyArt';
import { NavigationContainer } from '@react-navigation/native';
import { createStackNavigator } from '@react-navigation/stack';
import { createBottomTabNavigator } from '@react-navigation/bottom-tabs';
import { Ionicons } from '@expo/vector-icons';

const Stack = createStackNavigator();
const Tab = createBottomTabNavigator();

// Barra de navegação inferior
function BottomTabs() {
  
  return (
    <Tab.Navigator
      screenOptions={({ route }) => ({
        tabBarIcon: ({ focused, color, size }) => {
          let iconName;

          if (route.name === 'Arts') {
            iconName = focused ? 'cloud' : 'image-outline';
          } else if (route.name === 'Create') {
            iconName = focused ? 'add-circle-outline' : 'add';
          } else if (route.name === 'User') {
            iconName = focused ? 'person' : 'person-outline';
          }

          return <Ionicons name={iconName} size={size} color={color} />;
        },
        tabBarStyle: {
          backgroundColor: '#312C50',
          borderTopWidth: 2,
          borderColor: '#5D4B8E',
        },
        tabBarActiveTintColor: '#5D4B8E',
        tabBarInactiveTintColor: '#5D4B8E',
      })}
    >
      <Tab.Screen
        name="Arts"
        component={UserHome}
        options={{
          headerShown: false,
        }}
      />
      <Tab.Screen
        name="Create"
        component={CreateArt}
        options={{
          headerShown: false,
        }}
      />
      <Tab.Screen
        name="User"
        component={MyArts}
        options={{
          headerShown: false,
        }}
      />

    </Tab.Navigator>
  );
}

export default function App() {
  return (
    <NavigationContainer>
      <Stack.Navigator>
        <Stack.Screen name="Home" component={Home} options={{
          title: '',
          headerShown: false,

        }} />

        <Stack.Screen name="Login" component={Login} options={{

          title: '',
          headerShown: false,

        }} />

        <Stack.Screen
          name="UserHome"
          component={BottomTabs}
          options={{
            title: '',
            headerShown: false,
          }}
        />

        <Stack.Screen
          name="Create"
          component={BottomTabs}
          options={{
            title: '',
            headerShown: false,
          }}
        />

        <Stack.Screen
          name="Register"
          component={Register}
          options={{
            headerShown: false,
          }}
        />
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
