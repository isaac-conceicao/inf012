package ifba.edu.br.chamadosapi.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ifba.edu.br.chamadosapi.dtos.CustomerIn;
import ifba.edu.br.chamadosapi.dtos.CustomerOut;
import ifba.edu.br.chamadosapi.services.ClienteService;
import io.swagger.annotations.ApiOperation;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/clientes")
@CrossOrigin(origins = "*")
public class ClienteController {
	
	private final ClienteService clienteService;

    @ApiOperation(value = "GetAllCustomers")
    @GetMapping
    public ResponseEntity<List<CustomerOut>> listAll() {
        List<CustomerOut> clientes = clienteService.listAll();
        return new ResponseEntity<List<CustomerOut>>(clientes, HttpStatus.OK);
    }

    @ApiOperation(value = "GetCustomerById")
    @GetMapping("/{id}")
    public ResponseEntity<CustomerOut> findById(@Valid @PathVariable Long id) {
        CustomerOut usuario = clienteService.findById(id);
        return new ResponseEntity<CustomerOut>(usuario, HttpStatus.OK);
    }

    @ApiOperation(value = "PostCustomer")
    @PostMapping
    public ResponseEntity<CustomerOut> save(@Valid @RequestBody CustomerIn clienteIn) {
        CustomerOut clienteOut = clienteService.save(clienteIn);
        return new ResponseEntity<CustomerOut>(clienteOut, HttpStatus.CREATED);

    }
   
    @ApiOperation(value = "PutCustomerById")
    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody CustomerIn clienteIn) {
       clienteService.update(id, clienteIn);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @ApiOperation(value = "DeletCustomerById")
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> delete(@PathVariable Long id) {
        clienteService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
