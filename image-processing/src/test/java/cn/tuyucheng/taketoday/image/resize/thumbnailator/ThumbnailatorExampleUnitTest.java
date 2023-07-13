package cn.tuyucheng.taketoday.image.resize.thumbnailator;

import org.junit.Test;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class ThumbnailatorExampleUnitTest {
	@Test(expected = Test.None.class)
	public void whenOriginalImageExistsAndTargetSizesAreNotZero_thenImageGeneratedWithoutError() throws IOException {
		int targetWidth = 200;
		int targetHeight = 200;
		BufferedImage originalImage = ImageIO.read(new File("src/main/resources/images/sampleImage.jpg"));
		BufferedImage outputImage = ThumbnailatorExample.resizeImage(originalImage, targetWidth, targetHeight);

		assertNotNull(outputImage);
	}

	@Test(expected = Test.None.class)
	public void whenOriginalImageExistsAndTargetSizesAreNotZero_thenOutputImageSizeIsValid() throws IOException {
		int targetWidth = 200;
		int targetHeight = 200;
		BufferedImage originalImage = ImageIO.read(new File("src/main/resources/images/sampleImage.jpg"));
		assertNotEquals(originalImage.getWidth(), targetWidth);
		assertNotEquals(originalImage.getHeight(), targetHeight);
		BufferedImage outputImage = ThumbnailatorExample.resizeImage(originalImage, targetWidth, targetHeight);

		assertEquals(outputImage.getWidth(), targetWidth);
		assertEquals(outputImage.getHeight(), targetHeight);
	}

	@Test(expected = Exception.class)
	public void whenTargetWidthIsZero_thenErrorIsThrown() throws IOException {
		int targetWidth = 0;
		int targetHeight = 200;
		BufferedImage originalImage = ImageIO.read(new File("src/main/resources/images/sampleImage.jpg"));
		BufferedImage outputImage = ThumbnailatorExample.resizeImage(originalImage, targetWidth, targetHeight);

		assertNull(outputImage);
	}

	@Test(expected = Exception.class)
	public void whenTargetHeightIsZero_thenErrorIsThrown() throws IOException {
		int targetWidth = 200;
		int targetHeight = 0;
		BufferedImage originalImage = ImageIO.read(new File("src/main/resources/images/sampleImage.jpg"));
		BufferedImage outputImage = ThumbnailatorExample.resizeImage(originalImage, targetWidth, targetHeight);

		assertNull(outputImage);
	}

	@Test(expected = Exception.class)
	public void whenOriginalImageDoesNotExist_thenErrorIsThrown() throws IOException {
		int targetWidth = 200;
		int targetHeight = 200;
		BufferedImage outputImage = ThumbnailatorExample.resizeImage(null, targetWidth, targetHeight);

		assertNull(outputImage);
	}
}