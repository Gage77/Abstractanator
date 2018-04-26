import java.awt.image.BufferedImage;

/**
 * A class to hold the images of current and previous iterations.
 * Has an img stored, as well as a grayscale variable saying if that image is in grayscale.
 * Additionally, the image thumbnail is stored.
 * @author Evan
 *
 */
public class AbstractImage {
	private BufferedImage img;
	private BufferedImage fold;
	private int foldPosition;
	private BufferedImage thumbnail;
	private boolean inGrayscale;
	
	//Offset values, determining what kind of fold the folded image is.
	//The name is relative to the fold, so "LEFT_FOLD" means the fold is to the left of the image.
	public static final int LEFT_FOLD = 1;
	public static final int RIGHT_FOLD = 2;
	public static final int TOP_FOLD = 3;
	public static final int BOTTOM_FOLD = 4;
	
	//Other constructor, for if the image has a folded section.
	public AbstractImage(BufferedImage img, BufferedImage thumbnail, BufferedImage fold, boolean inGrayscale, int foldPosition) {
		this.img = img;
		this.inGrayscale = inGrayscale;
		this.thumbnail = thumbnail;
		this.foldPosition = foldPosition;
		this.fold = fold;
	}
	
	public BufferedImage getImg() { return img; }
	public BufferedImage getThumbnail() { return thumbnail; }
	public BufferedImage getFold() { return fold; }
	public int getOffset() { return foldPosition; }
	public boolean isInGrayScale() { return inGrayscale; }
	public void setImg(BufferedImage img) { this.img = img; }
	public void setInGrayScale(boolean inGrayscale) { this.inGrayscale = inGrayscale; }
}
