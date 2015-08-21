package core;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.PrintStream;
import java.net.URL;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import core.impl.AnaliseSimples;
import exceptions.LeituraLogException;
import exceptions.ProcessaPartidaException;

public class AnaliseTeste {

	private File logPadrao;
	private File logAvancado;
	
	
	private final ByteArrayOutputStream streamConsole = new ByteArrayOutputStream();

	@Before
	public void inicializaStreams() {
	    System.setOut(new PrintStream(streamConsole));
	}
	
	@Before
	public void inicializa(){
		URL logPadraoUrl = Thread.currentThread().getContextClassLoader().getResource("logPadrao.txt");
		logPadrao = new File(logPadraoUrl.getPath());
		
		URL logAvancadoUrl = Thread.currentThread().getContextClassLoader().getResource("logAvancado.txt");
		logAvancado = new File(logAvancadoUrl.getPath());
	}

	@After
	public void fechaStreams() {
	    System.setOut(null);
	}
	
	@Test
	public void deveraIniciarCorretamente() throws LeituraLogException{
		Analise<?, ?> analise = new AnaliseSimples();
		analise.inicializa(logPadrao);
		Assert.assertNotNull(analise.getEventos());	
		Assert.assertNotNull(analise.getPartidas());		
	}
	
	
	@Test
	public void deveraReconhecerUmaPartida() throws LeituraLogException, ProcessaPartidaException{
		Analise<?, ?> analise = new AnaliseSimples();
		analise.inicializa(logPadrao);
		analise.reconhecePartidas();
		Assert.assertEquals(1, analise.getPartidas().size());
		
	}
	
	@Test
	public void deveraReconhecerCincoPartida() throws LeituraLogException, ProcessaPartidaException{
		Analise<?, ?> analise = new AnaliseSimples();
		analise.inicializa(logAvancado);
		analise.reconhecePartidas();
		Assert.assertEquals(5, analise.getPartidas().size());
		
	}
	
	@Test
	public void deveraExibirOResultado() throws LeituraLogException, ProcessaPartidaException{
		Analise<?, ?> analise = new AnaliseSimples();
		analise.inicializa(logPadrao);
		analise.reconhecePartidas();
		analise.resultado();
		Assert.assertTrue(streamConsole.toString().contains("Partida"));
		
	}
	
}
