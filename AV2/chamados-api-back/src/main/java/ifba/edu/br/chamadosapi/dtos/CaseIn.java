package ifba.edu.br.chamadosapi.dtos;

import ifba.edu.br.chamadosapi.models.Assunto;
import ifba.edu.br.chamadosapi.models.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CaseIn {

    private Long clienteId;
    
    private Assunto assunto;
    private Status status;
    private String complemento;
	
}
