import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.*;

public class Main {
	
	//********************************************************************
	// Copyright information
	//********************************************************************
	// Last modified:
	//********************************************************************
	// Major Modification History:
	//
	// 03/07/18 [black]: Initial file GUI setup
	//
	//********************************************************************
	// Notes on use:
	//
	//********************************************************************

	/**
	 * The <CODE>Main Abstractanator</CODE> class.<P>
	 *
	 * @author  Hunter Black
	 * @version %I%, %G%
	 */


	  /****************************************
	  * Private class variables
	  ****************************************/

	  // Main JFrame window parameters
	  private static final int WINDOW_HEIGHT = 800;
	  private static final int WINDOW_WIDTH = 1200;

	  /****************************************
	  * Main method
	  ****************************************/
	  public static void main(String[] args)
	  {
		  
	    // Create the view with the specified window parameters
	    View view = new View(WINDOW_WIDTH, WINDOW_HEIGHT);

	    // Create the model
	    Model model = new Model();

	    // Create the controller and connect the view and model to it
	    Controller controller = new Controller(view, model);
	  }	

  
}