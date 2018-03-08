/**
 * The <CODE>Abstractanator Controller</CODE> class.<P>
 *
 * @author  Hunter Black
 * @version %I%, %G%
 */

public class Controller
{
  /****************************************
  * Public class variables
  ****************************************/

  /****************************************
  * Private class variables
  ****************************************/

  private View theView;
  private Model theModel;

  /****************************************
  * Constructor(s)
  ****************************************/
  public Controller(View theView, Model theModel)
  {
    this.theView = theView;
    this.theModel = theModel;

    // Add action listeners for buttons in theView
  }

  /****************************************
  * ActionListener(s)
  ****************************************/
}
