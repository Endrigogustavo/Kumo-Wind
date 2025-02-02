import React, { useState, useEffect } from 'react';
import { StyleSheet, Dimensions, ScrollView, Image, ImageBackground, Text } from 'react-native';
import AsyncStorage from '@react-native-async-storage/async-storage';
import { Block } from 'galio-framework';

const { width } = Dimensions.get('screen');
const thumbMeasure = (width - 48 - 32) / 3;

const Profile = () => {

  const [error, setError] = useState(null);
  const [arts, setArts] = useState([]);

  const fetchData = async () => {
    try {
      const token = await AsyncStorage.getItem('userToken');
      console.log(token)
      const response = await fetch('https://kumowind-api-3ris.onrender.com/art/getArts/', {
        method: 'GET',
        headers: {
          'Authorization': token,
        },
      });
  
      if (!response.ok) {
        const errorText = await response.text();
        throw new Error(`Erro na requisição: ${errorText}`);
      }
  
      const data = await response.json();
      console.log('Dados recebidos:', data);
      setArts(data);
  
    } catch (error) {
      setError(error.message || 'Erro desconhecido');
      console.error('Erro:', error);
    }
  };
  
  useEffect(() => {
    fetchData();
  }, []);

  const categories = [
    { name: 'Painting', image: require('../assets/images/painting.jpg') },
    { name: 'Sculpture', image: require('../assets/images/sculpture.jpg') },
    { name: 'Photography', image: require('../assets/images/photography.jpg') },
    { name: 'Digital Art', image: require('../assets/images/digital.jpg') },
    { name: 'IA', image: require('../assets/images/ia.jpg') },
    { name: 'Printmaking', image: require('../assets/images/printmaking.jpeg') },
  ];

  const TopWeek = [
    { name: 'Painting', image: require('../assets/topart/1.jpg') },
    { name: 'Sculpture', image: require('../assets/topart/2.jpg') },
    { name: 'Photography', image: require('../assets/topart/3.jpg') },
  ];

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

        {/* Seção de Categorias */}
        <Block style={styles.categories}>
          <Text style={styles.text}>Art Categories</Text>
          <ScrollView horizontal showsHorizontalScrollIndicator={false} style={styles.categoriesScroll}>
            {categories.map((category, index) => (
              <Block key={index} style={styles.categoryItem}>
                <Image
                  source={category.image}
                  resizeMode="cover"
                  style={styles.imgcat}
                />
                <Text style={styles.categoryText}>
                  {category.name}
                </Text>
              </Block>
            ))}
          </ScrollView>
        </Block>

        <Block style={styles.categories}>
          <Text style={styles.text}>Top of week</Text>
          <ScrollView horizontal showsHorizontalScrollIndicator={false} style={styles.categoriesScroll}>
            {TopWeek.map((TopWeek, index) => (
              <Block key={index} style={styles.top}>
                <Image
                  source={TopWeek.image}
                  resizeMode="cover"
                  style={styles.imgtop}
                />
              </Block>
            ))}
          </ScrollView>
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
                    <Image
                      source={{ uri: art.filePath }}
                      key={`art-${index}`}
                      resizeMode="cover"
                      style={styles.thumb}
                    />
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

const styles = StyleSheet.create({
  home: {
    backgroundColor: "#312C50",
  },
  text: {
    color: '#FFF',
    fontSize: 25,
    fontWeight: 'bold',
    marginTop: 10,
  },
  profileImage: {
    width: 400 * 1.1,
    height: 'auto',
    resizeMode: "cover",
  },
  profileContainer: {
    width: 300,
    height: 600 / 2,
  },
  profileDetails: {
    paddingTop: 10 * 4,
    justifyContent: 'flex-end',
    position: 'relative',
  },
  profileTexts: {
    paddingHorizontal: 10 * 2,
    paddingVertical: 10 * 2,
    zIndex: 2
  },
  categories: {
    padding: 5,
    backgroundColor: "#312C50",
    marginTop: 10,
  },
  categoriesScroll: {
    marginTop: 10,
  },
  categoryItem: {
    paddingVertical: 3,
    paddingHorizontal: 5,
    backgroundColor: "#5D4B8E",
    borderRadius: 10,
    marginRight: 10,
    
  },

  categoryText: {
    paddingVertical: 3,
    paddingHorizontal: 5,
    backgroundColor: "#5D4B8E",
    borderRadius: 10,
    marginRight: 10,
    color: '#FFF',
    fontSize: 15,
  },
  imgcat: {
    borderRadius: 10,
    marginVertical: 10,
    alignSelf: 'center',
    width: 120,
    height: 120,
    resizeMode: "cover",
  },
  imgtop: {
    borderRadius: 10,
    marginVertical: 10,
    alignSelf: 'center',
    width: 190,
    height: 120,
    resizeMode: "cover",
  },
  top: {
    paddingVertical: 3,
    paddingHorizontal: 5,
    borderRadius: 10,
    marginRight: 10,
  },
  options: {
    padding: 10,
    backgroundColor: "#312C50",
    marginTop: -10,
    margin: 5,
  },
  thumb: {
    borderRadius: 10,
    marginVertical: 7,
    alignSelf: 'center',
    width: 185,
    height: 270,
    borderWidth: 2,
    borderColor: "#5D4B8E",
  },
});

export default Profile;
