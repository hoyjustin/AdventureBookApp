package c301.AdventureBook;


public class OnlineLibrary {

	/**
	 * @uml.property   name="eSClient"
	 * @uml.associationEnd   inverse="remoteDatabase:com.example.adventurebook.SController"
	 */
	private SController esClient;

	/**
	 * Getter of the property <tt>eSClient</tt>
	 * @return  Returns the esClient.
	 * @uml.property  name="eSClient"
	 */
	public SController getESClient() {
		return esClient;
	}

	/**
	 * Setter of the property <tt>eSClient</tt>
	 * @param eSClient  The esClient to set.
	 * @uml.property  name="eSClient"
	 */
	public void setESClient(SController esClient) {
		this.esClient = esClient;
	}

}
