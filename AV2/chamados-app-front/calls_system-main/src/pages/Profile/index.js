import { useState, useEffect } from 'react';
import Header from '../../components/Header';
import Title from '../../components/Title';
import avatar from '../../assets/avatar.png';
import { FiSettings, FiUpload } from 'react-icons/fi';
import { useUserAuth } from '../../contexts/auth';
import { useNavigate } from 'react-router-dom';
import api from '../../services/api';
import { toast } from 'react-toastify';
import './profile.css';

export default function Profile() {
  
  const { user, setFoto, logOut } = useUserAuth();

  const [nome, setNome]   = useState();
  const [email, setEmail] = useState();

  const [avatarImagem, setAvatarImagem] = useState(null);
  const [avatarUrl, setAvatarUrl] = useState(null);

  const navigate = useNavigate();

  useEffect(() => {

    setEmail(user.email);
    fetchUsuario(user.uid);
    
  }, []);

  async function fetchUsuario(uid) {
    api.get(`/usuarios/${uid}`)
      .then(response => {
        
        setNome(response.data.nome);

        if(response.data.foto) {
          setAvatarUrl(true);
          setAvatarImagem(`data:image/png;base64,${response.data.foto}`);
        }

      });
  }

  const handleUploadImage = (imagem) => {
    setAvatarUrl(imagem ? imagem : null);
    setAvatarImagem(imagem ? URL.createObjectURL(imagem) : null);
  }

   const handleSave = async (e) => {
    e.preventDefault();
    
    const formData = new FormData();
    formData.append('uid', user.uid);
    formData.append('nome', nome);
    formData.append('foto', avatarUrl);
    const header = { "Content-Type": "multipart/form-data" };

    api.put(`/usuarios/${user.uid}`, formData, header)
      .then(_ =>  {
        setFoto(avatarImagem);
        toast('Usuário atualizado com sucesso!');
        
      })
      .catch(_ => toast('Erro ao atualizar o usuário!'));
  }

  const handleLogout = async (e) => {
    e.preventDefault();
    try {
      localStorage.clear();
      await logOut();
      navigate('/');
    } catch(error) {
      toast('Ocorreu um erro ao tentar deslogar!');
    }
  }

  return(
    <div>
      <Header/>

      <div className="content">
        <Title nome="Meu perfil">
          <FiSettings size={25} />
        </Title>


        <div className="container">
          <form onSubmit={ (e) => handleSave(e) } className="form-profile">
            <label className="label-avatar">
              <span>
                <FiUpload color="#000" size={25} />
              </span>

              <input type="file" accept="image/*" onChange={ e => handleUploadImage(e.target.files[0]) }/><br/>
              { avatarUrl === null ? 
                <img src={ avatar } width="250" height="250" alt="Foto de perfil do usuario" />
                :
                <img src={ avatarImagem } width="250" height="250" alt="Foto de perfil do usuario" />
              }
            </label>

            <label>Nome</label>
            <input type="text" value={ nome } onChange={ (e) => setNome(e.target.value) } />

            <label>Email</label>
            <input type="text" value={ email } disabled={ true } /> 

            <button type="submit">Salvar</button>       
          </form>
        </div>

        <div className="container">
            <button className="logout-btn" onClick={ handleLogout }>
               Sair
            </button>
        </div>

      </div>
    </div>
  )
}
