import { useEffect, useState } from 'react';
import api from '../../../services/api';
import './detalhe-chamado.css';

export default function DetalheChamado({ setOpenModal, chamadoId }) {

    const [complemento, setComplemento] = useState();

    useEffect(() => {
        async function loadChamado() {
            api
                .get(`/chamados/${chamadoId}`)
                .then(response => {
                  setComplemento(response.data.complemento);
                });
        }
        loadChamado();
    }, []);

    return (
        <div className="modalBackground-detalhe-chamado">
          <div className="modalContainer-detalhe-chamado">
            <div className="titleCloseBtn-detalhe-chamado">
              <button
                onClick={() => {
                  setOpenModal(false);
                }}
              >
                X
              </button>
            </div>
            <div className="title-detalhe-chamado">
              <h1>Detalhamento do Chamado</h1>
            </div>
            <div className="body-detalhe-chamado">
              <textarea
                className="textarea-detalhe-chamado"
                type="text"
                value={ complemento }
                disabled
              />
            </div>
          </div>
        </div>
      );
}