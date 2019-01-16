package br.com.codenation;

import static br.com.codenation.repository.RepositoryDao.TIMES;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import br.com.codenation.desafio.annotation.Desafio;
import br.com.codenation.desafio.app.MeuTimeInterface;
import br.com.codenation.desafio.exceptions.CapitaoNaoInformadoException;
import br.com.codenation.desafio.exceptions.IdentificadorUtilizadoException;
import br.com.codenation.desafio.exceptions.JogadorNaoEncontradoException;
import br.com.codenation.desafio.exceptions.TimeNaoEncontradoException;
import br.com.codenation.jogador.model.JogadorModel;
import br.com.codenation.time.factory.TimeFactory;
import br.com.codenation.time.model.TimeModel;

public class DesafioMeuTimeApplication implements MeuTimeInterface {

	@Desafio("incluirTime")
	public void incluirTime(Long id, String nome, LocalDate dataCriacao, String corUniformePrincipal,
			String corUniformeSecundario) {
		if (buscarTimePorId(id) != null)
			throw new IdentificadorUtilizadoException();
		TimeModel time = new TimeFactory().montaTime(id, nome, dataCriacao, corUniformePrincipal,
				corUniformeSecundario);

		adicionaTime(time);

		// throw new UnsupportedOperationException();
	}

	@Desafio("incluirJogador")
	public void incluirJogador(Long id, Long idTime, String nome, LocalDate dataNascimento, Integer nivelHabilidade,
			BigDecimal salario) {
		TimeModel time = buscarTimePorId(idTime);

		if (buscarJogadorPorId(id) != null)
			throw new IdentificadorUtilizadoException();
		if (time == null)
			throw new TimeNaoEncontradoException();

		time.getJogadores().add(new JogadorModel(id, idTime, nome, dataNascimento, nivelHabilidade, salario));

		// throw new UnsupportedOperationException();
	}

	@Desafio("definirCapitao")
	public void definirCapitao(Long idJogador) {

		JogadorModel jogador = buscarJogadorPorId(idJogador);
		if (jogador == null)
			throw new JogadorNaoEncontradoException();

		buscarTimePorId(jogador.getIdTime()).setCapitaoTime(idJogador);

//		throw new UnsupportedOperationException();
	}

	@Desafio("buscarCapitaoDoTime")
	public Long buscarCapitaoDoTime(Long idTime) {

		TimeModel time = buscarTimePorId(idTime);

		if (time == null)
			throw new TimeNaoEncontradoException();
		if (time.getCapitaoTime() == null)
			throw new CapitaoNaoInformadoException();

		return time.getCapitaoTime();

		// throw new UnsupportedOperationException();
	}

	@Desafio("buscarNomeJogador")
	public String buscarNomeJogador(Long idJogador) {

		JogadorModel jogador = buscarJogadorPorId(idJogador);

		if (jogador == null)
			throw new JogadorNaoEncontradoException();

		return jogador.getNome();

		// throw new UnsupportedOperationException();
	}

	@Desafio("buscarNomeTime")
	public String buscarNomeTime(Long idTime) {

		TimeModel time = buscarTimePorId(idTime);

		if (time == null)
			throw new TimeNaoEncontradoException();

		return time.getNome();

//		throw new UnsupportedOperationException();
	}

	@Desafio("buscarJogadoresDoTime")
	public List<Long> buscarJogadoresDoTime(Long idTime) {

		TimeModel time = buscarTimePorId(idTime);

		if (time == null)
			throw new TimeNaoEncontradoException();

		List<Long> jogadoresId = time.getJogadores().stream().map(x -> x.getId()).collect(Collectors.toList());

		Collections.sort(jogadoresId);

		return jogadoresId;

//		throw new UnsupportedOperationException();
	}

	@Desafio("buscarMelhorJogadorDoTime")
	public Long buscarMelhorJogadorDoTime(Long idTime) {

		TimeModel time = buscarTimePorId(idTime);

		if (time == null)
			throw new TimeNaoEncontradoException();

		List<JogadorModel> jogadores = time.getJogadores();

		if (jogadores.size() < 0)
			return null;

		Collections.sort(jogadores,
				Comparator.comparing(JogadorModel::getNivelHabilidade).reversed().thenComparing(JogadorModel::getId));

		return jogadores.get(0).getId();

		// throw new UnsupportedOperationException();
	}

	@Desafio("buscarJogadorMaisVelho")
	public Long buscarJogadorMaisVelho(Long idTime) {

		TimeModel time = buscarTimePorId(idTime);

		if (time == null)
			throw new TimeNaoEncontradoException();

		List<JogadorModel> jogadores = time.getJogadores();

		if (jogadores.size() < 0)
			return null;

		Collections.sort(jogadores,
				Comparator.comparing(JogadorModel::getDataNascimento).thenComparing(JogadorModel::getId));

		return jogadores.get(0).getId();

//		 throw new UnsupportedOperationException();
	}

	@Desafio("buscarTimes")
	public List<Long> buscarTimes() {

		List<Long> times = TIMES.stream().map(time -> time.getId()).collect(Collectors.toList());
		Collections.sort(times);
		return times;

//		throw new UnsupportedOperationException();
	}

	@Desafio("buscarJogadorMaiorSalario")
	public Long buscarJogadorMaiorSalario(Long idTime) {

		TimeModel time = buscarTimePorId(idTime);

		if (time == null)
			throw new TimeNaoEncontradoException();

		List<JogadorModel> jogadores = time.getJogadores();

		if (jogadores.size() < 0)
			return null;

		Collections.sort(jogadores,
				Comparator.comparing(JogadorModel::getSalario).reversed().thenComparing(JogadorModel::getId));

		return jogadores.get(0).getId();
//		throw new UnsupportedOperationException();
	}

	@Desafio("buscarSalarioDoJogador")
	public BigDecimal buscarSalarioDoJogador(Long idJogador) {
		JogadorModel jogador = buscarJogadorPorId(idJogador);

		if (jogador == null)
			throw new JogadorNaoEncontradoException();

		return jogador.getSalario();
//		throw new UnsupportedOperationException();
	}

	@Desafio("buscarTopJogadores")
	public List<Long> buscarTopJogadores(Integer top) {

		List<JogadorModel> jogadores = retornaJogadores();
		List<Long> melhores = new ArrayList<Long>();

		if (jogadores.size() > 0) {
			Collections.sort(jogadores, Comparator.comparing(JogadorModel::getNivelHabilidade)
					.thenComparing(JogadorModel::getId));

			melhores = jogadores.stream().map(x -> x.getId()).collect(Collectors.toList());

			if (melhores.size() <= top)

				return new ArrayList<>();

			else

				return melhores.subList(0, top);
		}
		return new ArrayList<>();

//		throw new UnsupportedOperationException();
	}

	@Desafio("buscarCorCamisaTimeDeFora")
	public String buscarCorCamisaTimeDeFora(Long timeDaCasa, Long timeDeFora) {

		TimeModel timeCasa = buscarTimePorId(timeDaCasa);
		TimeModel timeFora = buscarTimePorId(timeDeFora);

		if ((timeCasa == null || timeFora == null))
			throw new TimeNaoEncontradoException();
		if (timeCasa.getCorUniformePrincipal().equalsIgnoreCase(timeFora.getCorUniformePrincipal()))
			return timeFora.getCorUniformeSecundario();
		else
			return timeFora.getCorUniformePrincipal();
//		throw new UnsupportedOperationException();
	}

	private TimeModel buscarTimePorId(Long id) {
		return TIMES.stream().filter(x -> id.equals(x.getId())).findAny().orElse(null);
	}

	private JogadorModel buscarJogadorPorId(Long id) {
		List<JogadorModel> jogadores = retornaJogadores();
		return jogadores.stream().filter(x -> id.equals(x.getId())).findAny().orElse(null);
	}

	private void adicionaTime(TimeModel time) {
		TIMES.add(time);
	}

	private List<JogadorModel> retornaJogadores() {
		List<JogadorModel> jogadores = new ArrayList<>();
		TIMES.forEach(time -> jogadores.addAll(time.getJogadores()));
		return jogadores;
	}

	/*
	 * ===================== Classe RepositoryDAO =====================
	 * 
	 * package br.com.codenation.repository;
	 * 
	 * import java.util.ArrayList; import java.util.List;
	 * 
	 * import br.com.codenation.time.model.TimeModel;
	 * 
	 * public class RepositoryDao {
	 * 
	 * public static List<TimeModel> TIMES = new ArrayList<>(); }
	 *
	 *
	 * ===================== Classe TimeModel =====================
	 * 
	 * 
	 * package br.com.codenation.time.model;
	 * 
	 * import java.time.LocalDate; import java.util.List;
	 * 
	 * import br.com.codenation.jogador.model.JogadorModel;
	 * 
	 * public class TimeModel{
	 * 
	 * //implements Comparable<TimeModel>
	 * 
	 * private Long id; private String nome; private LocalDate dataCriacao; private
	 * String corUniformePrincipal; private String corUniformeSecundario; private
	 * Long capitaoTime; private List<JogadorModel> jogadores;
	 * 
	 * public TimeModel(Long id, String nome, LocalDate dataCriacao, String
	 * corUniformePrincipal, String corUniformeSecundario, Long capitaoTime,
	 * List<JogadorModel> jogadores) { super(); this.id = id; this.nome = nome;
	 * this.dataCriacao = dataCriacao; this.corUniformePrincipal =
	 * corUniformePrincipal; this.corUniformeSecundario = corUniformeSecundario;
	 * this.capitaoTime = capitaoTime; this.jogadores = jogadores; }
	 * 
	 * public Long getId() { return id; }
	 * 
	 * public void setId(Long id) { this.id = id; }
	 * 
	 * public String getNome() { return nome; }
	 * 
	 * public void setNome(String nome) { this.nome = nome; }
	 * 
	 * public LocalDate getDataCriacao() { return dataCriacao; }
	 * 
	 * public void setDataCriacao(LocalDate dataCriacao) { this.dataCriacao =
	 * dataCriacao; }
	 * 
	 * public String getCorUniformePrincipal() { return corUniformePrincipal; }
	 * 
	 * public void setCorUniformePrincipal(String corUniformePrincipal) {
	 * this.corUniformePrincipal = corUniformePrincipal; }
	 * 
	 * public String getCorUniformeSecundario() { return corUniformeSecundario; }
	 * 
	 * public void setCorUniformeSecundario(String corUniformeSecundario) {
	 * this.corUniformeSecundario = corUniformeSecundario; }
	 * 
	 * public Long getCapitaoTime() { return capitaoTime; }
	 * 
	 * public void setCapitaoTime(Long capitaoTime) { this.capitaoTime =
	 * capitaoTime; }
	 * 
	 * public List<JogadorModel> getJogadores() { return jogadores; }
	 * 
	 * public void setJogadores(List<JogadorModel> jogadores) { this.jogadores =
	 * jogadores; } }
	 * 
	 * 
	 * ===================== Classe jogadorModel =====================
	 * 
	 * package br.com.codenation.jogador.model;
	 * 
	 * import java.math.BigDecimal; import java.time.LocalDate;
	 * 
	 * public class JogadorModel implements Comparable<JogadorModel>{
	 * 
	 * private Long id; private Long idTime; private String nome; private LocalDate
	 * dataNascimento; private Integer nivelHabilidade; private BigDecimal salario;
	 * 
	 * 
	 * 
	 * public JogadorModel(Long id, Long idTime, String nome, LocalDate
	 * dataNascimento, Integer nivelHabilidade, BigDecimal salario) { this.id = id;
	 * this.idTime = idTime; this.nome = nome; this.dataNascimento = dataNascimento;
	 * this.nivelHabilidade = nivelHabilidade; this.salario = salario; }
	 * 
	 * public Long getId() { return id; } public void setId(Long id) { this.id = id;
	 * } public Long getIdTime() { return idTime; } public void setIdTime(Long
	 * idTime) { this.idTime = idTime; } public String getNome() { return nome; }
	 * public void setNome(String nome) { this.nome = nome; } public LocalDate
	 * getDataNascimento() { return dataNascimento; } public void
	 * setDataNascimento(LocalDate dataNascimento) { this.dataNascimento =
	 * dataNascimento; } public Integer getNivelHabilidade() { return
	 * nivelHabilidade; } public void setNivelHabilidade(Integer nivelHabilidade) {
	 * this.nivelHabilidade = nivelHabilidade; } public BigDecimal getSalario() {
	 * return salario; } public void setSalario(BigDecimal salario) { this.salario =
	 * salario; }
	 * 
	 * public int compareTo(JogadorModel jogador) { if (this.nivelHabilidade <
	 * jogador.nivelHabilidade) { return -1; } if (this.nivelHabilidade >
	 * jogador.nivelHabilidade) { return 1; } return 0; } }
	 * 
	 * 
	 * 
	 * ===================== Classe TimeFactory =====================
	 * 
	 * 
	 * package br.com.codenation.time.factory;
	 * 
	 * import java.time.LocalDate; import java.util.ArrayList;
	 * 
	 * import br.com.codenation.jogador.model.JogadorModel; import
	 * br.com.codenation.time.model.TimeModel;
	 * 
	 * public class TimeFactory {
	 * 
	 * public TimeModel montaTime(Long id, String nome, LocalDate dataCriacao,
	 * String corUniformePrincipal, String corUniformeSecundario) {
	 * 
	 * TimeModel time = new TimeModel(id, nome, dataCriacao, corUniformePrincipal,
	 * corUniformeSecundario, null, new ArrayList<JogadorModel>());
	 * 
	 * return time; }
	 * 
	 * }
	 * 
	 * 
	 */

}