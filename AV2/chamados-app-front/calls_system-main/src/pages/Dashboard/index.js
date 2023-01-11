import './dashboard.css';
import { useState, useEffect, useContext } from 'react';
import Header from '../../components/Header';
import Title from '../../components/Title';
import { FiMessageSquare, FiPlus, FiSearch, FiEdit2 } from 'react-icons/fi';
import { Link } from 'react-router-dom';
import api from '../../services/api';
import DetalheChamado from './DetalheChamado';
import EditarChamado from './EditChamado';
import { formataStatus, getDataFormatada } from '../../utils/utils';
import { useUserAuth } from '../../contexts/auth';

export default function Dashboard() {

  const { user, foto, setFoto } = useUserAuth();

  const [chamados, setChamados] = useState([]);

  const [openDetalhe, setOpenDetalhar] = useState(false);
  const [openEditar,  setOpenEditar]   = useState(false);
  const [chamadoId, setChamadoId]      = useState();

  useEffect(() => { 
    getChamados();
    getProfilePhoto();
  }, [ chamados, foto ]);

  async function getChamados () {

    let response;

    try {
      response = await api.get(`/chamados/usuarios/${user.uid}`);
    } catch (error) {
      console.error(error);
    }

    if(response.data) {
      setChamados(response.data);
    }

  }

  const getProfilePhoto = async () => {
    // Se não tiver foto no firebase, busque no Banco
    if(foto) {
      return;
    }

    let response;

    try {
      response = await api.get(`/usuarios/${user.uid}`);
      } catch (error) {
      console.error(error);
    }

    if(!response.data) {
      return;
    }

    const fotoBD = response.data.foto;

    if (fotoBD) {
      setFoto(`data:image/png;base64,${response.data.foto}`);
    }
    
  }

  const detalhar = (id) => {
    setOpenDetalhar(true);
    setChamadoId(id);
  }

  const editar = (id) => {
    setOpenEditar(true);
    setChamadoId(id);
  }

  return(
    <div>
      { openDetalhe && <DetalheChamado setOpenModal={ setOpenDetalhar } chamadoId={ chamadoId } user={ user }/>}
      { openEditar  && <EditarChamado  setOpenModal={ setOpenEditar  } chamadoId={ chamadoId } user={ user }/>}
      <Header/>

      <div className="content">
        <Title nome="Atendimentos">
          <FiMessageSquare size={25} />
        </Title>

        { chamados.length === 0 ? (
          <div className="container dashboard">
            <span>Nenhum chamado registrado...</span>
            <Link to="/new" className="new">
              <FiPlus size={25} color="#FFF" />
              Novo chamado
            </Link>
          </div>
          ) : (
          <>
            <Link to="/new" className="new">
              <FiPlus size={25} color="#FFF" />
              Novo chamado
            </Link>

            <table>
              <thead>
                <tr>
                  <th scope="col">Cliente</th>
                  <th scope="col">Assunto</th>
                  <th scope="col">Status</th>
                  <th scope="col">Cadastrado em</th>
                  <th scope="col">#</th>
                </tr>
              </thead>
              <tbody>
                {chamados.map((chamado, index) => {
                  return (
                    <tr key={ index }>
                      <td data-label="Cliente">{ chamado.cliente.nome }</td>
                      <td data-label="Assunto">{ chamado.assunto }</td>
                      <td data-label="Status">
                        <span 
                          className="badge"
                          style={
                            chamado.status === 'ABERTO' ? 
                            { backgroundColor: '#bf2d17' } : 
                            chamado.status === 'ANDAMENTO' ? 
                            { backgroundColor: '#c2b43c' } : 
                            { backgroundColor: '#5cb85c'} 
                          }
                        >
                          { formataStatus(chamado.status) }
                        </span>
                      </td>
                      <td data-label="Cadastrado">{ getDataFormatada(chamado.dataCadastro) }</td>
                      <td data-label="#">
                        <button 
                          onClick={ () => { detalhar(chamado.id) } } 
                          className="action" 
                          style={{backgroundColor: '#3583f6' }}
                        >
                          <FiSearch color="#FFF" size={17} />
                        </button>
                        <button
                        onClick={ () => { editar(chamado.id) } }
                          className="action" 
                          style={{backgroundColor: '#F6a935' }}
                        >
                          <FiEdit2 color="#FFF" size={17} />
                        </button>
                      </td>
                    </tr>
                  )
                })}
              </tbody>
            </table>
          </>
        )}

      </div>

    </div>
  )
}