package kr.or.ddit.basic;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class BufferedIOTest01 {

	public static void main(String[] args) {
		// 입출력의 성능 향상을 위해서 Buffered스트림을 사용한다.
		try {
			FileOutputStream fout = new FileOutputStream("d:/d_other/bufferTest.txt");
			
			// 버퍼의 크기가 5byte인 버퍼 스트림 객체 생성
			// 버퍼의 크기를 지정하지 않으면 버퍼의 기본 크기는 8KB(8196byte)가 된다.
			BufferedOutputStream bout = new BufferedOutputStream(fout, 5);
			
			for(char c='1'; c<='9'; c++) {
				bout.write(c);
			}
			
			bout.flush();  	// 출력 작업이 완료되면 출력 버퍼에 남아 있는 데이터를
							// 강제로 출력해 주어야 한다.
			
			// 스트림 닫기
//			fout.close();
			bout.close();  	// 보조 스트림을 닫으면 기반이 되는 스트림도 자동으로 같이 닫힌다.
							// 버퍼 스트림의 close()메서드에는 flush기능이 내장되어 있다.
			
			System.out.println("작업 끝...");
			
		} catch (IOException e) {
			// TODO: handle exception
		}

	}

}
