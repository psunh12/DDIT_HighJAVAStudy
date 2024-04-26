package kr.or.ddit.basic;

import java.io.IOException;

import org.apache.pdfbox.pdmodel.PDDocument;

public class pdfboxTest02 {

	public static void main(String[] args) throws IOException{
		//PDF 문서 객체 생성
		PDDocument document = new PDDocument();
		
		// 페이지 추가
		PDPage blankPage = new PDPage(PDRectangle.A4);
		
		
		
		// 문서 저장 ==> 저장 후 출력문으로 확인
		document.save("C:\\Users\\선혜\\HighJava\\test.pdf");
		System.out.println("pdf파일이 생성되었습니다.");
		
		// 문서 닫기
		document.close();
	}

}