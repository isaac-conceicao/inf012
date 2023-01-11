import { useState, createContext, useEffect, useContext } from 'react';
import { auth } from '../services/firebaseConnection';
import { 
    createUserWithEmailAndPassword, 
    signInWithEmailAndPassword,
    onAuthStateChanged, 
    signOut
} from 'firebase/auth';

export const AuthContext = createContext({});

export function AuthProvider({ children }) {

    const [user, setUser] = useState(null);
    const [foto, setFoto] = useState(null);

    async function signUp(email, password) {
       return await createUserWithEmailAndPassword(auth, email, password)
    }

    async function signIn(email, password) {
        return await signInWithEmailAndPassword(auth, email, password);
    }

    async function logOut() {
        return await signOut(auth);
    }

    useEffect(() => {        
        const unsubscribe = onAuthStateChanged(auth, (currentUser) => {
            setUser(currentUser);
        });
        return () => unsubscribe();
    }, []);

    const value = {
        user,
        foto,
        setUser,
        setFoto,
        signUp,
        signIn,
        logOut
    };

    return (
        <AuthContext.Provider value={ value }>
            { children }
        </AuthContext.Provider>
    );
}

export function useUserAuth() {
    return useContext(AuthContext);
}

export default AuthProvider;
