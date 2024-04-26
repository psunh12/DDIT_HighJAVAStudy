package kr.or.ddit.basic;

import java.io.IOException;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.PdfWriter;



public class combinePDF {

    public static void main(String[] args) {
    	// 병합 할 파일경로
        String[] filesToMerge = {
            "D:/D_Other/pdfTest/image.pdf", // 첫 번째 파일 경로
            "D:/D_Other/pdfTest/hangule.pdf", // 두 번째 파일 경로
        };       
        // 병합 후 파일 경로
        String mergedFilePath = "D:/D_Other/pdfTest/mergedFile.pdf";

        try {
            mergePDFs(filesToMerge, mergedFilePath);
            System.out.println("PDF 파일이 성공적으로 병합되었습니다.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void mergePDFs(String[] filepaths, String mergedPdfPath) throws IOException {
        // 새로운 PDF파일을 작성
    	PdfWriter writer = new PdfWriter(mergedPdfPath);
    	// PdfDocument 객체를 만들어 PdfWriter로 초기화
        PdfDocument pdfDoc = new PdfDocument(writer);
        
        // for 루프를 통해 페이지를 새로운 PdfDocument에 복사
        for (String filePath : filepaths) {
        	// PdfReader 를 사용하여 현재파일을 읽고
            PdfReader reader = new PdfReader(filePath);
            // PdfDocument에 현재 파일의 페이지를 복사한다
            PdfDocument sourcePdf = new PdfDocument(reader);
            
            // (복사를 시작할 페이지 번호, 복사할 페이지 수, 복사될 대상)
            sourcePdf.copyPagesTo(1, sourcePdf.getNumberOfPages(), pdfDoc);
            sourcePdf.close();
        }

        pdfDoc.close();
    }
}