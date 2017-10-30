package LiaoNing_Recognise;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class changePictureSize {

	/***
	 * 功能 :调整图片大小 开发：按照需求将图片转换为指定尺寸
	 * 
	 * @param srcImgPath
	 *            原图片路径
	 * @param distImgPath
	 *            转换大小后图片路径
	 * @param width
	 *            转换后图片宽度
	 * @param height
	 *            转换后图片高度
	 */
	public static void resizeImage(String srcImgPath, String distImgPath, int width, int height) throws IOException {

		File srcFile = new File(srcImgPath);
		Image srcImg = ImageIO.read(srcFile);

		BufferedImage buffImg = null;
		buffImg = new BufferedImage(width, height, BufferedImage.TYPE_BYTE_BINARY);
		buffImg.getGraphics().drawImage(srcImg.getScaledInstance(width, height, Image.SCALE_SMOOTH), 0, 0, null);

		BufferedImage bufferedImage = ImageIO.read(new File(srcImgPath));
		int width1 = bufferedImage.getWidth();
		int height1 = bufferedImage.getHeight();
		String sq[] = new String[width1 * height1];
		int count = 0;
		for (int i = 0; i < width1; i++) {
			for (int j = 0; j < height1; j++) {
				sq[count] = String.valueOf(bufferedImage.getRGB(i, j));
				count++;
			}
		}
		ImageIO.write(buffImg, "JPEG", new File(distImgPath));
	}

	public static void main(String[] args) throws IOException {
		try {
			changePictureSize.resizeImage(System.getProperty("user.dir") + "/picture/4.jpg",
					System.getProperty("user.dir") + "/picture/4.jpg", 28, 28);
		} catch (IOException e) {
			System.out.println("图片转换出现异常！");
		}

	}

}