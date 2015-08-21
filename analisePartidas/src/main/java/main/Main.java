package main;

import java.io.File;
import java.util.regex.Pattern;

import core.Analise;
import core.impl.AnaliseSimples;
import exceptions.LeituraLogException;
import exceptions.ProcessaPartidaException;

public class Main {

	private static final File LOG_DEFAULT = new File( Thread.currentThread().getContextClassLoader().getResource("logPadrao.txt").getPath());
	
	private static final String regexPathArquivoTxt = "([aA-zZ]):(?:[/|\\\\]{1}[^:*?<>\\|/\\\\]+)+[tT][xX][tT]";
	
	public static void main(String[] args) throws LeituraLogException, ProcessaPartidaException {
		Analise<?, ?> analise = new AnaliseSimples();
		File log = LOG_DEFAULT;
		
		if(args.length > 0 && !args[0].isEmpty()){
			if(Pattern.matches(regexPathArquivoTxt, args[0])){
				log = new File(args[0]);
				if(log.isDirectory()){
					System.out.println("O path informado não é um arquivo TXT válido.");
					return;
				}
			}else{
				System.out.println("O path informado não é um arquivo TXT válido.");
				return;
			}
		}
		analise.inicializa(log);
		analise.reconhecePartidas();
		analise.resultado();
		
	}

}
