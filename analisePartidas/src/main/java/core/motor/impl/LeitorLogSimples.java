package core.motor.impl;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.List;

import core.motor.LeitorLog;
import exceptions.LeituraLogException;

public class LeitorLogSimples implements LeitorLog<String> {

	/**
	 * Realiza a leitura do log utilizando a API nativa 
	 */
	public void lerLog(File log, List<String> eventos) throws LeituraLogException {
		if (log != null && log.exists()) {
			try {
				eventos.addAll(Files.readAllLines(log.toPath(), Charset.defaultCharset()));
			} catch (IOException ex) {
				throw new LeituraLogException("Não foi possivel ler o log.", ex);
			}
		} else {
			throw new LeituraLogException("O Log informado não existe.");
		}
	}

}
