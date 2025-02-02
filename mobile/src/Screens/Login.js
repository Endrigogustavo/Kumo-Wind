import React, { useState } from "react";
import { View, Image, TextInput, TouchableOpacity, Text, Button } from "react-native";
import AsyncStorage from '@react-native-async-storage/async-storage';
import axios from "axios";
import styles from '../Style/StyleLogin'

export default function LoginScreen({ navigation }) {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [error, setError] = useState('');

  const handleLogin = async (email, password) => {
    try {
      const loginData = { email: String(email), password: String(password) };

      const response = await axios.post(
        'https://kumowind-api-3ris.onrender.com/auth/login',
        loginData,
        { headers: { 'Content-Type': 'application/json' } }
      );

      if (response.status === 200) {
        const tokenAuth = "Bearer " + response.data.token;
        await AsyncStorage.setItem('userToken', tokenAuth);
        navigation.navigate('UserHome')
      } else {
        setError(response.data.message || 'Erro ao fazer login');
      }
    } catch (error) {
      setError('Erro na requisição: ' + error.message);
    }
  };

  return (
    <View style={styles.container}>
      <Image source={{ uri: 'https://i.ibb.co/jZgs3gKs/Logo.png' }} style={styles.logo} />

      <Text style={styles.title}>Bem-vindo ao Kumo Wind</Text>

      <TextInput
        style={styles.input}
        placeholder="Email"
        placeholderTextColor="#888"
        value={email}
        onChangeText={setEmail}
        keyboardType="email-address"
      />

      <TextInput
        style={styles.input}
        placeholder="Senha"
        placeholderTextColor="#888"
        value={password}
        onChangeText={setPassword}
        secureTextEntry
      />

      <TouchableOpacity style={styles.customButton} >
        <Button style={styles.buttonText} title="Login" onPress={() => handleLogin(email, password)} />
        {error && <Text>{error}</Text>}
      </TouchableOpacity>

    </View>
  );
}

