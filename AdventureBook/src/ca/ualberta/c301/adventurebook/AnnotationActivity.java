package ca.ualberta.c301.adventurebook;

import android.app.Activity;
import android.os.Bundle;

import com.example.adventurebook.R;


public class AnnotationActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.annotations);
	}
	
	
	
//	/**
//	 * @uml.property  name="sController"
//	 * @uml.associationEnd  multiplicity="(1 1)" inverse="annotationActivity:com.example.adventurebook.SController"
//	 */
//	private SController sController = new ca.ualberta.c301.adventurebook.SController();
//
//	/**
//	 * Getter of the property <tt>sController</tt>
//	 * @return  Returns the sController.
//	 * @uml.property  name="sController"
//	 */
//	public SController getSController() {
//		return sController;
//	}
//
//	/**
//	 * Setter of the property <tt>sController</tt>
//	 * @param sController  The sController to set.
//	 * @uml.property  name="sController"
//	 */
//	public void setSController(SController sController) {
//		this.sController = sController;
//	}

}
