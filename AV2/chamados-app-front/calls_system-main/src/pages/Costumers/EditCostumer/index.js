import { useEffect, useRef, useState } from 'react';
import { toast } from 'react-toastify';
import api from '../../../services/api';
import './edit-costumer.css';

function EditCostumer({ setOpenModal, clienteId, user }) {

  const nome     = useRef();
  const cnpj     = useRef();
  const endereco = useRef();


  useEffect(() => {
    loadCliente();
  }, []);

  async function loadCliente() {
    api
      .get(`/clientes/${clienteId}`)
      .then(response => {
        nome.current.value     = response.data.nome;
        cnpj.current.value     = response.data.cnpj;
        endereco.current.value = response.data.endereco;
      });
  }

   const handleSubmit = (e) => {
    e.preventDefault();

    updateCliente();

    setOpenModal(false);
  }

  const updateCliente = async () => {
    let response;

    try {
      response = api.put(`clientes/${clienteId}`, {
        nome:     nome.current.value,
        usuarioId: user.uid,
        cnpj:     cnpj.current.value,
        endereco: endereco.current.value,
      });
      
    } catch (error) {
      toast('Erro ao atualizar o cliente!');
    }

    if(response.data) {
      toast('Cliente atualizado com sucesso!');
    }
  }

  return (
    <div className="modalBackground-costumer">
      <div className="modalContainer-costumer">
        <div className="titleCloseBtn-costumer">
          <button
            onClick={() => {
              setOpenModal(false);
            }}
          >
            X
          </button>
        </div>
        <div className="title-costumer">
          <h1>Editar Cliente</h1>
        </div>
        <div className="body-costumer">
          <form className="form-profile costumers">
              <label>Nome</label>
              <input ref={ nome } placeholder="Digite o Nome Fantasia" type="text"/>

              <label>CNPJ</label>
              <input ref={ cnpj } placeholder="Digite o CNPJ" type="text"/>

              <label>Endereço</label>
              <input ref={ endereco } placeholder="Digite o seu Endereço" type="text"/>
          </form>
        </div>
        <div className="footer-costumer">
          <button
            onClick={() => { setOpenModal(false) }}
            id="cancelBtn"
          >
            Cancelar
          </button>
          <button onClick={ handleSubmit }>Confirmar</button>
        </div>
      </div>
    </div>
  );
}

export default EditCostumer;
