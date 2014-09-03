package br.com.MDSGPP.ChamadaParlamentar.dao;

import java.net.MalformedURLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.xml.rpc.ServiceException;

import br.com.MDSGPP.ChamadaParlamentar.exception.DataFormatoErradoException;
import br.com.MDSGPP.ChamadaParlamentar.model.Dia;
import br.com.MDSGPP.ChamadaParlamentar.model.SessoesEReunioes;

public class SessoesEReunioesDao extends ConnectionFactory {

	public SessoesEReunioesDao() throws ClassNotFoundException, SQLException {
		new ConnectionFactory().getConnection();
	}
	
	/**
	 * This method add a date in the DB table
	 * @param insert
	 * @throws SQLException
	 * @throws MalformedURLException
	 * @throws ServiceException
	 */
	
	public void adcionarDataNaTable(ArrayList<String> insert) 
			throws SQLException, MalformedURLException, ServiceException {			     

		for(int i = 0; i <insert.size(); i= i+2) {
			String sql = "insert into datas(datas, sessao) values(?, ?)";

			PreparedStatement stmt;

			stmt = getConexao().prepareStatement(sql);
			stmt.setString(1, insert.get(i));
			stmt.setString(2, insert.get(i+1));

			stmt.execute();
			stmt.close();
		}
	}
	
	/**
	 * This method add a session on a DB table
	 * @param insert
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 * @throws MalformedURLException
	 * @throws ServiceException
	 */

	public void adcionarSessaoNaTable(ArrayList<String> insert)
			throws SQLException, ClassNotFoundException, MalformedURLException, ServiceException {

		String sql = "insert into sessao(idSessoes, deputadoPresente)values(?,?)";
		PreparedStatement stmt = getConexao().prepareStatement(sql);

		for(int i = 0; i<insert.size(); i=i+2) {
			stmt.setString(1, insert.get(i));
			stmt.setString(2, insert.get(i+1));

			stmt.execute();
		}
		stmt.close();
	}
	
	/**
	 * This method return the number of sessions that some deputy was present
	 * @return
	 * @throws SQLException
	 */

	public int passarNumeroDeSessoes() throws SQLException
	{
		String sql = "select * from datas";

		PreparedStatement stmt= ConnectionFactory.getConexao().prepareStatement(sql);
		ResultSet rs = stmt.executeQuery(); 
		int i = 0;

		while(rs.next())
		{
			i++;
		}

		rs.close();
		return i;		
	}
	
	/**
	 * This method creates a list of sessions and reunions that a deputy was present
	 * @return
	 * @throws SQLException
	 * @throws DataFormatoErradoException
	 */
	public ArrayList<SessoesEReunioes> pegarSessoes() throws SQLException, DataFormatoErradoException {
		String sql = "Select * from datas";
		
		PreparedStatement stmt = ConnectionFactory.getConexao().prepareStatement(sql);
		
		ResultSet rs = stmt.executeQuery();
		
		ArrayList<SessoesEReunioes> lista = new ArrayList<SessoesEReunioes>();
		
		while(rs.next()) {
			SessoesEReunioes sessao = new SessoesEReunioes();
			sessao.setData(rs.getString("datas"));
			lista.add(sessao);
		}
		
		return lista;		
	}
	
	/**
	 * This method search a session based in its ID in the DB
	 * @param descricao
	 * @return
	 * @throws SQLException
	 */

	public ArrayList<String> procurarSessao(String descricao) throws SQLException {
		String sql = "select * from sessao where idSessoes LIKE ?";

		PreparedStatement stmt = ConnectionFactory.getConexao().prepareStatement(sql);

		stmt.setString(1, descricao);
		ResultSet rs = stmt.executeQuery();

		ArrayList<String> lista = new ArrayList<String>();

		while(rs.next()) {
			lista.add(rs.getString("deputadoPresente"));
		}

		rs.close();
		return lista;
	}
	
	/**
	 * This method search a session based on a date
	 * @param data
	 * @return
	 * @throws SQLException
	 */
	
	public Dia procuraDia(String data) throws SQLException{
		Dia dia = new Dia();
		String sql = "select * from datas where datas LIKE ?";
	
		PreparedStatement stmt = getConexao().prepareStatement(sql);
		stmt.setString(1, data);
		ResultSet rs = stmt.executeQuery();
		ArrayList<SessoesEReunioes> lista = new ArrayList<SessoesEReunioes>();

		while(rs.next()){
			SessoesEReunioes sessoes = new SessoesEReunioes();
			sessoes.setDescricao(rs.getString("sessao"));
			sessoes.setDescricaoCompleta(sessoes.getDescricao());
			lista.add(sessoes);
		}
		
		dia.setListaSessoes(lista);
		rs.close();
		return dia;
	}
}

