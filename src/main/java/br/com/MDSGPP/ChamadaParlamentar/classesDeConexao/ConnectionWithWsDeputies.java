/**
 * Class: ConnectionWithWSDeputados
 * 
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
import java.net.UnknownHostException;
import java.rmi.RemoteException;
import java.util.ArrayList;

import javax.xml.rpc.ServiceException;

import org.apache.axis.message.MessageElement;
import org.w3c.dom.NodeList;

import br.com.MDSGPP.ChamadaParlamentar.model.Deputies;
import br.com.MDSGPP.ChamadaParlamentar.model.Party;
import br.gov.camara.www.SitCamaraWS.Deputados.DeputadosLocator;
import br.gov.camara.www.SitCamaraWS.Deputados.DeputadosSoapStub;
import br.gov.camara.www.SitCamaraWS.Deputados.ObtainDeputiesResponseObtainDeputiesResult;
import br.gov.camara.www.SitCamaraWS.Deputados.ObterPartidosCDResponseObterPartidosCDResult;

public class ConnectionWithWsDeputies {

	// Empty constructor.

	public ConnectionWithWsDeputies() {

	}

	/**
	 * This method is supposed to start connective to the webService and return
	 * it.
	 * 
	 * @return service what is the connection with webService.
	 * @throws MalformedURLException
	 * @throws ServiceException
	 * @throws Un
	 *             knownHostException
	 */

	public static DeputadosSoapStub obtainConnction()
			throws MalformedURLException, ServiceException,
			UnknownHostException {
		URL url;// Variable used to receive the site of the webservice.
		url = new URL("http://www.camara.gov.br/SitCamaraWS/Deputados.asmx");
		DeputadosSoapStub service = (DeputadosSoapStub) new DeputadosLocator()
				.getDeputadosSoap(url); // create the connection of deputies.

		return service;
	}

	/**
	 * This method is only to open the connectivity to the classes from the
	 * webService.
	 * 
	 * @param service
	 *            it's the connection to the service.
	 * @return returns the connection to the classes of the webService
	 */

	public static ObtainDeputiesResponseObtainDeputiesResult receberElementDeputados(
			DeputadosSoapStub service) {

		// connection created, now call the class of ws.
		try {
			ObtainDeputiesResponseObtainDeputiesResult deputies;
			deputies = service.obterDeputados();

			return deputies;

		} catch (RemoteException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * This method is only to open the connectivity to the classes from the
	 * webService.
	 * 
	 * @param service
	 *            it's the connection to the service.
	 * @return returns the connection to the classes of the webService.
	 */

	public static ArrayList<Deputies> criaLista() throws MalformedURLException,
			UnknownHostException, ServiceException {
		ArrayList<Deputies> lista;// Receive a list of deputies.
		lista = new ArrayList<Deputies>();
		ObtainDeputiesResponseObtainDeputiesResult deputados;

		deputados = ConnectionWithWsDeputies
				.receberElementDeputados(ConnectionWithWsDeputies
						.obtainConnction());
		NodeList name = deputados.get_any()[0].getElementsByTagName("nome");/*
																			 * Receives
																			 * the
																			 * attribute
																			 * name
																			 * of
																			 * the
																			 * Member
																			 * .
																			 */

		NodeList nomeTratamento = deputados.get_any()[0]
				.getElementsByTagName("nomeParlamentar");/*
														 * Is called the
														 * treatment of a
														 * deputy.
														 */

		NodeList id = deputados.get_any()[0]
				.getElementsByTagName("ideCadastro");/*
													 * Receives the
													 * identification number of
													 * deputy.
													 */

		NodeList matricula = deputados.get_any()[0]
				.getElementsByTagName("matricula");/*
													 * Receives tuition from
													 * deputy.
													 */

		NodeList idParlamentar = deputados.get_any()[0]
				.getElementsByTagName("idParlamentar");/*
														 * Receives the
														 * identification number
														 * of parliamentary
														 * deputy.
														 */

		NodeList sexo = deputados.get_any()[0].getElementsByTagName("sexo");/*
																			 * Receive
																			 * sex
																			 * of
																			 * deputy
																			 * .
																			 */

		NodeList uf = deputados.get_any()[0].getElementsByTagName("uf");/*
																		 * Receives
																		 * the
																		 * acronym
																		 * of
																		 * the
																		 * federative
																		 * Unit
																		 * that
																		 * Deputy
																		 * .
																		 */

		NodeList party = deputados.get_any()[0].getElementsByTagName("partido");/*
																				 * Receives
																				 * the
																				 * political
																				 * party
																				 * of
																				 * the
																				 * deputy
																				 * .
																				 */

		NodeList gabinet = deputados.get_any()[0]
				.getElementsByTagName("gabinete");/*
												 * Receives the current cabinet
												 * of deputy.
												 */

		NodeList attachment = deputados.get_any()[0]
				.getElementsByTagName("anexo");/*
												 * Receive the attachment from
												 * deputy .
												 */

		NodeList fone = deputados.get_any()[0].getElementsByTagName("fone");/*
																			 * Receive
																			 * the
																			 * phone
																			 * from
																			 * deputy
																			 * .
																			 */

		NodeList email = deputados.get_any()[0].getElementsByTagName("email");/*
																			 * Receive
																			 * the
																			 * e
																			 * -
																			 * mail
																			 * from
																			 * deputy
																			 * .
																			 */

		int sizeName = name.getLength();/* Receive the size of the name. */

		for (int count = 0; count < sizeName; count++) {

			MessageElement nomeTratamentoTemp = (MessageElement) nomeTratamento
					.item(count);/*
								 * Temporary message variable to the name of
								 * treatment.
								 */

			MessageElement nomeTemp = (MessageElement) name.item(count);/*
																		 * Temporary
																		 * variable
																		 * for
																		 * message
																		 * name
																		 * on.
																		 */

			MessageElement idTemp = (MessageElement) id.item(count);/*
																	 * Temporary
																	 * variable
																	 * for
																	 * message
																	 * id on.
																	 */

			MessageElement matriculaTemp = (MessageElement) matricula
					.item(count);/*
								 * Temporary variable for enrollment message.
								 */

			MessageElement idParlamentarTemp = (MessageElement) idParlamentar
					.item(count);/*
								 * Temporary variable for message about the
								 * identity of the Parliamentary.
								 */

			MessageElement sexoTemp = (MessageElement) sexo.item(count);/*
																		 * Temporary
																		 * variable
																		 * for
																		 * message
																		 * about
																		 * sex.
																		 */

			MessageElement ufTemp = (MessageElement) uf.item(count);/*
																	 * Variable
																	 * for
																	 * temporary
																	 * federal
																	 * facility.
																	 */

			MessageElement partidoTemp = (MessageElement) party.item(count);/*
																			 * Temporary
																			 * variable
																			 * message
																			 * to
																			 * Political
																			 * Party
																			 * .
																			 */

			MessageElement gabineteTemp = (MessageElement) gabinet.item(count);/*
																				 * Temporary
																				 * variable
																				 * message
																				 * to
																				 * the
																				 * office
																				 * of
																				 * the
																				 * Deputy
																				 * .
																				 */

			MessageElement anexoTemp = (MessageElement) attachment.item(count);/*
																				 * Temporary
																				 * variable
																				 * message
																				 * to
																				 * the
																				 * annex
																				 * .
																				 */

			MessageElement foneTemp = (MessageElement) fone.item(count);/*
																		 * Temporary
																		 * variable
																		 * message
																		 * to
																		 * the
																		 * phone
																		 * .
																		 */

			MessageElement emailTemp = (MessageElement) email.item(count);/*
																		 * Temporary
																		 * variable
																		 * message
																		 * to
																		 * the
																		 * e-
																		 * mail.
																		 */

			int idInt = Integer.parseInt(idTemp.getFirstChild().getNodeValue());/*
																				 * Variable
																				 * to
																				 * received
																				 * the
																				 * integer
																				 * value
																				 * of
																				 * id
																				 * .
																				 */

			int matriculaInt = Integer.parseInt(matriculaTemp.getFirstChild()
					.getNodeValue());/*
									 * Variable to received the integer value of
									 * enrollment.
									 */

			int idParlamentarInt = Integer.parseInt(idParlamentarTemp
					.getFirstChild().getNodeValue());/*
													 * Variable to received the
													 * integer value of
													 * identification of Deputy.
													 */

			String nomeText = nomeTemp.getFirstChild().getNodeValue();/*
																	 * variable
																	 * to
																	 * received
																	 * the text
																	 * of the
																	 * name.
																	 */

			String nomeTratamentoText = nomeTratamentoTemp.getFirstChild()
					.getNodeValue();/*
									 * Variable to received the text of
									 * treatment name.
									 */

			String sexoText;// Variable to received the text of the sex.
			sexoText = sexoTemp.getFirstChild().getNodeValue();

			String ufText;// Variable to received the text of the UF.
			ufText = ufTemp.getFirstChild().getNodeValue();

			String partidoText;/*
								 * Variable to received the text of the
								 * political party.
								 */
			partidoText = partidoTemp.getFirstChild().getNodeValue();

			String gabineteText;
			String anexoText;
			String foneText;

			try {/*
				 * This try catch this one because of an error coming from the
				 * webservice in which the tag is not complete due to the
				 * treatment.
				 */

				gabineteText = gabineteTemp.getFirstChild().getNodeValue();
				anexoText = anexoTemp.getFirstChild().getNodeValue();
				foneText = foneTemp.getFirstChild().getNodeValue();

			} catch (NullPointerException e) {
				gabineteText = null;
				anexoText = null;
				foneText = null;

			}

			String emailText;// Variable to received the text of the e-mail.
			emailText = emailTemp.getFirstChild().getNodeValue();

			Deputies deputadoNovo;// Variable to received the new deputy.
			deputadoNovo = new Deputies(idParlamentarInt, matriculaInt, idInt,
					nomeText, nomeTratamentoText, sexoText, ufText,
					partidoText, gabineteText, anexoText, foneText, emailText);

			lista.add(deputadoNovo);
		}
		return lista;
	}

	/**
	 * This method is to receive the message element from the webService.
	 * 
	 * @param service
	 *            It's the connection to the webService.
	 * @return
	 */

	public static ObterPartidosCDResponseObterPartidosCDResult receberElementPartido(
			DeputadosSoapStub service) {
		// Connection created, now call the class of ws.
		try {
			ObterPartidosCDResponseObterPartidosCDResult partidos;/*
																 * Variable to
																 * received the
																 * political
																 * parties.
																 */
			partidos = service.obterPartidosCD();

			return partidos;

		} catch (RemoteException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * Method to get all the political party from the webService and return then
	 * as an {@link ArrayList} of {@link Party}.
	 * 
	 * @return an {@link ArrayList} of {@link Party} containing all the
	 *         political party.
	 * @throws MalformedURLException
	 * @throws UnknownHostException
	 * @throws ServiceException
	 */

	public static ArrayList<Party> criaListaPartidos()
			throws MalformedURLException, UnknownHostException,
			ServiceException {
		ArrayList<Party> list = new ArrayList<Party>();

		ObterPartidosCDResponseObterPartidosCDResult politicalParties = receberElementPartido(obtainConnction());

		NodeList nameParty = politicalParties.get_any()[0]
				.getElementsByTagName("nomePartido");/*
													 * Variable to received the
													 * name of the political
													 * party.
													 */

		NodeList siglaPartido;// Variable that receives the symbol of the party.
		siglaPartido = politicalParties.get_any()[0]
				.getElementsByTagName("siglaPartido");

		int sizeNameParty = nameParty.getLength();/*
												 * Variable that receives the
												 * size of name from political
												 * party.
												 */

		for (int count = 0; count < sizeNameParty; count++) {
			MessageElement namePartyTemp;
			namePartyTemp = (MessageElement) nameParty.item(count);

			MessageElement siglaPartidoTemp;
			siglaPartidoTemp = (MessageElement) siglaPartido.item(count);

			String namePartyText;
			namePartyText = namePartyTemp.getFirstChild().getNodeValue();
			String siglaPartidoText;
			siglaPartidoText = siglaPartidoTemp.getFirstChild().getNodeValue();

			// Try catch this is to pull the parties that have not exist.
			try {

			} catch (NullPointerException e) {
				Party partidoAdicionar = new Party();
				partidoAdicionar.setAcronyn(siglaPartidoText);
				partidoAdicionar.setNameParty(namePartyText);

				// If this expression is false.
				if (!namePartyText.equalsIgnoreCase("sem partido")) {
					list.add(partidoAdicionar);
				}
			}
		}

		return list;
	}
}
