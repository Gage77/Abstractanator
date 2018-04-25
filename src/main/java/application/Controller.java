import java.awt.event.*;

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

  /****************************************
  * Constructor(s)
  ****************************************/
  public Controller(View theView)
  {
    // Initialize the View
    this.theView = theView;

    // Add action listeners for I/O buttons in theView
    this.theView.addImportButtonListener(new ImportListener());
    this.theView.addExportButtonListener(new ExportListener());

    // Add action listeners for abstraction buttons in theView
    this.theView.addRGBButtonListener(new RGBListener());
    this.theView.addBWButtonListener(new BWListener());
    this.theView.addGeomButtonListener(new GeomListener());
    this.theView.addFoldButtonListener(new FoldListener());
  }

  /****************************************
  * ActionListener(s)
  ****************************************/

  // Action listener for the Import button
  class ImportListener implements ActionListener
  {
    @Override
    public void actionPerformed(ActionEvent arg0)
    {
      System.out.println("Import button pushed");

      theView.openImage();
    }
  }

  // Action listener for the Export button
  class ExportListener implements ActionListener
  {
    @Override
    public void actionPerformed(ActionEvent arg0)
    {
      System.out.println("Export button pushed");

      theView.saveImage();
    }
  }

  // Action listener for the RGB abstraction button
  class RGBListener implements ActionListener
  {
    @Override
    public void actionPerformed(ActionEvent arg0)
    {
      System.out.println("RGB button pushed");
    }
  }

  // Action listener for the BW abstraction button
  class BWListener implements ActionListener
  {
    @Override
    public void actionPerformed(ActionEvent arg0)
    {
      System.out.println("BW button pushed");
    }
  }

  // Action listener for the Geometry abstraction button
  class GeomListener implements ActionListener
  {
    @Override
    public void actionPerformed(ActionEvent arg0)
    {
      System.out.println("Geom button pushed");
    }
  }

  // Action listener for the Fold abstraction button
  class FoldListener implements ActionListener
  {
    @Override
    public void actionPerformed(ActionEvent arg0)
    {
      System.out.println("Fold button pushed");
    }
  }
}
