package br.com.orion.registroponto.services;

import java.util.List;
import java.util.UUID;

import br.com.orion.registroponto.enums.TipoRegistro;
import br.com.orion.registroponto.models.RegistroPonto;

public interface IRegistroService {

	RegistroPonto registrarPonto(UUID funcionarioId, TipoRegistro tipoRegistro);
    List<RegistroPonto> buscarPorFuncionario(Long funcionarioId);

}
