package br.com.codenation.time.factory;

import java.time.LocalDate;
import java.util.ArrayList;

import br.com.codenation.jogador.model.JogadorModel;
import br.com.codenation.time.model.TimeModel;

public class TimeFactory {

	public TimeModel montaTime(Long id, String nome, LocalDate dataCriacao, String corUniformePrincipal,
			String corUniformeSecundario) {

		TimeModel time = new TimeModel(id, nome, dataCriacao, corUniformePrincipal, corUniformeSecundario, null, new ArrayList<JogadorModel>());

		return time;
	}

}
