package ifba.edu.br.chamadosapi.controllers;

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
import org.springframework.web.bind.annotation.RestController;

import ifba.edu.br.chamadosapi.dtos.CaseIn;
import ifba.edu.br.chamadosapi.dtos.CaseOut;
import ifba.edu.br.chamadosapi.services.ChamadoService;
import io.swagger.annotations.ApiOperation;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/chamados")
@CrossOrigin(origins = "*")
public class ChamadoController {
	
	private final ChamadoService chamadoService;

    
    @ApiOperation(value = "GetCaseByCustomerId")
    @GetMapping("/cliente/{customerId}")
    public ResponseEntity<List<CaseOut>> listAll(@PathVariable Long customerId) {
        List<CaseOut> chamados = chamadoService.listAll(customerId);
        return new ResponseEntity<List<CaseOut>>(chamados, HttpStatus.OK);
    }

    @ApiOperation(value = "GetCaseByUserId")
    @GetMapping("/usuarios/{userId}")
    public ResponseEntity<List<CaseOut>> listAll(@PathVariable String userId) {
        List<CaseOut> chamados = chamadoService.listAll(userId);
        return new ResponseEntity<List<CaseOut>>(chamados, HttpStatus.OK);
    }

    @ApiOperation(value = "GetCaseById")
    @GetMapping("/{id}")
    public ResponseEntity<CaseOut> findById(@Valid @PathVariable Long id) {
        CaseOut usuario = chamadoService.findById(id);
        return new ResponseEntity<CaseOut>(usuario, HttpStatus.OK);
    }

    @ApiOperation(value = "PostCase")
    @PostMapping
    public ResponseEntity<CaseOut> save(@RequestBody CaseIn caseIn) {
        CaseOut chamadoOut = chamadoService.save(caseIn);
        return new ResponseEntity<CaseOut>(chamadoOut, HttpStatus.CREATED);
    }

    @ApiOperation(value = "PutCaseById")
    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<?> atualizar(@PathVariable Long id, @RequestBody CaseIn caseIn) {
        chamadoService.update(id, caseIn);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @ApiOperation(value = "DeletaCaseById")
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> delete(@PathVariable Long id) {
        chamadoService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
