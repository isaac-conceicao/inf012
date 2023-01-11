package ifba.edu.br.chamadosapi.models;

import java.time.LocalDate;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity(name = "chamados")
public class Case {
    
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Customer cliente;

    @Enumerated(EnumType.STRING)
    private Assunto assunto;

    @Enumerated(EnumType.STRING)
    private Status status;

    @Column(columnDefinition = "LONGTEXT")
    private String complemento;

    private LocalDate dataCadastro;

	public Case(Customer cliente, Assunto assunto, Status status, String complemento, LocalDate dataCadastro) {
		super();
		this.cliente = cliente;
		this.assunto = assunto;
		this.status = status;
		this.complemento = complemento;
		this.dataCadastro = dataCadastro;
	}
	
}
