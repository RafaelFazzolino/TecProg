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
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.xml.rpc.ServiceException;

import org.apache.axis.message.MessageElement;
import org.w3c.dom.NodeList;

import br.com.MDSGPP.ChamadaParlamentar.dao.DeputadoDao;
import br.gov.camara.www.SitCamaraWS.SessoesReunioes.ListarPresencasParlamentarResponseListarPresencasParlamentarResult;
import br.gov.camara.www.SitCamaraWS.SessoesReunioes.SessoesReunioesLocator;
import br.gov.camara.www.SitCamaraWS.SessoesReunioes.SessoesReunioesSoapStub;

public class ConexaoComWsSessoesEReunioes {

	public ConexaoComWsSessoesEReunioes() {

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
		URL url;/* Variable used to receive the site of the webservice. */
		url = new URL(
				"http://www.camara.gov.br/SitCamaraWS/SessoesReunioes.asmx");

		SessoesReunioesSoapStub service;/* create the connection of sessions. */
		service = (SessoesReunioesSoapStub) new SessoesReunioesLocator()
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

	public static ListarPresencasParlamentarResponseListarPresencasParlamentarResult receberElementPresenca(
			SessoesReunioesSoapStub service, String inicio, String fim,
			String matricula) {

		try {
			ListarPresencasParlamentarResponseListarPresencasParlamentarResult sessoes;/*
																						 * Variable
																						 * that
																						 * contains
																						 * the
																						 * sessions
																						 * .
																						 */
			sessoes = service
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
		ArrayList<String> foi;
		foi = new ArrayList<String>();
		ArrayList<Integer> list;
		list = new ArrayList<Integer>();

		DeputadoDao conectionDeputy;/*
									 * Variable that create the connection with
									 * deputies.
									 */
		conectionDeputy = new DeputadoDao();

		list = conectionDeputy.getMatriculaDeputados();

		int sizeList;/* Variable that contains the size of the list. */
		sizeList = list.size();

		for (int i = 0; i < sizeList; i++) {

			double percentage; /*
								 * Variable that will contains the percentage of
								 * assisted sessions.
								 */
			percentage = (((double) (i) / (double) sizeList) * 100.0);

			System.out.println(i + "- " + percentage + "%");
			Calendar today; /* Variable that will contains the date of today. */
			today = new GregorianCalendar();

			SimpleDateFormat df;/*
								 * Variable that contains the simple format of
								 * date.
								 */
			df = new SimpleDateFormat();
			df.applyPattern("dd/MM/yyyy");

			ListarPresencasParlamentarResponseListarPresencasParlamentarResult sessao;/*
																					 * Variable
																					 * that
																					 * contains
																					 * the
																					 * sessions
																					 * .
																					 */
			try {
				sessao = ConexaoComWsSessoesEReunioes.receberElementPresenca(
						ConexaoComWsSessoesEReunioes.obterConexao(), data,
						df.format(today.getTime()),
						Integer.toString(list.get(i)));

				NodeList days;/* Variable that contains the days. */
				days = sessao.get_any()[0].getElementsByTagName("dia");

				int sizeDays;/* Variable that contains the size of the days. */
				sizeDays = days.getLength();

				for (int j = 0; j < sizeDays; j++) {

					MessageElement diasTemp;/*
											 * Variable temporary that contains
											 * the days.
											 */
					diasTemp = (MessageElement) days.item(j);

					NodeList descricaoTemp;/*
											 * Variable temporary that contains
											 * the description of the session.
											 */
					descricaoTemp = diasTemp.getElementsByTagName("descricao");

					NodeList presencaTemp;/*
										 * Variable temporary that contains the
										 * presence.
										 */
					presencaTemp = diasTemp.getElementsByTagName("frequencia");

					int descriptionSize;/*
										 * Variable that contains the size of
										 * description.
										 */
					descriptionSize = descricaoTemp.getLength();

					for (int k = 0; k < descriptionSize; k++) {

						MessageElement descricaoText;/*
													 * Variable that contains
													 * the text of description.
													 */
						descricaoText = (MessageElement) descricaoTemp.item(k);

						NodeList nomeTemp;/*
										 * Variable temporary that contains the
										 * name of the session.
										 */
						nomeTemp = sessao.get_any()[0]
								.getElementsByTagName("nomeParlamentar");

						MessageElement nomeText;/*
												 * Variable that contains the
												 * text of the name.
												 */
						nomeText = (MessageElement) nomeTemp.item(0);

						MessageElement presencaText;/*
													 * Variable that contains
													 * the text of the presence.
													 */
						presencaText = (MessageElement) presencaTemp.item(k);

						if (presencaText.getFirstChild().getNodeValue()
								.equals("Presença")) {

							foi.add(descricaoText.getFirstChild()
									.getNodeValue());
							foi.add(nomeText.getFirstChild().getNodeValue());
						}
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return foi;
	}

	/**
	 * Method that adds the dates in the database.
	 * 
	 * @param data
	 *            , is the date of the session.
	 * @param matricula
	 *            , is the ID of the Deputy.
	 * @return pass, to know if the goal has been completed.
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

		ListarPresencasParlamentarResponseListarPresencasParlamentarResult sessao;/*
																				 * Variable
																				 * that
																				 * contains
																				 * the
																				 * sessions
																				 * .
																				 */

		Calendar today; /* Variable that will contains the date of today. */
		today = new GregorianCalendar();

		SimpleDateFormat df;/*
							 * Variable that contains the simple format of the
							 * date.
							 */
		df = new SimpleDateFormat();
		df.applyPattern("dd/MM/yyyy");

		sessao = ConexaoComWsSessoesEReunioes.receberElementPresenca(
				ConexaoComWsSessoesEReunioes.obterConexao(), data,
				df.format(today.getTime()), matricula);

		NodeList days;/* Variable that contains the days. */
		days = sessao.get_any()[0].getElementsByTagName("dia");

		int sizeDays;
		sizeDays = days.getLength();

		for (int i = 0; i < sizeDays; i++) {

			MessageElement diaTemp;/*
									 * Variable temporary that contains the
									 * days.
									 */
			diaTemp = (MessageElement) days.item(i);

			NodeList dataTemp;/* Variable temporary that contains the date. */
			dataTemp = diaTemp.getElementsByTagName("data");

			NodeList descricaoTemp;/*
									 * Variable temporary that contains the
									 * description of the session.
									 */
			descricaoTemp = diaTemp.getElementsByTagName("descricao");

			MessageElement dataText;/*
									 * Variable that contains the text of the
									 * date.
									 */
			dataText = (MessageElement) dataTemp.item(0);

			int descriptionSize;/*
								 * Variable that contains the size of
								 * description.
								 */
			descriptionSize = descricaoTemp.getLength();

			for (int j = 0; j < descriptionSize; j++) {

				MessageElement descricaoText;/*
											 * Variable that contains the text
											 * of description.
											 */
				descricaoText = (MessageElement) descricaoTemp.item(j);

				System.out.println(dataText.getFirstChild().getNodeValue());
				pass.add(dataText.getFirstChild().getNodeValue());
				pass.add(descricaoText.getFirstChild().getNodeValue());
			}
		}
		return pass;
	}
}