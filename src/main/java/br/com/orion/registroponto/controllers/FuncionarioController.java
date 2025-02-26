package br.com.orion.registroponto.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.orion.registroponto.dtos.FuncionarioDTO;
import br.com.orion.registroponto.dtos.RespostaDTO;
import br.com.orion.registroponto.enums.StatusFuncionario;
import br.com.orion.registroponto.models.Funcionario;
import br.com.orion.registroponto.services.impl.FuncionarioServiceImpl;

@RestController
@RequestMapping("")
@CrossOrigin("*")
public class FuncionarioController {
	
	@Autowired
	private FuncionarioServiceImpl service;
	
	@PostMapping("/cadastrar")
	public ResponseEntity<RespostaDTO> cadastrarFuncionario(@RequestBody FuncionarioDTO dto) {

		Optional<Funcionario> funcionario = service.findByEmail(dto.email());
		
		if(!funcionario.isPresent()) {
			
			return ResponseEntity
					.status(HttpStatus.CREATED)
					.body(new RespostaDTO("Funcionário cadastrado com sucesso."));
		}
		
		service.cadastrarFuncionario(dto);
		
		
		return ResponseEntity
				.status(HttpStatus.CONFLICT)
				.body(new RespostaDTO("Funcionário já cadastrado."));
		
	}
	
	@PutMapping("/desativar")
	public ResponseEntity<RespostaDTO> desativarFuncionario(@RequestBody FuncionarioDTO funcDTO) {

	    Optional<Funcionario> funcionarioOptional = service.findByEmail(funcDTO.email());

	    if (funcionarioOptional.isEmpty()) {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND)
	                .body(new RespostaDTO("Funcionário não encontrado"));
	    }

	    Funcionario funcionario = funcionarioOptional.get();
	    funcionario.setStatus(StatusFuncionario.INATIVO);
	    service.desativarFuncionario(funcionario);

	    return ResponseEntity.ok(new RespostaDTO("O Funcionário foi desativado com sucesso."));
	}
	
	@GetMapping("/listar")
	public ResponseEntity<List<Funcionario>> listarTodos(){
		
		List<Funcionario> funcionarios = service.listarTodosFuncionarios();

        if (funcionarios.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(funcionarios);
	}

	

}
