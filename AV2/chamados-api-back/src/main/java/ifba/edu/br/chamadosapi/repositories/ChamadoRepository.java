package ifba.edu.br.chamadosapi.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ifba.edu.br.chamadosapi.models.Case;

@Repository
public interface ChamadoRepository extends JpaRepository<Case, Long>{
    
    @Query(value = "SELECT * FROM CHAMADOS WHERE CLIENTE_ID = ?", nativeQuery = true)
    List<Case> findByClienteId(@Param("clientId") Long clientId);

    @Query(value = "SELECT CHAMADOS.ID, CHAMADOS.ASSUNTO, CHAMADOS.COMPLEMENTO, CHAMADOS.DATA_CADASTRO, CHAMADOS.STATUS, CHAMADOS.CLIENTE_ID FROM CHAMADOS INNER JOIN CLIENTES ON CHAMADOS.CLIENTE_ID = CLIENTES.ID WHERE CLIENTES.USUARIO_UID = ?", nativeQuery = true)
    List<Case> findByUsuarioId(@Param("usuarioId") String usuarioId);
}
