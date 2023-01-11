package ifba.edu.br.chamadosapi.dtos;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import ifba.edu.br.chamadosapi.models.Assunto;
import ifba.edu.br.chamadosapi.models.Case;
import ifba.edu.br.chamadosapi.models.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CaseOut {
	
    private Long id;
    private CustomerOut cliente;
    private Assunto assunto;
    private Status status;
    private String complemento;
    private LocalDate dataCadastro;

    public CaseOut(Case chamado) {
        this.id = chamado.getId();
        this.cliente = new CustomerOut(chamado.getCliente());
        this.assunto = chamado.getAssunto();
        this.status = chamado.getStatus();
        this.complemento = chamado.getComplemento();
        this.dataCadastro = chamado.getDataCadastro();
    }

    public static List<CaseOut> converte(List<Case> lista) {
        return lista.stream().map(CaseOut::new).collect(Collectors.toList());        
    }
    
}
