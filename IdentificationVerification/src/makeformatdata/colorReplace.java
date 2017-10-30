package makeformatdata;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.NumberFormat;

import javax.imageio.ImageIO;

public class colorReplace {

	public static void ExtractRed(String imgpath) throws IOException {
		int countnum = 0;
		// TODO Auto-generated method stub
		File file = new File(imgpath);
		//File file1 = new File(System.getProperty("user.dir") + "/picture/" + "25" + ".jpg");
		File file1 = new File("D:\\DownLoad\\" + "25" + ".jpg");
		BufferedImage bufferedImage = ImageIO.read(file);
		BufferedImage bufferedImage1 = ImageIO.read(file1);
		int width1 = bufferedImage.getWidth();
		int height1 = bufferedImage.getHeight();
		//System.out.println(width1 + " " + height1);

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

				DecimalFormat df = new DecimalFormat("0.0000");
				if ((Math.pow(Double.valueOf(rgb[0] - 255) / Double.valueOf(255), 2)
						+ Math.pow(Double.valueOf(rgb[1]) / Double.valueOf(255), 2)
						+ Math.pow(Double.valueOf(rgb[2]) / Double.valueOf(255), 2)) < 0.65) {

					bufferedImage1.setRGB(i, j, pixel);
					countnum++;
				} else {
					bufferedImage1.setRGB(i, j, 16777215);
				}

				count++;
			}

		}
		//ImageIO.write(bufferedImage1, "jpg", new File(System.getProperty("user.dir") + "/picture/24.jpg"));
		
		ImageIO.write(bufferedImage1, "jpg", new File("D:\\DownLoad\\" + "24" + ".jpg"));
	}

}
