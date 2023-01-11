package ifba.edu.br.chamadosapi.controllers;

import java.io.IOException;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import ifba.edu.br.chamadosapi.dtos.UserIn;
import ifba.edu.br.chamadosapi.dtos.UserOut;
import ifba.edu.br.chamadosapi.services.UsuarioService;
import io.swagger.annotations.ApiOperation;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/usuarios")
@CrossOrigin(origins = "*")
public class UsuarioController {
	
    final UsuarioService usuarioService;

    @ApiOperation(value = "GetAllUsers")
    @GetMapping
    public ResponseEntity<List<UserOut>> listAll() {
        List<UserOut> usuarios = usuarioService.listAll();
        return new ResponseEntity<List<UserOut>>(usuarios, HttpStatus.OK);
    }

    @ApiOperation(value = "GetUseById")
    @GetMapping("/{id}")
    public ResponseEntity<UserOut> findById(@Valid @PathVariable String id) {
        UserOut usuario = usuarioService.findById(id);
        return new ResponseEntity<UserOut>(usuario, HttpStatus.OK);
    }

    @ApiOperation(value = "PostUser")
    @PostMapping
    public ResponseEntity<UserOut> save(@Valid @RequestBody UserIn usuarioIn) {
        UserOut UsuarioOut = usuarioService.save(usuarioIn);
        return new ResponseEntity<UserOut>(UsuarioOut, HttpStatus.CREATED);

    }
    
    @ApiOperation(value = "PutUserById")
    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<?> update(@PathVariable String id,  @RequestParam("nome") String nome, @RequestPart("foto") MultipartFile foto) throws IOException {
       usuarioService.update(id, nome, foto);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @ApiOperation(value = "DeletUserById")
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> delete(@PathVariable String id) {
        usuarioService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
