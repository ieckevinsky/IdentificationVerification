package LiaoNing_Recognise;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class changePictureSize {

	/***
	 * ���� :����ͼƬ��С ��������������ͼƬת��Ϊָ���ߴ�
	 * 
	 * @param srcImgPath
	 *            ԭͼƬ·��
	 * @param distImgPath
	 *            ת����С��ͼƬ·��
	 * @param width
	 *            ת����ͼƬ���
	 * @param height
	 *            ת����ͼƬ�߶�
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
			System.out.println("ͼƬת�������쳣��");
		}

	}

}