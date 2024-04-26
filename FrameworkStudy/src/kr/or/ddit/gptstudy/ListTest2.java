package kr.or.ddit.gptstudy;

import java.util.ArrayList;
import java.util.List;

/*
	 문제:정수형(Integer)을 요소로 갖는 ArrayList를 생성하고, 다음 조건에 따라 요소를 추가하고 출력하세요.
	
	1.리스트에 1부터 10까지의 숫자를 추가합니다.
	2.리스트의 크기를 출력합니다.
	3.리스트에 15라는 값을 첫 번째 위치(index 0)에 추가합니다.
	4.리스트에서 3번째(index 2) 위치의 값을 제거합니다.
	5.변경된 리스트의 내용을 출력합니다. (+반복문 사용?)
 */

public class ListTest2 {

	public static void main(String[] args) {
		List<Integer> list = new ArrayList<>();
	
		for (int i = 1; i <=10; i++) {
			list.add(i);
		}
		System.out.println("list의 크기 : "+list.size());
		
		list.add(0,15);
		list.remove(2);
		
		for (int i = 0; i < list.size(); i++) {
			System.out.print(list.get(i)+" ");
		}
		
//		System.out.println("변경된 list :"+list);
		
	}

}
