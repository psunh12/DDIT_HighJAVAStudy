package kr.or.ddit.gptstudy;

import java.util.HashMap;

/*
 문제: '학생 이름'과 '점수'를 저장하는 Map을 생성하고, 여러 학생의 점수를 입력하세요.
 */
public class MapTest1 {

	public static void main(String[] args) {
		HashMap<String,Integer> map = new HashMap<String, Integer>();
		
		map.put("박은진",100);
		map.put("이은진",90);
		map.put("김은진",60);
		map.put("최은진",70);
		map.put("남궁은진",90);
		
		System.out.println(map);
	}

}
