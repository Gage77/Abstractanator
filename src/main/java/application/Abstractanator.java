import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.JComponent;

/**
 * This class will hold all abstraction functions including RGB, BW, Geom,
 * and Folding. The class itself extends JComponenent, which allows it to
 * be added to a container (such as a JPanel).
 * @author Evan Johnston
 * @version %I%, %G%
 */


public class Abstractanator extends JComponent
{
	/****************************************
	* Public class variables
	****************************************/

	/****************************************
	* Private class variables
	****************************************/

  private ArrayList<AbstractImage> historylist;
  private BufferedImage image;
  private boolean inGrayscale = false; //Checks to see if the image loaded is in grayscale.

  public static final int RANDOMIZE = 0;
  public static final int POLARIZE = 1;
  public static final int COLORPOLARIZE = 2;

	/****************************************
  * Constructor(s)
  ****************************************/

  public Abstractanator()
	{
    // Try reading in a default image
    try {
      image = ImageIO.read(new File("C:/Users/Hunter/Desktop/CGProject/Abstractanator/images/sky.jpg"));
    } catch (IOException ex) {
      ex.printStackTrace();
  	}

    // initialize the history list
    historylist = new ArrayList<AbstractImage>(5);
  }

	/****************************************
	* Getters and Setters
	****************************************/

	/**
	* Set the BufferedImage with the input image
	*/
	public void setImage(BufferedImage imageIn)
	{
		this.image = imageIn;
    this.revalidate();
		System.out.println("Abstractanator image set");
	}

  public void setGrayscale(boolean grayscale)
  {
    this.inGrayscale = grayscale;
  }

  /**
	* Returns the currently set BufferedImage
	*/
  public BufferedImage getImage()
  {
    return this.image;
  }

  public ArrayList<AbstractImage> getHistory()
  {
    return historylist;
  }

  public boolean inGrayscale()
  {
    return inGrayscale;
  }

	/****************************************
	* Public method(s) - OVERRIDES
	****************************************/

  @Override
  public Dimension getPreferredSize()
	{
    return image == null ? new Dimension(500, 500) : new Dimension(image.getWidth(), image.getHeight());
  }

  @Override
  public void paint(Graphics g)
	{
    super.paint(g);
    if (image != null)
		{
      int x = (getWidth() - image.getWidth()) / 2;
      int y = (getHeight() - image.getHeight()) / 2;
      g.drawImage(image, x, y, this);
    }
  }

  /****************************************
	* Public method(s)
	****************************************/

  /** Abstracts an image for n iterations
  * @param abstractType The type of abstraction to be performed
  * @param iter The amount of times the iteration is to be performed. Defaults to 1 if below 1
  */
  public void abstractinate(int abstractType, int iter)
  {
    if (iter < 1) {
      iter = 1;
    }
    for (int i = 0; i < iter; i++) {
      switch (abstractType) {
        case RANDOMIZE: // randomly change the rgb values of each pixel
          randomizePixels();
          break;
        case POLARIZE:  // shift rgb values of each pixel closer to white or black
          polarizePixels();
          break;
        case COLORPOLARIZE:  // shift rgb values of each pixel closer to 0 or 255 respectively
          colorPolarizePixels();
          break;
      }
    }
    // The image gets added to the front
    historylist.add(0, (new AbstractImage(image, thumbnail(75, 75), inGrayscale)));

    // Tell AWT that this panel's info has changed, so redraw it
    this.revalidate();
  }

  /** Gets the thumbnail of the abstract image.
  * <p>This is really just a resize function, but the naming convention is to be more clear on its purpose.
  * @param width The width of the resized image.
  * @param height The height of the resized image.
  * @return The resized image.
  */
  public BufferedImage thumbnail(int width, int height)
  {
    BufferedImage thumbnail = new BufferedImage(width, height, image.getType());
    Graphics2D g = thumbnail.createGraphics();
      g.drawImage(image, 0, 0, width, height, null);
      g.dispose();
      return thumbnail;
  }
  
  /** "Folds" the image. Spits the image in half according to the fold position, then makes the next AbstractImage hold both.
   * <p>Any further abstract functions only act on the unfolded part. The fold is always smaller.
   * @param foldPosition The position from which the image is to be folded. See AbstractImage for more details.
   */
  public void Fold(int foldPosition) {
	  //First we need copy destinations for the image and the folded portion.
	  BufferedImage fold;
	  BufferedImage unfold;
	  
	  //Then how the image gets drawn depends on the foldPosition.
	  switch (foldPosition) {
	  case AbstractImage.LEFT_FOLD:
		  
		  break;
	  case AbstractImage.RIGHT_FOLD:
		  break;
	  case AbstractImage.TOP_FOLD:
		  break;
	  case AbstractImage.BOTTOM_FOLD:
		  break;
	  }
  }

	/****************************************
	* Private method(s)
	****************************************/

   /**
	 * Randomly adjusts the red, green, and blue values of each pixel in an image up to a limit.
	 * The limit is 32, which is an eighth of each color value's range.
	 * <p>There is no "bleed over"; that is, if a value would below 0 or above 255,
	 * it will stay at 0 or 255 (the side effect is that if this effect repeats, colors
	 * will tend toward the upper or lower limit).
	 */
	private void randomizePixels()
	{
		int width = image.getWidth(); //The width of the image.
		int height = image.getHeight(); //The height of the image.
		final int LIMIT = 32; //The +/- limit by which each color value can be adjusted.
		Random rand = new Random(); //The random to determine how much a color value is adjusted.
		int p; //The pixel being currently examined in the loop.
		int alpha, red, green, blue; //The alpha and RGB values of the pixel.

		//The for loop guarantees this function will iterate over the whole image.
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				p = image.getRGB(x, y); //Take the pixel of the current coordinates.
				//Now obtain the RGB values of the pixel.
				//The alpha value should be preserved.
				alpha = (p >> 24) & 0xff;
				red = (p >> 16) & 0xff;
				green = (p >> 8) & 0xff;
				blue = p & 0xff;

				//Now determine the new RGB values of the pixel.
				//It's possible any value won't change at all (if the random value returns 32, 32 - 32 = 0 change).
				red = red + rand.nextInt(LIMIT * 2 + 1) - LIMIT;
				green = green + rand.nextInt(LIMIT * 2 + 1) - LIMIT;
				blue = blue + rand.nextInt(LIMIT * 2 + 1) - LIMIT;

				//The values must be checked to make sure that 0 <= value <= 255.
				if (red < 0) { red = 0; } else if (red > 255) red = 255;
				if (green < 0) { green = 0; } else if (green > 255) green = 255;
				if (blue < 0) { blue = 0; } else if (blue > 255) blue = 255;

				//Now to set the pixel value to 0 and give it the new values.
				p = 0;
				p = p | blue | (green << 8) | (red << 16) | (alpha << 24);

				//Finally to set the image's corresponding pixel with the new values.
				image.setRGB(x, y, p);
			}
		}
		inGrayscale = false; //If the pixels are randomized, this is no longer true!
	}

	/**
	 * Polarizes an image.
	 * <p>If the image is not grayscale, it corrects that first (and sets the grayscale boolean to true to save time later).
	 * Then the image will turn each pixel more white or more dark, depending on if its color values are above 127
	 * or below 128, up to a limit of 32 each time this method is called. After a max of four calls, each pixel will be
	 * completely black or completely white.
	 */
	private void polarizePixels()
  {
		int width = image.getWidth(); //The width of the image.
		int height = image.getHeight(); //The height of the image.
		final int LIMIT = 32; //The +/- limit by which each color value can be adjusted.
		int p; //The pixel being currently examined in the loop.
		int alpha, red; //The alpha and red values of the pixel.

		if (!inGrayscale) {
			grayscale();
			inGrayscale = true;
		}

		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				p = image.getRGB(x, y);
				alpha = (p >> 24) & 0xff;
				red = (p >> 16) & 0xff;

				//red, green, and blue are the same value by now, so red is used to represent them all.
				//If the value is below 128, it tends to black.
				if (red < 128) {
					red = (red - LIMIT >= 0) ? (red - LIMIT) : 0; //A catch to make sure the value stays valid.
				}
				else {
					red = (red + LIMIT <= 255) ? (red + LIMIT) : 255;
				}

				p = 0;
				//And again because red stands for them all, it can represent green and blue here too.
				p = p | red | (red << 8) | (red << 16) | (alpha << 24);

				image.setRGB(x, y, p);
			}
		}
	}

  /** Polarizes an image color-wise incrementally.
	* <p>For example, if a pixel had the RGB values 210, 85, 130, it would eventually become 255, 0, 255.
	* Each value will increase or decrease by 32 each time this method is called. Four calls means an image is color polarized.
	*/
	public void colorPolarizePixels()
  {
		int width = image.getWidth(); //The width of the image.
		int height = image.getHeight(); //The height of the image.
		final int LIMIT = 32; //The +/- limit by which each color value can be adjusted.
		int p; //The pixel being currently examined in the loop.
		int alpha, red, green, blue; //The RGBA values of the pixel.

		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				p = image.getRGB(x, y);
				alpha = (p >> 24) & 0xff;
				red = (p >> 16) & 0xff;
				green = (p >> 8) & 0xff;
				blue = p & 0xff;

				//First adjusting red...
				if (red < 128) {
					red = (red - LIMIT >= 0) ? (red - LIMIT) : 0; //A catch to make sure the value stays valid.
				}
				else {
					red = (red + LIMIT <= 255) ? (red + LIMIT) : 255;
				}
				//... then green...
				if (green < 128) {
					green = (green - LIMIT >= 0) ? (green - LIMIT) : 0; //A catch to make sure the value stays valid.
				}
				else {
					green = (green + LIMIT <= 255) ? (green + LIMIT) : 255;
				}
				//... then blue.
				if (blue < 128) {
					blue = (blue - LIMIT >= 0) ? (blue - LIMIT) : 0; //A catch to make sure the value stays valid.
				}
				else {
					blue = (blue + LIMIT <= 255) ? (blue + LIMIT) : 255;
				}

				p = 0;
				p = p | blue | (green << 8) | (red << 16) | (alpha << 24);

				image.setRGB(x, y, p);
			}
		}

		setGrayscale(false);
	}

	/** Makes an image grayscale.
	 * <p>This is done by finding the average of the RGB values (rounded) and then setting the RGB values to the average.
	 */
	private void grayscale()
  {
		int width = image.getWidth(); //The width of the image.
		int height = image.getHeight(); //The height of the image.
		int p; //The pixel being currently examined in the loop.
		int alpha, red, green, blue; //The alpha and RGB values of the pixel.
		int average; //The average value of the three color values.

		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				p = image.getRGB(x, y);
				alpha = (p >> 24) & 0xff;
				red = (p >> 16) & 0xff;
				green = (p >> 8) & 0xff;
				blue = p & 0xff;

				average = (int)Math.round((red + green + blue) / 3);

				red = average;
				green = average;
				blue = average;

				p = 0;
				p = p | blue | (green << 8) | (red << 16) | (alpha << 24);

				image.setRGB(x, y, p);
			}
		}
	}
}