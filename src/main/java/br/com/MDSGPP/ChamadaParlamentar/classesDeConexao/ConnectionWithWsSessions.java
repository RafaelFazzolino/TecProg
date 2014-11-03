package br.com.MDSGPP.ChamadaParlamentar.classesDeConexao;

import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;
import java.security.Security;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.xml.rpc.ServiceException;

import org.apache.axis.message.MessageElement;
import org.w3c.dom.NodeList;

import br.com.MDSGPP.ChamadaParlamentar.dao.DeputiesDao;
import br.gov.camara.www.SitCamaraWS.SessoesReunioes.ListPresencesCongressmanResponseListPresencesCongressmanResult;
import br.gov.camara.www.SitCamaraWS.SessoesReunioes.ListarPresencasParlamentarResponse;
import br.gov.camara.www.SitCamaraWS.SessoesReunioes.SessoesReunioesLocator;
import br.gov.camara.www.SitCamaraWS.SessoesReunioes.SessoesReunioesSoapStub;

import com.sun.mail.imap.protocol.Status;

public class ConnectionWithWsSessions {

	public ConnectionWithWsSessions() {

	}

	/**
	 * Method that creates the connection to the camera about webService
	 * sessions and meetings.
	 * 
	 * @return the connection 'service'.
	 * @throws MalformedURLException
	 *             if the URL has a problem.
	 * @throws ServiceException
	 *             if there is a problem with the connection.
	 */
	public static SessoesReunioesSoapStub obterConexao()
			throws MalformedURLException, ServiceException {
		URL url;
		url = new URL(
				"http://www.camara.gov.br/SitCamaraWS/SessoesReunioes.asmx");

		SessoesReunioesSoapStub service = (SessoesReunioesSoapStub) new SessoesReunioesLocator()
				.getSessoesReunioesSoap(url);

		return service;
	}

	/**
	 * Method that lists the presence of the Deputy.
	 * 
	 * @param service
	 *            is the variable created in last method.
	 * @param inicio
	 *            is the start of the session.
	 * @param fim
	 *            is the finish of the session.
	 * @param matricula
	 *            is the ID of the Deputy.
	 * @return the sessions.
	 */
	public static ListPresencesCongressmanResponseListPresencesCongressmanResult receberElementPresenca(
			SessoesReunioesSoapStub service, String inicio, String fim,
			String matricula) {

		// conexao criada, agora chamaremos a classe do ws
		try {
			ListPresencesCongressmanResponseListPresencesCongressmanResult sessoes = service
					.listarPresencasParlamentar(inicio, fim, matricula);

			return sessoes;

		} catch (RemoteException e) {

			e.printStackTrace();
			return null;
		}
	}

	/**
	 * Method that adds the sessions in the database.
	 * 
	 * @param data
	 *            is the date of the session
	 * @return the variable 'foi', to know if the goal has been completed.
	 * @throws SQLException
	 *             if miss spelled SQL is entered.
	 * @throws ClassNotFoundException
	 *             if the database is off.
	 * @throws MalformedURLException
	 *             if the URL has a problem.
	 * @throws ServiceException
	 *             if there is a problem with the connection.
	 */
	public static ArrayList<String> adcionarSessaoNaTable(String data)
			throws SQLException, ClassNotFoundException, MalformedURLException,
			ServiceException {
		ArrayList<String> foi = new ArrayList<String>();
		ArrayList<Integer> lista = new ArrayList<Integer>();

		DeputiesDao conexaoDeputado = new DeputiesDao();

		lista = conexaoDeputado.getMatriculaDeputados();

		foi = getDateFromWs(lista, foi, data);

		return foi;
	}

	/**
	 * Method that adds the dates in the database.
	 * 
	 * @param data
	 *            , is the date of the session.
	 * @param matricula
	 *            , is the ID of the Deputy.
	 * @return passar, to know if the goal has been completed.
	 * @throws SQLException
	 *             if miss spelled SQL is entered.
	 * @throws MalformedURLException
	 *             if the URL has a problem.
	 * @throws ServiceException
	 *             if there is a problem with the connection.
	 */
	public static ArrayList<String> adcionarDataNaTable(String data,
			String matricula) throws SQLException, MalformedURLException,
			ServiceException {
		ArrayList<String> passar = new ArrayList<String>();

		ListPresencesCongressmanResponseListPresencesCongressmanResult sessao;

		Calendar hoje = new GregorianCalendar();

		SimpleDateFormat df = new SimpleDateFormat();
		df.applyPattern("dd/MM/yyyy");

		sessao = ConnectionWithWsSessions.receberElementPresenca(
				ConnectionWithWsSessions.obterConexao(), data,
				df.format(hoje.getTime()), matricula);

		NodeList dias = sessao.get_any()[0].getElementsByTagName("dia");

		for (int i = 0; i < dias.getLength(); i++) {
			MessageElement diaTemp = (MessageElement) dias.item(i);

			NodeList dataTemp = diaTemp.getElementsByTagName("data");
			NodeList descricaoTemp = diaTemp.getElementsByTagName("descricao");

			MessageElement dataText = (MessageElement) dataTemp.item(0);

			for (int j = 0; j < descricaoTemp.getLength(); j++) {
				MessageElement descricaoText = (MessageElement) descricaoTemp
						.item(j);

				System.out.println(dataText.getFirstChild().getNodeValue());
				passar.add(dataText.getFirstChild().getNodeValue());
				passar.add(descricaoText.getFirstChild().getNodeValue());
			}
		}
		return passar;
	}

	/**
	 * This method is to get actual text for session.
	 * @param foi is an Array that contains all texts.
	 * @param sessao is the session.
	 * @param descricaoTemp is a variable temporary that contains the description.
	 * @param presencaTemp contains the precense.
	 * @param k is used of the 'for'.
	 * @return foi.
	 */
	private static ArrayList<String> getActualTextForSession(
			ArrayList<String> foi,
			ListPresencesCongressmanResponseListPresencesCongressmanResult sessao,
			NodeList descricaoTemp, NodeList presencaTemp, int k) {
		MessageElement descricaoText = (MessageElement) descricaoTemp.item(k);
		NodeList nomeTemp = sessao.get_any()[0]
				.getElementsByTagName("nomeParlamentar");
		MessageElement nomeText = (MessageElement) nomeTemp.item(0);
		MessageElement presencaText = (MessageElement) presencaTemp.item(k);

		if (presencaText.getFirstChild().getNodeValue().equals("Presença")) {

			foi.add(descricaoText.getFirstChild().getNodeValue());
			foi.add(nomeText.getFirstChild().getNodeValue());
		}
		return foi;
	}

	/**
	 * This method is to show the progress.
	 * @param placeOnArray
	 * @param total
	 */
	private static void showProgress(final int placeOnArray, final int total) {
		double porcentagem = (((double) (placeOnArray) / (double) total) * 100.0);

		System.out.println(placeOnArray + "- " + porcentagem + "%");
	}

	/**
	 * This method is to get all days.
	 * @param foi is an Array that contains all days.
	 * @param dias contains the dates.
	 * @param sessao is the session.
	 * @return foi.
	 */
	private static ArrayList<String> getNoListsOfDias(
			ArrayList<String> foi,
			NodeList dias,
			ListPresencesCongressmanResponseListPresencesCongressmanResult sessao) {
		
		int sizeDays;
		sizeDays = dias.getLength();
		
		for (int j = 0; j < sizeDays ; j++) {

			MessageElement diasTemp = (MessageElement) dias.item(j);
			NodeList descricaoTemp = diasTemp.getElementsByTagName("descricao");
			NodeList presencaTemp = diasTemp.getElementsByTagName("frequencia");

			int sizeDescription;
			sizeDescription = descricaoTemp.getLength();
			
			for (int k = 0; k < sizeDescription ; k++) {
				foi = getActualTextForSession(foi, sessao, descricaoTemp,
						presencaTemp, k);

			}
		}
		return foi;
	}

	/**
	 * This method is to get the date.
	 * @param lista is an array that contains all dates.
	 * @param foi
	 * @param data
	 * @return foi.
	 */
	private static ArrayList<String> getDateFromWs(ArrayList<Integer> lista,
		ArrayList<String> foi, String data) {
		int size;
		size = lista.size();
		for (int i = 0; i < size; i++) {
			showProgress(i, size);

			ListPresencesCongressmanResponseListPresencesCongressmanResult sessao;
			try {

				Calendar hoje = new GregorianCalendar();

				SimpleDateFormat df = new SimpleDateFormat();
				df.applyPattern("dd/MM/yyyy");

				SessoesReunioesSoapStub service = ConnectionWithWsSessions
						.obterConexao();

				sessao = ConnectionWithWsSessions.receberElementPresenca(
						service, data, df.format(hoje.getTime()),
						Integer.toString(lista.get(i)));

				NodeList dias = sessao.get_any()[0].getElementsByTagName("dia");

				foi = getNoListsOfDias(foi, dias, sessao);

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return foi;
	}

}
