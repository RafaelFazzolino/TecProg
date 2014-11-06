package br.com.MDSGPP.ChamadaParlamentar.model;

public class Deputies {

	/* Atributs to our class. */
	private int idCongressman;
	private int registry;
	private int idRegister;
	private String nameCivilCongressman;
	private String nameTreamentCOngressman;
	private String sex;
	private String federativeUnit;
	private String party;
	private String numberCabinet;
	private String annexx;
	private String telephone;
	private String email;

	/* Constructor to our class. */
	public Deputies() {

	}

	public Deputies(int idParlamentar, int matricula, int ideCadastro,
			String nomeCivil, String nomeTratamento, String sexo, String uf,
			String partido, String numeroGabinete, String anexo,
			String telefone, String email) {

		this.idCongressman = idParlamentar;
		this.registry = matricula;
		this.idRegister = ideCadastro;
		this.nameCivilCongressman = nomeCivil;
		this.nameTreamentCOngressman = nomeTratamento;
		this.sex = sexo;
		this.federativeUnit = uf;
		this.party = partido;
		this.numberCabinet = numeroGabinete;
		this.annexx = anexo;
		this.telephone = telefone;
		this.email = email;
	}

	/* Geters and setters. */
	public int getIdDeputy() {
		return idCongressman;
	}

	public void setIdDeputy(int idDoParlamentar) {
		this.idCongressman = idDoParlamentar;
	}

	public String getNameCivilCongressman() {
		return nameCivilCongressman;
	}

	public void setNameCivilCongressman(String nomeCivilDoParlamentar) {
		this.nameCivilCongressman = nomeCivilDoParlamentar;
	}

	public String getNameTreatmentCongressman() {
		return nameTreamentCOngressman;
	}

	public void setNameTreatmentCongressman(String nomeDeTratamentoDoParlamentar) {
		this.nameTreamentCOngressman = nomeDeTratamentoDoParlamentar;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sexo) {
		this.sex = sexo;
	}

	public String getFederativeUnit() {
		return federativeUnit;
	}

	public void setFederativeUnit(String uf) {
		this.federativeUnit = uf;
	}

	public String getParty() {
		return party;
	}

	public void setParty(String partido) {
		this.party = partido;
	}

	public String getNumberCabinet() {
		return numberCabinet;
	}

	public void setNumberCabinet(String numeroDoGabinete) {
		this.numberCabinet = numeroDoGabinete;
	}

	public String getAnnexx() {
		return annexx;
	}

	public void setAnnexx(String anexo) {
		this.annexx = anexo;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telefone) {
		this.telephone = telefone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getResgitry() {
		return registry;
	}

	public void setRegsitry(int matricula) {
		this.registry = matricula;
	}

	public int getIdRegister() {
		return idRegister;
	}

	public void setIdRegister(int ideCadastro) {
		this.idRegister = ideCadastro;
	}
}
