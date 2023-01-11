package ifba.edu.br.chamadosapi.services;

import java.time.LocalDate;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import ifba.edu.br.chamadosapi.dtos.CustomerIn;
import ifba.edu.br.chamadosapi.dtos.CustomerOut;
import ifba.edu.br.chamadosapi.models.Customer;
import ifba.edu.br.chamadosapi.models.User;
import ifba.edu.br.chamadosapi.repositories.ClienteRepository;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class ClienteService {
	private ClienteRepository customerRepository;
    private UsuarioService userService;

    public Customer findByIdOrThrowNotFoundRequestException(Long id) {
        return customerRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Contato não encontrado"));
    }

    public CustomerOut save(CustomerIn customerIn) {
        User user = userService.findByIdOrThrowNotFoundRequestException(customerIn.getUsuarioId());
        Customer customer = new Customer(user, customerIn.getNome(), customerIn.getCnpj(), customerIn.getEndereco(), LocalDate.now());

        customer = customerRepository.save(customer);

        return new CustomerOut(customer);
    }

    public CustomerOut findById(Long id) {
        return new CustomerOut(findByIdOrThrowNotFoundRequestException(id));
    }

    public List<CustomerOut> listAll() {
        return CustomerOut.converte(customerRepository.findAll());
    }

    public void update(Long id, CustomerIn clienteIn) {
        Customer savedCustomer = findByIdOrThrowNotFoundRequestException(id);
        Customer customer = new Customer(savedCustomer.getId(), savedCustomer.getUsuario(), clienteIn.getNome(), clienteIn.getCnpj(), clienteIn.getEndereco(), savedCustomer.getDataCadastro());
    
        customerRepository.save(customer);
    }

    public void delete(Long id) {
        customerRepository.delete(findByIdOrThrowNotFoundRequestException(id));
    }
}
