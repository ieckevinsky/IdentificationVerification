package makeformatdata;

import java.io.IOException;

public class test {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		Recognise Recognise =new Recognise();
		RecogniseResult RecogniseResult = new RecogniseResult();
		//for(int i=1;i<101;i++){
			RecogniseResult=Recognise.RecogniseProcess("D:\\验证码训练数据\\tianjin\\"+555+".jpg");
			System.out.println("firstnumber: "+RecogniseResult.getFirst());
			System.out.println("operator: "+RecogniseResult.getOperatortype());
			System.out.println("secondnumber: "+RecogniseResult.getSecond());
			System.out.println("result: "+RecogniseResult.getResult());
			
			
		//}
		System.exit(0);
	}

}
