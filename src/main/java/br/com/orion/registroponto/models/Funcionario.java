package br.com.orion.registroponto.models;

import java.util.List;
import java.util.UUID;

import br.com.orion.registroponto.enums.StatusFuncionario;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "TBL_FUNCIONARIO")
public class Funcionario {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "funcionario_id")
	private UUID id;
	
	@Column(name = "nome_funcionario", nullable = false, length = 100)
	private String nome;
	
	@Column(name ="password", nullable = false)
	private String senha;
	
	@Column(name = "endereco", nullable = false, length = 150)
	private String endereco;
	
	@Column(name = "email", nullable = false, unique = true)
	private String email;
	
	@Column(name = "telefone", nullable = false)
	private String telefone;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "status_funcionario")
	private StatusFuncionario status;
	
	@OneToMany(mappedBy = "funcionario")
	private List<RegistroPonto> registros;
	

	public List<RegistroPonto> getRegistros() {
		return registros;
	}

	public void setRegistros(List<RegistroPonto> registros) {
		this.registros = registros;
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	
	public StatusFuncionario getStatus() {
		return status;
	}

	public void setStatus(StatusFuncionario status) {
		this.status = status;
	}
	
	

}
