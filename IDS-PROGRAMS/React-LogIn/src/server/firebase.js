import app from 'firebase/app';
import 'firebase/auth';
import * as firebaseui from 'firebaseui';

const   firebaseConfig = {
    apiKey: "AIzaSyBcYZQ6lSVZHzuBYydIxAhon2D07pATLEQ",
    authDomain: "proyectlogin-a13af.firebaseapp.com",
    databaseURL: "https://proyectlogin-a13af.firebaseio.com",
    projectId: "proyectlogin-a13af",
    storageBucket: "proyectlogin-a13af.appspot.com",
    messagingSenderId: "722031372265",
    appId: "1:722031372265:web:eae44e3e52a6fe5f974d78",
    measurementId: "G-TQDW8RW2N8"
  };

  class Firebase {
      constructor(){
          app.initializeApp(firebaseConfig);
          this.auth=app.auth();
          this.autorization=app.auth;
          this.firebaseui=new firebaseui.auth.AuthUI(app.auth());
      }
  }


export default Firebase;  