package br.com.orion.registroponto.servicees;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.orion.registroponto.dtos.FuncionarioDTO;
import br.com.orion.registroponto.enums.StatusFuncionario;
import br.com.orion.registroponto.models.Funcionario;
import br.com.orion.registroponto.services.IFuncionarioService;

@SpringBootTest
public class FuncionarioTestesService {
	
	@Mock
	private IFuncionarioService service;
	
	private FuncionarioDTO funcDTO;
	private FuncionarioDTO funcEmailDuplicado;
	private FuncionarioDTO funcInativo;
	private Funcionario employe;
	private Funcionario employeInativo;
	private List<Funcionario> funcionariosAtivos;
	
	@BeforeEach
	public void setUp() {
		
		funcionariosAtivos = new ArrayList<>();
		
		employe = new Funcionario();
		employe.setNome("Renato");
		employe.setSenha("12345");
		employe.setEndereco("Rua Amapá");
		employe.setEmail("renato@email.com");
		employe.setTelefone("1978788945");
		employe.setStatus(StatusFuncionario.ATIVO);
		
		employeInativo = new Funcionario();
		employeInativo.setNome("Renato");
		employeInativo.setSenha("12345");
		employeInativo.setEndereco("Rua Amapá");
		employeInativo.setEmail("renato@email.com");
		employeInativo.setTelefone("1978788945");
		employeInativo.setStatus(StatusFuncionario.INATIVO);
		
		funcionariosAtivos.add(employe);
		funcionariosAtivos.add(employeInativo);
		
		funcDTO = new FuncionarioDTO("Renato Campos", "12345", "Rua Maranhão", "teste@email.com", "19989889999", StatusFuncionario.ATIVO);
		funcEmailDuplicado = new FuncionarioDTO("Renato Campos", "12345", "Rua Maranhão", "teste@email.com", "19989889999", StatusFuncionario.ATIVO);
		funcInativo = new FuncionarioDTO("Renato", "12345", "Rua Maranhão", "teste@email.com", "19989889999", StatusFuncionario.INATIVO);
	}
	
	@Test
	public void deveCadastrarFuncionario() {
		
		when(service.cadastrarFuncionario(funcDTO)).thenReturn(employe);
		
		Funcionario resultado = service.cadastrarFuncionario(funcDTO);

        assertNotNull(resultado);
        assertEquals(StatusFuncionario.ATIVO, resultado.getStatus());
	}
	
	@Test
	public void deveRejeitarFuncionarioEmailDuplicado() {
		
		when(service.cadastrarFuncionario(funcDTO)).thenReturn(null);
		
		assertEquals(service.cadastrarFuncionario(funcEmailDuplicado), null);
	}
	
	@Test
	public void deveDesativarFuncionario() {
		
		when(service.desativarFuncionario(employeInativo)).thenReturn(employeInativo);
		
		Funcionario resultado = service.desativarFuncionario(employeInativo);

        assertNotNull(resultado);
        assertEquals(StatusFuncionario.INATIVO, resultado.getStatus());
		
		
	}
	
	@Test
	public void deveListarTodosFuncionariosAtivos() {
				
		when(service.listarTodosFuncionarios()).thenReturn(funcionariosAtivos);
		
		List<Funcionario> resultado = service.listarTodosFuncionarios()
				.stream()
				.filter(f -> f.getStatus() == StatusFuncionario.ATIVO)
				.collect(Collectors.toList());
		
		assertEquals(1, resultado.size());
		assertEquals(StatusFuncionario.ATIVO, resultado.get(0).getStatus());
	}

}
