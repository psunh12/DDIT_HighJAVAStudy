package kr.or.ddit.basic;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/*
문제)
	'D:/d_other' 폴더에 있는 '펭귄.jpg'파일을
	'D:/d_other/연습용'폴더에 '복사본_펭귄.jpg'파일로 
	복사하는 프로그램을 작성하시오.
*/
public class FileCopy {

	public static void main(String[] args) {
		File file = new File("d:/d_other/펭귄.jpg");
		
		if(!file.exists()) {
			System.out.println(file.getPath() + " 파일이 없습니다.");
			System.out.println("복사 작업을 중지합니다...");
			return;
		}
		
		try {
			// 복사할 원본 파일을 처리할 스트림 객체 생성
			FileInputStream fin = new FileInputStream(file);
			BufferedInputStream bin = new BufferedInputStream(fin);
			
			// 복사될 대상 파일을 처리할 스트림 객체 생성
			FileOutputStream fout = 
						new FileOutputStream("d:/d_other/연습용/복사본_펭귄.jpg");
			BufferedOutputStream bout = new BufferedOutputStream(fout);
			
			
			System.out.println("복사 시작...");
			
			int data; 
			
//			while( (data = fin.read()) != -1 ) {
//				fout.write(data);
//			}
//			
//			fout.flush();
//			
//			
//			// 스트림 닫기
//			fin.close();
//			fout.close();
			
			
			// 버퍼 스트림을 이용한 처리
			while( (data = bin.read()) != -1 ) {
				bout.write(data);
			}
			
			bout.flush();
			
			
			// 스트림 닫기
			bin.close();
			bout.close();
			
			System.out.println("복사 작업 완료...");
			
		} catch (IOException e) {
			// TODO: handle exception
		}
		

	}

}









