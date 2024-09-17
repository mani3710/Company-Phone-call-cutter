import React from "react";
import { StyleSheet, Text, View, NativeModules,PermissionsAndroid } from 'react-native';
const { PhoneCallModule } = NativeModules;

const App = ()=>{
  const requestPermission = async()=>{
    try {
      const granted = await PermissionsAndroid.request(
        PermissionsAndroid.PERMISSIONS.READ_PHONE_STATE,
        {
          title: "Phone State Permission",
          message: "This app needs access to your phone state.",
          buttonNeutral: "Ask Me Later",
          buttonNegative: "Cancel",
          buttonPositive: "OK",
        }
      );
      const granted2 = await PermissionsAndroid.request(
        PermissionsAndroid.PERMISSIONS.ANSWER_PHONE_CALLS,
        {
          title: 'Answer Phone Calls Permission',
          message: 'This app needs access to answer or reject phone calls.',
          buttonNeutral: 'Ask Me Later',
          buttonNegative: 'Cancel',
          buttonPositive: 'OK',
        },
      );
      const granted3 = await PermissionsAndroid.request(
        PermissionsAndroid.PERMISSIONS.READ_CALL_LOG,
        {
          title: 'Answer Phone Calls Permission',
          message: 'This app needs access to answer or reject phone calls.',
          buttonNeutral: 'Ask Me Later',
          buttonNegative: 'Cancel',
          buttonPositive: 'OK',
        },
      );
      if (granted === PermissionsAndroid.RESULTS.GRANTED) {
        console.log("You can read phone state");
        PhoneCallModule.listenForIncomingCall();
      } else {
        console.log("Phone state permission denied");
      }
    } catch (err) {
      console.warn(err);
    }
  }
  return(
    <View style={styles.containerStyle}>
    <Text
    onPress={()=>{
      try{
      console.log("pressed");
      requestPermission();
      }catch(e){
        console.log("Error",e)
      }
    }}
    
    style={styles.connectTextStyle}>Connect</Text>
    </View>
  );
}
export default App;
const styles = StyleSheet.create({
  containerStyle:{
    flex:1,
    justifyContent:"center",
    alignItems:"center",
    backgroundColor:"white"
  },
  connectTextStyle:{
    color:"black"
  }
})