package ifba.edu.br.chamadosapi.services;

import java.time.LocalDate;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import ifba.edu.br.chamadosapi.dtos.CaseIn;
import ifba.edu.br.chamadosapi.dtos.CaseOut;
import ifba.edu.br.chamadosapi.models.Case;
import ifba.edu.br.chamadosapi.models.Customer;
import ifba.edu.br.chamadosapi.repositories.ChamadoRepository;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class ChamadoService {
	private ChamadoRepository caseRepository;
    private ClienteService customerService;

    public Case findByIdOrThrowNotFoundRequestException(Long id) {
        return caseRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Chamado não encontrado"));
    }

    public CaseOut save(CaseIn chamadoIn) {
        Customer cliente = customerService.findByIdOrThrowNotFoundRequestException(chamadoIn.getClienteId());
        Case chamado = new Case(cliente, chamadoIn.getAssunto(), chamadoIn.getStatus(), chamadoIn.getComplemento(), LocalDate.now());
       
        chamado = caseRepository.save(chamado);
        
       return new CaseOut(chamado);
    }

    public CaseOut findById(Long id) {
        return new CaseOut(findByIdOrThrowNotFoundRequestException(id));
    }

    public List<CaseOut> listAll(Long customerId) {
        return CaseOut.converte(caseRepository.findByClienteId(customerId));
    }

    public List<CaseOut> listAll(String userId) {
        return CaseOut.converte(caseRepository.findByUsuarioId(userId));
    }

    public void update(Long id, CaseIn caseIn) {
        Case savedChamado = findByIdOrThrowNotFoundRequestException(id);
        Case chamado = new Case(savedChamado.getId() ,savedChamado.getCliente(), caseIn.getAssunto(), caseIn.getStatus(), caseIn.getComplemento(), LocalDate.now());
        
        caseRepository.save(chamado);
    }

    public void delete(Long id) {
        caseRepository.delete(findByIdOrThrowNotFoundRequestException(id));
    }

}
