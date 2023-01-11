package ifba.edu.br.chamadosapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ifba.edu.br.chamadosapi.models.User;

@Repository
public interface UsuarioRepository extends JpaRepository<User, String>{

}
