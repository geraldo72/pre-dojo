package core.impl;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map.Entry;

import core.Analise;
import core.motor.LeitorLog;
import core.motor.impl.LeitorLogSimples;
import core.vo.Jogador;
import core.vo.Partida;
import exceptions.LeituraLogException;
import exceptions.ProcessaPartidaException;
import utils.Constants;

public class AnaliseSimples implements Analise<String,Partida>{
	
	private List<String> eventos;
	private List<Partida> partidas;
	
	private LeitorLog<String> leitor;

	/**
	 * Inicializa o processo de analise, instanciando as variaveis necessarias e chamando o leitor do log
	 */
	public void inicializa(File log) throws LeituraLogException {
		instanciaVariaveis();
		leitor.lerLog(log,eventos);
	}

	private void instanciaVariaveis() {
		partidas = new ArrayList<Partida>();
		eventos = new ArrayList<String>();
		leitor = new LeitorLogSimples();
	}

	/**
	 * O reconhecimento analisa a linha do log e procura pelas chaves (started, ended e killed) para realizar a acao correspondente
	 *   Started - Inicia uma nova partida
	 *   Ended - Verifica quem foi campeao e insere a partida atual na lista de partidas analisadas
	 *   killed - Registra a morte e o assassinato (exceto quando for WORLD ) para os jogadores correspondentes 
	 *    
	 */
	public void reconhecePartidas() throws ProcessaPartidaException {
		Partida partidaAtual = null;
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		for (String string : eventos) {
			String[] tokens = string.split(" ");
			Date horaEvento;
			try {
				horaEvento = sdf.parse(tokens[0] + " " + tokens[1]);
			} catch (ParseException e) {
				throw new ProcessaPartidaException("O horário do evento está fora do padrão", e);
			}
			
			// Se for nova partida
			// Ex: 23/04/2013 15:34:22 - New match 11348965 has started
			//         0         1     2  3    4       5     6     7
			if (string.contains(Constants.CHAVE_NOVA_PARTIDA)) {
				partidaAtual = new Partida(tokens[5]);
			} 
			// Se for fim da partida
			// Ex: 23/04/2013 15:39:22 - Match 11348965 has ended
			//         0         1     2  3      4       5     6 
			else if (string.contains(Constants.CHAVE_FIM_PARTIDA)) {
				partidaAtual.verificaCampeao();
				partidas.add(partidaAtual);
				partidaAtual = null;
			} 
			// Se for morte
			// Ex: 23/04/2013 15:36:04 - Roman killed Nick using M16
			//		   0         1     2   3     4      5    6    7
			else if (string.contains(Constants.CHAVE_MORTE)) {
				String nomeAssassino = tokens[3];

				// Se for <WORLD> nao registra assassinato
				if (!Constants.CHAVE_MUNDO.equalsIgnoreCase(nomeAssassino)) {
					Jogador assassino = partidaAtual.registraJogador(tokens[3]);
					assassino.mata(tokens[7], horaEvento);
				}

				Jogador morto = partidaAtual.registraJogador(tokens[5]);
				morto.morreu();

			}

		}
	}
	
	/**
	 * Para o resultado, foi implementado um print simples no console. 
	 */
	public void resultado() {
		for (Partida partida : partidas) {

			//Exibe cabeçalho
			System.out.format("+------------------------------------------------------------------------------------------------------+%n");
			System.out.format("| %-42s%-59s|%n", "", "Partida " + partida.getId());
			System.out.format("+------------------------------------------------------------------------------------------------------+%n");
			System.out.printf("|         |     Jogador     | Matou | Morreu | Melhor Streak | Arma Fav. | Sem Morrer | 5 Mortes em 1m |%n");
			System.out.format("+------------------------------------------------------------------------------------------------------+%n");

			//Exibe campeão
			String formatCampeao = "| CAMPEÃO | %-15s | %-5d | %-6d | %-13s | %-9s | %-10s | %-14s |%n";
			System.out.format(formatCampeao, 
					partida.getCampeao().getNome(),
					partida.getCampeao().getQtdMortes(), 
					partida.getCampeao().getQtdMorreu(),
					partida.getCampeao().getQtdMelhorStreak(), 
					partida.getCampeao().getArmaPreferida(),
					(partida.getCampeao().isCampeaoSemMorrer() ? "*" : ""),
					(partida.getCampeao().isMatou5em1min() ? "*" : ""));

			//Exibe lista dos jogadores
			String formatJogadores = "|         | %-15s | %-5d | %-6d | %-13s | %-9s | %-10s | %-14s |%n";
			for (Entry<String, Jogador> jogadorEntry : partida.getRanking()) {
				
				Jogador jogador = jogadorEntry.getValue();
				System.out.format(formatJogadores,
						jogador.getNome(),
						jogador.getQtdMortes(),
						jogador.getQtdMorreu(),
						jogador.getQtdMelhorStreak(),
						jogador.getArmaPreferida(),
						(jogador.isCampeaoSemMorrer() ? "*" : ""),
						(jogador.isMatou5em1min() ? "*" : ""));
				
			}
			System.out.format("+------------------------------------------------------------------------------------------------------+%n");

		}

	}

	public List<String> getEventos() {
		return eventos;
	}

	public List<Partida> getPartidas() {
		return partidas;
	}

}
