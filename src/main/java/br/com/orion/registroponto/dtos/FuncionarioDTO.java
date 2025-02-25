package br.com.orion.registroponto.dtos;

import org.hibernate.validator.constraints.Length;

import br.com.orion.registroponto.enums.StatusFuncionario;
import br.com.orion.registroponto.models.Funcionario;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public record FuncionarioDTO(@NotNull String nome, 
							 @NotNull @Length(min = 8) String senha, 
							 @NotNull String endereco, 
							 @Email @NotNull String email, 
							 @NotNull String telefone, 
							 @NotNull StatusFuncionario status) {
	
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
