import { useContext } from 'react';
import { Navigate } from 'react-router-dom';
import { AuthContext } from '../contexts/auth'

export default function PrivateRoute({ children }) {
    const { user } = useContext(AuthContext);
    return user ? children : <Navigate to="/" />
}