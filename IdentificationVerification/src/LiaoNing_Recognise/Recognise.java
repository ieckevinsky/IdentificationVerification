package LiaoNing_Recognise;

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
		BufferedImage binaryImage = new BufferedImage(width, height, BufferedImage.TYPE_BYTE_BINARY);
		for(int i= 0 ; i < width ; i++){
		    for(int j = 0 ; j < height; j++){
			int rgb = image.getRGB(i, j);
			binaryImage.setRGB(i, j, rgb);
		    }
		}		
		File newFile = new File(imgOutputpath);
		ImageIO.write(binaryImage, "jpg", newFile);
	    }

	public static RecogniseResult RecogniseProcess(String imgpath,String Id) throws IOException {
		// TODO Auto-generated method stub
		RecogniseResult RecogniseResult =new RecogniseResult();
		//过滤图片噪音及杂色，提取待识别文字
		//参数 图像输入路径
//		colorReplace colorReplace =new colorReplace();
//		colorReplace.ExtractRed(imgpath,Id);   
		
		//图像二值化
		//参数 滤色后的图像的存储位置
		imageCover imageCover =new imageCover();
		Recognise test = new Recognise();
		test.binaryImage(imgpath,  "D:\\DownLoad\\LiaoNing\\"  +Id+"4" + ".jpg");
	

		//图像投影曲线平滑、确定切分点
		//参数 二值后图像的存储路径
		LinkedList<Integer> colum3 = new LinkedList<Integer>();
		plotline plotline =new plotline();
		colum3=plotline.paint("D:\\DownLoad\\LiaoNing\\"  +Id+"4" + ".jpg");
			
		//切分图像
		//参数 平滑后图像水平投影切分点linkedlist集合
		
		plotthreePicture plotthreePicture= new plotthreePicture();
		plotthreePicture.splitpicture(colum3,"D:\\DownLoad\\LiaoNing\\" +Id+"4.jpg","D:\\DownLoad\\LiaoNing\\" +Id+"5.jpg","D:\\DownLoad\\LiaoNing\\" +Id+"6.jpg","D:\\DownLoad\\LiaoNing\\" +Id+"7.jpg");
			
		//图片大小归一化
		
		changePictureSize changePictureSize =new changePictureSize();
		changePictureSize.resizeImage("D:\\DownLoad\\LiaoNing\\" +Id+"5.jpg","D:\\DownLoad\\LiaoNing\\" +Id+"55.jpg",28,28);
		changePictureSize.resizeImage("D:\\DownLoad\\LiaoNing\\" +Id+"6.jpg","D:\\DownLoad\\LiaoNing\\" +Id+"66.jpg",28,28);
		changePictureSize.resizeImage("D:\\DownLoad\\LiaoNing\\" +Id+"7.jpg","D:\\DownLoad\\LiaoNing\\" +Id+"77.jpg",28,28);
		
		//图片转为行向量
		ImageDemo ImageDemo= new ImageDemo();
		ImageDemo.BinaryValue(Id+"1",Id+"55");
		ImageDemo.BinaryValue(Id+"2",Id+"66");
		ImageDemo.BinaryValue(Id+"3",Id+"77");
		
		
		String modelName1 = "D:\\DownLoad\\LiaoNing\\LiaoNing_model1.cnn";
		CNN cnn1 = CNN.loadModel(modelName1);	
		Dataset testset1 = Dataset.load("D:\\DownLoad\\LiaoNing\\"+Id+"1.format", ",", -1);
		cnn1.predict(testset1, "D:\\DownLoad\\LiaoNing\\"+Id+"test1.predict");
		
		String modelName2 = "D:\\DownLoad\\LiaoNing\\LiaoNing_model2.cnn";
		CNN cnn2 = CNN.loadModel(modelName2);	
		Dataset testset2 = Dataset.load("D:\\DownLoad\\LiaoNing\\"+Id+"2.format", ",", -1);
		cnn2.predict(testset2, "D:\\DownLoad\\LiaoNing\\"+Id+"test2.predict");
		
		String modelName3 = "D:\\DownLoad\\LiaoNing\\LiaoNing_model3.cnn";
		CNN cnn3 = CNN.loadModel(modelName3);	
		Dataset testset3 = Dataset.load("D:\\DownLoad\\LiaoNing\\"+Id+"3.format", ",", -1);
		cnn3.predict(testset3, "D:\\DownLoad\\LiaoNing\\"+Id+"test3.predict");
		
		
		
		int firstNumber=0;
		int secondNumber=0;
		int  result=0;
		File file1=new File("D:\\DownLoad\\LiaoNing\\"+Id+"test1.predict");
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
		
		File file3=new File("D:\\DownLoad\\LiaoNing\\"+Id+"test3.predict");
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
		File file2=new File("D:\\DownLoad\\LiaoNing\\"+Id+"test2.predict");
		if(file2.isFile() && file2.exists()){ //判断文件是否存在
        	
            InputStreamReader read1 = new InputStreamReader(
            new FileInputStream(file2),"GBK");//考虑到编码格式
            BufferedReader bufferedReader1 = new BufferedReader(read1);
            String lineTxt = null;
            
            while((lineTxt = bufferedReader1.readLine()) != null){
            	if(lineTxt.equals("1")){
            		result= firstNumber+secondNumber;
            		RecogniseResult.setOperatortype("+");
            		RecogniseResult.setResult(result);
            	}
            	if(lineTxt.equals("0")){
            		result=firstNumber*secondNumber;
            		RecogniseResult.setOperatortype("*");
            		RecogniseResult.setResult(result);
            	}
            	if(lineTxt.equals("2")){
            		result=firstNumber-secondNumber;
            		RecogniseResult.setOperatortype("-");
            		RecogniseResult.setResult(result);
            	}
            	}
            read1.close();
            }
		
		//System.out.println("reslut: "+result);
		LinkedList<String> deletefile = new LinkedList<String>();
		//deletefile.add(System.getProperty("user.dir") + "/picture/"+Id+"24.jpg");
		deletefile.add("D:\\DownLoad\\LiaoNing\\"+Id+"4" + ".jpg");
		deletefile.add("D:\\DownLoad\\LiaoNing\\"+Id+"5" + ".jpg");
		deletefile.add("D:\\DownLoad\\LiaoNing\\"+Id+"6" + ".jpg");
		deletefile.add("D:\\DownLoad\\LiaoNing\\"+Id+"7" + ".jpg");
		deletefile.add("D:\\DownLoad\\LiaoNing\\"+Id+"55" + ".jpg");
		deletefile.add("D:\\DownLoad\\LiaoNing\\" +Id+"66" + ".jpg");
		deletefile.add("D:\\DownLoad\\LiaoNing\\" +Id+"77" + ".jpg");
		deletefile.add("D:\\DownLoad\\LiaoNing\\"+Id+"1.format");
		deletefile.add("D:\\DownLoad\\LiaoNing\\"+Id+"2.format");
		deletefile.add("D:\\DownLoad\\LiaoNing\\"+Id+"3.format");
		deletefile.add("D:\\DownLoad\\LiaoNing\\"+Id+"test1.predict");
		deletefile.add("D:\\DownLoad\\LiaoNing\\"+Id+"test2.predict");
		deletefile.add("D:\\DownLoad\\LiaoNing\\"+Id+"test3.predict");
		for(int j=0;j<deletefile.size();j++){
			File file = new File(deletefile.get(j));  
			if (file.isFile() && file.exists()) {  
		        file.delete();  
		    } 
		}
		
		
		return RecogniseResult;
		
	}

}
