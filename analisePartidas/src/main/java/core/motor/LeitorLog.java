package core.motor;

import java.io.File;
import java.util.List;

import exceptions.LeituraLogException;

public interface LeitorLog<T> {
	
	/**
	 * Realiza a leitura do log e grava os eventos em uma lista
	 * O objeto da lista irá depender da implementacao
	 * 
	 * @param log Arquivo que contem o log
	 * @param eventos Lista que contem os eventos do log
	 * 
	 * @throws LeituraLogException 
	 */
	public void lerLog(File log, List<T> eventos) throws LeituraLogException;

}
