import React, { useState } from "react";
import { View, Image, TextInput, TouchableOpacity, Text, Button } from "react-native";
import AsyncStorage from '@react-native-async-storage/async-storage';
import axios from "axios";
import styles from '../Style/StyleCreateArt'
import * as ImagePicker from "expo-image-picker";


export default function CreateArt({ navigation }) {
    const [image, setImage] = useState(null);
    const [title, setTitle] = useState("");
    const [description, setDescription] = useState("");
    const [uploading, setUploading] = useState(false);

    const pickImage = async () => {
        let result = await ImagePicker.launchImageLibraryAsync({
            mediaTypes: ImagePicker.MediaTypeOptions.Images,
            allowsEditing: true,
            quality: 1,
        });

        if (!result.canceled) {
            setImage(result.assets[0].uri);
        }
    };

    const Create = async () => {
        setUploading(true);
        const formData = new FormData();
        formData.append("file", {
            uri: image,
            name: "image.jpg",
            type: "image/jpeg",
        });
        formData.append("title", title);
        formData.append("description", description);

        try {
            const token = await AsyncStorage.getItem('userToken');
            const response = await axios.post(
                'https://kumowind-api-3ris.onrender.com/art/create',
                formData,
                {
                    headers:
                    {
                        'Content-Type': 'multipart/form-data',
                        'Authorization': token
                    }
                }
            );

            if (response.status === 200) {
                navigation.replace('UserHome')
            } else {
                setError(response.data.message || 'Erro ao criar imagem');
            }
        } catch (error) {
            setError('Erro na requisição: ' + error.message);
        }
    };


    return (
        <View style={styles.container}>

            <Text style={styles.title}>Criar imagem</Text>

            <TextInput
                style={styles.input}
                placeholder="Titulo"
                placeholderTextColor="#FFF"
                value={title}
                onChangeText={setTitle}
                keyboardType="email-address"
            />

            <TextInput
                style={styles.input}
                placeholder="Descrição"
                placeholderTextColor="#FFF"
                value={description}
                onChangeText={setDescription}
            />

            <TouchableOpacity onPress={pickImage} style={styles.button}>
                <Text style={styles.buttonText}>Selecionar Imagem</Text>
            </TouchableOpacity>
            {image && <Image source={{ uri: image }} style={{ borderRadius: 15, width: 200, height: 200, marginVertical: 10 }} />}

            <TouchableOpacity style={styles.customButton} >
                <Button style={styles.buttonText} title={uploading ? "Enviando..." : "Enviar Imagem"} onPress={Create} disabled={uploading} />
            </TouchableOpacity>


        </View>
    );
}

