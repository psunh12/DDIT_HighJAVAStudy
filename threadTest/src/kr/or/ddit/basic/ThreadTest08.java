package kr.or.ddit.basic;

// 데몬 쓰레드 연습용 ==> 자동 저장 기능 구현

public class ThreadTest08 {

	public static void main(String[] args) {
		AutoSave as = new AutoSave();
		
		as.setDaemon(true);   // 데몬 쓰레드로 설정하기 (반드시 start()메서드 호출전에 설정한다.)
		
		as.start();
		
//		as.setDaemon(true); // start()메서드 이후에 설정하면 Exception이 발생한다.
		
		try {
			for(int i=1; i<=20; i++) {
				System.out.println(i);
				Thread.sleep(1000);
			}
		} catch (InterruptedException e) {
			// TODO: handle exception
		}
		
		System.out.println("main 쓰레드 종료...");

	}

}

// 자동 저장하는 쓰레드 작성 (3초에 한번씩 저장하는 쓰레드)
class AutoSave extends Thread{
	
	// 작업 내용을 저장하는 메서드
	public void save() {
		System.out.println("작업 내용을 저장합니다...");
	}
	
	@Override
	public void run() {
		while(true) {
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				// TODO: handle exception
			}
			save();
		}
	}
	
	
}


