package makeformatdata;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;

import javax.imageio.ImageIO;

public class CreateTraindata {

	public void binaryImage(String imgpath,String imgOutputpath) throws IOException{
		File file = new File(imgpath);
		BufferedImage image = ImageIO.read(file);
		
		int width = image.getWidth();
		int height = image.getHeight();
		
		BufferedImage binaryImage = new BufferedImage(width, height, BufferedImage.TYPE_BYTE_BINARY);//�ص㣬�������������BufferedImage.TYPE_BYTE_BINARY
		for(int i= 0 ; i < width ; i++){
		    for(int j = 0 ; j < height; j++){
			int rgb = image.getRGB(i, j);
			binaryImage.setRGB(i, j, rgb);
		    }
		}
		
		File newFile = new File(imgOutputpath);
		ImageIO.write(binaryImage, "jpg", newFile);
	    }
	
	
	public static void main(String[] args) throws IOException {
		LinkedList<String> filename = new LinkedList<String>();
//		filename.add("one");
//		filename.add("two");
//		filename.add("three");
//		filename.add("four");
//		filename.add("five");
//		filename.add("six");
//		filename.add("seven");
//		filename.add("eight");
//		filename.add("nine");
		filename.add("��");
		filename.add("��");
//		CreateTraindata CreateTraindata=new CreateTraindata();
//		// TODO Auto-generated method stub
//		imageCover imageCover =new imageCover();
//		for(int i=0;i<10000;i++){
//			String imgpath="D:\\��֤��ѵ������\\tianjinfilter\\"+i+".jpg";
//			String imgOutputpath="D:\\��֤��ѵ������\\tianjinbinary\\"+i+".jpg";
//			System.out.println(imgpath);
//			CreateTraindata.binaryImage(imgpath, imgOutputpath);	
//		}
//
//				LinkedList<Integer> colum3 = new LinkedList<Integer>();
//				plotline plotline =new plotline();
//				plotthreePicture plotthreePicture= new plotthreePicture();
//				
//				for(int i=0;i<10000;i++){
//					String imgpath="D:\\��֤��ѵ������\\tianjinbinary\\"+i+".jpg";
//					String imgOutputpath1="D:\\��֤��ѵ������\\1\\"+i+".jpg";
//					String imgOutputpath2="D:\\��֤��ѵ������\\2\\"+i+".jpg";
//					String imgOutputpath3="D:\\��֤��ѵ������\\3\\"+i+".jpg";
//					System.out.println(imgpath);
//					colum3=plotline.paint(imgpath);
//					plotthreePicture.splitpicture(colum3,imgpath,imgOutputpath1,imgOutputpath2,imgOutputpath3);
//				}
//		
				
				//ͼƬ��С��һ��
				
				changePictureSize changePictureSize =new changePictureSize();
				//ͼƬתΪ������
				ImageDemo ImageDemo= new ImageDemo();
				String imgformatpath="D:\\��֤��ѵ������\\2.format";
				FileWriter writer  =new  FileWriter(imgformatpath);
				String imgformatpath1="D:\\��֤��ѵ������\\2test.format";
				FileWriter writer1  =new  FileWriter(imgformatpath1);
				for(int j=0;j<filename.size();j++){
					for(int i=1;i<101;i++){
						String imgpath="D:\\��֤��ѵ������\\"+filename.get(j)+"\\"+i+".jpg";
						System.out.println(imgpath);
						String imgOutputpath="D:\\��֤��ѵ������\\"+filename.get(j)+"\\"+i+".jpg";
						changePictureSize.resizeImage(imgpath,imgOutputpath,28,28);
						
						ImageDemo.BinaryValue(writer,writer1,imgformatpath,imgOutputpath,String.valueOf(j));
					}
				}
				writer.flush();
				writer.close();
				writer1.flush();
				writer1.close();
				
				
	}

}
