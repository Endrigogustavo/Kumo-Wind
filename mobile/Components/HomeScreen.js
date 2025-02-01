import React from "react";
import { PixelRatio } from "react-native";
import { View, StyleSheet, Image , TouchableOpacity} from "react-native";
import { Card, Text, Button } from "react-native-paper";
import { NavigationContainer } from '@react-navigation/native';
import { createStackNavigator } from '@react-navigation/stack';

export default function HomeScreen({navigation}) {
  return (
    <View style={styles.container}>
      {/* Logo do App */}
      <Image source={{ uri: 'https://i.ibb.co/jZgs3gKs/Logo.png' }}  style={styles.logo} />

      <TouchableOpacity
        style={styles.customButton}
        onPress={() => navigation.navigate('Login')}
      >
        <Text style={styles.buttonText}>Sing In</Text>
      </TouchableOpacity>

      {/* Imagem ilustrativa */}
      <Image source={{ uri: 'https://i.ibb.co/vx2mzP5K/Fundo-Home.png'}} style={styles.image} />
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    justifyContent: "center",
    alignItems: "center",
    padding: 20,
    backgroundColor: "#312C50",
  },
  logo: {
    width: 150 * PixelRatio.get(),
    height: 150 * PixelRatio.get(),
    marginTop: -10,
    marginBottom: 20,
    resizeMode: "contain",
    position: "relative"
  },
  title: {
    textAlign: "center",
    fontWeight: "bold",
    marginBottom: 10,
  },
  description: {
    textAlign: "center",
  },
  image: {
    width: 450,
    height: 500,
    resizeMode: "cover",
    position: "relative",
    bottom: 0,
  },
  customButton: {
    backgroundColor: "#594F80",
    paddingVertical: 15,
    paddingHorizontal: 30,
    borderRadius: 13,
    position: "absolute", 
    zIndex: 10, 
    bottom: 150, 
    width: 150,
    borderColor: "#312C50",
    borderWidth: 3,
  },
  buttonText: {
    color: "#FFF",
    fontSize: 18,
    fontWeight: "bold",
    textAlign: "center",
  },
});
