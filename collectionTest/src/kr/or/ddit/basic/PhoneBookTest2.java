package kr.or.ddit.basic;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

/*
       문제) 이름, 주소, 전화번호를 멤버로 갖는 Phone클래스를 만들고, 
          Map을 이용하여 전화번호 정보를 관리하는 프로그램을 작성하시오.

          이 프로그램에는 아래의 메뉴가 있고 이 메뉴를 구현하시오.
          ----------------
          1.전화번호 등록
          2.전화번호 수정
          3.전화번호 삭제
          4.전화번호 검색
          5.전화번호 전체 출력
          0.프로그램 종료
          ----------------

         - 사용되는 Map의 구조는 key값으로  '등록될 사람의 이름'을 사용하고
          value값으로는 'Phone클래스의 인스턴스'로 한다.
          (HashMap<String, Phone>)-제네릭 이렇게 쓰쇼 
         - 삭제,검색 기능은 '이름'을 입력받아 처리한다.

         실행예시)
          ----------------
          1.전화번호 등록
          2.전화번호 수정
          3.전화번호 삭제
          4.전화번호 검색
          5.전화번호 전체 출력
          0.프로그램 종료
          ----------------
          작업번호 >> 1

          새롭게 등록할 전화번호 정보를 입력하세요.
          이 름 >> 홍길동
          전화번호 >> 010-1111-1111
          주 소 >> 대전시 중구 오류동

          '홍길동'의 전화번호 등록 완료!!

          ----------------
          1.전화번호 등록
          2.전화번호 수정
          3.전화번호 삭제
          4.전화번호 검색
          5.전화번호 전체 출력
          0.프로그램 종료
          ----------------
          작업번호 >> 1

          새롭게 등록할 전화번호 정보를 입력하세요.
          이 름 >> 홍길동

          '홍길동'은 이미 등록된 사람입니다.

          ----------------
          1.전화번호 등록
          2.전화번호 수정
          3.전화번호 삭제
          4.전화번호 검색
          5.전화번호 전체 출력
          0.프로그램 종료
          ----------------
          작업번호 >> 5

          ==========================================
        	 번호          이름                  전화번호                       주소 
          ==========================================
           1          홍길동        010-1111-1111    대전시 중구 오류동
           ~~~
           ~~~
          ==========================================
           출력 완료...


          ----------------
          1.전화번호 등록
          2.전화번호 수정
          3.전화번호 삭제
          4.전화번호 검색
          5.전화번호 전체 출력
          0.프로그램 종료
          ----------------
          작업번호 >>0

          프로그램을 종료합니다...
 */

public class PhoneBookTest2 {
	private HashMap<String, Phone> phoneBookMap;
	private Scanner scan;

	public PhoneBookTest2() {
		HashMap<String, Phone> phoneBookMap = new HashMap<String, Phone>();
		scan = new Scanner(System.in);
	}

	public static void main(String[] args) {
		new PhoneBookTest2().phoneBookStart();

	}

	// 시작 메서드
	public void phoneBookStart() {
		System.out.println("******************************************");
		System.out.println("/t/t 전 화 번 호 관 리 프 로 그 램");
		System.out.println("******************************************");

		while (true) {
			int choice = displayMenu();
			switch (choice) {
			case 1: // 등록
				insert();
				break;
			case 2: // 수정
				update();
				break;
			case 3:
				delete();
				break;// 삭제
			case 4:
				search();
				break;// 검색
			case 5:
				displayAll();
				break;// 전체출력
			case 0:
				System.out.println();
				System.out.println("프로그램을 종료합니다...");

				return;// 종료
			default:
				System.out.println();
				System.out.println("작업 번호를 잘못 입력했습니다.");
				System.out.println("다시 선택하세요...");

			}
		}
	}

	// 메뉴를 출력하고 작업번호를 입력받아 반환하는 메서드
	private int displayMenu() {
		System.out.println("-------------------------");
		System.out.println("1. 전화번호 등록");
		System.out.println("2. 전화번호 수정");
		System.out.println("3. 전화번호 삭제");
		System.out.println("4. 전화번호 검색");
		System.out.println("5. 전화번호 전체 출력");
		System.out.println("0. 프로그램 종료");
		System.out.println("-------------------------");
		System.out.print("작업번호 >> ");

		return scan.nextInt();

	}

	// 전화번호 정보를 검색하는 메서드
	private void search() {
		System.out.println();
		System.out.print("이름 >>");
		String name = scan.next();

		// 검색할 사람이 있는지 검사
		if (!phoneBookMap.containsKey(name)) {
			System.out.println(name + "씨의 전화번호 정보가 없습니다...");
			return;
		}
		Phone p = phoneBookMap.get(name);
		System.out.println(name + "씨의 전화번호 정보");
		System.out.println("-------------------------");
		System.out.println("이 름 : " + p.getName());
		System.out.println("전 화 : " + p.getTel());
		System.out.println("주 소 : " + p.getAddr());
		System.out.println("-------------------------");

	}

	// 전화번호 정보를 삭제하는 메서드
	private void delete() {
		System.out.println();
		System.out.println("삭제할 전화번호 정보를 입력하세요...");
		System.out.print("이름 >>");
		String name = scan.next();

		if (!phoneBookMap.containsKey(name)) {// 수정할 사람이 없다.
			System.out.println();
			System.out.println(name + "씨의 전화번호 정보가 없습니다...");
			return;
		}
		phoneBookMap.remove(name);

		System.out.println(name + "씨의 전화번호 정보를 삭제했습니다...");

	}

	// 전화번호 정보를 수정하는 메서드
	private void update() {
		System.out.println();
		System.out.println("수정할 전화번호 정보를 입력하세요...");
		System.out.println("이 름 >> ");
		String name = scan.next();

		// 수정할 사람이 이미 등록되어 있는지 검사
		if (!phoneBookMap.containsKey(name)) {// 수정할 사람이 없다.
			System.out.println();
			System.out.println(name + "씨의 전화번호 정보가 없습니다...");
			return;
		}
		System.out.println("새로운 전화번호 >> ");
		String newTel = scan.next();

		scan.nextLine();// 입력버퍼 비우기
		System.out.println("새로운 주소 >> ");
		String newAddr = scan.nextLine();

		// 수정 작업 ==> 같은 key값에 새로운 value값을 추가하면 된다.
		phoneBookMap.put(name, new Phone(name, newTel, newAddr));

		System.out.println(name + "씨의 전화번호 정보를 수정했습니다...");
	}

	// 전체 자료를 출력하는 메서드
	private void displayAll() {
		System.out.println("==========================");
		System.out.println("번호\t이름\t전화번호\t주소");
		System.out.println("==========================");

		if (phoneBookMap.size() == 0) {
			System.out.println("\t등록된 전화번호 정보가 하나도 없습니다.");
		} else {
			int num = 0; // 번호 출력용

			Iterator<String> keyIt = phoneBookMap.keySet().iterator();
			while (keyIt.hasNext()) {
				num++;
				String key = keyIt.next(); // key값(등록된 이름) 구하기
				Phone p = phoneBookMap.get(key); // value값(Phone 객체) 구하기
				System.out.println(num + "/t" + p.getName() + "\t" + p.getTel() + "\t" + p.getAddr());
			}

		}

		System.out.println("==========================");
		System.out.println("출력 완료...");

	}

	// 새로운 전화번호 정보를 등록하는 메서드
	// 이미 등록된 사람은 등록되지 않음 (동명이인 없음)
	private void insert() {
		System.out.println();
		System.out.println("새롭게 등록할 전화번호 정보를 입력하세요...");
		System.out.println("이  름 >> ");
		String name = scan.next();

		// 이미 등록된 사람인지 검사한다.
		if (phoneBookMap.containsKey(name)) {
			System.out.println(name + "씨는 이미 등록된 사람입니다.");
			return;
		}
		System.out.print("전화번호 >> ");
		String tel = scan.next();

		
		// next만 입력하면 공백있으면 오류나고 nextLine을 사용하면 공백으로 넘어가버림
		scan.nextLine();// 띄어쓰기 한 값이 들어가도록 처리함.(입력 버퍼 비우기)
		System.out.print("주소  >> ");
		String addr = scan.nextLine();

//	   Phone p =new Phone(name, tel, addr);
//	   phoneBookMap.put(name, p);

		phoneBookMap.put(name, new Phone(name, tel, addr));

		System.out.println("'" + name + "'의 전화번호 등록 완료!!");

	}

	class Phone {
		private String name;
		private String tel;
		private String addr;

		public Phone() {
		}

		public Phone(String name, String tel, String addr) {
			super();
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

	}

}