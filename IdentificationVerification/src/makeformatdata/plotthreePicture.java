package makeformatdata;

import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;

import javax.imageio.ImageIO;

public class plotthreePicture {

	//public static void main(String[] args) throws IOException {
	public static void splitpicture(LinkedList<Integer> list,String imgpath,String outputpath1,String outputpath2,String outputpath3) throws IOException {
		// TODO Auto-generated method stub
		LinkedList<Integer> colum3 = new LinkedList<Integer>();
		colum3.add(26);
		colum3.add(66);
		colum3.add(90);
		//File file = new File(System.getProperty("user.dir")+"/picture/4.jpg");
		File file = new File(imgpath);
		
		File file1 = new File("D:\\DownLoad\\" + "11" + ".jpg");
		//File file1 = new File(System.getProperty("user.dir")+"/picture/11.jpg");
		BufferedImage bufferedImage1 =ImageIO.read(file1);
		BufferedImage bufferedImage2 =ImageIO.read(file1);
		BufferedImage bufferedImage3 =ImageIO.read(file1);
		BufferedImage bufferedImage = ImageIO.read(file);   
	    int width1 = bufferedImage.getWidth();   
	    int height1 = bufferedImage.getHeight();   
	    int i=0;    
	    for( int k=0;k<colum3.size();k++){
	    	
	    	Integer sq[][]=new Integer[colum3.get(k)][height1]; 
	    	for(i=i;i<colum3.get(k);i++){
	    		//System.out.println(i);
	    		for(int j=0;j<height1;j++){
	    			
	    			sq[i][j]=bufferedImage.getRGB(i, j);
	    			//System.out.print(sq[i][j]);
	    			if(k==0){
	    			bufferedImage1.setRGB(i, j, sq[i][j]);
	    			}
	    			if(k==1){
		    			bufferedImage2.setRGB(i-colum3.get(k-1), j, sq[i][j]);
		    			}
	    			if(k==2){
		    			bufferedImage3.setRGB(i-colum3.get(k-1), j, sq[i][j]);
		    			}
	    		}	    		
	    	}  	
	    	if(k==0){
	    		//ImageIO.write(bufferedImage1, "jpg", new File(System.getProperty("user.dir")+"/picture/5.jpg")); 
	    		ImageIO.write(bufferedImage1, "jpg", new File(outputpath1)); 
			}
	    	if(k==1){
	    		//ImageIO.write(bufferedImage2, "jpg", new File(System.getProperty("user.dir")+"/picture/6.jpg")); 
	    		ImageIO.write(bufferedImage2, "jpg", new File(outputpath2)); 
			}
	    	if(k==2){
	    		//ImageIO.write(bufferedImage3, "jpg", new File(System.getProperty("user.dir")+"/picture/7.jpg")); 
	    		ImageIO.write(bufferedImage3, "jpg", new File(outputpath3)); 
			}
	    	i=colum3.get(k);

	    }

	    	
	    	
	}

}
