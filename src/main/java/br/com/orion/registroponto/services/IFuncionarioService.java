package br.com.orion.registroponto.services;

import java.util.List;
import java.util.Optional;

import br.com.orion.registroponto.dtos.FuncionarioDTO;
import br.com.orion.registroponto.models.Funcionario;

public interface IFuncionarioService {
	
	Funcionario cadastrarFuncionario(FuncionarioDTO funcionario);
	Funcionario desativarFuncionario(Funcionario funcionario);
	List<Funcionario> listarTodosFuncionarios();
	Optional<Funcionario> findByEmail(String email);

}
