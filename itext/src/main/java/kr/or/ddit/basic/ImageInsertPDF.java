package kr.or.ddit.basic;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfWriter;

public class ImageInsertPDF {    
 
    public static void main(String[] args) {
 
        // 1) com.lowagie.text.Document 클래스 인스턴스를 생성합니다.
        Document document = new Document();    
        File file = new File("D:/D_Other/pdfTest/image.pdf");
 
        try {
            // 2) Writer와 Document 사이의 연관을 맺어줍니다.
            PdfWriter.getInstance(document, new FileOutputStream(file));    
 
            // 3)  문서를 오픈합니다.
            document.open();
 
            // 4) 한글을 사용하기 위해 폰트를 설정해줍니다. 앞에서 충분히 다루었기 때문에 압축해서 한번에 표현했습니다.
            Font font = new Font(BaseFont.createFont("HYGoThic-Medium", "UniKS-UCS2-H", BaseFont.NOT_EMBEDDED), 20);
 
            // 5) 사용할 이미지 객체를 생성해줍니다. 
            Image jpeg = Image.getInstance("D:/D_Other/펭귄.jpg");
 
            // 6) 제가 선택한 이미지가 너무 커서 사이즈를 50%로 줄였습니다.
            jpeg.scalePercent(50);
 
            // 7) document에 텍스트와 이미지를 추가해줍니다.
            document.add(new Paragraph("\n\n", font));
            document.add(jpeg);
            
            System.out.println("이미지 삽입이 완료되었습니다.");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            document.close();    
        }
 
        // 8) Chrome 으로 방금 작성한 파일을 바로 열어서 확인해봅니다.
        String chrome = "C:/Program Files/Google/Chrome/Application/chrome.exe";
        try {
            new ProcessBuilder(chrome,file.getAbsolutePath()).start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}