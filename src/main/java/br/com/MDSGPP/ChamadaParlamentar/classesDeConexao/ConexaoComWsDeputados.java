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
		URL url;
		url = new URL("http://www.camara.gov.br/SitCamaraWS/Deputados.asmx");
		DeputadosSoapStub service;
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
		ArrayList<Deputados> lista = new ArrayList<Deputados>();
		ObterDeputadosResponseObterDeputadosResult deputados;

		deputados = ConexaoComWsDeputados.receberElementDeputados
				(ConexaoComWsDeputados.obterConexao());			
		NodeList nome = deputados.get_any()[0].getElementsByTagName("nome");
		NodeList nomeTratamento = deputados.get_any()[0].getElementsByTagName("nomeParlamentar");
		NodeList id = deputados.get_any()[0].getElementsByTagName("ideCadastro");
		NodeList matricula = deputados.get_any()[0].getElementsByTagName("matricula");
		NodeList idParlamentar = deputados.get_any()[0].getElementsByTagName("idParlamentar");
		NodeList sexo = deputados.get_any()[0].getElementsByTagName("sexo");
		NodeList uf = deputados.get_any()[0].getElementsByTagName("uf");
		NodeList partido = deputados.get_any()[0].getElementsByTagName("partido");
		NodeList gabinete = deputados.get_any()[0].getElementsByTagName("gabinete");
		NodeList anexo = deputados.get_any()[0].getElementsByTagName("anexo");
		NodeList fone = deputados.get_any()[0].getElementsByTagName("fone");
		NodeList email = deputados.get_any()[0].getElementsByTagName("email");

		int sizeName;
		sizeName = nome.getLength();
		
		for( int i = 0 ; i < sizeName ; i++ ) {
			MessageElement nomeTratamentoTemp = (MessageElement) nomeTratamento.item(i);
			MessageElement nomeTemp = (MessageElement) nome.item(i);
			MessageElement idTemp = (MessageElement) id.item(i);
			MessageElement matriculaTemp = (MessageElement) matricula.item(i);
			MessageElement idParlamentarTemp = (MessageElement) idParlamentar.item(i);
			MessageElement sexoTemp = (MessageElement) sexo.item(i);
			MessageElement ufTemp = (MessageElement) uf.item(i);
			MessageElement partidoTemp = (MessageElement) partido.item(i);
			MessageElement gabineteTemp = (MessageElement) gabinete.item(i);
			MessageElement anexoTemp = (MessageElement) anexo.item(i);
			MessageElement foneTemp = (MessageElement) fone.item(i);
			MessageElement emailTemp = (MessageElement) email.item(i);

			int idInt = Integer.parseInt(idTemp.getFirstChild().getNodeValue());
			int matriculaInt = Integer.parseInt(matriculaTemp.getFirstChild().getNodeValue());
			int idParlamentarInt = Integer.parseInt(idParlamentarTemp.getFirstChild().getNodeValue());
			
			String nomeText = nomeTemp.getFirstChild().getNodeValue();
			String nomeTratamentoText = nomeTratamentoTemp.getFirstChild().getNodeValue();
			String sexoText = sexoTemp.getFirstChild().getNodeValue();
			String ufText = ufTemp.getFirstChild().getNodeValue();
			String partidoText = partidoTemp.getFirstChild().getNodeValue();
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



			String emailText;
			emailText = emailTemp.getFirstChild().getNodeValue();

			Deputados deputadoNovo;
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
			ObterPartidosCDResponseObterPartidosCDResult partidos;
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

		NodeList nomePartido = partidos.get_any()[0].getElementsByTagName("nomePartido");
		NodeList siglaPartido = partidos.get_any()[0].getElementsByTagName("siglaPartido");
		NodeList dataExtincao = partidos.get_any()[0].getElementsByTagName("dataExtincao");

		int sizeNameParty;
		sizeNameParty = nomePartido.getLength();
		
		for( int i = 0 ; i < sizeNameParty ; i++ ) {
			MessageElement nomePartidoTemp = (MessageElement) nomePartido.item(i);
			MessageElement siglaPartidoTemp = (MessageElement) siglaPartido.item(i);
			MessageElement dataExtincaoTemp = (MessageElement) dataExtincao.item(i);

			String nomePartidoText = nomePartidoTemp.getFirstChild().getNodeValue();
			String siglaPartidoText = siglaPartidoTemp.getFirstChild().getNodeValue();

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
