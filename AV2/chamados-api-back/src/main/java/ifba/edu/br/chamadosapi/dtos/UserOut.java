package ifba.edu.br.chamadosapi.dtos;

import java.util.List;
import java.util.stream.Collectors;

import ifba.edu.br.chamadosapi.models.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserOut {
	private String uid;
    private String nome;
    private String email;
    private byte[] foto;

	public UserOut(User usuario) {
        this.uid = usuario.getUid();
        this.nome = usuario.getNome();
        this.email = usuario.getEmail();
        this.foto = usuario.getFoto();
    }

    public static List<UserOut> converte(List<User> lista) {
        return lista.stream().map(UserOut::new).collect(Collectors.toList());
    }
    
}
