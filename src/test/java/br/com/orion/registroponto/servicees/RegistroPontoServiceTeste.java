package br.com.orion.registroponto.servicees;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.time.LocalDateTime;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.orion.registroponto.enums.TipoRegistro;
import br.com.orion.registroponto.models.Funcionario;
import br.com.orion.registroponto.models.RegistroPonto;
import br.com.orion.registroponto.services.IRegistroService;

@SpringBootTest
public class RegistroPontoServiceTeste {
	
	@Mock
	private IRegistroService registroService;
	
	private RegistroPonto ponto;
	
	private Funcionario funcionario;
	
	@BeforeEach
	public void setup() {
		
		funcionario = new Funcionario();
		funcionario.setNome("Renato Campos");
		funcionario.setId(UUID.randomUUID());
		
		ponto = new RegistroPonto();
		ponto.setDataHora(LocalDateTime.now());
		ponto.setTipo(TipoRegistro.ENTRADA);
		
		Mockito.when(registroService.registrarPonto(funcionario.getId(), TipoRegistro.ENTRADA)).thenReturn(ponto);
	}
	
	@Test
	public void deveCadastrarNovoRegistro() {
		
        RegistroPonto resultado = registroService.registrarPonto(funcionario.getId(), TipoRegistro.ENTRADA);
		
        assertNotNull(resultado, "O registro de ponto não deveria ser nulo");
        assertEquals(TipoRegistro.ENTRADA, resultado.getTipo(), "O tipo de registro deveria ser ENTRADA");
        assertEquals(funcionario.getId(), funcionario.getId(), "O ID do funcionário deveria corresponder");

        
        verify(registroService, times(1)).registrarPonto(funcionario.getId(), TipoRegistro.ENTRADA);
	}

}
