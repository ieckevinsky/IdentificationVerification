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
		if (oldfile.exists()) { //�ļ�����ʱ
		InputStream inStream = new FileInputStream(oldPath); //����ԭ�ļ�
		FileOutputStream fs = new FileOutputStream(newPath);
		byte[] buffer = new byte[1444];
		int length;
		while ( (byteread = inStream.read(buffer)) != -1) {
		bytesum += byteread; //�ֽ��� �ļ���С
		//System.out.println(bytesum);
		fs.write(buffer, 0, byteread);
		}
		inStream.close();
		}
		}
		catch (Exception e) {
		//System.out.println("���Ƶ����ļ���������");
		e.printStackTrace();

		}

		}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/**
		* ���Ƶ����ļ�
		* @param oldPath String ԭ�ļ�·�� �磺c:/fqf.txt
		* @param newPath String ���ƺ�·�� �磺f:/fqf.txt
		* @return boolean
		*/
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmssSSS");
		String str = sdf.format(date);
		copy copy = new copy();
		
		copy.copyFile("D:\\DownLoad\\original.jpg", "D:\\DownLoad\\wrong\\"+str+".jpg");
	}

}
