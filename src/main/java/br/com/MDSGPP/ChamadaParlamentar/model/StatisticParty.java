package br.com.MDSGPP.ChamadaParlamentar.model;

public class StatisticParty {

	private Partidos politicalParty;
	private int numberOfSessions;
	private int assistedSessions;
	private String percentage;

	/**
	 * Getter of the political party.
	 * 
	 * @return an name of {@link politicalParty} containing the name of the
	 *         political party.
	 */

	public Partidos getpoliticalParty() {
		return politicalParty;
	}

	/**
	 * Setter of the political party.
	 * 
	 * @param politicalParty
	 *            an variable of {@link politicalParty} containing the name of
	 *            the political party.
	 */

	public void setpoliticalParty(Partidos politicalParty) {
		this.politicalParty = politicalParty;
	}

	/**
	 * Getter of number of sessions.
	 * 
	 * @return an variable of {@link politicalParty} containing the number of
	 *         the sessions.
	 */

	public int getnumberOfSessions() {
		return numberOfSessions;
	}

	/**
	 * Setter of number of sessions.
	 * 
	 * @param numberOfSessions
	 *            an variable of {@link sessoes} containing the number of the
	 *            sessions.
	 */

	public void setnumberOfSessions(int numberOfSessions) {
		this.numberOfSessions = numberOfSessions;
	}

	/**
	 * Getter of assisted sessions.
	 * 
	 * @return an variable of {@link politicalParty} containing the number of
	 *         the sessions assisted.
	 */

	public int getassistedSessions() {
		return assistedSessions;
	}

	/**
	 * Setter of assisted sessions.
	 * 
	 * @param assistedSessions
	 *            an variable of {@link politicalParty} containing the number of
	 *            the sessions assisted.
	 */

	public void setassistedSessions(int assistedSessions) {
		this.assistedSessions = assistedSessions;
	}

	/**
	 * Getter of percentage.
	 * 
	 * @return an variable of {@link politicalParty} containing the percentage
	 *         of the sessions assisted.
	 */

	public String getpercentage() {
		return percentage;
	}

	/**
	 * Setter of percentage.
	 * 
	 * @param percentage
	 *            an variable of {@link politicalParty} containing the
	 *            percentage of the sessions assisted.
	 */

	public void setpercentage(String percentage) {
		this.percentage = percentage;
	}
}
