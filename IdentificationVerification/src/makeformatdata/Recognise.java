package makeformatdata;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;

import javax.imageio.ImageIO;

import edu.hitsz.c102c.cnn.CNN;
import edu.hitsz.c102c.dataset.Dataset;

public class Recognise {
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
	/**
	 * @param args
	 * @throws IOException
	 */
	public static RecogniseResult RecogniseProcess(String imgpath) throws IOException {
		// TODO Auto-generated method stub
		RecogniseResult RecogniseResult =new RecogniseResult();
		//过滤图片噪音及杂色，提取待识别文字
		//参数 图像输入路径
		//imgpath="D:\\验证码训练数据\\tianjin\\6830.jpg";
		colorReplace colorReplace =new colorReplace();
		colorReplace.ExtractRed(imgpath);   
		
		//图像二值化
		//参数 滤色后的图像的存储位置
		imageCover imageCover =new imageCover();
		Recognise test = new Recognise();
		
		test.binaryImage("D:\\DownLoad\\" + "24" + ".jpg",  "D:\\DownLoad\\" + "4" + ".jpg");
		//test.binaryImage( System.getProperty("user.dir") + "/picture/" + "24" + ".jpg",  System.getProperty("user.dir") + "/picture/" + "4" + ".jpg");
	

		//图像投影曲线平滑、确定切分点
		//参数 二值后图像的存储路径
		LinkedList<Integer> colum3 = new LinkedList<Integer>();
		plotline plotline =new plotline();
		colum3=plotline.paint("D:\\DownLoad\\" + "4" + ".jpg");
		//colum3=plotline.paint(System.getProperty("user.dir") + "/picture/" + "4" + ".jpg");
		
		//切分图像
		//参数 平滑后图像水平投影切分点linkedlist集合
		
		plotthreePicture plotthreePicture= new plotthreePicture();
		//plotthreePicture.splitpicture(colum3,System.getProperty("user.dir")+"/picture/4.jpg",System.getProperty("user.dir")+"/picture/5.jpg",System.getProperty("user.dir")+"/picture/6.jpg",System.getProperty("user.dir")+"/picture/7.jpg");
		plotthreePicture.splitpicture(colum3,"D:\\DownLoad\\" + "4" + ".jpg","D:\\DownLoad\\" + "5" + ".jpg","D:\\DownLoad\\" + "6" + ".jpg","D:\\DownLoad\\" + "7" + ".jpg");
		
		//图片大小归一化
		
		changePictureSize changePictureSize =new changePictureSize();
		//changePictureSize.resizeImage(System.getProperty("user.dir")+"/picture/5.jpg",System.getProperty("user.dir")+"/picture/55.jpg",28,28);
		//changePictureSize.resizeImage(System.getProperty("user.dir")+"/picture/6.jpg",System.getProperty("user.dir")+"/picture/66.jpg",28,28);
		//changePictureSize.resizeImage(System.getProperty("user.dir")+"/picture/7.jpg",System.getProperty("user.dir")+"/picture/77.jpg",28,28);
		changePictureSize.resizeImage("D:\\DownLoad\\" + "5" + ".jpg","D:\\DownLoad\\" + "55" + ".jpg",28,28);
		changePictureSize.resizeImage("D:\\DownLoad\\" + "6" + ".jpg","D:\\DownLoad\\" + "66" + ".jpg",28,28);
		changePictureSize.resizeImage("D:\\DownLoad\\" + "7" + ".jpg","D:\\DownLoad\\" + "77" + ".jpg",28,28);
		//图片转为行向量
		ImageDemo ImageDemo= new ImageDemo();
		ImageDemo.BinaryValue("1","55");
		ImageDemo.BinaryValue("2","66");
		ImageDemo.BinaryValue("3","77");
		
		
		String modelName1 = "D:\\DownLoad\\model\\model1.cnn";
		CNN cnn1 = CNN.loadModel(modelName1);	
		Dataset testset1 = Dataset.load("D:\\DownLoad\\dataset\\1.format", ",", -1);
		cnn1.predict(testset1, "D:\\DownLoad\\dataset\\test1.predict");
		
		String modelName2 = "D:\\DownLoad\\model\\model2.cnn";
		CNN cnn2 = CNN.loadModel(modelName2);	
		Dataset testset2 = Dataset.load("D:\\DownLoad\\dataset\\2.format", ",", -1);
		cnn2.predict(testset2, "D:\\DownLoad\\dataset\\test2.predict");
		
		String modelName3 = "D:\\DownLoad\\model\\model3.cnn";
		CNN cnn3 = CNN.loadModel(modelName3);	
		Dataset testset3 = Dataset.load("D:\\DownLoad\\dataset\\3.format", ",", -1);
		cnn3.predict(testset3, "D:\\DownLoad\\dataset\\test3.predict");
		
		
		
		int firstNumber=0;
		int secondNumber=0;
		int  result=0;
		File file1=new File("D:\\DownLoad\\dataset\\test1.predict");
		if(file1.isFile() && file1.exists()){ //判断文件是否存在
        	
            InputStreamReader read1 = new InputStreamReader(
            new FileInputStream(file1),"GBK");//考虑到编码格式
            BufferedReader bufferedReader1 = new BufferedReader(read1);
            String lineTxt = null;
            
            while((lineTxt = bufferedReader1.readLine()) != null){
            	int number1=Integer.valueOf(lineTxt)+1;
            	//System.out.println("number1: "+number1);
            	firstNumber=number1;
            	RecogniseResult.setFirst(firstNumber);
            	}
            read1.close();
            }
		
		File file3=new File("D:\\DownLoad\\dataset\\test3.predict");
		if(file3.isFile() && file3.exists()){ //判断文件是否存在
        	
            InputStreamReader read1 = new InputStreamReader(
            new FileInputStream(file3),"GBK");//考虑到编码格式
            BufferedReader bufferedReader1 = new BufferedReader(read1);
            String lineTxt = null;
            
            while((lineTxt = bufferedReader1.readLine()) != null){
            	int number2=Integer.valueOf(lineTxt)+1;
            	//System.out.println("number2: "+number2);
            	secondNumber=number2;
            	RecogniseResult.setSecond(secondNumber);
            	}
            read1.close();
            }
		File file2=new File("D:\\DownLoad\\dataset\\test2.predict");
		if(file2.isFile() && file2.exists()){ //判断文件是否存在
        	
            InputStreamReader read1 = new InputStreamReader(
            new FileInputStream(file2),"GBK");//考虑到编码格式
            BufferedReader bufferedReader1 = new BufferedReader(read1);
            String lineTxt = null;
            
            while((lineTxt = bufferedReader1.readLine()) != null){
            	if(lineTxt.equals("1")){
            		//System.out.println("operator: "+"+");
            		result= firstNumber+secondNumber;
            		RecogniseResult.setOperatortype("+");
            		RecogniseResult.setResult(result);
            	}
            	if(lineTxt.equals("0")){
            	//	System.out.println("operator: "+"*");
            		result=firstNumber*secondNumber;
            		RecogniseResult.setOperatortype("*");
            		RecogniseResult.setResult(result);
            	}
            	}
            read1.close();
            }
		//System.out.println("reslut: "+result);
		
		return RecogniseResult;
		
	}

}
