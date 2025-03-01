package br.com.orion.registroponto.api_integracao;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
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
import br.com.orion.registroponto.models.Funcionario;
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
    
    private List<Funcionario> lista;
    
    @BeforeEach
    public void setup() {
    	
    	 lista = new ArrayList<>();
    	
    	Funcionario f1 = new Funcionario();
    	f1.setNome("Renato");
    	
    	Funcionario f2 = new Funcionario();
    	f1.setNome("Pedro");
    	
    	lista.add(f1);
    	lista.add(f2);
    	
    }
    
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
    
    @Test
    public void deveDeixarFuncionarioInativo() throws  Exception {
    	
    	FuncionarioDTO funcionarioInativo = new FuncionarioDTO("Renato Campos",
                "senha123",
                "Rua Maranhão, 123",
                "renato@email.com",
                "11999999999",
                StatusFuncionario.ATIVO
                );
    	
    	Mockito.when(funcionarioService.findByEmail(funcionarioInativo.email()))
    	.thenReturn(Optional.of(funcionarioInativo.toFuncionario()));
    	
    	mvc.perform(MockMvcRequestBuilders.put("/desativar")
    			.contentType(MediaType.APPLICATION_JSON)
    			.content(objectMapper.writeValueAsString(funcionarioInativo)))
    	.andExpect(status().isOk());
    }
    
    @Test
    public void deveListarTodosFuncionarios() throws Exception {
    	
    	Mockito.when(funcionarioService.listarTodosFuncionarios()).thenReturn(lista);
    	
    	mvc.perform(MockMvcRequestBuilders.get("/listar")
    			.contentType(MediaType.APPLICATION_JSON)
    			.content(objectMapper.writeValueAsString(lista)))
    	.andExpect(status().isOk())
    	.andExpect(resultado -> {
    		
    		String resposta = resultado.getResponse().getContentAsString();
    		System.out.println(resposta);
    	})
    	.andExpect(jsonPath("$.length()").value(2));
    }

}
