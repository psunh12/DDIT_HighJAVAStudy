package kr.or.ddit.basic;

import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class DataIOTest01 {

	public static void main(String[] args) {
		try {
			FileOutputStream fout = 
						new FileOutputStream("d:/d_other/test.dat");
			
			// 자료형 단위로 출력할 보조 스트림 객체 생성
			DataOutputStream dout =
						new DataOutputStream(fout);
			
			dout.writeInt(200);			// 정수형으로 데이터 출력
			dout.writeFloat(123.45f);	// 실수형으로 데이터 출력
			dout.writeBoolean(false);	// 논리형으로 데이터 출력
			dout.writeUTF("ABCDabcd");	// 문자열형식으로 데이터 출력
			
			System.out.println("출력 완료...");
			
			dout.close();		// 스트림 닫기
			
		} catch (IOException e) {
			// TODO: handle exception
		}

	}

}
