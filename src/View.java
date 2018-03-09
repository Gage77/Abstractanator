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


  /****************************************
  * Constructor(s)
  ****************************************/
  public View()
  {
    h = 600;
    w = 600;

    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setSize(h, w);

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

    // setup a panel that will hold the two inner panels for the right hand side
    // of the application (I/O, abstraction widgets)
    JPanel rightHandSidePanel = new JPanel();
    rightHandSidePanel.setLayout(new BoxLayout(rightHandSidePanel,
      BoxLayout.Y_AXIS));
    rightHandSidePanel.setPreferredSize(new Dimension(w / 4, h));

    /****************************************
		* Setup image panel
		****************************************/

    JPanel imagePanel = new JPanel();
    imagePanel.setBorder(BorderFactory.createCompoundBorder(
      BorderFactory.createLineBorder(Color.black, 2),
      BorderFactory.createTitledBorder("Image")
    ));

    this.getContentPane().add(imagePanel, BorderLayout.CENTER);
    
    imagePanel.add(abstractanator);

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

    JButton importButton = new JButton("Import Image");
    JButton exportButton = new JButton("Export Image");
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
    JButton rgbButton = new JButton("RGB");
    rgbButton.setToolTipText("test");
    JButton bwButton = new JButton("Black / White");
    JButton geomButton = new JButton("Geometric");
    JButton foldButton = new JButton("Fold");

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
    JSpinner rgbSpin = new JSpinner(rgbModel);
    JSpinner bwSpin = new JSpinner(bwModel);
    JSpinner geomSpin = new JSpinner(geomModel);
    JSpinner foldSpin = new JSpinner(foldModel);

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

    /****************************************
		* Setup previous image view panel - will go
    * on the left hand side of the main frame
		****************************************/

    JPanel prevImagePanel = new JPanel();
    prevImagePanel.setBorder(BorderFactory.createCompoundBorder(
      BorderFactory.createLineBorder(Color.black, 2),
      BorderFactory.createTitledBorder("History")
    ));
    prevImagePanel.setPreferredSize(new Dimension(w / 4, h));

    this.getContentPane().add(prevImagePanel, BorderLayout.LINE_START);

    /****************************************
		* Finish up and show the view
		****************************************/

    // Add rightHandSidePanel to main frame
    this.getContentPane().add(rightHandSidePanel, BorderLayout.LINE_END);

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

}