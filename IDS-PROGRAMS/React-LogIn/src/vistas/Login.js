import React from 'react';
import 'firebaseui/dist/firebaseui';
import Firebase from '../server/firebase';
import { render } from '@testing-library/react';

class Login extends React.Component{
    
    state={
        autenticado: false,
        usuario:"",
        firebase:null
    }

    componentDidMount(){
      const firebase = new Firebase();

      firebase.auth.onAuthStateChanged(authUser => {
          authUser
          ? this.setState({
              autenticado: true,
              usuario: firebase.auth.currentUser.email,
              firebase: firebase
          })
          :firebase.firebaseui.start("#firebaseui-auth-container",{
              signInSuccessUrl: "/",
              credentialHelper:"/",
              callbacks:{
                  signInSucccessWithAuthResult:(authResult,redirectUrl)=>{
                      this.setState({
                        autenticado:true,
                        usuario: firebase.auth.currentUser.email,
                        firebase:firebase

                      })
                      return false;
                  }
              }, 
              signInOptions:[
                  {
                      provider:firebase.autorization.EmailAuthProvider.PROVIDER_ID
                  }
              ]
          })

      });

    }
    render(){
        return this.state.autenticado
        ? (
            <React.Fragment>   
                <div>USUARIO LOGEADO {this.state.usuario}</div>
                <a href="#" onClick={()=> { this.state.firebase.auth.signOut().then(success=>{
                        this.setState({
                            autenticado:false
                        })
            })  
                }
                } >SALIR DE LOGEO</a>
            </React.Fragment>
        )
        :<div id="firebaseui-auth-container" ></div>
    }

}

export default Login;