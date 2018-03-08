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
    this.h = h;
    this.w = w;

    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setSize(w, h);

    JPanel rightHandSidePanel = new JPanel();
    rightHandSidePanel.setLayout(new BoxLayout(rightHandSidePanel,
      BoxLayout.Y_AXIS));
    rightHandSidePanel.setPreferredSize(new Dimension(w / 4, h));

    /****************************************
		* Setup image panel
		****************************************/

    JPanel imagePanel = new JPanel();
    imagePanel.setBorder(BorderFactory.createLineBorder(Color.black, 2));

    this.getContentPane().add(imagePanel, BorderLayout.CENTER);

    /****************************************
		* Setup IO panel - will go on top part of
    * rightHandSidePanel
		****************************************/

    JPanel ioPanel = new JPanel();
    ioPanel.setBorder(BorderFactory.createLineBorder(Color.black, 2));

    rightHandSidePanel.add(ioPanel);

    /****************************************
		* Setup abstraction buttons panel - will go
    * on bottom part of rightHandSidePanel
		****************************************/

    // Create two panels, one for border and one for title
    JPanel abstractionPanel = new JPanel();

    // Add border
    abstractionPanel.setBorder(BorderFactory.createLineBorder(Color.black, 2));
    abstractionPanel.setPreferredSize(new Dimension(w / 4, h / 2));

    // Add panel to rightHandSidePanel in main Jframe
    rightHandSidePanel.add(abstractionPanel);

    /****************************************
		* Setup previous image view panel - will go
    * on the left hand side of the main frame
		****************************************/

    JPanel prevImagePanel = new JPanel();
    prevImagePanel.setBorder(BorderFactory.createLineBorder(Color.black, 2));
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
