package core.vo;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class Jogador implements Comparable<Jogador>{
	
	/**
	 * Nome do jogador
	 */
	private String nome;
	
	/**
	 * Quantidade de vezes que matou
	 */
	private Long qtdMortes;
	
	/**
	 * Quantidade de vezes que morreu
	 */
	private Long qtdMorreu;
	
	/**
	 * Mapa das armas que o jogador utilizou
	 * 
	 * Key: Nome da Arma
	 * Value: Quantidade de vezes que utilizou a arma
	 */
	private Map<String,Integer> armasUtilizadas;
	
	/**
	 * Quantidade do melhor Streak (vezes que matou sem morrer) do jogador
	 */
	private Long qtdMelhorStreak;
	
	
	/**
	 * Quantidade do Streak (vezes que matou sem morrer) atual do jogador
	 */
	private Long qtdStreakAtual;
	
	/**
	 * Flag do troféu 'Partida sem morrer'
	 */
	private boolean semMorrer;
	
	/**
	 * Flag do troféu 'Matar 5 jogadores em 1 minuto'
	 */
	private boolean matou5em1min;
	
	
	public Jogador(){
		this.nome = "";
		this.qtdMortes = 0l;
		this.qtdMorreu = 0l;
		this.armasUtilizadas = new HashMap<String, Integer>();
		this.qtdMelhorStreak = 0l;
		this.qtdStreakAtual = 0l;
		this.semMorrer = true;
		this.matou5em1min = false;
	}
	
	public Jogador(String nome){
		this.nome = nome;
		this.qtdMortes = 0l;
		this.qtdMorreu = 0l;
		this.armasUtilizadas = new HashMap<String, Integer>();
		this.qtdMelhorStreak = 0l;
		this.qtdStreakAtual = 0l;
		this.semMorrer = true;
		this.matou5em1min = false;
	}

	
	//Gets dos dados do jogador
	public String getNome() {
		return nome;
	}

	public Long getQtdMortes() {
		return qtdMortes;
	}

	public Long getQtdMorreu() {
		return qtdMorreu;
	}

	public Long getQtdMelhorStreak() {
		return qtdMelhorStreak;
	}

	public boolean isCampeaoSemMorrer() {
		return semMorrer;
	}

	public boolean isMatou5em1min() {
		return matou5em1min;
	}

	//Metodos de acoes	
	
	/**
	 * Recupera a arma mais utilizada pelo jogador
	 * 
	 * @return nome da arma
	 */
	public String getArmaPreferida() {
		int maxQtdUso = 0;
		String armaPreferida = "";
		//Varre a lista das armas utilizadas para encontrar a maior
		for (Entry<String,Integer> entry: armasUtilizadas.entrySet()) {
			if(entry.getValue() > maxQtdUso){
				maxQtdUso = entry.getValue();
				armaPreferida = entry.getKey();
			}
		}
		return armaPreferida;
	}
	
	/**
	 * Processa o evento quando o jogador mata outro
	 * 
	 * @param arma Nome da arma utilizada
	 * @param hora Horario do evento
	 */
	public void mata(String arma, Date hora){
		if(armasUtilizadas.containsKey(arma)){
			Integer qtdUtilizada = armasUtilizadas.get(arma);
			armasUtilizadas.put(arma, ++qtdUtilizada);
		}else{
			armasUtilizadas.put(arma, 1);
		}
		this.qtdMortes++;
		this.qtdStreakAtual++;
		//Se o streak atual for maior que o melhor, atualiza o melhor
		if(this.qtdStreakAtual > this.qtdMelhorStreak){
			this.qtdMelhorStreak = this.qtdStreakAtual;
		}
	
		//TODO realizar implementacao do trofeu 5 mortes
		
	}
	
	/**
	 * Processa o evento de morte do jogador
	 */
	public void morreu(){
		this.qtdMorreu++;
		this.qtdStreakAtual = 0l;
		
		//Se morreu, nao tem award
		this.semMorrer = false;
	}

	public int compareTo(Jogador o) {
		//Se tiver mais mortes, maior
		if(this.qtdMortes > o.qtdMortes){
			return -1;
		}else if(this.qtdMortes < o.qtdMortes){ // Menos mortes, menor
			return 1;
		}else{ // Mortes iguais, compara quantas vezes morreu
			if(this.qtdMorreu < o.qtdMorreu){ //Morreu menos 
				return -1;
			}else if(this.qtdMorreu > o.qtdMorreu) {// Morreu mais
				return 1;
			}
		}
		return 0;
	}
	
}
