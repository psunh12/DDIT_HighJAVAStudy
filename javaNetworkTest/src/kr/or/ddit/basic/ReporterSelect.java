package kr.or.ddit.basic;

import java.util.Arrays;
import java.util.Collections;

public class ReporterSelect {

	public static void main(String[] args) {
		String[][] members = {
			{"허주희", "이소망", "전희환", "이현장", "박은진"},	
			{"김건석", "이재훈", "박초원", "송인호"},			// 조요한
			{"정동균", "설국현", "공성배", "황지원", "최재형"},	
			{"한주희", "문준석", "오연지", "정지은", "전소희"},	
			{"박선혜", "신성우", "남의양", "강초록", "김동현"},	
			{"조한봄", "이근규", "양연주", "이규상"}				// 정민준
		};
		
		String[] reporters = new String[members.length];
		
		for(int i=0; i<members.length; i++) {
			int index = (int)(Math.random() * members[i].length);
			reporters[i] = members[i][index];
		}
		
		System.out.println("  === 발 표 자 ===");
		
		for(int i=0; i<reporters.length; i++) {
			System.out.println((i+1) + "조 발표자 : " + reporters[i]);
		}
		
		System.out.println();
		System.out.println();
		
		Collections.shuffle(Arrays.asList(reporters));
		
		System.out.println("발표 순서 : " + Arrays.toString(reporters));
		
		
		
		

	}

}
