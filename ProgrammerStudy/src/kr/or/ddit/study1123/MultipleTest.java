package kr.or.ddit.study1123;

import java.util.ArrayList;
import java.util.List;

/*
 	정수 n과 정수 배열 numlist가 매개변수로 주어질 때,
 	numlist에서 n의 배수가 아닌 수들을 제거한 배열을 return하도록 solution 함수를 완성해주세요.
 */
public class MultipleTest {
    public int[] solution(int n, int[] numlist) {
        List<Integer> list= new ArrayList();

        // list에 저장
        for (int i = 0; i < numlist.length; i++) {
			if(numlist[i]%n==0) list.add(numlist[i]);
		}
        
        int[] answer = new int[list.size()];
        // list값 배열에 다시저장~~
        for (int i = 0; i < list.size(); i++) {
			answer[i]=list.get(i);
		}
        return answer;
    }
}
