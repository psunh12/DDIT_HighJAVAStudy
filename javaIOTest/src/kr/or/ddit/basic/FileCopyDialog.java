package kr.or.ddit.basic;

import java.awt.Panel;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

/*
문제)
	'D:/d_other' 폴더에 있는 '펭귄.jpg'파일을
	'D:/d_other/연습용'폴더에 '복사본_펭귄.jpg'파일로 
	복사하는 프로그램을 작성하시오.
*/
public class FileCopyDialog {

	public static void main(String[] args) {
		FileCopyDialog copy = new FileCopyDialog();
		
		
//		File file = new File("d:/d_other/펭귄.jpg");
		File file = copy.showDialog("OPEN");   // 원본 파일 선택
		
		File targetFile = copy.showDialog("SAVE"); // 대상 파일(저장할 파일) 선택
		
		if(file==null || targetFile==null) {
			System.out.println("원본 파일이나 대상 파일을 지정하지 않았습니다.");
			System.out.println("복사 작업을 중지합니다...");
			return;
		}
		
		
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
//			FileOutputStream fout = 
//						new FileOutputStream("d:/d_other/연습용/복사본_펭귄.jpg");
			FileOutputStream fout = new FileOutputStream(targetFile);
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
	
	// Dialog창을 나타내고 선택한 파일 정보를 갖는 File객체를 반환하는 메서드
	// option 매개변수는 "SAVE" 또는 "OPEN"값을 갖는다.
	public File showDialog(String option) {
		
		// SWING의 파일 열기 창, 파일 저장 창 만들기 연습
		JFileChooser chooser = new JFileChooser();
		
		// 선택할 파일의 확장자 설정
		FileNameExtensionFilter txt = 
				new FileNameExtensionFilter("텍스트문서(*.txt)", "txt");
		FileNameExtensionFilter img = 
				new FileNameExtensionFilter("이미지 파일", "png", "jpg", "gif");
		FileNameExtensionFilter doc = 
				new FileNameExtensionFilter("MS Word문서", new String[] {"doc", "docx"});
		FileNameExtensionFilter pdf = 
				new FileNameExtensionFilter("PDF문서", "pdf");
		
		chooser.addChoosableFileFilter(img);
		chooser.addChoosableFileFilter(doc);
		chooser.addChoosableFileFilter(txt);
		chooser.addChoosableFileFilter(pdf);
		
		chooser.setFileFilter(txt);  // 확장자 목록 중 처음부터 선택된 상태가 될 확장자 지정 
		
		// '모든 파일' 항목을 나타내거나 나타나지 않게 하기 
//				chooser.setAcceptAllFileFilterUsed(false);  // 나타나지 않음
		chooser.setAcceptAllFileFilterUsed(true);	// 나타남
		
		// Dialog창의 기본 위치(디렉토리 위치) 설정하기
		chooser.setCurrentDirectory(new File("d:/d_other"));
		
		int result;
		if("SAVE".equals(option.toUpperCase())) {
			result = chooser.showSaveDialog(new Panel()); // 저장용 창
		}else if("OPEN".equals(option.toUpperCase())) {
			result = chooser.showOpenDialog(new Panel()); // 열기용 창
		}else {
			System.out.println("option은 SAVE 또는 OPEN만 가능합니다..");
			return null;
		}
		
		File selectFile = null;
		// '저장' 또는 '열기' 버튼을 눌렀는지 여부 검사
		if(result == JFileChooser.APPROVE_OPTION) {  
			// 선택한 파일 정보를 갖는 File객체를 반환한다.
			selectFile = chooser.getSelectedFile();
		}
		
		return selectFile;
	}
	
	

}









