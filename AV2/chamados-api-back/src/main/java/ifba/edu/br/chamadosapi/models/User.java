package ifba.edu.br.chamadosapi.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity(name = "usuarios")
public class User {
    
    @Id
    @NotBlank
    private String uid;

    @NotBlank
    private String nome;

    @NotBlank @Email
    private String email;

    @Lob
    private byte[] foto;

	public User(@NotBlank String nome, @NotBlank @Email String email, byte[] foto) {
		super();
		this.nome = nome;
		this.email = email;
		this.foto = foto;
	}
	
}
