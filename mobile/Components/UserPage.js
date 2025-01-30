import React from 'react';
import { StyleSheet, Dimensions, ScrollView, Image } from 'react-native';
import { Block, Text } from 'galio-framework';

const { width } = Dimensions.get('screen');
const thumbMeasure = (width - 48 - 32) / 3;

export default class Profile extends React.Component {
  render() {
    return (
      <Block flex style={styles.profile}>
        {/* Seção de Categorias */}
        <Block style={styles.categories}>
          <Text color={"#FFF"} bold size={20}>Art Categories</Text>
          <ScrollView horizontal showsHorizontalScrollIndicator={false} style={styles.categoriesScroll}>
            {['Painting', 'Sculpture', 'Photography', 'Digital Art', 'Printmaking'].map((category, index) => (
              <Block key={index} style={styles.categoryItem}>
                <Text color={"#FFF"}>{category}</Text>
              </Block>
            ))}
          </ScrollView>
        </Block>

        {/* Seção de Obras de Arte */}
        <Block flex style={styles.options}>
          <ScrollView showsVerticalScrollIndicator={false}>
            <Block row space="between" style={{ paddingVertical: 16, alignItems: 'baseline' }}>
              <Text color={"#FFF"} bold size={20}>My Artis</Text>
            </Block>
            <Block style={{ paddingBottom: 20 }}>
              <Block row space="between" style={{ flexWrap: 'wrap' }} >
                {[1, 2, 3, 4, 5, 6, 7, 8, 9].map((index) => (
                  <Image
                    source={{ uri: `https://picsum.photos/200/200?random=${index}` }}
                    key={`viewed-${index}`}  
                    resizeMode="cover"
                    style={styles.thumb}
                  />
                ))}
              </Block>
            </Block>
          </ScrollView>
        </Block>
      </Block>
    );
  }
}

const styles = StyleSheet.create({
  profile: {
    flex: 1,
  },
  categories: {
    padding: 16,
    backgroundColor: "#312C50",
    shadowColor: 'black',
    shadowOffset: { width: 0, height: 0 },
    shadowRadius: 8,
    shadowOpacity: 0.2,
  },
  categoriesScroll: {
    marginTop: 10,
  },
  categoryItem: {
    paddingVertical: 8,
    paddingHorizontal: 12,
    backgroundColor: "#5D4B8E",
    borderRadius: 8,
    marginRight: 10,
  },
  options: {
    padding: 20,
    backgroundColor: "#312C50",
    shadowColor: 'black',
    shadowOffset: { width: 0, height: 0 },
    shadowRadius: 8,
    shadowOpacity: 0.2,
  },
  thumb: {
    borderRadius: 10,
    marginVertical: 4,
    alignSelf: 'center',
    width: thumbMeasure,
    height: 150,
  },
});
