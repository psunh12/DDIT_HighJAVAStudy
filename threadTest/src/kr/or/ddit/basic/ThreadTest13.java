package kr.or.ddit.basic;

import java.util.Arrays;

/*
	10마리의 말들이 경주하는 프로그램을 작성하시오.
	
	말은 Horse라는 이름의 쓰레드 클래스로 자성하는데
	이 클래스는 말이름(String), 등수(int), 현재위치(int)를 멤버변수로 갖는다.
	
	그리고 이 클래스에는 등수를 오름차순으로 처리할 수 있는 내부 정렬 기준이 있다.
	(Comparable인터페이스 구현하기)
	
	경기 구간은 1 ~ 50 구간으로 한다.
	
	경기 중 중간 중간에 각 말들의 위치를 아래와 같이 나타내시오.
	예)
	01번말 : ----->----------------------------------------
	02번말 : ------->--------------------------------------
	..
	10번말 : -->-------------------------------------------

*/
public class ThreadTest13 {

	public static void main(String[] args) {
		Horse[] horseArr = new Horse[] {
			new Horse("01번말"), new Horse("02번말"), new Horse("03번말"),   	
			new Horse("04번말"), new Horse("05번말"), new Horse("06번말"),   	
			new Horse("07번말"), new Horse("08번말"), new Horse("09번말"),   	
			new Horse("10번말")   	
		};
		
		GameState gs = new GameState(horseArr);
		
		for(Horse h : horseArr) {
			h.start();
		}
		
		gs.start();
		
		for(Horse h : horseArr) {
			try {
				h.join();
			} catch (InterruptedException e) {
				// TODO: handle exception
			}
		}
		
		try {
			gs.join();
		} catch (InterruptedException e) {
			// TODO: handle exception
		}
		
		System.out.println();
		System.out.println("경기 끝...");
		System.out.println();
		
		
		System.out.println("  == 경기 결과 ==");
		
		// 등수의 오름차순 정렬하기
		Arrays.sort(horseArr);
		
		for(Horse h : horseArr) {
			System.out.println(h);
		}
		
		

	}

}

// 말은 Horse라는 이름의 쓰레드 클래스로 자성하는데
// 이 클래스는 말이름(String), 등수(int), 현재위치(int)를 멤버변수로 갖는다.
// 그리고 이 클래스에는 등수를 오름차순으로 처리할 수 있는 내부 정렬 기준이 있다.
// (Comparable인터페이스 구현하기)
class Horse extends Thread implements Comparable<Horse>{
	public static int currentRank = 0;	// 경주가 끝난 말의 등수를 구하기 위한 변수
	
	private String horseName;
	private int rank;
	private int location;
	
	// 생성자
	public Horse(String horseName) {
		super();
		this.horseName = horseName;
	}

	public String getHorseName() {
		return horseName;
	}

	public void setHorseName(String horseName) {
		this.horseName = horseName;
	}

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	public int getLocation() {
		return location;
	}

	public void setLocation(int location) {
		this.location = location;
	}
	
	@Override
	public String toString() {
		return "경주마 " + horseName + "은(는) " + rank + "등 입니다...";
	}

	// 등수의 오름차순
	@Override
	public int compareTo(Horse horse) {
		return Integer.compare(this.rank, horse.rank);
	}
	
	@Override
	public void run() {
		for(int i=1; i<=50; i++) {
			this.location = i;   // 말의 현재 위치 저장하기
			
			try {
				Thread.sleep((int)(Math.random() * 500));
			} catch (InterruptedException e) {
				// TODO: handle exception
			}
		}
		
		// 현재 말의 경주가 끝나면 등수를 구해서 저장한다.
		currentRank++;
		this.rank = currentRank;
		
	}
	
	
}


/*

예)
01번말 : ----->----------------------------------------
02번말 : ------->--------------------------------------
..
10번말 : -->-------------------------------------------
*/

//경기 중 중간 중간에 각 말들의 위치를 나타내는 쓰레드 
class GameState extends Thread{
	private Horse[] horseArr;   // 경주에 참가하는 말들이 저장된 배열을 저장할 배열 변수
	
	// 생성자
	public GameState(Horse[] horseArr) {
		this.horseArr = horseArr;
	}
	
	@Override
	public void run() {
		while(true) {
			// 모든 말의 경주가 끝났는지 여부 검사
			if(Horse.currentRank==horseArr.length) {
				break;
			}
			
			for(int i=1; i<=20;i++) {
				System.out.println();
			}
			
			
			for(Horse h : horseArr) {
				System.out.print( h.getHorseName() + " : ");
				
				for(int i=1; i<=50; i++) {
					// 말의 현재 위치를 비교하여 '>'문자를 출력한다.
					if(h.getLocation() == i) {
						System.out.print(">");
					}else {
						System.out.print("-");
					}
				}
				System.out.println();  // 줄바꿈
			}
			
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO: handle exception
			}
			
		}
	}
	
}









