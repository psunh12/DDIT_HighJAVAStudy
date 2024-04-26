package kr.or.ddit.basic;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;

public class AddPage {

	public static void main(String args[]) throws Exception {
		// PDF 작성자 생성자 생성 및 경로 설정
		String file = "D:/D_Other/pdfTest/blank_page.pdf";
		PdfWriter writer = new PdfWriter(file);

		// PDF문서 생성
		PdfDocument pdfDoc = new PdfDocument(writer);

		// 빈 페이지 추가
		pdfDoc.addNewPage();

		// 문서 생성(document 개체 만들기)
		Document document = new Document(pdfDoc);

		// 문서 닫기
		document.close();
		System.out.println("PDF 생성 완료");
	}
}
