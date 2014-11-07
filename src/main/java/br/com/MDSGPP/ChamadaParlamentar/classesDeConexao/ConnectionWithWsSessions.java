/**
 * License: This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This class is responsible to establish a connection to the webService Deputados 
 * from the Deputy chamber, and return data.
 */

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
		URL url;/* Variable that contains the link of the WS. */
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

		/* connection created, now get the class of WS. */
		try {
			ListPresencesCongressmanResponseListPresencesCongressmanResult sessions;
			sessions = service.listarPresencasParlamentar(inicio, fim,
					matricula);

			return sessions;

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
		ArrayList<Integer> list = new ArrayList<Integer>();

		DeputiesDao conexaoDeputado = new DeputiesDao();

		list = conexaoDeputado.getMatriculaDeputados();

		foi = getDateFromWs(list, foi, data);

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
		ArrayList<String> pass = new ArrayList<String>();

		ListPresencesCongressmanResponseListPresencesCongressmanResult session;

		Calendar hoje = new GregorianCalendar();

		SimpleDateFormat df = new SimpleDateFormat();
		df.applyPattern("dd/MM/yyyy");

		session = ConnectionWithWsSessions.receberElementPresenca(
				ConnectionWithWsSessions.obterConexao(), data,
				df.format(hoje.getTime()), matricula);

		NodeList dias = session.get_any()[0].getElementsByTagName("dia");

		for (int i = 0; i < dias.getLength(); i++) {
			MessageElement diaTemp = (MessageElement) dias.item(i);

			NodeList dataTemp = diaTemp.getElementsByTagName("data");
			NodeList descricaoTemp = diaTemp.getElementsByTagName("descricao");

			MessageElement dataText = (MessageElement) dataTemp.item(0);

			int sizeDescription;/*
								 * Variable that contains the size of
								 * description.
								 */
			sizeDescription = descricaoTemp.getLength();

			for (int j = 0; j < sizeDescription; j++) {
				MessageElement descricaoText = (MessageElement) descricaoTemp
						.item(j);

				System.out.println(dataText.getFirstChild().getNodeValue());
				pass.add(dataText.getFirstChild().getNodeValue());
				pass.add(descricaoText.getFirstChild().getNodeValue());
			}
		}
		return pass;
	}

	/**
	 * This method is to get actual text for session.
	 * 
	 * @param foi
	 *            is an Array that contains all texts.
	 * @param sessao
	 *            is the session.
	 * @param descricaoTemp
	 *            is a variable temporary that contains the description.
	 * @param presencaTemp
	 *            contains the precense.
	 * @param k
	 *            is used of the 'for'.
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
	 * 
	 * @param placeOnArray
	 * @param total
	 */
	private static void showProgress(final int placeOnArray, final int total) {
		double percentage = (((double) (placeOnArray) / (double) total) * 100.0);

		System.out.println(placeOnArray + "- " + percentage + "%");
	}

	/**
	 * This method is to get all days.
	 * 
	 * @param foi
	 *            is an Array that contains all days.
	 * @param dias
	 *            contains the dates.
	 * @param sessao
	 *            is the session.
	 * @return foi.
	 */
	private static ArrayList<String> getNoListsOfDias(
			ArrayList<String> foi,
			NodeList days,
			ListPresencesCongressmanResponseListPresencesCongressmanResult sessao) {

		int sizeDays;
		sizeDays = days.getLength();

		for (int j = 0; j < sizeDays; j++) {

			MessageElement diasTemp = (MessageElement) days.item(j);
			NodeList descricaoTemp = diasTemp.getElementsByTagName("descricao");
			NodeList presencaTemp = diasTemp.getElementsByTagName("frequencia");

			int sizeDescription;/*
								 * Variable that contains the size of
								 * Description.
								 */
			sizeDescription = descricaoTemp.getLength();

			for (int k = 0; k < sizeDescription; k++) {
				foi = getActualTextForSession(foi, sessao, descricaoTemp,
						presencaTemp, k);

			}
		}
		return foi;
	}

	/**
	 * This method is to get the date.
	 * 
	 * @param lista
	 *            is an array that contains all dates.
	 * @param foi
	 * @param data
	 * @return foi.
	 */
	private static ArrayList<String> getDateFromWs(ArrayList<Integer> lista,
			ArrayList<String> foi, String data) {
		int sizeList; /* Variable that contains the size of List. */
		sizeList = lista.size();
		for (int i = 0; i < sizeList; i++) {
			showProgress(i, sizeList);

			ListPresencesCongressmanResponseListPresencesCongressmanResult session;
			try {

				Calendar today;/* Variable that contains the date of today. */
				today = new GregorianCalendar();

				SimpleDateFormat df;/* Contains the simple Date format. */
				df = new SimpleDateFormat();
				df.applyPattern("dd/MM/yyyy");

				SessoesReunioesSoapStub service;
				service = ConnectionWithWsSessions.obterConexao();

				session = ConnectionWithWsSessions.receberElementPresenca(
						service, data, df.format(today.getTime()),
						Integer.toString(lista.get(i)));

				NodeList days;/* Variable that contains all days. */
				days = session.get_any()[0].getElementsByTagName("dia");

				foi = getNoListsOfDias(foi, days, session);

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return foi;
	}

}
