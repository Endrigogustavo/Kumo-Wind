import React from "react";

import { View, Image , TouchableOpacity} from "react-native";
import { Text } from "react-native-paper";
import styles from '../Style/StyleHome'

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
};
