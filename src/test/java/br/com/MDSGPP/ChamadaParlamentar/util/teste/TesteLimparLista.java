package br.com.MDSGPP.ChamadaParlamentar.util.teste;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import br.com.MDSGPP.ChamadaParlamentar.model.Statistic;
import br.com.MDSGPP.ChamadaParlamentar.util.LimparLista;

public class TesteLimparLista {
	
	LimparLista limpar;
	
	@Before
	public void setUp() {
		limpar = new LimparLista();
	}
	
	@Test
	public void testLimparListaConstrutor() {
		assertNotNull(limpar);
	}
	
	@Test
	public void testLimparLista() {
		ArrayList<Statistic> lista = new ArrayList<Statistic>();
		for(int i = 0; i<10; i++) {
			Statistic estatistica = Mockito.spy(new Statistic());
			Mockito.when(estatistica.getNumberSession()).thenReturn("50");
			lista.add(estatistica);
		}
		
		for(int i = 0; i<10; i++) {
			Statistic estatistica = Mockito.spy(new Statistic());
			lista.add(estatistica);
		}
		
		ArrayList<ArrayList<Statistic>> listaRecebida = LimparLista.limparLista(lista);
		
		assertNotNull(listaRecebida);
		ArrayList<Statistic> bons = listaRecebida.get(0);
		ArrayList<Statistic> ruins = listaRecebida.get(1);
		
		assertNotNull(bons);
		assertNotNull(ruins);
		
		for(int i = 0; i<bons.size(); i++) {
			assertTrue(bons.get(i).getNumberSession().equalsIgnoreCase("50"));
			assertTrue(ruins.get(i).getNumberSession() == null);
		}
		
	}

}






