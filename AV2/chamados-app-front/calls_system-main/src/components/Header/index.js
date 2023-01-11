import { Link } from 'react-router-dom';
import { FiHome, FiUser, FiSettings } from 'react-icons/fi';
import { useUserAuth } from '../../contexts/auth';
import avatar from '../../assets/avatar.png';
import './header.css'

export default function Header() {

    const { foto } = useUserAuth();
    
    return (
        <div className="sidebar">
            <div>
                <img alt="Foto Avatar" src={ foto ? foto : avatar } />
            </div>
            <Link to="/dashboard">
                <FiHome color="#FFF" size={24} />
            Chamados
        </Link>
            <Link to="/costumers">
                <FiUser color="#FFF" size={24} />
            Clientes
        </Link>
            <Link to="/profile">
                <FiSettings color="#FFF" size={24} />
            Configurações
        </Link>

        </div>
    );
}