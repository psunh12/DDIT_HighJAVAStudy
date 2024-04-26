package kr.or.ddit.basic;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;

/*
	문제) 이름, 주소, 전화번호를 멤버로 갖는 Phone클래스를 만들고,
		Map을 이용하여 전화번호 정보를 관리하는 프로그램을 작성하시오.
		
		이 프로그램에는 아래의 메뉴가 있고 이 메뉴를 구현하시오.
		------------------------
		1. 전화번호 등록
		2. 전화번호 수정
		3. 전화번호 삭제
		4. 전화번호 검색
		5. 전화번호 전체 출력
		0. 프로그램 종료
		------------------------
		
		- 사용되는 Map의 구조는 key값으로 '등록될 사람의 이름'을 사용하고
		  value값으로는 'Phone클래스의 인스턴스'로 한다.
		  ( HashMap<String, Phone>  )
		  
		- 삭제, 검색 기능은 '이름'을 입력 받아 처리한다.
	
		- 추가 조건)
		1) '6. 전화번호 저장'메뉴를 추가하고 이 기능을 구현한다.
			( 저장파일명은 'phoneData.dat'로 한다. )
		2) 프로그램이 시작될 때 저장된 파일이 있으면 그 파일 데이터를 읽어와
		   Map에 저장한다.
		3) 프로그램을 종료할 때 Map의 데이터가 변경되거나 추가, 삭제되었으면
		   자동으로 저장한 후에 종료되도록 한다.
		   
		
		
		
		
	실행예시)
		------------------------
		1. 전화번호 등록
		2. 전화번호 수정
		3. 전화번호 삭제
		4. 전화번호 검색
		5. 전화번호 전체 출력
		0. 프로그램 종료
		------------------------
		작업번호 >> 1
		
		새롭게 등록할 전화번호 정보를 입력하세요.
		이 름 >> 홍길동
		전화번호 >> 010-1111-1111
		주 소 >> 대전시 중구 오류동
		
		'홍길동'의 전화번호 등록 완료!!
		 
		------------------------
		1. 전화번호 등록
		2. 전화번호 수정
		3. 전화번호 삭제
		4. 전화번호 검색
		5. 전화번호 전체 출력
		0. 프로그램 종료
		------------------------
		작업번호 >> 	1
		
		새롭게 등록할 전화번호 정보를 입력하세요.
		이 름 >> 홍길동
		
		'홍길동'은 이미 등록된 사람입니다.
		
		------------------------
		1. 전화번호 등록
		2. 전화번호 수정
		3. 전화번호 삭제
		4. 전화번호 검색
		5. 전화번호 전체 출력
		0. 프로그램 종료
		------------------------
		작업번호 >> 	5
		
		=============================================================
		번호     이 름     전화번호           주 소
		=============================================================
		 1      홍길동    010-1111-1111  대전시 중고 오류동
		~~~
		~~~
		=============================================================
		출력 완료...
		
		------------------------
		1. 전화번호 등록
		2. 전화번호 수정
		3. 전화번호 삭제
		4. 전화번호 검색
		5. 전화번호 전체 출력
		0. 프로그램 종료
		------------------------
		작업번호 >> 	0
		
		프로그램을 종료합니다...
	
*/
public class PhoneBookTest {
	private HashMap<String, Phone> phoneBookMap;
	private Scanner scan;
	
	// 저장 파일명 설정
	private final String fileName = "d:/d_other/phoneData.dat";
	
	private boolean dataChange;  // 데이터의 변경 사항이 있는지 여부를 나타내는 변수
	
	
	// 생성자
	public PhoneBookTest() {
//		phoneBookMap = new HashMap<String, Phone>();
		phoneBookMap = load();
		scan = new Scanner(System.in);
	}
	
	public static void main(String[] args) {
		new PhoneBookTest().phoneBookStart();
	}
	
	// 시작 메서드
	public void phoneBookStart() {
		System.out.println("**************************************");
		System.out.println("    전 화 번 호 관 리 프 로 그 램");
		System.out.println("**************************************");
		System.out.println();
		
		while(true) {
			int choice = displayMenu();
			switch(choice) {
				case 1 : 	// 등록
					insert(); break;
				case 2 : 	// 수정
					update(); break;
				case 3 : 	// 삭제
					delete(); break;
				case 4 : 	// 검색
					search(); break;
				case 5 : 	// 전체 출력
					displayAll(); break;
				case 6 : 	// 파일로 저장
					save(); break;
				case 0 : 	// 종료
					if(dataChange == true) {  // 데이터의 변경사항이 있으면...
						save();
					}
					System.out.println();
					System.out.println("프로그램을 종료합니다...");
					return;
				default : 
					System.out.println();
					System.out.println("작업 번호를 잘못 입력했습니다.");
					System.out.println("다시 선택하세요...");
					
			}
			
		}
		
	}
	
	// 전화번호 정보가 저장된 파일을 읽어와서 Map에 저장하여 반환하는 메서드
	private HashMap<String, Phone> load(){
		// 반환값이 저장될 변수 선언
		HashMap<String, Phone> pMap = new HashMap<String, Phone>();		
		
		File file = new File(fileName);
		if(!file.exists()) {   // 저장된 파일이 없으면...
			return pMap;
		}
		
		// 저장된 객체을 읽어올 입력용 스트림 객체 변수 선언
		ObjectInputStream oin = null;
		try {
			// 입력용 스트림 객체 생성
			oin = new ObjectInputStream(
					new BufferedInputStream(new FileInputStream(file)));
			
			// 입력용 스트림으로 데이터를 읽어와서 Map에 저장하기
			
			// 방법1)로 저장했을 때 처리하기
//			Object obj = null;  // 읽어온 데이터가 저장될 변수
//			
//			while( (obj = oin.readObject())  != null ) {
//				Phone p = (Phone) obj;
//				
//				pMap.put(p.getName(), p );  // 읽어온 자료를 Map에 추가하기
//			}
			//-------------------------------------------------
			
			// 방법2)로 저장했을 때 처리하기
			pMap = (HashMap<String, Phone>)oin.readObject();
			
			
//			System.out.println("읽기 작업 완료~~~");
			
		} catch (EOFException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			// 사용했던 스트림 닫기
			if(oin!=null) try { oin.close(); }catch(IOException e) {}
		}
		
		return pMap;
	}
	
	
	
	// 전화번호 정보를 파일로 저장하는 메서드
	private void save() {
		ObjectOutputStream oout = null;
		try {
			// 객체를 저장할 출력용 스트림 객체 생성
			oout = new ObjectOutputStream(
					new BufferedOutputStream(new FileOutputStream(fileName) ) );
			
			// Map에 저장되어 있는 데이터들을 파일로 저장한다.
			
			// 방법1) Map에 저장된 Phone객체를 하나씩 꺼내서 저장하기
//			for(String name : phoneBookMap.keySet()) {
//				Phone p = phoneBookMap.get(name);
//				oout.writeObject(p);
//			}
//			oout.writeObject(null);   // 저장된 데이터의 마지막은 null값을 저장한다.
			//-------------------------------------------------------
			
			// 방법2) Map객체 자체를 파일로 저장한다.
			oout.writeObject(phoneBookMap);
			
			
			System.out.println("저장이 완료 되었습니다...");
			
			dataChange = false;
			
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			// 사용했던 스트림 닫기
			if(oout!=null) try { oout.close(); }catch(IOException e) {}
		}
		
		
	}
	
	// 전화번호 정보를 검색하는 메서드
	private void search() {
		System.out.println();
		System.out.println("검색할 전화번호 정보를 입력하세요...");
		System.out.print("이 름 >> ");
		String name = scan.next();
		
		System.out.println();
		
		// 검색할 사람이 있는지 검사
		if(!phoneBookMap.containsKey(name)) {
			System.out.println(name + "씨의 전화번호 정보가 없습니다...");
			return;
		}
		
		Phone p = phoneBookMap.get(name);
		System.out.println(name + "씨의 전화번호 정보");
		System.out.println("-------------------------");
		System.out.println(" 이 름 : " + p.getName());
		System.out.println(" 전 화 : " + p.getTel());
		System.out.println(" 주 소 : " + p.getAddr());
		System.out.println("-------------------------");
		
	}
	
	
	// 전화번호 정보를 삭제하는 메서드
	private void delete() {
		System.out.println();
		System.out.println("삭제할 전화번호 정보를 입력하세요...");
		System.out.print("이 름 >> ");
		String name = scan.next();
		
		// 삭제할 사람이 있는지 검사
		if(!phoneBookMap.containsKey(name)) {
			System.out.println();
			System.out.println(name + "씨의 전화번호 정보가 없습니다...");
			return;
		}
		
		phoneBookMap.remove(name);
		
		System.out.println(name + "씨의 전화번호 정보를 삭제했습니다...");
		
		dataChange = true;
	}
	
	
	// 전화번호 정보를 수정하는 메서드
	private void update() {
		System.out.println();
		System.out.println("수정할 전화번호 정보를 입력하세요...");
		System.out.print("이 름 >> ");
		String name = scan.next();
		
		// 수정할 사람이 이미 등록되어 있는지 검사
		if(!phoneBookMap.containsKey(name)) {
			System.out.println();
			System.out.println(name + "씨의 전화번호 정보가 없습니다...");
			return;
		}
		
		System.out.print("새로운 전화번호 >> ");
		String newTel = scan.next();
		
		scan.nextLine();   // 입력 버퍼 비우기
		
		System.out.print("새로운 주소 >> ");
		String newAddr = scan.nextLine();
		
		// 수정 작업 ==> 같은 key값에 새로운 value값을 추가하면 된다.
		phoneBookMap.put(name, new Phone(name, newTel, newAddr));
		
		System.out.println(name + "씨의 전화번호 정보를 수정했습니다...");
		
		dataChange = true;
	}
	
	
	
	// 전체 자료를 출력하는 메서드
	private void displayAll() {
		System.out.println();
		System.out.println("=============================================================");
		System.out.println(" 번호     이 름     전화번호           주 소");
		System.out.println("=============================================================");
		
		if(phoneBookMap.size() == 0) {
			System.out.println("\t등록된 전화번호 정보가 하나도 없습니다.");
		}else {
			int num = 0;	// 번호 출력용
			
			Iterator<String> keyIt = phoneBookMap.keySet().iterator();
			while(keyIt.hasNext()) {
				num++;
				String key = keyIt.next();   		// key값(등록된 이름) 구하기
				Phone p = phoneBookMap.get(key);  	// value값(Phone객체) 구하기
				System.out.println(num + "\t" + p.getName() + "\t" 
										+ p.getTel() + "\t" + p.getAddr());
			}
			
		}
		System.out.println("=============================================================");
		System.out.println("출력 완료...");
		
	}
	
	
	
	// 새로운 전화번호 정보를 등록하는 메서드 
	// 이미 등록된 사람은 등록되지 않음 (동명이인 없음)
	private void insert() {
		System.out.println();
		System.out.println("새롭게 등록할 전화번호 정보를 입력하세요...");
		System.out.print("이 름 >> ");
		String name = scan.next();
		
		// 이미 등록된 사람인지 검사한다.
		if(phoneBookMap.containsKey(name)) {
			System.out.println(name + "씨는 이미 등록된 사람입니다...");
			return;
		}
		
		System.out.print("전화번호 >> ");
		String tel = scan.next();
		
		scan.nextLine();   // 입력 버퍼 비우기
		
		System.out.print("주 소 >> ");
		String addr = scan.nextLine();
		
//		Phone p = new Phone(name, tel, addr);
//		phoneBookMap.put(name, p);
		
		phoneBookMap.put(name, new Phone(name, tel, addr));
		
		System.out.println("'" + name + "'의 전화번호 등록 완료!!");
		
		dataChange = true;
		
	}
	
	// 메뉴를 출력하고 작업 번호를 입력 받아 반환하는 메서드
	private int displayMenu() {
		System.out.println();
		System.out.println("------------------------");
		System.out.println(" 1. 전화번호 등록");
		System.out.println(" 2. 전화번호 수정");
		System.out.println(" 3. 전화번호 삭제");
		System.out.println(" 4. 전화번호 검색");
		System.out.println(" 5. 전화번호 전체 출력");
		System.out.println(" 6. 전화번호 저장");
		System.out.println(" 0. 프로그램 종료");
		System.out.println("------------------------");
		System.out.print("작업번호 >> ");
		return scan.nextInt();
	}                       		
	

}


class Phone implements Serializable{
	private String name;
	private String tel;
	private String addr;
	
	public Phone() { }
	
	public Phone(String name, String tel, String addr) {
		this.name = name;
		this.tel = tel;
		this.addr = addr;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	@Override
	public String toString() {
		return "Phone [name=" + name + ", tel=" + tel + ", addr=" + addr + "]";
	}
	
}

















