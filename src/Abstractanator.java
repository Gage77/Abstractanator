import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.JComponent;

/**
 * This class will hold all abstraction functions including RGB, BW, Geom,
 * and Folding
 * @author Evan
 *
 */

public class Abstractanator extends JComponent {

		private ArrayList<AbstractImage> historylist;
    private BufferedImage image; //Maybe change to array of 10 to allow previous images.
    private boolean inGrayscale = false; //Checks to see if the image loaded is in grayscale.

    public Abstractanator()
		{
      historylist = new ArrayList<AbstractImage>();

      try {
        image = ImageIO.read(new File("images/sampleBird1.jpg"));
      } catch (IOException ex) {
        ex.printStackTrace();
    	}

      //Uncomment / change the limits of i to get different results.
//    for (int i = 0; i < 4; i++)
//		{
//   		polarizePixels();
//    }

//    for (int i = 0; i < 100; i++)
//		{
//  	 	randomizePixels();
//    }
    }

    @Override
    public Dimension getPreferredSize()
		{
        return image == null ? new Dimension(200, 200) : new Dimension(image.getWidth(), image.getHeight());
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

     /**
		 * Randomly adjusts the red, green, and blue values of each pixel in an image up to a limit.
		 * The limit is 32, which is an eighth of each color value's range.
		 * <p>There is no "bleed over"; that is, if a value would below 0 or above 255,
		 * it will stay at 0 or 255 (the side effect is that if this effect repeats, colors
		 * will tend toward the upper or lower limit).
		 */
		private void randomizePixels()
		{
			int width = image.getHeight(); //The width of the image.
			int height = image.getWidth(); //The height of the image.
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
		private void polarizePixels() {
			int width = image.getHeight(); //The width of the image.
			int height = image.getWidth(); //The height of the image.
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

		/** Makes an image grayscale.
		 * <p>This is done by finding the average of the RGB values (rounded) and then setting the RGB values to the average.
		 */
		private void grayscale() {
			int width = image.getHeight(); //The width of the image.
			int height = image.getWidth(); //The height of the image.
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
