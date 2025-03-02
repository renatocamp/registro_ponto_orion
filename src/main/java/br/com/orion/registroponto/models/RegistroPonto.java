package br.com.orion.registroponto.models;

import java.time.LocalDateTime;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.orion.registroponto.enums.TipoRegistro;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "TBL_REGISTRO_PONTO")
public class RegistroPonto {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "registro_id")
	private UUID registro_id;
	
	@Column(name = "dataHora_registro", nullable = false)
	@JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'")
	private LocalDateTime dataHora;
	
	@Column(name = "tipo_registro", nullable = false)
	@Enumerated(EnumType.STRING)
	private TipoRegistro tipo;
	
	@ManyToOne
	@JoinColumn(name = "funcionario_id")
	private Funcionario funcionario;

	public UUID getRegistro_id() {
		return registro_id;
	}

	public void setRegistro_id(UUID registro_id) {
		this.registro_id = registro_id;
	}

	public LocalDateTime getDataHora() {
		return dataHora;
	}

	public void setDataHora(LocalDateTime dataHora) {
		this.dataHora = dataHora;
	}

	public TipoRegistro getTipo() {
		return tipo;
	}

	public void setTipo(TipoRegistro tipo) {
		this.tipo = tipo;
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}
	
	

}
