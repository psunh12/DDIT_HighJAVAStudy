 package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class HotelTest2 {
	private Map<Integer, Room> hotelMap;
	private Scanner scan;


	/*
	- 201~209 : 싱글룸
	- 301~309 : 더블룸
	- 401~409 : 스위트룸
	 */
	

	public HotelTest2() {
		
		hotelMap = new HashMap<Integer, Room>();
		scan= new Scanner(System.in);
		
		// 객실 초기화
		
		for(int i=2;i<=4;i++) {
			String type = null;
			
			switch(i) {
				case 2: type="싱글룸"; break;
				case 3: type="더블룸"; break;
				case 4: type="스위트룸"; break;
			}
			
			for(int j=1;j<=9;j++) {
				int num= i*100+j;
//				Room room = new Room(num, type);
//				hotelMap.put(num, room);
				hotelMap.put(num, new Room(num, type));
			
		}
	}
}

	
	public static void main(String[] args) {
		new HotelTest2().hotelStart();

	}
	
	// 시작 메서드
	public void hotelStart() {
		System.out.println();
		System.out.println("*********************************************"); 
		System.out.println("\t호텔문을 열었습니다. 어서오십시요.");
		System.out.println("*********************************************"); 
		System.out.println();

		while (true) {
			int choice = displayMenu();
			switch (choice) {
			case 1 : // 체크인
				checkIn(); break;
			case 2 : // 체크아웃
				checkOut();
				break;
			case 3 : // 객실상태
				displayRoomState(); break;
			case 4 : // 업무종료
				System.out.println();
				System.out.println("*********************************************"); 
				System.out.println("\t호텔문을 닫았습니다.");
				System.out.println("*********************************************"); 
				return;

			default:
				System.out.println("작업 번호를 잘못 입력했습니다.");
				System.out.println("다시 선택하세요...");
	
			}
		}
	}
	

	// 체크아웃 메서드
	   private void checkOut() {
	      System.out.println("----------------------------------------------");
	      System.out.println("      체크아웃 작업");
	      System.out.println("----------------------------------------------");
	      System.out.println("체크아웃 할 방 번호를 입력하세요.");
	      System.out.println("방 번호 입력 >> ");
	      int num = scan.nextInt();

	      // 입력한 방번호가 존재하는지 여부 검사 (Map의 key값에 방번호가 있는지 검사)
	      if(!hotelMap.containsKey(num)) {
	         System.out.println(num+"호 객실은 존재하지 않습니다.");
	         return;
	      }

	      // 해당 객실에 손님이 없는지 여부검사
	      if(hotelMap.get(num).getGuestName() ==null) {
	         System.out.println(num+"호 객실은 이미 손님이 없습니다.");
	         return;
	      }

	      //체크아웃 작업은 해당 객실의 투숙객 이름을 null로 변경하는 것을 말한다.
	      String name = hotelMap.get(num).getGuestName();   //현재 투숙객 이름 구하기
	      hotelMap.get(num).setGuestName(null);

	      System.out.println(num + "호 객실의 " + name + "님 체크아웃을 완료하였습니다.");


	   }

	
	
	// 객실 상태 출력
	private void displayRoomState() {
		
		// 방번호를 순서대로 나오게 하기
		//		==> Map에서 방번호(key값)를 꺼내서 List에 넣은 후 정렬하여 사용한다.
		ArrayList<Integer> roomNumList=new ArrayList<Integer>(hotelMap.keySet());
		
		Collections.sort(roomNumList);	// 방번호의 오름차순 정렬

		System.out.println();
		System.out.println("----------------------------------------------");
		System.out.println("	   현재 객실 상태");
		System.out.println("----------------------------------------------");
		System.out.println("방 번호\t\t방 종류\t\t투숙객 이름");
		System.out.println("----------------------------------------------");
		
		// List에서 방번호를 하나씩 꺼내와
		// Map에서 해당 방번호와 일치하는 Room객체를 구해서 출력한다.
		for (int num : roomNumList) {
			Room r = hotelMap.get(num);
//			System.out.println(num);					// 방번호 출력
			System.out.print(r.getRoomNum()+"\t");	// 방번호 출력
			System.out.print(r.getRoomType()+"\t");	// 방종류 출력 출력
			System.out.println(r.getGuestName()==null ? " -":r.getGuestName());
		}
		System.out.println("----------------------------------------------");
		System.out.println();
	}
	
	// 체크인 메서드
	private void checkIn() {
		System.out.println("----------------------------------------------");
		System.out.println("	   체크인 작업");
		System.out.println("----------------------------------------------");
		System.out.println("* 201~209 : 싱글룸");
		System.out.println("* 301~309 : 더블룸");
		System.out.println("* 401~409 : 스위트룸");
		System.out.println("----------------------------------------------");
		System.out.println("방 번호 입력 >> ");
		
		int num=scan.nextInt();

		// 입력한 방번호가 존재하는지 여부 검사 (Map의 key값에 방번호가 있는지 검사)
		if(!hotelMap.containsKey(num)) {
			System.out.println(num+"호 객실은 존재하지 않습니다.");
			return;
		}
		
		// 해당 객실에 손님이 이미 있는지 여부 검사(Map에 방번호가 있다!)
		if(hotelMap.get(num).getGuestName() !=null) {
			System.out.println(num+"호 객실은 이미 손님이 있습니다.");
			return;
		}
		System.out.println("누구를 체크인 하시겠습니까?");
		System.out.print("이름 입력 >> ");
		String name=scan.next();
		
		// 입력 받은 투숙객 이름을 해당 객실의 guestName변수에 저장한다.
		hotelMap.get(num).setGuestName(name);
		
		System.out.println(num+"호 객실에 "+ name+ "님의 체크인이 완료되었습니다.");
	
	}
	
	
	
	
	// 메뉴 출력 및 작업 번호를 반환하는 메서드
	public int displayMenu() {
		
		System.out.println();
		System.out.println("-----------------------------------------------------------");
		System.out.println("	어떤 업무를 하시겠습니까?");
		System.out.println("1. 체크인    2. 체크아웃    3. 객실상태    4. 업무종료");
		System.out.println("-----------------------------------------------------------");
		System.out.print("선택>> ");
		return scan.nextInt();
	}

}

// Room클래스는 방번호(int), 방종류, 투숙객 이름 필드로 구성
class Room{
	private int roomNum;
	private String roomType;
	private String guestName;
	
	// 생성자
	public Room() {}
	
	public Room(int roomNum, String roomType) {
		super();
		this.roomNum = roomNum;
		this.roomType = roomType;
	}

	public int getRoomNum() {
		return roomNum;
	}

	public void setRoomNum(int roomNum) {
		this.roomNum = roomNum;
	}

	public String getRoomType() {
		return roomType;
	}

	public void setRoomType(String roomType) {
		this.roomType = roomType;
	}

	public String getGuestName() {
		return guestName;
	}

	public void setGuestName(String guestName) {
		this.guestName = guestName;
	}

	@Override
	public String toString() {
		return "Room [roomNum=" + roomNum + ", roomType=" + roomType + ", guestName=" + guestName + "]";
	}
	
	
	
	
}