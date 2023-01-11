import { initializeApp } from 'firebase/app';
import { getAuth } from 'firebase/auth';

const firebaseConfig = {
    apiKey: "AIzaSyA-civks4YSSeTHJv9KthQPONwIT4fwjs0",
    authDomain: "chamados-fbapi.firebaseapp.com",
    projectId: "chamados-fbapi",
    storageBucket: "chamados-fbapi.appspot.com",
    messagingSenderId: "960473001812",
    appId: "1:960473001812:web:d94e0f5acfc8d8310e62c1"
};
  

const app = initializeApp(firebaseConfig);
export const auth = getAuth(app);