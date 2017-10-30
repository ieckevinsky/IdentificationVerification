package LiaoNing_Recognise;

import java.io.IOException;
import java.util.LinkedList;

public class data {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		imageCover imageCover =new imageCover();
		Recognise test = new Recognise();
		plotline plotline =new plotline();
		changePictureSize changePictureSize =new changePictureSize();
		plotthreePicture plotthreePicture= new plotthreePicture();
		ImageDemo ImageDemo= new ImageDemo();
		for(int i=0;i<10000;i++){
		test.binaryImage( "D:\\验证码训练数据\\liaoning\\suanshu\\" +i + ".jpg",   "D:\\验证码训练数据\\liaoning\\suanshu\\binary\\" +i + ".jpg" );
		//图像投影曲线平滑、确定切分点
		//参数 二值后图像的存储路径
		LinkedList<Integer> colum3 = new LinkedList<Integer>();
		colum3=plotline.paint( "D:\\验证码训练数据\\liaoning\\suanshu\\binary\\" +i + ".jpg" );		
		//切分图像
		//参数 平滑后图像水平投影切分点linkedlist集合
		plotthreePicture.splitpicture(colum3, "D:\\验证码训练数据\\liaoning\\suanshu\\binary\\" +i + ".jpg" , "D:\\验证码训练数据\\liaoning\\suanshu\\1\\" +i + ".jpg" , "D:\\验证码训练数据\\liaoning\\suanshu\\2\\" +i + ".jpg" , "D:\\验证码训练数据\\liaoning\\suanshu\\3\\" +i + ".jpg" );			
		//图片大小归一化
		changePictureSize.resizeImage("D:\\验证码训练数据\\liaoning\\suanshu\\1\\" +i + ".jpg" ,"D:\\验证码训练数据\\liaoning\\suanshu\\1\\" +i + ".jpg" ,28,28);
		changePictureSize.resizeImage("D:\\验证码训练数据\\liaoning\\suanshu\\2\\" +i + ".jpg" ,"D:\\验证码训练数据\\liaoning\\suanshu\\2\\" +i + ".jpg" ,28,28);
		changePictureSize.resizeImage("D:\\验证码训练数据\\liaoning\\suanshu\\3\\" +i + ".jpg" ,"D:\\验证码训练数据\\liaoning\\suanshu\\3\\" +i + ".jpg" ,28,28);
		
		}
	}

}
