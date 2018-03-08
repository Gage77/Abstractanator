		/**
		 * Adjusts the red, green, and blue values of each pixel in an image up to a limit.
		 * The limit is 32, which is an eighth of each color value's range.
		 * <p>There is no "bleed over"; that is, if a value would below 0 or above 255,
		 * it will stay at 0 or 255 (the side effect is that if this effect repeats, colors
		 * will tend toward the upper or lower limit).
		 * @param image The image to randomize the pixels of.
		 * @return The finished image with its pixels randomized.
		 */
		private void randomizePixels() {
			int width = image.getHeight(); //The width of the image.
			int height = image.getWidth(); //The height of the image.
			final int LIMIT = 32; //The +/- limit by which each color value can be adjusted.
			Random rand = new Random(); //The random to determine how much a color value is adjusted.
			int p; //The pixel being currently examined in the loop.
			int red, green, blue; //The RGB values of the pixel.

			//The for loop guarantees this function will iterate over the whole image.
			for (int x = 0; x < width; x++) {
				for (int y = 0; y < height; y++) {
					p = image.getRGB(x, y); //Take the pixel of the current coordinates.
					//Now obtain the RGB values of the pixel.
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
					p = p | blue | (green << 8) | (red << 16);

					//Finally to set the image's corresponding pixel with the new values.
					image.setRGB(x, y, p);
				}
			}
		}
