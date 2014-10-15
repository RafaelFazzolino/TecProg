package br.com.MDSGPP.ChamadaParlamentar.classesDeConexao;

import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;
import java.rmi.RemoteException;
import java.util.ArrayList;

import javax.xml.rpc.ServiceException;

import org.apache.axis.message.MessageElement;
import org.w3c.dom.NodeList;

import br.com.MDSGPP.ChamadaParlamentar.model.Deputados;
import br.com.MDSGPP.ChamadaParlamentar.model.Partidos;
import br.gov.camara.www.SitCamaraWS.Deputados.DeputadosLocator;
import br.gov.camara.www.SitCamaraWS.Deputados.DeputadosSoapStub;
import br.gov.camara.www.SitCamaraWS.Deputados.ObterDeputadosResponseObterDeputadosResult;
import br.gov.camara.www.SitCamaraWS.Deputados.ObterPartidosCDResponseObterPartidosCDResult;

public class ConexaoComWsDeputados {
	
	/**
	 * Empty constructor.
	 */

	public ConexaoComWsDeputados() {


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


	public static DeputadosSoapStub obterConexao()
			throws MalformedURLException, ServiceException, UnknownHostException {
		URL url;/*Variable used to receive the site of the webservice.*/
		url = new URL("http://www.camara.gov.br/SitCamaraWS/Deputados.asmx");
		DeputadosSoapStub service;/*create the connection of deputies.*/
		service = (DeputadosSoapStub) new DeputadosLocator().getDeputadosSoap(url);

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

	public static ObterDeputadosResponseObterDeputadosResult 
	receberElementDeputados(DeputadosSoapStub service) {

		/*connection created, now call the class of ws.*/
		try {
			ObterDeputadosResponseObterDeputadosResult deputies;
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

	public static ArrayList<Deputados> criaLista() throws MalformedURLException,
	UnknownHostException, ServiceException {
		ArrayList<Deputados> lista;/*Receive a list of deputies.*/
		lista = new ArrayList<Deputados>();
		ObterDeputadosResponseObterDeputadosResult deputados;

		deputados = ConexaoComWsDeputados.receberElementDeputados
				(ConexaoComWsDeputados.obterConexao());			
		NodeList nome;/*Receives the attribute name of the Member.*/
		nome = deputados.get_any()[0].getElementsByTagName("nome");
		
		NodeList nomeTratamento;/*Is called the treatment of a deputy.*/
		nomeTratamento = deputados.get_any()[0].getElementsByTagName("nomeParlamentar");
		
		NodeList id;/*Receives the identification number of deputy.*/
		id = deputados.get_any()[0].getElementsByTagName("ideCadastro");
		
		NodeList matricula;/*Receives tuition from deputy.*/
		matricula = deputados.get_any()[0].getElementsByTagName("matricula");
		
		NodeList idParlamentar;/*Receives the identification number of parliamentary deputy.*/
		idParlamentar = deputados.get_any()[0].getElementsByTagName("idParlamentar");
		
		NodeList sexo;/*Receive sex of deputy.*/
		sexo = deputados.get_any()[0].getElementsByTagName("sexo");
		
		NodeList uf;/*Receives the acronym of the federative Unit that Deputy.*/
		uf = deputados.get_any()[0].getElementsByTagName("uf");
		
		NodeList partido;/*Receives the political party of the deputy.*/
		partido = deputados.get_any()[0].getElementsByTagName("partido");
		
		NodeList gabinete;/*Receives the current cabinet of deputy.*/
		gabinete = deputados.get_any()[0].getElementsByTagName("gabinete");
		
		NodeList anexo;/*Receive the attachment from deputy.*/
		anexo = deputados.get_any()[0].getElementsByTagName("anexo");
		
		NodeList fone;/*Receive the phone from deputy.*/
		fone = deputados.get_any()[0].getElementsByTagName("fone");
		
		NodeList email;/*Receive the e-mail from deputy.*/
		email = deputados.get_any()[0].getElementsByTagName("email");

		int sizeName; /*Receive the size of the name.*/
		sizeName = nome.getLength();
		
		for( int i = 0 ; i < sizeName ; i++ ) {
			
			MessageElement nomeTratamentoTemp;/*Temporary message variable to the name of treatment.*/
			nomeTratamentoTemp = (MessageElement) nomeTratamento.item(i);
			
			MessageElement nomeTemp;/*Temporary variable for message name on.*/
			nomeTemp = (MessageElement) nome.item(i);
			
			MessageElement idTemp;/*Temporary variable for message id on.*/
			idTemp= (MessageElement) id.item(i);
			
			MessageElement matriculaTemp;/*Temporary variable for enrollment message.*/
			matriculaTemp = (MessageElement) matricula.item(i);
			
			MessageElement idParlamentarTemp;/*Temporary variable for message about the identity of the Parliamentary.*/
			idParlamentarTemp = (MessageElement) idParlamentar.item(i);
			
			MessageElement sexoTemp;/*Temporary variable for message about sex.*/
			sexoTemp = (MessageElement) sexo.item(i);
			
			MessageElement ufTemp;/*Variable for temporary federal facility.*/
			ufTemp = (MessageElement) uf.item(i);
			
			MessageElement partidoTemp;/*Temporary variable message to Political Party.*/
			partidoTemp = (MessageElement) partido.item(i);
			
			MessageElement gabineteTemp;/*Temporary variable message to the office of the Deputy.*/
			gabineteTemp = (MessageElement) gabinete.item(i);
			
			MessageElement anexoTemp;/*Temporary variable message to the annex.*/
			anexoTemp = (MessageElement) anexo.item(i);
			
			MessageElement foneTemp;/*Temporary variable message to the phone.*/
			foneTemp = (MessageElement) fone.item(i);
			
			MessageElement emailTemp;/*Temporary variable message to the e-mail.*/
			emailTemp = (MessageElement) email.item(i);

			int idInt;/*Variable to received the integer value of id.*/
			idInt = Integer.parseInt(idTemp.getFirstChild().getNodeValue());
			
			int matriculaInt;/*Variable to received the integer value of enrollment.*/
			matriculaInt = Integer.parseInt(matriculaTemp.getFirstChild().getNodeValue());
			
			int idParlamentarInt;/*Variable to received the integer value of identification of Deputy.*/
			idParlamentarInt = Integer.parseInt(idParlamentarTemp.getFirstChild().getNodeValue());
			
			String nomeText;/*variable to received the text of the name.*/
			nomeText = nomeTemp.getFirstChild().getNodeValue();
			
			String nomeTratamentoText;/*Variable to received the text of treatment name.*/
			nomeTratamentoText = nomeTratamentoTemp.getFirstChild().getNodeValue();
			
			String sexoText;/*Variable to received the text of the sex.*/
			sexoText = sexoTemp.getFirstChild().getNodeValue();
			
			String ufText;/*Variable to received the text of the UF.*/
			ufText = ufTemp.getFirstChild().getNodeValue();
			
			String partidoText;/*Variable to received the text of the political party.*/
			partidoText = partidoTemp.getFirstChild().getNodeValue();
			
			String gabineteText;
			String anexoText;
			String foneText;

			try {/* This try catch this one because of an error coming from the webservice in which 
				the tag is not complete due to the treatment.*/
				
				gabineteText = gabineteTemp.getFirstChild().getNodeValue();
				anexoText = anexoTemp.getFirstChild().getNodeValue();
				foneText = foneTemp.getFirstChild().getNodeValue();
				
				} catch (NullPointerException e) {
				gabineteText = null;
				anexoText = null;
				foneText = null;

				  }



			String emailText;/*Variable to received the text of the e-mail.*/
			emailText = emailTemp.getFirstChild().getNodeValue();

			Deputados deputadoNovo;/*Variable to received the new deputy.*/
			deputadoNovo = new Deputados(idParlamentarInt, matriculaInt, idInt,
					nomeText, nomeTratamentoText, sexoText, ufText, partidoText, gabineteText,
					anexoText, foneText, emailText);

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

	public static ObterPartidosCDResponseObterPartidosCDResult 
	receberElementPartido(DeputadosSoapStub service) {			
		/*Connection created, now call the class of ws.*/
		try {
			ObterPartidosCDResponseObterPartidosCDResult partidos;/*Variable to received the political parties.*/
			partidos = service.obterPartidosCD();

			return partidos;

			} catch (RemoteException e) {
			     e.printStackTrace();
			     return null;
			  }	
	}
	
	/**
	 * Method to get all the political party from the webService and return then
	 * as an {@link ArrayList} of {@link Partidos}.
	 * 
	 * @return an {@link ArrayList} of {@link Partidos} containing all the
	 *         political party.
	 * @throws MalformedURLException
	 * @throws UnknownHostException
	 * @throws ServiceException
	 */

	public static ArrayList<Partidos> criaListaPartidos() 
			throws MalformedURLException, UnknownHostException, ServiceException {
		ArrayList<Partidos> lista = new ArrayList<Partidos>();

		ObterPartidosCDResponseObterPartidosCDResult partidos;

		partidos = receberElementPartido(obterConexao());

		NodeList nomePartido;/*Variable to received the name of the political party.*/
		nomePartido = partidos.get_any()[0].getElementsByTagName("nomePartido");
		
		NodeList siglaPartido;/*Variable that receives the symbol of the party.*/
		siglaPartido = partidos.get_any()[0].getElementsByTagName("siglaPartido");
		
		NodeList dataExtincao;/*Variable that receives the termination date of the party.*/
		dataExtincao = partidos.get_any()[0].getElementsByTagName("dataExtincao");

		int sizeNameParty;/*Variable that receives the size of name from political party.*/
		sizeNameParty = nomePartido.getLength();
		
		for( int i = 0 ; i < sizeNameParty ; i++ ) {
			MessageElement nomePartidoTemp;
			nomePartidoTemp = (MessageElement) nomePartido.item(i);
			
			MessageElement siglaPartidoTemp;
			siglaPartidoTemp = (MessageElement) siglaPartido.item(i);
			
			MessageElement dataExtincaoTemp;
			dataExtincaoTemp = (MessageElement) dataExtincao.item(i);

			String nomePartidoText;
			nomePartidoText = nomePartidoTemp.getFirstChild().getNodeValue();
			String siglaPartidoText;
			siglaPartidoText = siglaPartidoTemp.getFirstChild().getNodeValue();

			/*Try catch this is to pull the parties that have not exist.*/
			try {
				String dataExtincaoText = dataExtincaoTemp.getFirstChild().getNodeValue();
			} catch(NullPointerException e) {
				Partidos partidoAdicionar = new Partidos();
				partidoAdicionar.setSigla(siglaPartidoText);
				partidoAdicionar.setNomePartido(nomePartidoText);

				/* If this expression is false.*/
				if( !nomePartidoText.equalsIgnoreCase("sem partido") ) {
					lista.add(partidoAdicionar);
				}
			  }
		}

		return lista;
	}
}	
