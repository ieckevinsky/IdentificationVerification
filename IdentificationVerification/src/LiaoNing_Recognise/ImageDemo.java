package LiaoNing_Recognise;

/**
 * @author hylanda 
 * 将彩色图像转换为二值图像、灰度图像
 * 
 */
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImageDemo {

	public void binaryImage() throws IOException {
		File file = new File(System.getProperty("user.dir") + "/src/0.jpg");
		BufferedImage image = ImageIO.read(file);

		int width = image.getWidth();
		int height = image.getHeight();

		BufferedImage binaryImage = new BufferedImage(width, height, BufferedImage.TYPE_BYTE_BINARY);// 重点，技巧在这个参数BufferedImage.TYPE_BYTE_BINARY
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				int rgb = image.getRGB(i, j);
				binaryImage.setRGB(i, j, rgb);
			}
		}

		File newFile = new File(System.getProperty("user.dir") + "/src/12.jpg");
		ImageIO.write(binaryImage, "jpg", newFile);
	}

	public void grayImage() throws IOException {
		File file = new File(System.getProperty("user.dir") + "/src/11.jpg");
		BufferedImage image = ImageIO.read(file);

		int width = image.getWidth();
		int height = image.getHeight();

		BufferedImage grayImage = new BufferedImage(width, height, BufferedImage.TYPE_BYTE_GRAY);// 重点，技巧在这个参数BufferedImage.TYPE_BYTE_GRAY
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				int rgb = image.getRGB(i, j);
				grayImage.setRGB(i, j, rgb);
			}
		}

		File newFile = new File(System.getProperty("user.dir") + "/src/13.jpg");
		ImageIO.write(grayImage, "jpg", newFile);
	}

	public static void BinaryValue(FileWriter writer, FileWriter writer1, String s1, String s2, String t)
			throws IOException {
		// 将图像rgb值二值化
		File newFile = new File(s1);
		File file = new File(s2);
		BufferedImage bufferedImage = ImageIO.read(file);
		int width1 = bufferedImage.getWidth();
		int height1 = bufferedImage.getHeight();
		System.out.println(width1 + " " + height1);
		int sq[] = new int[width1 * height1];
		int colum[] = new int[width1];
		int count = 0;
		String simage = "";
		for (int i = 0; i < width1; i++) {
			int countcolum = 0;
			for (int j = 0; j < height1; j++) {
				sq[count] = bufferedImage.getRGB(i, j);

				int[] rgb = new int[3];
				int pixel = bufferedImage.getRGB(i, j);
				rgb[0] = (pixel & 0xff0000) >> 16;
				rgb[1] = (pixel & 0xff00) >> 8;
				rgb[2] = (pixel & 0xff);

				if (sq[count] != -1) {
					sq[count] = 0;
				} else {
					sq[count] = 1;
					countcolum++;
				}

				simage = simage + sq[count] + ",";
				count++;
			}
			colum[i] = countcolum;
			System.out.print(colum[i] + " ");
		}
		writer.write(simage.substring(0, simage.length()) + t + "\r\n");
		writer1.write(simage.substring(0, simage.length() - 1) + "\r\n");

	}

	public static void BinaryValue(String s1, String s2) throws IOException {
		// 将图像rgb值二值化
		File newFile = new File("D:\\DownLoad\\LiaoNing\\" + s1 + ".format");
		// File newFile = new File(s1);
		FileWriter writer = new FileWriter(newFile);
		File file = new File("D:\\DownLoad\\LiaoNing\\" + s2 + ".jpg");

		BufferedImage bufferedImage = ImageIO.read(file);
		int width1 = bufferedImage.getWidth();
		int height1 = bufferedImage.getHeight();
		int sq[] = new int[width1 * height1];
		int colum[] = new int[width1];
		int count = 0;
		String simage = "";
		for (int i = 0; i < width1; i++) {
			int countcolum = 0;
			for (int j = 0; j < height1; j++) {
				sq[count] = bufferedImage.getRGB(i, j);

				int[] rgb = new int[3];
				int pixel = bufferedImage.getRGB(i, j);
				rgb[0] = (pixel & 0xff0000) >> 16;
				rgb[1] = (pixel & 0xff00) >> 8;
				rgb[2] = (pixel & 0xff);
				if (sq[count] != -1) {
					sq[count] = 0;
				} else {
					sq[count] = 1;
					countcolum++;
				}
				simage = simage + sq[count] + ",";
				count++;
			}
			colum[i] = countcolum;
		}
		writer.write(simage.substring(0, simage.length() - 1) + "\r\n");
		writer.flush();
		writer.close();

	}

	public static void main(String[] args) throws IOException {
		ImageDemo demo = new ImageDemo();
		demo.binaryImage();
		demo.grayImage();

		// 将图像rgb值二值化
		File newFile = new File(System.getProperty("user.dir") + "/dataset/number.format");
		FileWriter writer = new FileWriter(newFile);
		File file = new File(System.getProperty("user.dir") + "/src/12.jpg");
		BufferedImage bufferedImage = ImageIO.read(file);
		int width1 = bufferedImage.getWidth();
		int height1 = bufferedImage.getHeight();
		System.out.println(width1 + " " + height1);

		int sq[] = new int[width1 * height1];
		int colum[] = new int[width1];
		int count = 0;
		String simage = "";
		for (int i = 0; i < width1; i++) {
			int countcolum = 0;
			for (int j = 0; j < height1; j++) {
				sq[count] = bufferedImage.getRGB(i, j);

				int[] rgb = new int[3];
				int pixel = bufferedImage.getRGB(i, j);
				rgb[0] = (pixel & 0xff0000) >> 16;
				rgb[1] = (pixel & 0xff00) >> 8;
				rgb[2] = (pixel & 0xff);
				if (sq[count] != -1) {
					sq[count] = 0;
				} else {
					sq[count] = 1;
					countcolum++;
				}
				simage = simage + sq[count] + ",";
				count++;
			}
			colum[i] = countcolum;
			System.out.print(colum[i] + " ");
		}
		writer.write(simage.substring(0, simage.length() - 1) + "\r\n");
		writer.flush();
		writer.close();
	}

}
