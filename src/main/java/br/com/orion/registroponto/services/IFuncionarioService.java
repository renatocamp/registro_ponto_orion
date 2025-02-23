package br.com.orion.registroponto.services;

import java.util.List;
import java.util.UUID;

import br.com.orion.registroponto.dtos.FuncionarioDTO;
import br.com.orion.registroponto.models.Funcionario;

public interface IFuncionarioService {
	
	Funcionario cadastrarFuncionario(FuncionarioDTO funcionario);
	Funcionario desativarFuncionario(FuncionarioDTO funcionario);
	List<Funcionario> listarTodosFuncionarios();
	Funcionario findById(UUID id);

}
