package kr.or.ddit.basic;

import java.io.IOException;

import org.apache.pdfbox.pdmodel.PDDocument;

public class pdfboxTest01 {

	public static void main(String[] args) throws IOException{
		//PDF 문서 객체 생성
		PDDocument document = new PDDocument();
		
		// 문서 저장 ==> 저장 후 출력문으로 확인
		document.save("d:/d_other/test.pdf");
		System.out.println("pdf파일이 생성되었습니다.");
		
		// 문서 닫기
		document.close();
	}

}
