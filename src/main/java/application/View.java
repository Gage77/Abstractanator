import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.BorderFactory;
import java.awt.event.ActionListener;
import java.awt.*;
import javax.swing.border.TitledBorder;
import java.io.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.util.ArrayList;

/**
 * This class will create and display the main UI, as
 * well as all interactable features of our application.
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

  private static int h = 0;
  private static int w = 0;
  private static Abstractanator abstractanator;

  private JPanel prevImagePanel;
  private JPanel historyButtons;
  private JPanel thumbnailPanel;

  private JButton undoButton;

  private JButton importButton;
  private JButton exportButton;

  private JButton rgbButton;
  private JButton bwButton;
  private JButton colPolButton;
  private JButton foldButton;

  private JSpinner rgbSpin;
  private JSpinner bwSpin;
  private JSpinner colPolSpin;
  private JSpinner foldSpin;

  private String format = "";

  // Abstractinate finals to specify abstraction type
  final int RANDOMIZE = 0;
  final int POLARIZE = 1;
  final int COLORPOLARIZE = 2;

  /****************************************
  * Constructor(s)
  ****************************************/

  /**
  * Empty constructor
  */
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

  /**
  * Main constructor
  */
  public View(int w, int h)
  {
    // Setup the frame (this class)
    this.h = h;
    this.w = w;
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setSize(w, h);
    this.setTitle("Abstractanator");

    // Initialize Abstractanator and history list to hold old images
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

  /**
  * Abstract image using colPolSpin value as # of iterations
  */
  private void setSaveFileFormat(String saveFormat)
  {
    this.format = saveFormat;
  }

  /**
  * Retrieve the file format for saving the current image (png, jpg, gif)
  */
  private void getSaveFileFormat()
  {
    Object[] options = {"png", "jpg", "gif"};
    String chosenOption = (String)JOptionPane.showInputDialog(
      this,
      "Choose a file format to save to",
      "Save Format",
      JOptionPane.PLAIN_MESSAGE,
      null,
      options,
      "png"
    );

    if (chosenOption != null && chosenOption.length() > 0)
    {
      setSaveFileFormat(chosenOption);
    }
    else
    {
      System.out.println("Error in setting save file format type");
    }
  }

  /****************************************
  * Public method(s)
  ****************************************/

  /**
  * Select an image to open and manipulate through the use of a JFileChooser
  */
  public void openImage()
  {
    JFileChooser fc = new JFileChooser();
    fc.showOpenDialog(this);

    BufferedImage image = null;

    try {
      File imageFile = fc.getSelectedFile();
      image = ImageIO.read(imageFile);
      System.out.println("Image file loaded successfully");
      abstractanator.setImage(image);
      updateHistoryList();
    } catch (Exception e) {
      System.out.println("Error reading image file");
    }
    this.repaint();
  }

  /**
  * Save the current abstractImage to specified directory using JFileChooser
  */
  public void saveImage()
  {
    BufferedImage imageToSave = this.abstractanator.getImage();
    format = "png";
    File saveFile = new File("savedimage." + format);
    JFileChooser fc = new JFileChooser();
    fc.setSelectedFile(saveFile);

    int fcval = fc.showSaveDialog(this);
    if (fcval == JFileChooser.APPROVE_OPTION)
    {
      saveFile = fc.getSelectedFile();
      try {
        ImageIO.write(imageToSave, format, saveFile);
      } catch (IOException ex) {
        System.out.println("Error in saving image file");
      }
    }
  }

  /**
  * Abstract image using rgbSpin value as # of iterations
  */
  public void rgbAbstraction()
  {
    int iterations = (int)rgbSpin.getValue();
    abstractanator.abstractinate(RANDOMIZE, iterations);

    // Update history
    updateHistoryList();
    // Repaint the changed image
    this.repaint();
  }

  /**
  * Abstract image using bwSpin value as # of iterations
  */
  public void bwAbstraction()
  {
    int iterations = (int)bwSpin.getValue();
    abstractanator.abstractinate(POLARIZE, iterations);

    // Update history
    updateHistoryList();
    // Repaint the changed image
    this.repaint();
  }

  /**
  * Abstract image using colPolSpin value as # of iterations
  */
  public void colorPolarizationAbstraction()
  {
    int iterations = (int)colPolSpin.getValue();
    abstractanator.abstractinate(COLORPOLARIZE, iterations);

    // Update history
    updateHistoryList();
    // Repaint the changed image
    this.repaint();
  }

  public void foldAbstraction()
  {
    // Get fold position
    int foldPosition = (int)foldSpin.getValue();
    abstractanator.fold(foldPosition);

    // Update history
    updateHistoryList();
    // Repaint the changed image
    this.repaint();
  }

  /**
  * Adjust the current image by going back in history
  */
  public void goBack()
  {
    System.out.println("Attempting to go back...");
    // If there is something in the history, set the current abstract
    // image to the most recent abstract image, then remove that abstract
    // image from the history

    ArrayList<AbstractImage> historyList = abstractanator.getHistory();

    if (historyList.size() != 0) {
      abstractanator.removeFront();
      // Update the thumbnails to show the new history minus the most previous image
      updateHistoryList();
      this.repaint();
    }
    else {
      System.out.println("Can't go back any further");
    }
  }

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

    // Add the abstractanator to this panel. The Abstractanator class extends
    // the JComponent class, and thus can be added to a JPanel
    imagePanel.add(abstractanator);
  }

  /**
  * Set up the left hand side panel which will hold the previous
  * 5 or so abstracted images, thus allowing the user to return
  * to a previous version of their image
  */
  private void createHistoryPanel()
  {
    prevImagePanel = new JPanel();
    prevImagePanel.setBorder(BorderFactory.createCompoundBorder(
      BorderFactory.createLineBorder(Color.black, 2),
      BorderFactory.createTitledBorder("History")
    ));
    prevImagePanel.setLayout(new BoxLayout(prevImagePanel, BoxLayout.PAGE_AXIS));
    prevImagePanel.setPreferredSize(new Dimension(w / 4, h));

    historyButtons = new JPanel();
    historyButtons.setLayout(new BoxLayout(historyButtons, BoxLayout.LINE_AXIS));

    undoButton = new JButton("Undo");

    historyButtons.add(undoButton);

    thumbnailPanel = new JPanel();
    thumbnailPanel.setLayout(new BoxLayout(thumbnailPanel, BoxLayout.PAGE_AXIS));

    prevImagePanel.add(historyButtons, BorderLayout.PAGE_START);
    prevImagePanel.add(thumbnailPanel, BorderLayout.CENTER);

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
    colPolButton = new JButton("Color Polarize");
    colPolButton.setToolTipText("Shift individual RGB values closer to their respective extremes (contrastint)");
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
    SpinnerNumberModel colPolModel = new SpinnerNumberModel(initialSpin,
      minSpin, maxSpin, stepSpin);
    SpinnerNumberModel foldModel = new SpinnerNumberModel(1,
      1, 4, stepSpin);

    // Create all necessary spinners
    rgbSpin = new JSpinner(rgbModel);
    bwSpin = new JSpinner(bwModel);
    colPolSpin = new JSpinner(colPolModel);
    foldSpin = new JSpinner(foldModel);

    // Add it all to the abstraction pane
    abstractionPanel.add(rgbSpin);
    abstractionPanel.add(rgbButton);
    abstractionPanel.add(bwSpin);
    abstractionPanel.add(bwButton);
    abstractionPanel.add(colPolSpin);
    abstractionPanel.add(colPolButton);
    abstractionPanel.add(foldSpin);
    abstractionPanel.add(foldButton);

    // Add panel to rightHandSidePanel in main Jframe
    rightHandSidePanel.add(abstractionPanel);

    this.getContentPane().add(rightHandSidePanel, BorderLayout.LINE_END);
  }

  private void updateHistoryList()
  {
    System.out.println("updateHistoryList entered");
    // Retrieve the most up-to-date history list from the Abstractanator
    ArrayList<AbstractImage>historyList = this.abstractanator.getHistory();

    thumbnailPanel.removeAll();

    for (int i = 0; i < historyList.size(); i++) {
      if (historyList.get(i) != null && i < 5) {
        if (historyList.get(0).getFold() != null) {
          foldButton.setText("Unfold");
        }
        else if (historyList.get(0).getFold() == null) {
          foldButton.setText("Fold");
        }

        // Create a JLabel with the AbstractImage as the icon of the label
        BufferedImage thumbnail = historyList.get(i).getThumbnail();
        ImageIcon prevImgIcon = new ImageIcon(thumbnail);
        JLabel prevImg = new JLabel();
        prevImg.setIcon(prevImgIcon);

        // Pad the label
        Border border = BorderFactory.createEmptyBorder(5, 5, 5, 5);
        prevImg.setBorder(border);

        thumbnailPanel.add(prevImg);
      }
    }

    thumbnailPanel.revalidate();
    this.repaint();
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

  // ColPol
  void addColPolButtonListener(ActionListener listenForColPol)
  {
    colPolButton.addActionListener(listenForColPol);
  }

  // Fold
  void addFoldButtonListener(ActionListener listenForFold)
  {
    foldButton.addActionListener(listenForFold);
  }

  // Undo
  void addUndoButtonListener(ActionListener listenForUndo)
  {
    undoButton.addActionListener(listenForUndo);
  }
}
