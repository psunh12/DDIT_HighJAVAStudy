package kr.or.ddit.basic;

public class ArgsTest {

	// 가변형 인수 ==> 메서드의 인수의 개수가 메서드를 호출할 때마다 다를 때 사용한다.
	//		- 가변형 인수는 메서드 내부에서는 배열로 처리된다.
	//		- 가변형 인수는 한가지 자료형만 사용할 수 있다.
	
	
	
	// 매개변수로 받은 정수들의 합계를 구해서 반환하는 메서드
	// ( 이 정수들의 개수는 상황에 따라 다를 수 있다. )
	
	// 배열을 이용하여 처리하는 메서드
	public int sumArr(int[] data) {
		int sum = 0;
		
		for(int i=0; i<data.length; i++) {
			sum += data[i];
		}
		
		return sum;
	}
	
	
	// 가변형 인수를 이용한 메서드
	public int sumArgs(int... data) {
		int sum = 0;
		
		for(int i=0; i<data.length; i++) {
			sum += data[i];
		}
		
		return sum;
	}
	
	// 가변형 인수와 일반적인 인수를 같이 사용할 경우에는
	// 가변형 인수를 제일 뒤쪽에 배치해야 한다.
	public String sumArgs2(String name, int...data) {
		int sum = 0;
		
		for(int i=0; i<data.length; i++) {
			sum += data[i];
		}
		
		return name + "씨의 합계 : " + sum;
	}
	


	public static void main(String[] args) {
		ArgsTest test = new ArgsTest();
		
		int[] nums = {100, 200, 300};   // 배열 선언과 초기화를 같이 할 때만 사용 가능
		
//		int[] nums2;
//		nums2= new int[] {1,2,3,4,5};
		
		
		System.out.println(test.sumArr(nums));
		System.out.println(test.sumArr( new int[] {1,2,3,4,5} ));     // 1,2,3,4,5 가 들어가는 배열을 넣어보시오

		System.out.println();
		
		System.out.println(test.sumArgs(100,200,300));
		System.out.println(test.sumArgs(1,2,3,4,5));
		
		System.out.println();
		
		System.out.println(test.sumArgs2("홍길동", 1,2,3,4,5));
//		System.out.println(test.sumArgs2(1,2,3,4,5, 100));
	}

}





