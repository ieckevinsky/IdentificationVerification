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
		test.binaryImage( "D:\\��֤��ѵ������\\liaoning\\suanshu\\" +i + ".jpg",   "D:\\��֤��ѵ������\\liaoning\\suanshu\\binary\\" +i + ".jpg" );
		//ͼ��ͶӰ����ƽ����ȷ���зֵ�
		//���� ��ֵ��ͼ��Ĵ洢·��
		LinkedList<Integer> colum3 = new LinkedList<Integer>();
		colum3=plotline.paint( "D:\\��֤��ѵ������\\liaoning\\suanshu\\binary\\" +i + ".jpg" );		
		//�з�ͼ��
		//���� ƽ����ͼ��ˮƽͶӰ�зֵ�linkedlist����
		plotthreePicture.splitpicture(colum3, "D:\\��֤��ѵ������\\liaoning\\suanshu\\binary\\" +i + ".jpg" , "D:\\��֤��ѵ������\\liaoning\\suanshu\\1\\" +i + ".jpg" , "D:\\��֤��ѵ������\\liaoning\\suanshu\\2\\" +i + ".jpg" , "D:\\��֤��ѵ������\\liaoning\\suanshu\\3\\" +i + ".jpg" );			
		//ͼƬ��С��һ��
		changePictureSize.resizeImage("D:\\��֤��ѵ������\\liaoning\\suanshu\\1\\" +i + ".jpg" ,"D:\\��֤��ѵ������\\liaoning\\suanshu\\1\\" +i + ".jpg" ,28,28);
		changePictureSize.resizeImage("D:\\��֤��ѵ������\\liaoning\\suanshu\\2\\" +i + ".jpg" ,"D:\\��֤��ѵ������\\liaoning\\suanshu\\2\\" +i + ".jpg" ,28,28);
		changePictureSize.resizeImage("D:\\��֤��ѵ������\\liaoning\\suanshu\\3\\" +i + ".jpg" ,"D:\\��֤��ѵ������\\liaoning\\suanshu\\3\\" +i + ".jpg" ,28,28);
		
		}
	}

}
