package ifba.edu.br.chamadosapi.dtos;

import jakarta.persistence.Lob;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class UserIn {
	@NotBlank
    private String uid;

    @NotBlank
    private String nome;

    private String email;

    @Lob
    private byte[] foto;
	
}
