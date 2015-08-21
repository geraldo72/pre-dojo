package core.vo;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.SortedSet;

import utils.Util;

public class Partida {
	
	/**
	 * Mapa de jogadores da partida
	 * 
	 * Key: Nome do jogador
	 * Value: Instancia do Jogador
	 */
	private Map<String,Jogador> jogadores;
	
	/**
	 * Referência do jogador campeão
	 */
	private Jogador campeao;
	
	/**
	 * Id da partida
	 */
	private String id;
	
	public Partida(){
		this.jogadores = new HashMap<String,Jogador>();
		this.campeao = null;
		this.id = "";
	}
	
	public Partida(String id){
		this.jogadores = new HashMap<String,Jogador>();
		this.campeao = null;
		this.id = id;
	}
	
	public Partida(String id, Jogador campeao){
		this.jogadores = new HashMap<String,Jogador>();
		this.campeao = campeao;
		this.id = id;
	}

	public Map<String,Jogador> getJogadores() {
		return jogadores;
	}

	public String getId() {
		return id;
	}

	public Jogador getCampeao() {
		return campeao;
	}

	public void setCampeao(Jogador campeao) {
		this.campeao = campeao;
	}
	
	/**
	 * Retorna a lista de jogadores ordenados pela quantidade de mortes e quantidade que morreu
	 * @return Lista de jorgadores ordenada
	 * @see SortedSet 
	 */
	public SortedSet<Entry<String,Jogador>> getRanking(){
		
		return Util.ordenaMapaPorValor(this.getJogadores());
	}

	/**
	 * Cadastra/recupera jogador na partida
	 * @param nome Nome do jogador
	 * @return jogador da partida
	 */
	public Jogador registraJogador(String nome) {
		Jogador jogador = getJogadores().get(nome);
		
		if(jogador == null){
			jogador = new Jogador(nome);
			this.getJogadores().put(nome, jogador);
		}
		
		return jogador;
		
	}

	/**
	 * Analisa o ranking de jogadores e identifica o campeão
	 */
	public void verificaCampeao() {
		
		if(this.campeao == null){
			Entry<String,Jogador> campeaoEntry = getRanking().first();
			this.campeao = campeaoEntry.getValue();
			this.getJogadores().remove(campeaoEntry.getKey());
		}
	}
}
