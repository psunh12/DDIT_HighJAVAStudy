package kr.or.ddit.basic;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;

public class ByteArrayIOTest01 {

	public static void main(String[] args) {
		byte[] inSrc = {0,1,2,3,4,5,6,7,8,9};
		byte[] outSrc = null;
		
		// 입력용 스트림 객체 생성
		ByteArrayInputStream input = new ByteArrayInputStream(inSrc);
		
		// 출력용 스트림 객체 생성
		ByteArrayOutputStream output = new ByteArrayOutputStream();
		
		int data; 		// 읽어온 자료가 저장될 변수
		
		// read()메서드 ==> 더 이상 읽어올 자료가 없으면 -1을 반환한다.
		while( (data = input.read()) != -1 ) {   // 자료 읽기...
			// 이 부분에서는 읽어온 자료를 처리하는 코드를 작성한다.
			output.write(data);   // 자료 출력...
		}
		
		// 스트림에 출력된 값들을 배열로 변환해서 가져오기
		outSrc = output.toByteArray();
		
		// 스트림을 모두 사용한 후에는 자원을 반납해 주어야 한다.
		// 반납하는 메서드는 close()이다.
		try {
			input.close();
			output.close();
			
		} catch (IOException e) {
			// TODO: handle exception
		}
		
		System.out.println(" inSrc ==> " + Arrays.toString(inSrc));
		System.out.println("outSrc ==> " + Arrays.toString(outSrc));
		
		
		

	}

}
