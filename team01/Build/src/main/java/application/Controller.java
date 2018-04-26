import java.awt.event.*;
import javax.swing.*;

/**
 * This class will serve as an intermediary and a manager for
 * the interactable features of our main view.
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

    // Add action listener for undo/redo buttons in theView
    this.theView.addUndoButtonListener(new UndoListener());

    // Add action listeners for I/O buttons in theView
    this.theView.addImportButtonListener(new ImportListener());
    this.theView.addExportButtonListener(new ExportListener());

    // Add action listeners for abstraction buttons in theView
    this.theView.addRGBButtonListener(new RGBListener());
    this.theView.addBWButtonListener(new BWListener());
    this.theView.addColPolButtonListener(new ColPolListener());
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
      theView.resetFoldButtonText();
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

      theView.rgbAbstraction();
    }
  }

  // Action listener for the BW abstraction button
  class BWListener implements ActionListener
  {
    @Override
    public void actionPerformed(ActionEvent arg0)
    {
      System.out.println("BW button pushed");

      theView.bwAbstraction();
    }
  }

  // Action listener for the Geometry abstraction button
  class ColPolListener implements ActionListener
  {
    @Override
    public void actionPerformed(ActionEvent arg0)
    {
      System.out.println("ColPol button pushed");

      theView.colorPolarizationAbstraction();
    }
  }

  // Action listener for the Fold abstraction button
  class FoldListener implements ActionListener
  {
    @Override
    public void actionPerformed(ActionEvent arg0)
    {
      System.out.println("Fold button pushed");

      JButton button = (JButton)arg0.getSource();
      if (button.getText().equals("Fold")) {
        theView.foldAbstraction();
        button.setText("Unfold");
      }
      else {
        theView.unfoldAbstraction();
        button.setText("Fold");
      }
    }
  }

  // Action listener for the undo button
  class UndoListener implements ActionListener
  {
    @Override
    public void actionPerformed(ActionEvent arg0)
    {
      System.out.println("Undo button pushed");

      theView.goBack();
    }
  }
}
