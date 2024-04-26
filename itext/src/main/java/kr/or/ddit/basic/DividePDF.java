package kr.or.ddit.basic;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfCopy;
import com.itextpdf.text.pdf.PdfImportedPage;
import com.itextpdf.text.pdf.PdfReader;

public class DividePDF {

    public static void main(String... args) throws IOException, DocumentException {

        // 분할예정 파일
        String file = "D:/D_Other/pdfTest/mergedFile.pdf";
        PdfReader reader = new PdfReader(file);

        // 전체 페이지수 가져오기
        int n = reader.getNumberOfPages();
        System.out.println("페이지 수 : " + n);

        // 루프
        int i = 0;
        while (i < n){

            // 새로운 파일명 생성, 기존파일명+001,002.pdf 이렇게 붙는다.
            String destination = file.substring(0, file.indexOf(".pdf")) + "-" + String.format("%03d", i + 1) + ".pdf";
            System.out.println("Writing " + destination);

            // 새로운 Document 생성
            Document document = new Document(reader.getPageSizeWithRotation(1));

            // PdfCopy를 사용하여 새로운 파일에 페이지 추가
            PdfCopy copy = new PdfCopy(document, new FileOutputStream(destination));
            document.open();

         // 원본 페이지를 새로운 파일에 추가
            PdfImportedPage page = copy.getImportedPage(reader, ++i);
            copy.addPage(page);

         // 새로운 파일 저장 및 닫기
            document.close();
        }
    }
}
