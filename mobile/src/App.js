import { StyleSheet } from 'react-native';
import Home from './Screens/HomeScreen';
import Login from './Screens/Login';
import UserHome from './Screens/ArtGalery';
import CreateArt from './Screens/CreateArt';
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

          if (route.name === 'UserHome') {
            iconName = focused ? 'person' : 'person-outline';
          } else if (route.name === 'AnotherScreen') {
            iconName = focused ? 'grid' : 'grid-outline';
          } else if (route.name === 'teste') {
            iconName = focused ? 'grid' : 'grid-outline';
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
        name="UserHome"
        component={UserHome}
        options={{
          headerShown: false,
        }}
      />
      <Tab.Screen
        name="AnotherScreen"
        component={Login}
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
    </Tab.Navigator>
  );
}

export default function App() {
  return (
    <NavigationContainer>
      <Stack.Navigator>
        {/* Tela Home - Sem barra de navegação inferior e sem topbar */}
        <Stack.Screen name="Home" component={Home} options={{
          title: '',
          headerStyle: {
            backgroundColor: '#312C50',
          },
          headerTintColor: '#312C50',
          headerTitleStyle: {
            fontWeight: 'bold',
          },
        }} />

        {/* Tela Login - Sem barra de navegação inferior */}
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

        {/* Tela UserHome - Com barra de navegação inferior e sem topbar */}
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
