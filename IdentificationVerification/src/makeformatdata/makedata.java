package makeformatdata;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class makedata {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		FileInputStream fi=new FileInputStream("C:\\Users\\hylanda\\Desktop\\925.jpg ");
		BufferedInputStream in=new BufferedInputStream(fi);
		FileOutputStream fo=new FileOutputStream("C:\\Users\\hylanda\\Desktop\\16.jpg");
		BufferedOutputStream out=new BufferedOutputStream(fo);
		byte[] buf=new byte[784];
		int len=in.read(buf);
		//���ļ��������������ݷ��뵽buf�����У����ص��Ƕ����ĳ���
		while(len!=-1){
		out.write(buf, 0, len);
		len=in.read(buf);
		}
		out.close();
		fo.close();
		in.close();
		fi.close();
	}

}
