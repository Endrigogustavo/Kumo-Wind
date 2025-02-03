
import React, { useState, useEffect } from 'react';
import { StyleSheet, Dimensions, ScrollView, Image, ImageBackground, Text } from 'react-native';
import AsyncStorage from '@react-native-async-storage/async-storage';
import { Block } from 'galio-framework';
import styles from '../Style/StyleArtGalery'
import axios from 'axios';

const { width } = Dimensions.get('screen');


const Profile = () => {

  const [error, setError] = useState(null);
  const [arts, setArts] = useState([]);

  const fetchData = async () => {
    try {
      const token = await AsyncStorage.getItem('userToken');

      const response = await axios.get('https://kumowind-api-3ris.onrender.com/art/getArts/', {
        headers: {
          'Authorization': token,
        },
      });

      if (!response.status === 200) {
        const errorText = await response.text();
        throw new Error(`Erro na requisição: ${errorText}`);
      }
      setArts(response.data);

    } catch (error) {
      setError(error.message || 'Erro desconhecido');
      console.error('Erro:', error);
    }
  };

  useEffect(() => {
    fetchData();
  }, []);

  return (
    <ScrollView showsVerticalScrollIndicator={false} style={styles.home}>
      <Block flex style={styles.profile}>
        <Block flex>
          <ImageBackground
            source={require("../assets/UserHome.jpg")}
            style={styles.profileContainer}
            imageStyle={styles.profileImage}>
            <Block flex style={styles.profileDetails}>
              <Block style={styles.profileTexts}>
                <Block row space="between"></Block>
              </Block>
            </Block>
          </ImageBackground>
        </Block>

        {/* Seção de Obras de Arte */}
        <Block flex style={styles.options}>
          <ScrollView showsVerticalScrollIndicator={false}>
            <Block row space="between" style={{ paddingVertical: 16, alignItems: 'baseline' }}>
              <Text style={styles.text}>My Artworks</Text>
            </Block>
            <Block style={{ paddingBottom: 20 }}>
              <Block row space="between" style={{ flexWrap: 'wrap' }}>
                {/* Verificação se 'arts' tem dados antes de mapear */}
                {arts && arts.length > 0 ? (
                  arts.map((art, index) => (
                    <Block key={index} style={styles.categoryItemArt}>
                      <Image
                        source={{ uri: art.filePath }}
                        key={`art-${index}`}
                        resizeMode="cover"
                        style={styles.thumb}
                      />

                      <Text style={styles.categoryText}>
                        {art.title}
                      </Text>
                    </Block>

                  ))
                ) : (
                  <Text style={{ color: '#FFF', textAlign: 'center' }}>Nenhuma arte disponível.</Text>
                )}
              </Block>
            </Block>
          </ScrollView>
        </Block>

        {/* Exibindo erro, se houver */}
        {error && <Text style={{ color: '#f00', textAlign: 'center' }}>{error}</Text>}
      </Block>
    </ScrollView>
  );
};

export default Profile;
