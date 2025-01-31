import React, { useState } from "react";
import { PixelRatio, View, StyleSheet, Image, TextInput, TouchableOpacity, Text } from "react-native";

export default function LoginScreen({navigation}) {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");


  return (
    <View style={styles.container}>
      {/* Logo do App */}
      <Image source={{ uri: 'https://i.ibb.co/jZgs3gKs/Logo.png' }} style={styles.logo} />

      {/* Título */}
      <Text style={styles.title}>Bem-vindo ao Kumo Wind</Text>

      {/* Input de Email */}
      <TextInput
        style={styles.input}
        placeholder="Email"
        placeholderTextColor="#888"
        value={email}
        onChangeText={setEmail}
        keyboardType="email-address"
      />

      {/* Input de Senha */}
      <TextInput
        style={styles.input}
        placeholder="Senha"
        placeholderTextColor="#888"
        value={password}
        onChangeText={setPassword}
        secureTextEntry
      />

      {/* Botão de Login */}
      <TouchableOpacity style={styles.customButton} onPress={() => navigation.navigate('User')}>
        <Text style={styles.buttonText}>Log In</Text>
        
      </TouchableOpacity>

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
    marginTop: -300,
    marginBottom: 20,
    resizeMode: "contain",
    position: "relative",
  },
  title: {
    textAlign: "center",
    fontWeight: "bold",
    fontSize: 24,
    marginBottom: 20,
    color: "#FFF",
  },
  description: {
    textAlign: "center",
    color: "#FFF",
  },
  image: {
    width: 450,
    height: 500,
    resizeMode: "cover",
    position: "relative",
    bottom: 0,
  },
  input: {
    width: "80%",
    paddingVertical: 12,
    paddingHorizontal: 15,
    borderRadius: 15,
    backgroundColor: "#594F80",
    marginBottom: 15,
    color: "#312C50",
    fontSize: 16,
    borderColor: "#A48AC1",
    borderWidth: 1,
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
    borderWidth: 5,
    
  },
  buttonText: {
    color: "#FFF",
    fontSize: 18,
    fontWeight: "bold",
    textAlign: "center",
    
  },
});
