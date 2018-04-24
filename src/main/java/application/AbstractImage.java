import java.awt.image.BufferedImage;

/**
 * A class to hold the images of current and previous iterations.
 * Has an img stored, as well as a grayscale variable saying if that image is in grayscale.
 * @author Evan
 *
 */
public class AbstractImage {
	private BufferedImage img;
	private boolean inGrayscale;

	public AbstractImage(BufferedImage img, BufferedImage thumbnail, boolean inGrayscale) {
		this.img = img;
		this.inGrayscale = inGrayscale;
	}

	public BufferedImage getImg() { return img; }
	public boolean isInGrayScale() { return inGrayscale; }
	public void setImg(BufferedImage img) { this.img = img; }
	public void setInGrayScale(boolean inGrayscale) { this.inGrayscale = inGrayscale; }
}
