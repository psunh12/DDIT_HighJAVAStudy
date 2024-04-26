package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/*
	정렬과 관련된 interface는 Comparable, Comparator 이렇게 두 가지가 있다.
	
	Comparable은 Collection에 추가되는 데이터 자체에 정렬 기준을 넣고 싶을 때 구현하는 인터페이스이다.
	(이것을 내부 정렬 기준이라 한다.)
	
	Comparator은 외부에 별도로 정렬 기준을 구현하고 싶을 때 사용하는 인터페이스이다.
	(이것을 외부 정렬 기준이라 한다.)
	
	Comparable에서는 compareTo()메서드를 재정의해야 하고,
	Comparator에서는 compare()메서드를 재정의해야 한다.
	
	String클래스, Wrapper클래스, Date클래스, File클래스 등에는 이미 내부 정렬 기준이 구현되어 있다.
	(내부 정렬 기준은 오름차순으로 처리되도록 구현되어 있다.)

*/
public class ListSortTest01 {

	public static void main(String[] args) {
		ArrayList<String> list = new ArrayList<String>();
		
		list.add("일지매");
		list.add("홍길동");
		list.add("성춘향");
		list.add("변학도");
		list.add("강감찬");
		list.add("이순신");
		
		System.out.println("정렬전 >> " + list);
		
		// 정렬은 Collections.sort()메서드를 이용하여 정렬한다.
		// Collections.sort()메서드는 기본적으로 내부 정렬 기준(오름차순)으로 정렬한다.
		Collections.sort(list);
		
		System.out.println("정렬후 >> " + list);
		
		Collections.shuffle(list); 		// 자료 섞기
		System.out.println("자료 섞기 후 >> " + list);
		
		// 외부 정렬 기준 클래스를 지정해서 정렬하기
		Collections.sort(list, new Desc());
		
		System.out.println("내림차순 정렬후 >> " + list);

	}

}

//  111  222   99  444


// 외부 정렬 기준을 정해주는 class를 작성한다.
// Comparator인터페이스를 구현해서 작성해야 한다.
class Desc implements Comparator<String>{

	// compare()메서드를 이용하서 정렬하고자 하는 기준을 정해준다.
	
	// compare()메서드의 매개변수는 서로 인접한 데이터라고 생각하면 된다. 
	
	// compare()메서드의 반환값의 의미
	// 1) 반환값이 0  		==> 두 값이 같다.
	// 2) 반환값이 양수 		==> 앞, 뒤의 순서를 바꾼다.
	// 3) 반환값이 음수 		==> 앞, 뒤의 순서를 바꾸지 않는다.
	
	@Override
	public int compare(String str1, String str2) {
//		if(str1.compareTo(str2) > 0) {
//			return -1;
//		}else if(str1.compareTo(str2) < 0) {
//			return 1;
//		}else {
//			return 0;
//		}
		
		return str1.compareTo(str2) * -1;
	}
	
}











