package kr.or.ddit.gptstudy;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/*
 문제: 1부터 20까지의 숫자 중에서 홀수만을 요소로 갖는 HashSet을 생성하세요. 결과는 보여주지 않겠습니다.
 */
public class SetTest2 {

	public static void main(String[] args) {
		// 제네릭 쓰거나 안쓰거나 하기~!~~!
		Set<Integer> number=new HashSet<Integer>();
		
		for (int i = 1; i <=20; i++) {
			number.add(i);
		}
		
		System.out.println("전체 Set:"+number);
		
		
		Iterator<Integer> iter = number.iterator();
		
		while(iter.hasNext()) {
			int num= iter.next();
			if(num%2==0) iter.remove();
		}
		System.out.println("홀수 Set:"+number);
	}

}
