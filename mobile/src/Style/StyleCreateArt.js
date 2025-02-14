import { PixelRatio, StyleSheet } from "react-native";

export default StyleSheet.create({
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
      backgroundColor: "#FFF",
      marginBottom: 15,
      color: "#312C50",
      fontSize: 16,
      borderColor: "#A48AC1",
      
      borderWidth: 1,
    },
    customButton: {
      backgroundColor: "#594F80",
      paddingVertical: 0,
      paddingHorizontal: 30,
      borderRadius: 13,
      position: "absolute",
      zIndex: 10,
      bottom: 80,
      width: 200,
      borderColor: "#312C50",
      borderWidth: 5,
  
    },

        button: {
          padding: 10,
          borderRadius: 5,
          alignItems: 'center',
        },
        buttonText: {
          color: '#fff', 
          fontSize: 16,
          fontWeight: 'bold',
        },
      
  });
  