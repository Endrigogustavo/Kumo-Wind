import React, { useEffect, useState } from "react";
import { View, Image, TextInput, TouchableOpacity, Text, Button } from "react-native";
import AsyncStorage from '@react-native-async-storage/async-storage';
import axios from "axios";
import styles from '../Style/StyleLogin'
import { useNavigation } from '@react-navigation/native';

export default function LoginScreen() {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [error, setError] = useState('');
  const [loading, setLoading] = useState(false);

  const navigation = useNavigation();

  const handleLogin = async (email, password) => {
    try {
      setLoading(true);
      const loginData = { email: String(email), password: String(password) };

      const response = await axios.post(
        'https://kumowind-api-3ris.onrender.com/auth/login',
        loginData,
        { headers: { 'Content-Type': 'application/json' } }
      );

      if (response.status === 200) {
        const tokenAuth = "Bearer " + response.data.token;
        AsyncStorage.setItem('userToken', tokenAuth).then(() => {
          navigation.navigate('UserHome');
        });
      } else {
        setError(response.data.message || 'Erro ao fazer login');
      }
    } catch (error) {
      setError('Erro na requisição: ' + error.message);
    }
    finally {
      setLoading(false);
    }
  };

  return (
    <View style={styles.container}>
      <Image source={{ uri: 'https://i.ibb.co/jZgs3gKs/Logo.png' }} style={styles.logo} />

      <Text style={styles.title}>Bem-vindo ao Kumo Wind</Text>

      <TextInput
        style={styles.input}
        placeholder="Email"
        placeholderTextColor="#FFF"
        value={email}
        onChangeText={setEmail}
        keyboardType="email-address"
      />

      <TextInput
        style={styles.input}
        placeholder="Senha"
        placeholderTextColor="#FFF"
        value={password}
        onChangeText={setPassword}
        secureTextEntry
      />

      <TouchableOpacity style={styles.customButton} >
        <Button title={loading ? "Carregando..." : "Login"} disabled={loading} color={'#FFF'} onPress={() => handleLogin(email, password)} />
        {error && <Text>{error}</Text>}
      </TouchableOpacity>

      <Button title={"Register"}  color={'#FFF'}  onPress={() => navigation.navigate('Register')} />
      
    </View>
  );
}

