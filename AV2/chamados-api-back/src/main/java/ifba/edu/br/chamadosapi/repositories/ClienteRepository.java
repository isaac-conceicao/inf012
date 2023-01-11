package ifba.edu.br.chamadosapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ifba.edu.br.chamadosapi.models.Customer;

@Repository
public interface ClienteRepository extends JpaRepository<Customer, Long>{
    
}
