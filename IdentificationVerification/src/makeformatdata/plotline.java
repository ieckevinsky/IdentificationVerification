package makeformatdata;

import java.applet.Applet;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;

import javax.imageio.ImageIO;

public class plotline extends Applet {
	private static Color[] colors = { Color.white, Color.black, Color.blue, Color.red, Color.yellow, Color.orange,
			Color.cyan, Color.pink, Color.magenta, Color.green };
	public void init() {
		Button button = new Button("repaint");
		add(button);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				plotline.this.repaint();
			}
		});
	}
	public LinkedList<Integer> paint(String imgpath) {
		LinkedList<Integer> colum3 = new LinkedList<Integer>(); // 水平投影切分点
		//File file = new File(System.getProperty("user.dir") + "/picture/" + "4" + ".jpg");
		File file = new File(imgpath);
		int width1 = 0;
		int height1 = 0;
		try {
			BufferedImage bufferedImage;
			bufferedImage = ImageIO.read(file);
			width1 = bufferedImage.getWidth();
			height1 = bufferedImage.getHeight();
			//System.out.println(width1 + " " + height1);
			int[] xPoints = new int[width1];
			int[] yPoints = new int[width1];
			int sq[] = new int[width1 * height1];  //原图像转为行向量
			
			int colum[] = new int[width1]; // 原图像水平投影统计
			int colum2[] = new int[width1]; // 滑动平滑后图像水平投影统计
			
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
					// System.out.print(rgb[0]+" ");
					// System.out.print(rgb[1]+" ");
					// System.out.print(rgb[2]+" ");
					if (sq[count] != -1) {
						sq[count] = 0;
						countcolum++;
					} else {
						sq[count] = 1;
					}
					// System.out.println(sq[count]+" ");
					simage = simage + sq[count] + ",";
					// System.out.print(sq[count]);
					count++;
				}
				colum[i] = countcolum;
				//System.out.print(colum[i] + " ");
			}
			//System.out.println();
			for (int j = 0; j < colum.length; j++) {
				if (j == 0) {
					colum2[j] = colum[j];
				}
				if (j == 1) {
					colum2[j] = (colum[0] + colum[j]) / 2;
				}
				if (j == 2) {
					colum2[j] = (colum[0] + colum[1] + colum[j]) / 3;
				}
				if (j >= 3) {
					colum2[j] = (colum[j - 3] + colum[j - 2] + colum[j - 1] + colum[j]) / 4;
				}
				xPoints[j] = j;
				yPoints[j] = height1 - colum2[j];
				//System.out.print(colum2[j] + " ");
			}
			for (int j = 0; j < colum2.length - 1; j++) {
				if (colum2[j] != 0 && colum2[j + 1] == 0) {
					colum3.add(j);
				}

			}
			//System.out.println();
			
			
//			FileWriter writer1= new FileWriter("C:\\Users\\hylanda\\Desktop\\1.format");
//			int sq2[][] = new int[colum3.size()][width1 * height1];
//			int k=0;
//			for (int j = 0; j < colum3.size(); j++) {
//				int count1=0;
//				System.out.println(colum3.get(j));
//				for(k=k;k<=colum3.get(j);k++){
//					for(int h=0;h<height1;h++){
//						sq2[j][count1] = bufferedImage.getRGB(k, h);
//						if (sq2[j][count1] != -1) {
//							sq2[j][count1] = 0;							
//						} else {
//							sq2[j][count1] = 1;
//						}
//						writer1.write(sq2[j][count1]+",");
//					}
//					count1++;
//				}
//				k=colum3.get(j);
//				writer1.write("\r\n");
//			}
//			writer1.flush();
//			writer1.close();
			

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return colum3;

		// System.out.println(simage);

	}
	public void paint(Graphics g) {

		File file = new File("C:\\Users\\hylanda\\Desktop\\4.jpg");
		int width1 = 0;
		int height1 = 0;
		try {
			BufferedImage bufferedImage;
			bufferedImage = ImageIO.read(file);
			width1 = bufferedImage.getWidth();
			height1 = bufferedImage.getHeight();
			System.out.println(width1 + " " + height1);
			int[] xPoints = new int[width1];
			int[] yPoints = new int[width1];
			int sq[] = new int[width1 * height1];  //原图像转为行向量
			
			int colum[] = new int[width1]; // 原图像水平投影统计
			int colum2[] = new int[width1]; // 滑动平滑后图像水平投影统计
			LinkedList<Integer> colum3 = new LinkedList<Integer>(); // 水平投影切分点
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
					// System.out.print(rgb[0]+" ");
					// System.out.print(rgb[1]+" ");
					// System.out.print(rgb[2]+" ");
					if (sq[count] != -1) {
						sq[count] = 0;
						countcolum++;
					} else {
						sq[count] = 1;
					}
					// System.out.println(sq[count]+" ");
					simage = simage + sq[count] + ",";
					// System.out.print(sq[count]);
					count++;
				}
				colum[i] = countcolum;
				//System.out.print(colum[i] + " ");
			}
			//System.out.println();
			for (int j = 0; j < colum.length; j++) {
				if (j == 0) {
					colum2[j] = colum[j];
				}
				if (j == 1) {
					colum2[j] = (colum[0] + colum[j]) / 2;
				}
				if (j == 2) {
					colum2[j] = (colum[0] + colum[1] + colum[j]) / 3;
				}
				if (j >= 3) {
					colum2[j] = (colum[j - 3] + colum[j - 2] + colum[j - 1] + colum[j]) / 4;
				}
				xPoints[j] = j;
				yPoints[j] = height1 - colum2[j];
				//System.out.print(colum2[j] + " ");
			}
			for (int j = 0; j < colum2.length - 1; j++) {
				if (colum2[j] != 0 && colum2[j + 1] == 0) {
					colum3.add(j);
				}

			}
			//System.out.println();
			
			
			FileWriter writer1= new FileWriter("C:\\Users\\hylanda\\Desktop\\1.format");
			int sq2[][] = new int[colum3.size()][width1 * height1];
			int k=0;
			for (int j = 0; j < colum3.size(); j++) {
				int count1=0;
				System.out.println(colum3.get(j));
				for(k=k;k<=colum3.get(j);k++){
					for(int h=0;h<height1;h++){
						sq2[j][count1] = bufferedImage.getRGB(k, h);
						if (sq2[j][count1] != -1) {
							sq2[j][count1] = 0;							
						} else {
							sq2[j][count1] = 1;
						}
						writer1.write(sq2[j][count1]+",");
					}
					count1++;
				}
				k=colum3.get(j);
				writer1.write("\r\n");
			}
			writer1.flush();
			writer1.close();
			
			
			
			g.setColor(colors[(int) (Math.random() * 10)]);
			g.drawPolyline(xPoints, yPoints, xPoints.length);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// System.out.println(simage);

	}

}