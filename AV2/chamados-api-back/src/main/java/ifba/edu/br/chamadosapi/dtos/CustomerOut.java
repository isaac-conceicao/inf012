package ifba.edu.br.chamadosapi.dtos;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import ifba.edu.br.chamadosapi.models.Customer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CustomerOut {
	private Long id;
    private UserOut usuario;
    private String nome;
    private String cnpj;
    private String endereco;
    private LocalDate dataCadastro;
    

	public CustomerOut(Customer cliente) {
        this.id = cliente.getId();
        this.usuario = new UserOut(cliente.getUsuario());
        this.nome = cliente.getNome();
        this.cnpj = cliente.getCnpj();
        this.endereco = cliente.getEndereco();
        this.dataCadastro = cliente.getDataCadastro();
    }

    public static List<CustomerOut> converte(List<Customer> lista) {
        return lista.stream().map(CustomerOut::new).collect(Collectors.toList());
    }
    
}
