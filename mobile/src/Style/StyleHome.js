import { StyleSheet } from "react-native";
import { PixelRatio } from "react-native";

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
    marginTop: 75,
    marginBottom: 20,
    resizeMode: "contain",
    position: "relative"
  },
  title: {
    textAlign: "center",
    fontWeight: "bold",
    marginBottom: 10,
  },
  description: {
    textAlign: "center",
  },
  image: {
    width: 450,
    height: 500,
    resizeMode: "cover",
    position: "relative",
    bottom: 0,
  },
  customButton: {
    backgroundColor: "#594F80",
    paddingVertical: 15,
    paddingHorizontal: 30,
    borderRadius: 13,
    position: "absolute", 
    zIndex: 10, 
    bottom: 150, 
    width: 150,
    borderColor: "#312C50",
    borderWidth: 3,
  },
  buttonText: {
    color: "#FFF",
    fontSize: 18,
    fontWeight: "bold",
    textAlign: "center",
  },
})