package ca.ualberta.c301.adventurebook;


/**
 * @uml.dependency   supplier="com.example.adventurebook.SController"
 */
public class OnlineLibraryActivity {

	/** 
	 * @uml.property name="sController"
	 * @uml.associationEnd multiplicity="(1 1)" inverse="onlineLibraryActivity:com.example.adventurebook.SController"
	 */
	private SController sController = new ca.ualberta.c301.adventurebook.SController();

	/** 
	 * Getter of the property <tt>sController</tt>
	 * @return  Returns the sController.
	 * @uml.property  name="sController"
	 */
	public SController getSController() {
		return sController;
	}

	/** 
	 * Setter of the property <tt>sController</tt>
	 * @param sController  The sController to set.
	 * @uml.property  name="sController"
	 */
	public void setSController(SController sController) {
		this.sController = sController;
	}

}
