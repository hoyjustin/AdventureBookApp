package c301.AdventureBook;


public class ViewPageActivity {

	/**
	 * @uml.property  name="sController"
	 * @uml.associationEnd  multiplicity="(1 1)" inverse="viewPageActivity:com.example.adventurebook.SController"
	 */
	private SController sController = new c301.AdventureBook.SController();

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

	/** 
	 * @uml.property name="sController1"
	 * @uml.associationEnd multiplicity="(1 1)" inverse="viewPageActivity1:com.example.adventurebook.SController"
	 */
	private SController sController1 = new c301.AdventureBook.SController();

	/** 
	 * Getter of the property <tt>sController1</tt>
	 * @return  Returns the sController1.
	 * @uml.property  name="sController1"
	 */
	public SController getSController1() {
		return sController1;
	}

	/** 
	 * Setter of the property <tt>sController1</tt>
	 * @param sController1  The sController1 to set.
	 * @uml.property  name="sController1"
	 */
	public void setSController1(SController sController1) {
		this.sController1 = sController1;
	}

}
