package kr.or.ddit.basic;

import javax.swing.JOptionPane;

public class ThreadTest07_1 {
	public static boolean inputCheck;	// 입력 완료 여부를 나타내는 변수 선언
	
	public static void main(String[] args) {
		GameCounter gc = new GameCounter();
		
		// 난수를 이용하여 컴퓨터의 가위 바위 보 정하기
		String[] data = {"가위", "바위", "보"};
		int index = (int)(Math.random()*3);	// 0 ~ 2 사이의 난수 만들기
		String com = data[index];
	
		// 사용자의 가위 바위 보 입력받기
		gc.start();
		
		String man = null;
		
		do { 
			man = JOptionPane.showInputDialog("가위 바위 보를 입력하세요...");
		// 가위와 man 순서를 바꾸면 취소를 누르면 null포인트 오류가 안난다.
		}while(!("가위".equals(man) || "바위".equals(man)||"보".equals(man)));
		
		inputCheck = true;

		// 결과 판정하기
		String result ="";	// 결과가 저장될 변수 선언
		
		/*
		if(man.equals(com)) {
			result = "비겼습니다.";
		}else if(man.equals("가위")&&com.equals("보")||
				 man.equals("바위")&&com.equals("가위")||
				 man.equals("보")&&com.equals("바위") ) {
			result ="당신이 이겼습니다.";
		}else{
			result="당신이 졌습니다.";
		}
		*/
		
		switch(man + com) {
		case "가위가위":
		case "바위바위":
		case "보보": result ="비겼습니다."; break;

		case "가위보":
		case "바위가위":
		case "보바위": result ="당신이 이겼습니다."; break;
		
		default : result ="당신이 졌습니다."; break;
		}
		
		
		// 출력하기
		System.out.println();
		System.out.println("==결 과==");
		System.out.println("컴퓨터 : "+com);
		System.out.println("사용자 : "+man);
		System.out.println("결  과  : "+result);
	}
}



class GameCounter extends Thread{
	
	@Override
	public void run() {
		System.out.println("카운트 다운 시작...");
		for(int i=5; i>=1; i--) {
			if(ThreadTest07_1.inputCheck) {	// 입력 완료 여부 검사
				return;
			}
			System.out.println(i);
			
			try {
				Thread.sleep(1000);
			}catch(InterruptedException e) {
				
			}
		}
		System.out.println();
		System.out.println("==결 과==");
		System.out.println("시간 초과로 당신이 졌습니다...");
		System.exit(0);
	}
	
}