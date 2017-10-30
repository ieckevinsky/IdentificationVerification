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
		
		BufferedImage binaryImage = new BufferedImage(width, height, BufferedImage.TYPE_BYTE_BINARY);//重点，技巧在这个参数BufferedImage.TYPE_BYTE_BINARY
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
		filename.add("乘");
		filename.add("加");
//		CreateTraindata CreateTraindata=new CreateTraindata();
//		// TODO Auto-generated method stub
//		imageCover imageCover =new imageCover();
//		for(int i=0;i<10000;i++){
//			String imgpath="D:\\验证码训练数据\\tianjinfilter\\"+i+".jpg";
//			String imgOutputpath="D:\\验证码训练数据\\tianjinbinary\\"+i+".jpg";
//			System.out.println(imgpath);
//			CreateTraindata.binaryImage(imgpath, imgOutputpath);	
//		}
//
//				LinkedList<Integer> colum3 = new LinkedList<Integer>();
//				plotline plotline =new plotline();
//				plotthreePicture plotthreePicture= new plotthreePicture();
//				
//				for(int i=0;i<10000;i++){
//					String imgpath="D:\\验证码训练数据\\tianjinbinary\\"+i+".jpg";
//					String imgOutputpath1="D:\\验证码训练数据\\1\\"+i+".jpg";
//					String imgOutputpath2="D:\\验证码训练数据\\2\\"+i+".jpg";
//					String imgOutputpath3="D:\\验证码训练数据\\3\\"+i+".jpg";
//					System.out.println(imgpath);
//					colum3=plotline.paint(imgpath);
//					plotthreePicture.splitpicture(colum3,imgpath,imgOutputpath1,imgOutputpath2,imgOutputpath3);
//				}
//		
				
				//图片大小归一化
				
				changePictureSize changePictureSize =new changePictureSize();
				//图片转为行向量
				ImageDemo ImageDemo= new ImageDemo();
				String imgformatpath="D:\\验证码训练数据\\2.format";
				FileWriter writer  =new  FileWriter(imgformatpath);
				String imgformatpath1="D:\\验证码训练数据\\2test.format";
				FileWriter writer1  =new  FileWriter(imgformatpath1);
				for(int j=0;j<filename.size();j++){
					for(int i=1;i<101;i++){
						String imgpath="D:\\验证码训练数据\\"+filename.get(j)+"\\"+i+".jpg";
						System.out.println(imgpath);
						String imgOutputpath="D:\\验证码训练数据\\"+filename.get(j)+"\\"+i+".jpg";
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
