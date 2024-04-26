package kr.or.ddit.gptstudy;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/*
 문제:숫자 1부터 10까지를 요소로 갖는 HashSet을 생성하고,
 이 Set에 3의 배수를 모두 제거한 후 결과를 출력하세요.
 */
public class SetTest1 {

	public static void main(String[] args) {
		Set<Integer> set = new HashSet<>();
		
		for (int i = 1; i <=10; i++) {
			set.add(i);
		}
		
		// set에는 for문을 사용하여 삭제하는걸 권장하지 않음.
		
//		for (int i = 0; i <set.size(); i++) {
//			if(i%3==0) set.remove(i);	
//		}
		
		// set 데이터를 Iterator로 변환한다
		Iterator<Integer> iterator =set.iterator();
		
		while(iterator.hasNext()) {
			int num = iterator.next();
			if(num%3==0) iterator.remove();
		}
		System.out.println("3의 배수를 제거함:"+set);
	}

}
