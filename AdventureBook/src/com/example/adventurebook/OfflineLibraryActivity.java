package com.example.adventurebook;


import android.app.Activity;


public class OfflineLibraryActivity extends Activity {

	/**
	 * @uml.property  name="controller"
	 * @uml.associationEnd  inverse="offlineLibraryActivity:com.example.adventurebook.SController"
	 */
	private SController controller;

	/**
	 * Getter of the property <tt>controller</tt>
	 * @return  Returns the controller.
	 * @uml.property  name="controller"
	 */
	public SController getController() {
		return controller;
	}

	/**
	 * Setter of the property <tt>controller</tt>
	 * @param controller  The controller to set.
	 * @uml.property  name="controller"
	 */
	public void setController(SController controller) {
		this.controller = controller;
	}

	
}
