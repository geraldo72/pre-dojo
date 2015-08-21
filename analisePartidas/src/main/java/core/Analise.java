package core;

import java.io.File;
import java.util.List;

import exceptions.LeituraLogException;
import exceptions.ProcessaPartidaException;

public interface Analise<Eventos,Partidas> {

	/**
	 * Inicializa o processo de analise, como a leitura do log e a inicialização das variaveis necessarias
	 * @param log Arquivo que contem o log para analise
	 * @throws LeituraLogException
	 */
	public void inicializa(File log) throws LeituraLogException;
	
	/**
	 * Realiza o processamento dos eventos identificados na leitura do log
	 * @throws ProcessaPartidaException
	 */
	public void reconhecePartidas() throws ProcessaPartidaException;
	
	/**
	 * Verifica o resultado da analise
	 */
	public void resultado();

	public List<Eventos> getEventos();
	public List<Partidas> getPartidas();
	
}
