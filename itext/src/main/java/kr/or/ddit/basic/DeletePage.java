package kr.or.ddit.basic;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.*;

import java.io.FileOutputStream;
import java.io.IOException;

public class DeletePage {

    public static void main(String[] args) {
    	// 삭제 할 pdf가 있는 경로
        String sourceFilePath = "D:/D_Other/pdfTest/mergedFile.pdf";
        // 삭제 후 저장할 경로
        String destinationFilePath = "D:/D_Other/pdfTest/mergedFile_delete.pdf";
        // 삭제할 페이지
        int pageToRemove = 2;

        
        // try-catch를 사용하여 removePage 메서드를 호출하여
        // 삭제 성공하면 특정페이지 삭제 완료를 출력.
        try {
            removePage(sourceFilePath, destinationFilePath, pageToRemove);
            System.out.println("특정 페이지 삭제 완료");
        } catch (IOException e) {
        	e.printStackTrace();
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }

    
    // removePage메서드 정의
    public static void removePage(String sourceFilePath, String destinationFilePath, int pageToRemove)
            throws IOException, DocumentException {
    	// 원본PDF파일 읽어오기
        PdfReader reader = new PdfReader(sourceFilePath);
        // document 개체 만들기
        Document document = new Document();
        // 삭제 후 복사해서 새로운이름으로 저장
        PdfCopy copy = new PdfCopy(document, new FileOutputStream(destinationFilePath));
        document.open();

        // for 루프를 사용해 원본 PDF의 각 페이지를 새 문서에 추가.
        for (int i = 1; i <= reader.getNumberOfPages(); i++) {
            if (i != pageToRemove) {
                PdfImportedPage page = copy.getImportedPage(reader, i);
                copy.addPage(page);
            }
        }
        
        // 문서 닫기
        document.close();
        copy.close();
        reader.close();
    }
}
