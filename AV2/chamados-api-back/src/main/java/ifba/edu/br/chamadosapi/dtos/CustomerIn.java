package ifba.edu.br.chamadosapi.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class CustomerIn{
    @NotBlank
    private String usuarioId;

    @NotBlank
    private String nome;

    private String cnpj;

    private String endereco;
	
}
