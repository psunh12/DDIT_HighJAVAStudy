package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Scanner;

/*
	로또 판매를 관리하는 프로그램을 작성하시오.

실행예시)
	===================
	Lotto 판매 프로그램
	===================
	1. Lotto 판매
	2. 프로그램 종료
	-------------------
	메뉴 선택 >> 1
	
	Lotto 판매 시작...
	(1000원에 로또 번호 하나입니다.)
	(로또번호는 최대 100개까지 가능합니다.)
	
	금액 입력 >> 900
	입력 금액이 너무 적습니다. 로또번호 판매 실패!!
	
	===================
	Lotto 판매 프로그램
	===================
	1. Lotto 판매
	2. 프로그램 종료
	-------------------
	메뉴 선택 >> 1
	
	Lotto 판매 시작...
	(1000원에 로또 번호 하나입니다.)
	(로또번호는 최대 100개까지 가능합니다.)
	
	금액 입력 >> 101000
	입력 금액이 너무 많습니다. 로또번호 판매 실패!!
	
	===================
	Lotto 판매 프로그램
	===================
	1. Lotto 판매
	2. 프로그램 종료
	-------------------
	메뉴 선택 >> 1
	
	Lotto 판매 시작...
	(1000원에 로또 번호 하나입니다.)
	(로또번호는 최대 100개까지 가능합니다.)
	
	금액 입력 >> 2500
	
	행운의 로또번호는 아래와 같습니다.
	로또번호 1 >> 3 4 5 6 7 8
	로또번호 2 >> 7 8 10 22 31 42
	
	받은 금액은 2500원이고, 거스름돈은 500원 입니다.
	
	===================
	Lotto 판매 프로그램
	===================
	1. Lotto 판매
	2. 프로그램 종료
	-------------------
	메뉴 선택 >> 2
	
	감사합니다.
	안녕히 가세요.
	
	 
*/
public class LottoStore {
	private Scanner scan = new Scanner(System.in);

	public static void main(String[] args) {
		new LottoStore().startLotto();
	}
	
	public void startLotto() {
		while(true) {
			int choice = displayMenu();
			
			switch(choice) {
				case 1 :	// Lotto 판매
					saleLotto();
					break;
				case 2 : 	// 종료
					System.out.println();
					System.out.println("감사합니다.");
					System.out.println("안녕히 가세요.");
					return;
				default : 
					System.out.println("메뉴 번호를 잘못 입력했습니다.");
					System.out.println("('1' 또는 '2'를 입력하세요.)");
			}
			
		}
	}
	
	// 로또를 판매하는 메서드
	private void saleLotto() {
		System.out.println();
		System.out.println("Lotto 판매 시작...");
		System.out.println("(1000원에 로또 번호 하나입니다.)");
		System.out.println("(로또번호는 최대 100개까지 가능합니다.)");
		System.out.println();
		System.out.print("금액 입력 >> ");
		int money = scan.nextInt();

		System.out.println();
		
		if(money<1000) {
			System.out.println("입력 금액이 너무 적습니다. 로또번호 판매 실패!!");
			return;
		}
		
		if(money >= 101000) {
			System.out.println("입력 금액이 너무 많습니다. 로또번호 판매 실패!!");
			return;
		}
		
		// 로또번호 만들기
		createLotto(money);
		
		System.out.println();
		System.out.println("받은 금액은 " + money + "원이고, 거스름돈은 " + (money % 1000) + "원 입니다.");
		
		
	}
	
	// 금액을 매개변수로 받아서 금액에 맞게 로또번호를 생성하는 메서드
	private void createLotto(int money) {
		HashSet<Integer> lottoSet = new HashSet<Integer>();
		
		int count = money / 1000;  // 만들 로또번호 개수 구하기
		
		System.out.println();
		
		System.out.println("행운의 로또번호는 아래와 같습니다.");
		for(int i=1; i<=count; i++) {
			while(lottoSet.size() < 6) {  // 1 ~ 45 
				lottoSet.add( (int)(Math.random() * 45 + 1) );
			}
			
			ArrayList<Integer> lottoList = new ArrayList<Integer>(lottoSet);
			Collections.sort(lottoList);
			
			System.out.print("로또번호 " + i + " >> ");
			for(int lottoNum : lottoList) {
				System.out.print( lottoNum + " ");
			}
			System.out.println();
			
			lottoSet.clear();   // 작업할 때 만들어진 데이터 모두 삭제하기
		}
		
		
	}
	
	
	
	
	// 메뉴를 출력하고 메뉴 번호를 입력 받아 반환하는 메서드
	private int displayMenu() {
		
		System.out.println();
		System.out.println("===================");
		System.out.println("Lotto 판매 프로그램");
		System.out.println("===================");
		System.out.println(" 1. Lotto 판매");
		System.out.println(" 2. 프로그램 종료");
		System.out.println("-------------------");
		System.out.print("메뉴 선택 >> ");
		
		return scan.nextInt();
		
	}
	

}
















