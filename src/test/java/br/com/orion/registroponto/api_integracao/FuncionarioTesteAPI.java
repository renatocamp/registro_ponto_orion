package br.com.orion.registroponto.api_integracao;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.orion.registroponto.dtos.FuncionarioDTO;
import br.com.orion.registroponto.enums.StatusFuncionario;
import br.com.orion.registroponto.services.impl.FuncionarioServiceImpl;

@AutoConfigureMockMvc
@SpringBootTest
public class FuncionarioTesteAPI {
	
	@Autowired
	private MockMvc mvc;
	
	@MockBean
    private FuncionarioServiceImpl funcionarioService;
    
    @Autowired
    private ObjectMapper objectMapper;
    
    @Test
    public void deveCadastrarFuncionario() throws Exception {
    	
    	FuncionarioDTO novoFuncionario = new FuncionarioDTO(
                "Renato Campos",
                "senha123",
                "Rua Maranhão, 123",
                "renato@email.com",
                "11999999999",
                StatusFuncionario.ATIVO
        );
    	
    	Mockito.when(funcionarioService.findByEmail(novoFuncionario.email()))
        .thenReturn(Optional.empty());
    	
    	Mockito.when(funcionarioService.cadastrarFuncionario(novoFuncionario))
        .thenReturn(novoFuncionario.toFuncionario());
    	
    	
    	mvc.perform(MockMvcRequestBuilders.post( "/cadastrar")
    			.contentType(MediaType.APPLICATION_JSON)
    			.content(objectMapper.writeValueAsString(novoFuncionario)))
    	.andExpect(status().isCreated());
    }
    
    @Test
    public void deveRejeitarFuncionarioDuplicado() throws Exception{
    	
    	FuncionarioDTO funcionarioDuplicado = new FuncionarioDTO("Renato Campos",
                "senha123",
                "Rua Maranhão, 123",
                "renato@email.com",
                "11999999999",
                StatusFuncionario.ATIVO
        );
    	
    	Mockito.when(funcionarioService.findByEmail(funcionarioDuplicado.email()))
    	.thenReturn(Optional.of(funcionarioDuplicado.toFuncionario()));
    	
    	mvc.perform(MockMvcRequestBuilders.post("/cadastrar")
    			.contentType(MediaType.APPLICATION_JSON)
    			.content(objectMapper.writeValueAsString(funcionarioDuplicado)))
    	.andExpect(status().isConflict());
    	
    }

}
