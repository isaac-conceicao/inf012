import logo from '../../assets/login.png'
import { Link, useNavigate } from 'react-router-dom'
import { useEffect, useRef, useState } from 'react';
import { useUserAuth } from '../../contexts/auth';
import './signin.css'

export default function SignIn() {

  const email = useRef();
  const senha = useRef();

  const [error, setError] = useState();

  const { signIn } = useUserAuth();

  const navigate = useNavigate();

  useEffect(() => {
    const user = {
      email: localStorage.getItem('user-email'),
      senha: localStorage.getItem('user-senha'),
    };
    if(user.email && user.senha) {
      email.current.value = user.email;
      senha.current.value = user.senha;
      makeSignIn();
    }
  }, []);

  const handleSubmit = async (e) => {
    e.preventDefault();
    makeSignIn();
    localStorage.setItem('user-email', email.current.value);
    localStorage.setItem('user-senha', senha.current.value);
  }

  const makeSignIn = async () => {
    try {
      await signIn(email.current.value, senha.current.value);
      navigate('/dashboard');
      
    } catch(error) {
      switch(error.code) {
        case 'auth/user-not-found': setError('Usuário não encontrado!');       
        break;

        case 'auth/wrong-password': setError('Senha incorreta!');              
        break;

        case 'auth/invalid-email': setError('E-mail inválido!');              
        break;

        default: setError('Ocorreu um erro desconhecido!'); 
        break;
      }
    }
  }

  return (
    <div className="conteiner-center">
      <div className="login">
        
        <div className="login-area">
          <img src={logo} alt="Logo do Sistema"/>
        </div>
        
        <form>
          <h1>Entrar</h1>
          <input type="text"     placeholder="email@email.com" ref={ email }/>
          <input type="password" placeholder="*****"           ref={ senha }/>
          <button type="submit"  onClick={ handleSubmit }>Acessar</button>
          <p style={{ display: error ? "block" : "none" }}>{ error }</p>
        </form>
        
        <Link to="/register">Criar uma conta...</Link>
      
      </div>
    </div>
  );
}
