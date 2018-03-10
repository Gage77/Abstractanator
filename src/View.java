import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.*;
import javax.swing.border.TitledBorder;

/**
 * The <CODE>Abstractanator View/CODE> class.<P>
 *
 * @author  Hunter Black
 * @version %I%, %G%
 */
public final class View extends JFrame
{
  /****************************************
  * Public class variables
  ****************************************/


  /****************************************
  * Private class variables
  ****************************************/

  // Height and width of JFrame
  private static int h = 0;
  private static int w = 0;
  private static Abstractanator abstractanator;

  private JButton importButton;
  private JButton exportButton;

  private JButton rgbButton;
  private JButton bwButton;
  private JButton geomButton;
  private JButton foldButton;

  private JSpinner rgbSpin;
  private JSpinner bwSpin;
  private JSpinner geomSpin;
  private JSpinner foldSpin;

  /****************************************
  * Constructor(s)
  ****************************************/
  public View()
  {
    h = 600;
    w = 600;

    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setSize(h, w);
    this.setTitle("Abstractanator - NULL");

    JPanel errorPanel = new JPanel();
    JLabel errorLabel = new JLabel("SOMETHING HAS GONE HORRIBLY WRONG");

    errorPanel.add(errorLabel);

    this.add(errorPanel);

    this.setVisible(true);
  }

  public View(int w, int h)
  {
    // Setup the frame (this class)
    this.h = h;
    this.w = w;
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setSize(w, h);
    this.setTitle("Abstractanator");

    abstractanator = new Abstractanator();

    /****************************************
		* Setup all internal panels for the View
		****************************************/
    // Setup the main panel, which will hold the current image
    this.createImagePanel();
    // Setup the panel on the right hand side of the frame, which
    // will hold the I/O buttons, as well as the abstraction buttons
    this.createRHSPanel();
    // Setup the history panel which will hold the previous 5 (or so)
    // abstracted images
    this.createHistoryPanel();

    // Finish up and set the View as visible
    this.setVisible(true);
  }

  /****************************************
  * Getters and Setters
  ****************************************/


  /****************************************
  * Public method(s)
  ****************************************/


  /****************************************
  * Private method(s)
  ****************************************/
  /**
  * Set up the main panel of the View which will hold the
  * currently active image. This panel will be in the center
  * of the View
  */
  private void createImagePanel()
  {
    JPanel imagePanel = new JPanel();
    imagePanel.setBorder(BorderFactory.createCompoundBorder(
      BorderFactory.createLineBorder(Color.black, 2),
      BorderFactory.createTitledBorder("Image")
    ));

    this.getContentPane().add(imagePanel, BorderLayout.CENTER);

    imagePanel.add(abstractanator);
  }

  /**
  * Set up the left hand side panel which will hold the previous
  * 5 or so abstracted images, thus allowing the user to return
  * to a previous version of their image
  */
  private void createHistoryPanel()
  {
    JPanel prevImagePanel = new JPanel();
    prevImagePanel.setBorder(BorderFactory.createCompoundBorder(
      BorderFactory.createLineBorder(Color.black, 2),
      BorderFactory.createTitledBorder("History")
    ));
    prevImagePanel.setPreferredSize(new Dimension(w / 4, h));

    this.getContentPane().add(prevImagePanel, BorderLayout.LINE_START);
  }

  /**
  * Set up the right hand side panel. This panel will consist of 2
  * major panels: a top I/O panel, and a bottom abstraction panel.
  * <p>The top I/O panel will contain an import button and an export button
  * that will allow the user to import/export an image to be abstracted.
  * <p>The bottom panel will hold spinner and buttons corresponding to
  * the currently implemented abstraction functions.
  */
  private void createRHSPanel()
  {
    // setup a panel that will hold the two inner panels for the right hand side
    // of the application (I/O, abstraction widgets)
    JPanel rightHandSidePanel = new JPanel();
    rightHandSidePanel.setLayout(new BoxLayout(rightHandSidePanel,
      BoxLayout.Y_AXIS));
    rightHandSidePanel.setPreferredSize(new Dimension(w / 4, h));

    /****************************************
		* Setup IO panel - will go on top part of
    * rightHandSidePanel
		****************************************/

    JPanel ioPanel = new JPanel();
    ioPanel.setLayout(new BorderLayout());
    ioPanel.setBorder(BorderFactory.createCompoundBorder(
      BorderFactory.createLineBorder(Color.black, 2),
      BorderFactory.createTitledBorder("I/O")
    ));

    JPanel innerIOPanel = new JPanel();
    innerIOPanel.setLayout(new GridLayout(5, 1));

    importButton = new JButton("Import Image");
    exportButton = new JButton("Export Image");
    JLabel emptyLabel = new JLabel("");

    innerIOPanel.add(emptyLabel);
    innerIOPanel.add(importButton);
    innerIOPanel.add(emptyLabel);
    innerIOPanel.add(exportButton);

    ioPanel.add(innerIOPanel, BorderLayout.CENTER);
    rightHandSidePanel.add(ioPanel);

    /****************************************
		* Setup abstraction buttons panel - will go
    * on bottom part of rightHandSidePanel
		****************************************/

    JPanel abstractionPanel = new JPanel();
    abstractionPanel.setLayout(new GridLayout(4, 2));

    // Add border
    abstractionPanel.setBorder(BorderFactory.createCompoundBorder(
      BorderFactory.createLineBorder(Color.black, 2),
      BorderFactory.createTitledBorder("Abstract Functions")
    ));
    abstractionPanel.setPreferredSize(new Dimension(w / 4, h / 2));

    // Create all necessary abstraction buttons
    rgbButton = new JButton("RGB");
    rgbButton.setToolTipText("Randomly change the RGB values of each pixel of the image");
    bwButton = new JButton("Black / White");
    bwButton.setToolTipText("Gradually adjust each pixel of the image towards true black or true white");
    geomButton = new JButton("Geometric");
    geomButton.setToolTipText("Adjust the shapes within the image to a more basic geometric form");
    foldButton = new JButton("Fold");
    foldButton.setToolTipText("\"Fold\" the image along a specified crease");

    // Setup values for spinners
    int initialSpin = 0;
    int minSpin = 0;
    int maxSpin = 10;
    int stepSpin = 1;

    // Create a SpinnerModel for the framework for JSpinners to be
    // associated with the buttons above
    SpinnerNumberModel rgbModel = new SpinnerNumberModel(initialSpin,
      minSpin, maxSpin, stepSpin);
    SpinnerNumberModel bwModel = new SpinnerNumberModel(initialSpin,
      minSpin, maxSpin, stepSpin);
    SpinnerNumberModel geomModel = new SpinnerNumberModel(initialSpin,
      minSpin, maxSpin, stepSpin);
    SpinnerNumberModel foldModel = new SpinnerNumberModel(initialSpin,
      minSpin, maxSpin, stepSpin);

    // Create all necessary spinners
    rgbSpin = new JSpinner(rgbModel);
    bwSpin = new JSpinner(bwModel);
    geomSpin = new JSpinner(geomModel);
    foldSpin = new JSpinner(foldModel);

    // Add it all to the abstraction pane
    abstractionPanel.add(rgbSpin);
    abstractionPanel.add(rgbButton);
    abstractionPanel.add(bwSpin);
    abstractionPanel.add(bwButton);
    abstractionPanel.add(geomSpin);
    abstractionPanel.add(geomButton);
    abstractionPanel.add(foldSpin);
    abstractionPanel.add(foldButton);

    // Add panel to rightHandSidePanel in main Jframe
    rightHandSidePanel.add(abstractionPanel);

    this.getContentPane().add(rightHandSidePanel, BorderLayout.LINE_END);
  }

  /****************************************
  * Action Event Overrides (Magic stuff)
  ****************************************/

  // Import
  void addImportButtonListener(ActionListener listenForImport)
  {
    importButton.addActionListener(listenForImport);
  }

  // Export
  void addExportButtonListener(ActionListener listenForExport)
  {
    exportButton.addActionListener(listenForExport);
  }

  // RGB
  void addRGBButtonListener(ActionListener listenForRGB)
  {
    rgbButton.addActionListener(listenForRGB);
  }

  // BW
  void addBWButtonListener(ActionListener listenForBW)
  {
    bwButton.addActionListener(listenForBW);
  }

  // Geom
  void addGeomButtonListener(ActionListener listenForGeom)
  {
    geomButton.addActionListener(listenForGeom);
  }

  // Fold
  void addFoldButtonListener(ActionListener listenForFold)
  {
    foldButton.addActionListener(listenForFold);
  }

}
