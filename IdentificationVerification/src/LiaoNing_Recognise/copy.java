package LiaoNing_Recognise;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

public class copy {
	public void copyFile(String oldPath,String newPath) {
		try {
		int bytesum = 0;
		int byteread = 0;
		File oldfile = new File(oldPath);
		if (oldfile.exists()) { //文件存在时
		InputStream inStream = new FileInputStream(oldPath); //读入原文件
		FileOutputStream fs = new FileOutputStream(newPath);
		byte[] buffer = new byte[1444];
		int length;
		while ( (byteread = inStream.read(buffer)) != -1) {
		bytesum += byteread; //字节数 文件大小
		//System.out.println(bytesum);
		fs.write(buffer, 0, byteread);
		}
		inStream.close();
		}
		}
		catch (Exception e) {
		//System.out.println("复制单个文件操作出错");
		e.printStackTrace();

		}

		}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/**
		* 复制单个文件
		* @param oldPath String 原文件路径 如：c:/fqf.txt
		* @param newPath String 复制后路径 如：f:/fqf.txt
		* @return boolean
		*/
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmssSSS");
		String str = sdf.format(date);
		copy copy = new copy();
		
		copy.copyFile("D:\\DownLoad\\original.jpg", "D:\\DownLoad\\wrong\\"+str+".jpg");
	}

}
