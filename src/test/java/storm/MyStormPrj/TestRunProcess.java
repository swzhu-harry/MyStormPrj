package storm.MyStormPrj;

import java.io.IOException;

import org.junit.Test;

public class TestRunProcess {
	
	public static void main(String[] args) {
		try {
			Process pro = Runtime.getRuntime().exec("E:\\temp\\2017-6-14\\template\\package.bat");
			final int val = pro.waitFor();
			System.out.println("ִ����ɣ�����"+val);
		} catch (IOException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
