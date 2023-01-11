package ifba.edu.br.chamadosapi.services;

import java.io.IOException;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import ifba.edu.br.chamadosapi.dtos.UserIn;
import ifba.edu.br.chamadosapi.dtos.UserOut;
import ifba.edu.br.chamadosapi.models.User;
import ifba.edu.br.chamadosapi.repositories.UsuarioRepository;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class UsuarioService {

    private final UsuarioRepository userRepository;

	public User findByIdOrThrowNotFoundRequestException(String id) {
        return userRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Contato não encontrado"));
    }
    
    public UserOut save(UserIn userIn) {
        User user = new User(userIn.getUid(), userIn.getNome(), (userIn.getEmail()), userIn.getFoto());
        userRepository.save(user);

        return new UserOut(user);
    }

    public UserOut findById(String uid) {
        return new UserOut(findByIdOrThrowNotFoundRequestException(uid));
    }

    public List<UserOut> listAll() {
        return UserOut.converte(userRepository.findAll());
    }

    public void update(String uid, UserIn userIn) {
        User savedUser = findByIdOrThrowNotFoundRequestException(uid);
        User user = new User(savedUser.getUid(), userIn.getNome(), savedUser.getEmail(), userIn.getFoto());

        userRepository.save(user);
    }

    public void update(String id, String nome, MultipartFile img) throws IOException {
        User savedUser = findByIdOrThrowNotFoundRequestException(id);
        savedUser.setNome(nome);
        savedUser.setFoto(img.getBytes());
    }

    public void delete(String uid) {
        userRepository.delete(findByIdOrThrowNotFoundRequestException(uid));
    }
}
