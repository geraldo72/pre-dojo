package core.motor;

import static org.junit.Assert.assertArrayEquals;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import core.motor.impl.LeitorLogSimples;
import exceptions.LeituraLogException;

@RunWith(PowerMockRunner.class)
@PrepareForTest( { LeitorLog.class, LeitorLogSimples.class })
public class LeitorLogTeste {
	
	private LeitorLog<String> leitor;
	
	private final ByteArrayOutputStream streamConsole = new ByteArrayOutputStream();

	@Before
	public void inicializaStreams() {
	    System.setOut(new PrintStream(streamConsole));
	}
	
	@Before
	public void inicializa(){
		leitor = new LeitorLogSimples();
	}

	@After
	public void fechaStreams() {
	    System.setOut(null);
	}

	@Test
	public void deveraLerLogPadraoComSucesso() throws LeituraLogException {
		File logPadrao = getLogPadrao();
		
		List<String> eventos = new ArrayList<String>();
		leitor.lerLog(logPadrao, eventos);
		
		assertArrayEquals(getListLogPadrao().toArray(), eventos.toArray());
		
	}

	@Test
	public void deveraLerLogAvancadoComSucesso() throws LeituraLogException {
		File logAvancado = getLogAvancado();
		
		List<String> eventos = new ArrayList<String>();
		leitor.lerLog(logAvancado, eventos);
		
		assertArrayEquals(getListLogAvancado().toArray(), eventos.toArray());
	}

	@Test(expected=LeituraLogException.class)
	public void deveraLerLogNulo() throws LeituraLogException {
		File logPadrao = null;
		
		List<String> eventos = new ArrayList<String>();
		leitor.lerLog(logPadrao, eventos);
		
	}
	
	@Test(expected=LeituraLogException.class)
	public void deveraDarErroNaLeitura() throws Exception {
		File logPadrao = getLogPadrao();
		
		PowerMockito.mockStatic(Files.class);
		PowerMockito.when(Files.readAllLines(logPadrao.toPath(),Charset.defaultCharset())).thenThrow(new IOException());
		
		List<String> eventos = new ArrayList<String>();
		leitor.lerLog(logPadrao, eventos);
		
		assertArrayEquals(getListLogPadrao().toArray(), eventos.toArray());
		
	}
	

	private File getLogAvancado() {
		URL logAvancadoUrl = Thread.currentThread().getContextClassLoader().getResource("logAvancado.txt");
		File logAvancado = new File(logAvancadoUrl.getPath());
		return logAvancado;
	}
	private File getLogPadrao() {
		URL logPadraoUrl = Thread.currentThread().getContextClassLoader().getResource("logPadrao.txt");
		File logPadrao = new File(logPadraoUrl.getPath());
		return logPadrao;
	}
	
	

	private List<String> getListLogPadrao() {
		List<String> lista = new ArrayList<String>();
		lista.add("23/04/2013 15:34:22 - New match 11348965 has started");
		lista.add("23/04/2013 15:36:04 - Roman killed Nick using M16");
		lista.add("23/04/2013 15:36:33 - <WORLD> killed Nick by DROWN");
		lista.add("23/04/2013 15:39:22 - Match 11348965 has ended");
		return lista;
	}
	
	private List<String> getListLogAvancado() {
		List<String> lista = new ArrayList<String>();
		lista.add("23/04/2013 15:34:22 - New match 11348965 has started");
		lista.add("23/04/2013 15:36:04 - Roman killed Nick using M16");
		lista.add("23/04/2013 15:36:08 - Roman killed Nick using M17");
		lista.add("23/04/2013 15:36:10 - Roman killed Nick using M16");
		lista.add("23/04/2013 15:36:15 - Roman killed Nick using M17");
		lista.add("23/04/2013 15:37:03 - Roman killed Nick using M17");
		lista.add("23/04/2013 15:37:04 - Nick killed Roman using M17");
		lista.add("23/04/2013 15:37:08 - <WORLD> killed Nick by DROWN");
		lista.add("23/04/2013 15:37:11 - <WORLD> killed Nick by DROWN");
		lista.add("23/04/2013 15:37:15 - <WORLD> killed Nick by DROWN");
		lista.add("23/04/2013 15:37:33 - <WORLD> killed Nick by DROWN");
		lista.add("23/04/2013 15:38:04 - Nick killed Roman using M16");
		lista.add("23/04/2013 15:38:06 - Nick killed Roman using M16");
		lista.add("23/04/2013 15:38:28 - Nick killed Roman using M16");
		lista.add("23/04/2013 15:38:28 - Nick killed Roman using M16");
		lista.add("23/04/2013 15:39:22 - Match 11348965 has ended");
		lista.add("23/04/2013 15:34:22 - New match 11348966 has started");
		lista.add("23/04/2013 15:36:04 - Roman killed Nick using M16");
		lista.add("23/04/2013 15:36:08 - Roman killed Nick using M16");
		lista.add("23/04/2013 15:36:15 - Roman killed Nick using M16");
		lista.add("23/04/2013 15:36:22 - Roman killed Nick using M16");
		lista.add("23/04/2013 15:36:25 - Roman killed Nick using M16");
		lista.add("23/04/2013 15:36:33 - Roman killed Nick using M16");
		lista.add("23/04/2013 15:36:44 - Nick killed Roman using M16");
		lista.add("23/04/2013 15:36:50 - Roman killed Nick using M16");
		lista.add("23/04/2013 15:36:52 - Roman killed Nick using M16");
		lista.add("23/04/2013 15:36:55 - Roman killed Nick using M16");
		lista.add("23/04/2013 15:36:57 - Roman killed Nick using M16");
		lista.add("23/04/2013 15:37:08 - Roman killed Nick using M16");
		lista.add("23/04/2013 15:39:22 - Match 11348966 has ended");
		lista.add("23/04/2013 15:34:22 - New match 11348967 has started");
		lista.add("23/04/2013 15:36:04 - Roman killed Nick using M16");
		lista.add("23/04/2013 15:36:08 - Roman killed Nick using M16");
		lista.add("23/04/2013 15:36:10 - Roman killed Nick using M16");
		lista.add("23/04/2013 15:36:15 - Roman killed Nick using M16");
		lista.add("23/04/2013 15:37:04 - Roman killed Nick using M16");
		lista.add("23/04/2013 15:38:04 - Roman killed Nick using M16");
		lista.add("23/04/2013 15:39:22 - Match 11348967 has ended");
		lista.add("23/04/2013 15:34:22 - New match 11348968 has started");
		lista.add("23/04/2013 15:36:04 - Roman killed Nick using M16");
		lista.add("23/04/2013 15:36:08 - Roman killed Nick using M16");
		lista.add("23/04/2013 15:36:10 - Roman killed Nick using M16");
		lista.add("23/04/2013 15:37:01 - Roman killed Nick using M16");
		lista.add("23/04/2013 15:37:05 - Roman killed Nick using M16");
		lista.add("23/04/2013 15:39:22 - Match 11348968 has ended");
		lista.add("23/04/2013 15:34:22 - New match 11348969 has started");
		lista.add("23/04/2013 15:36:04 - Roman killed Nick using M16");
		lista.add("23/04/2013 15:36:08 - Roman killed Wayne using AK-47");
		lista.add("23/04/2013 15:36:10 - Roman killed Banner using M16");
		lista.add("23/04/2013 15:37:01 - Tony killed Roman using FuseCannon");
		lista.add("23/04/2013 15:37:05 - Tony killed Nick using NukeBomb");
		lista.add("23/04/2013 15:37:05 - Tony killed Roman using Knife");
		lista.add("23/04/2013 15:37:05 - Wayne killed Nick using Hand");
		lista.add("23/04/2013 15:37:05 - Tony killed Wayne using TossMoney");
		lista.add("23/04/2013 15:37:05 - Tony killed Banner using NukeBomb");
		lista.add("23/04/2013 15:37:05 - <WORLD> killed Nick by InfinityGaulet");
		lista.add("23/04/2013 15:39:22 - Match 11348969 has ended");
		return lista;
	}

}
