package br.com.orion.registroponto.dtos;

import br.com.orion.registroponto.enums.StatusFuncionario;
import br.com.orion.registroponto.models.Funcionario;

public record FuncionarioDTO(String nome, String senha, String endereco, String email, String telefone, StatusFuncionario status) {
	
	public Funcionario toFuncionario() {
		
		Funcionario funcionario = new Funcionario();
		
		funcionario.setNome(nome);
		funcionario.setSenha(senha);
		funcionario.setEndereco(endereco);
		funcionario.setEmail(email);
		funcionario.setTelefone(telefone);
		funcionario.setStatus(status);
		
		return funcionario;
	}

}
