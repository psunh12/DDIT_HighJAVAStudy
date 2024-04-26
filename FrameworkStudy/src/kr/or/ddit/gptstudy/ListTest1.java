package kr.or.ddit.gptstudy;

import java.util.ArrayList;
import java.util.List;

/*
 빈 ArrayList를 생성하고, 이 리스트에 1부터 5까지의 숫자를 추가한 후 모든 요소를 출력하세요.
 */

public class ListTest1 {
	public static void main(String[] args) {
		List<Integer> list = new ArrayList<>();
		for (int i = 1; i <=5 ; i++) {
			list.add(i);
		}
		System.out.println(list);
	}
}
