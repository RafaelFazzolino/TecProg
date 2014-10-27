package br.com.MDSGPP.ChamadaParlamentar.dao.teste;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import br.com.MDSGPP.ChamadaParlamentar.dao.ConnectionFactory;
import br.com.MDSGPP.ChamadaParlamentar.dao.DeputiesDao;
import br.com.MDSGPP.ChamadaParlamentar.model.Deputies;

public class TesteDeputadoDao {

	
	DeputiesDao deputadoDao;
	
	Deputies deputado1 = new Deputies();
	Deputies deputado2 = new Deputies();

	@Before
	public void setUp() throws ClassNotFoundException, SQLException {
		deputadoDao = new DeputiesDao();
	}

	public ArrayList<Deputies> criaLista() {
		ArrayList<Deputies> lista = new ArrayList<Deputies>();

		deputado1.setAnnexx("123");

		deputado1.setEmail("teste");
		deputado1.setIdDeputy(123);
		deputado1.setIdRegister(1234);
		deputado1.setRegsitry(123);
		deputado1.setNameCivilCongressman("nomeDeTeste");
		deputado1.setNameTreatmentCongressman("NomeDeTratamentoTeste");
		deputado1.setNumberCabinet("123");
		deputado1.setParty("partidoTeste");
		deputado1.setSex("teste");
		deputado1.setTelephone("12");
		deputado1.setFederativeUnit("Df");

		deputado2.setAnnexx("0");
		deputado2.setEmail("teste2");
		deputado2.setIdDeputy(000);
		deputado2.setIdRegister(0000);
		deputado2.setRegsitry(0000);
		deputado2.setNameCivilCongressman("nomeDeTeste2");
		deputado2.setNameTreatmentCongressman("NomeDeTratamentoTeste2");
		deputado2.setNumberCabinet("00000000");
		deputado2.setParty("partidoTeste2");
		deputado2.setSex("teste2");
		deputado2.setTelephone("00000000");
		deputado2.setFederativeUnit("Df");

		lista.add(deputado1);
		lista.add(deputado2);

		return lista;
	}

	@Test
	public void testDeputadoDao() {
		assertNotNull(deputadoDao);
	}

	@Test
	public void TestegetMatriculaDeputados() throws SQLException {
		ArrayList<Integer> lista = deputadoDao.getMatriculaDeputados();
		assertNotNull(lista);
	}

	@Test
	public void testAdicionaDeputado() throws SQLException {
		ArrayList<Deputies> deputados = criaLista();
		deputadoDao.addDeputy(deputados);
	}

	@Test
	public void testInstancia1() throws SQLException {
		assertNotNull(deputadoDao);

	}

	@Test
	public void testlista() throws SQLException {
		assertTrue(criaLista().size() == 2);
	}

	@Test
	public void testDeputado1() throws SQLException {
		assertNotNull(deputado1);
	}

	@Test
	public void testDeputado2() throws SQLException {
		assertNotNull(deputado2);
	}

	@After
	public void tearDown() throws ClassNotFoundException, SQLException {
		Connection conexao = new ConnectionFactory().getConnection();

		String sql = "Delete from deputado where nomeCivil = ?";

		java.sql.PreparedStatement stmt = conexao.prepareStatement(sql);

		ArrayList<Deputies> lista = criaLista();

		int sizeList;
		sizeList = lista.size();

		for (int i = 0; i < sizeList; i++) {

			stmt.setString(1, lista.get(i).getNameCivilCongressman());

			stmt.execute();
		}
		stmt.close();
	}

}
