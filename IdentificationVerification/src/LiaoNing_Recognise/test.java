package LiaoNing_Recognise;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;

public class test {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		Recognise Recognise =new Recognise();
		RecogniseResult RecogniseResult = new RecogniseResult();
		//for(int i=1;i<101;i++){
		//	String s1=String.valueOf(i+999);
			RecogniseResult=Recognise.RecogniseProcess("D:\\��֤��ѵ������\\liaoning\\suanshu\\"+7787+".jpg","sdsd");  // s1 ΪͼƬId����������Ϊ��ĸ+�̺߳ţ�
			System.out.println("firstnumber: "+RecogniseResult.getFirst());
			System.out.println("operator: "+RecogniseResult.getOperatortype());
			System.out.println("secondnumber: "+RecogniseResult.getSecond());
			System.out.println("result: "+RecogniseResult.getResult());
		//}
		System.exit(0);
	}

}
