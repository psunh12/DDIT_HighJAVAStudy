package kr.or.ddit.basic;

// 쓰레드의 상태를 출력하는 예제

public class ThreadTest09 {

	public static void main(String[] args) {
		TargetThread tg = new TargetThread();
		
		StatePrintThread th = new StatePrintThread(tg);
		
		th.start();

	}

}


// TargetThread의 상태값을 출력하는 쓰레드
class StatePrintThread extends Thread{
	private TargetThread target;
	
	// 생성자
	public StatePrintThread(TargetThread target) {
		this.target = target;
	}
	
	@Override
	public void run() {
		while(true) {
			// TargetThread 쓰레드의 상태값 구하기 (결과는 열거형 형태로 반환된다.)
			Thread.State state = target.getState();
			System.out.println("TargetThread의 상태값 : " + state);
			
			if(state == Thread.State.NEW) {   // 현재의 쓰레드 상태가 NEW 상태이면...
				target.start();
			}
			
			if(state == Thread.State.TERMINATED) {  // 쓰레드의 상태가 종료 상태이면...
				break;
			}
			
			try {
				Thread.sleep(500);
			}catch (InterruptedException e) {
				// TODO: handle exception
			}
			
			
		}
	}
	
}


// 쓰레드 상태의 검사 대상이 되는 쓰레드
class TargetThread extends Thread{
	@Override
	public void run() {
		for(long i=1L; i<=20_000_000_000L; i++) {
			long a = i + 1;
		}
		
		try {
			Thread.sleep(1500);
		} catch (InterruptedException e) {
			// TODO: handle exception
		}
		
		for(long i=1L; i<=20_000_000_000L; i++) {
			long a = i + 1;
		}
		
	}
}

