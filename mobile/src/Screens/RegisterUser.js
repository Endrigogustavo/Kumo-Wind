import React, { useEffect, useState } from "react";
import { View, Image, TextInput, TouchableOpacity, Text, Button } from "react-native";
import AsyncStorage from '@react-native-async-storage/async-storage';
import axios from "axios";
import styles from '../Style/StyleLogin'

export default function RegisterScreen({ navigation }) {
    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");
    const [phone, setPhone] = useState("");
    const [name, setName] = useState("");
    const [error, setError] = useState('');
    const [loading, setLoading] = useState(false);

    const handleRegister = async (email, password, phone, name) => {
        try {
            setLoading(true);
            const loginData = { email: String(email), password: String(password), name: String(name), phone: String(phone) };

            const response = await axios.post(
                'https://kumowind-api-3ris.onrender.com/auth/register',
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

            <TextInput
                style={styles.input}
                placeholder="Nome"
                placeholderTextColor="#FFF"
                value={name}
                onChangeText={setName}
                
            />

            <TextInput
                style={styles.input}
                placeholder="Telefone"
                placeholderTextColor="#FFF"
                value={phone}
                onChangeText={setPhone}
                keyboardType="phone-pad"
            />

            <TouchableOpacity style={styles.customButton} >
                <Button title={loading ? "Carregando..." : "Register"} disabled={loading} color={'#FFF'} onPress={() => handleRegister(email, password, phone, name)} />
                {error && <Text>{error}</Text>}
            </TouchableOpacity>

        </View>
    );
}

