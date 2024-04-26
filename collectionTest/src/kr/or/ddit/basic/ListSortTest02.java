package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class ListSortTest02 {

	public static void main(String[] args) {
		ArrayList<Member> memList = new ArrayList<Member>();
		
		memList.add(new Member(1, "홍길동", "010-1111-1111"));
		memList.add(new Member(5, "이순신", "010-2222-1111"));
		memList.add(new Member(9, "성춘향", "010-3333-1111"));
		memList.add(new Member(3, "강감찬", "010-4444-1111"));
		memList.add(new Member(6, "일지매", "010-5555-1111"));
		memList.add(new Member(2, "변학도", "010-6666-1111"));
		
		System.out.println("정렬전...");
		for(Member mem : memList) {
			System.out.println(mem);
		}
		System.out.println("------------------------------------------------");
		
		Collections.sort(memList);
		
		System.out.println("정렬후...");
		for(Member mem : memList) {
			System.out.println(mem);
		}
		System.out.println("------------------------------------------------");
		
		// Member의 name값으로 오름차순 정렬하는 외부 정렬 기준 클래스를 이용하여 정렬하시오.
		// 클래스명 : SortNameAsc
		Collections.sort(memList, new SortNameAsc());
		
		System.out.println("정렬후...");
		for(Member mem : memList) {
			System.out.println(mem);
		}
		System.out.println("------------------------------------------------");
		
		
	}

}

// 회원 정보를 저장하는 클래스
// 회원번호의 오름차순으로 정렬하는 내부 정렬 기준을 지정하여 작성해 보자
class Member implements Comparable<Member>{
	private int num;		// 회원번호
	private String name;	// 회원이름
	private String tel;		// 전화번호
	
	// 생성자
	public Member(int num, String name, String tel) {
		super();
		this.num = num;
		this.name = name;
		this.tel = tel;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
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

	@Override
	public String toString() {
		return "Member [num=" + num + ", name=" + name + ", tel=" + tel + "]";
	}

	// 회원번호의 오름차순
	@Override
	public int compareTo(Member mem) {
		if(this.num > mem.getNum()) {
			return 1;		//  양수를 반환하면 두 값의 순서가 바뀐다.
		}else if(this.num < mem.getNum()) {
			return -1;
		}else {
			return 0;
		}
	}
	
	
}



class SortNameAsc implements Comparator<Member>{
	// Member의 name값으로 오름차순 정렬 
	@Override
	public int compare(Member mem1, Member mem2) {
//		if(mem1.getName().compareTo( mem2.getName() ) > 0) {
//			return 1;
//		}else if(mem1.getName().compareTo( mem2.getName() ) < 0) {
//			return -1;
//		}else {
//			return 0;
//		}
		return mem1.getName().compareTo( mem2.getName() );
	}
	
}

















