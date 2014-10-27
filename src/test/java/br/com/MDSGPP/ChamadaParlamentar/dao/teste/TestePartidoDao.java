package br.com.MDSGPP.ChamadaParlamentar.dao.teste;

import static org.junit.Assert.*;

import java.net.MalformedURLException;
import java.net.UnknownHostException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.xml.rpc.ServiceException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import br.com.MDSGPP.ChamadaParlamentar.dao.PartidoDao;
import br.com.MDSGPP.ChamadaParlamentar.model.Party;

public class TestePartidoDao {
	PartidoDao partidoDao;
	
	@Before
	public void setUp() throws ClassNotFoundException, SQLException {
		partidoDao = new PartidoDao();
	}
	
	public ArrayList<Party> criaLista() {
		ArrayList<Party> lista = new ArrayList<Party>();
		
		for(int i = 0; i<10; i++) {
			Party partido = new Party();
			
			partido.setAcronyn("S"+i);
			partido.setNameParty("nome" +i);
			lista.add(partido);
		}
		return lista;
	}
	
	@Test
	public void testPartidoDao()
			throws ClassNotFoundException, SQLException {
		assertNotNull(partidoDao.getConnection());
	}

	@Test
	public void testAdicionarPartidoNaTable()
			throws MalformedURLException, UnknownHostException, 
			SQLException, ServiceException, ClassNotFoundException {
	
		ArrayList<Party> partido = criaLista();
		ArrayList<Party> listaTeste = new ArrayList<Party>();
		
		partidoDao.adicionarPartidoNaTable(partido);
		partidoDao.adicionarPartidoNaTable(listaTeste);
		
		String sql = "Select * from partido";
		
		PreparedStatement stmt = partidoDao.getConnection().prepareStatement(sql);
		
		ResultSet rs = stmt.executeQuery();
		
		ArrayList<String> siglas = new ArrayList<String>();
		
		while(rs.next()) {
			siglas.add(rs.getString("sigla"));
		}
		
		for(int i = 0; i<partido.size(); i++) {
			int j;
			for(j = 0; j<siglas.size(); j++) {
				if(partido.get(i).getAcronyn().equals(siglas.get(j))) {
					break;
				}
			}
			assertTrue(partido.get(i).getAcronyn().equals(siglas.get(j)));
		}
	}
	
	@Test
	public void testPegarPartidos() throws SQLException {
		ArrayList<ArrayList<String>> listaDeTeste = partidoDao.pegarPartidos();
		
		for(int i = 0; i<listaDeTeste.size(); i++) {
			for(int j = 0; j<listaDeTeste.get(i).size(); j++) {
				assertNotNull(listaDeTeste.get(i).get(j));
			}
		}
		
		assertTrue(listaDeTeste.size() >0);
	}
	
	@After
	public void tearDown() throws ClassNotFoundException, SQLException {
		ArrayList<Party> partido = criaLista();
		
		String sql = "Delete from partido where sigla = ?";
		
		PreparedStatement stmt = partidoDao.getConnection().prepareStatement(sql);
		
		
		for(int i = 0; i<partido.size(); i++) {
			stmt.setString(1, partido.get(i).getAcronyn());
			stmt.execute();
		}
		
		stmt.close();
	}

}
