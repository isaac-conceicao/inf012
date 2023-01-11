package ifba.edu.br.chamadosapi.models;

import java.time.LocalDate;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity(name = "clientes")
public class Customer {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User usuario;

    @NotBlank
    private String nome;
    
    private String cnpj;

    private String endereco;

    private LocalDate dataCadastro;

	public Customer(User usuario, @NotBlank String nome, String cnpj, String endereco, LocalDate dataCadastro) {
		super();
		this.usuario = usuario;
		this.nome = nome;
		this.cnpj = cnpj;
		this.endereco = endereco;
		this.dataCadastro = dataCadastro;
	}
	
}
