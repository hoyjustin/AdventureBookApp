package com.example.adventurebook;


public class RemoteStories {

	/**
	 * @uml.property   name="eSClient"
	 * @uml.associationEnd   inverse="remoteDatabase:com.example.adventurebook.Controller"
	 */
	private Controller esClient;

	/**
	 * Getter of the property <tt>eSClient</tt>
	 * @return  Returns the esClient.
	 * @uml.property  name="eSClient"
	 */
	public Controller getESClient() {
		return esClient;
	}

	/**
	 * Setter of the property <tt>eSClient</tt>
	 * @param eSClient  The esClient to set.
	 * @uml.property  name="eSClient"
	 */
	public void setESClient(Controller esClient) {
		this.esClient = esClient;
	}

}
