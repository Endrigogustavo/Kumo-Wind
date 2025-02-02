import { StyleSheet, Dimensions } from 'react-native';

const { width } = Dimensions.get('screen');
const thumbMeasure = (width - 48 - 32) / 3;

export default StyleSheet.create({
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
  