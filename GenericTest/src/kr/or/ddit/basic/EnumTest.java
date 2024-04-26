package kr.or.ddit.basic;

/*
- enum(열거형)  ==> 서로 관련있는 상수들의 집합을 나타낸다.
				==> 클래스처럼 보이게하는 상수
				
	==> 작성 위치
		- 하나의 java파일에 독립적인 클래스처럼 만들수 있고,
		  하나의 java파일에 클래스와 같이 만들수 있고,
		  클래스의 내부에 내부 클래스처럼 만들수 있다.
		  
	==> 열거형의 속성 및 메서드
	- name() 	--> 열거형 상수의 이름을 문자열로 반환한다.
	- ordinal()	--> 열거형 상수가 정의된 순서값(index값)을 반환한다. (순서값은 0부터 시작...)
	- valueOf("열거형상수명") --> 지정된 열거형에서 '열거형상수명'과 일지하는 열거형 상수를 반환한다.
	- 열거형이름.상수명		--> valueOf()메서드와 같다.
	- 열거형이름.values()	--> 모든 상수들을 배열로 가져온다.
	
- 열거형 선언하기
 방법1)
 	enum 열거형이름 { 상수명1, 상수명2, .... }
 	
 방법2)
 	enum 열거형이름 {
 		상수명1(값들...), 
 		상수명2(값들...),
 		...
 		상수명n(값들...);
 		
 		// '값들'이 저장될 변수들을 선언한다.
 		private 자료형이름 변수명1;
 		private 자료형이름 변수명2;
 		...
 		
 		// 열거형의 생성자를 작성한다.
 		// 		==> 열거형의 생성자는 '열거형상수'에 '값들'을 셋팅하는 역할을 수행한다.
 		//		==> 열거형 생성자는 묵시적으로 private이다.
 		
 		// 생성자의 매개변수는 위의 '값들'과 개수가 같고, '값들'의 자료형이 맞아야 한다.
 		private 열거형이름(자료형 변수명1, ... ){
 			위에 선언한 변수들을 초기화 한다.
 			...
 		}
 		
 		
 		// 구성된 '값들'을 외부에서 불러올 수 있는 getter메서드를 작성한다.
 		// (위에 '값들'이 저장될 변수를 이용하여 getter메서드를 작성하면 된다.)
 	
 	}
		  
*/

public class EnumTest {
	public enum Color { RED, GREEN, BLUE }
	public enum Count { ONE, TWO, THREE }
	
	// 계절의 나타내는 상수 작성하기 (설정할 값은 기간과 평균온도)
	public enum Season{
		봄("3월부터 5월까지", 13),
		여름("6월부터 8월까지", 28),
		가을("9월부터 11월까지", 15),
		겨울("12월부터 2월까지", 1);
		
		// 값들이 저장될 변수 선언
		private String span;
		private int temp;
		
		// 생성자
		Season(String span, int temp){   // ==> 'private Season(String span, int temp){' 와 같다.
			this.span = span;
			this.temp = temp;
		}
		
		// getter메서드 작성
		public String getSpan() {
			return span;
		}
		
		public int getTemp() {
			return temp;
		}
		
	}
	
	
	public static void main(String[] args) {
	/*	
		System.out.println("Red : " + ConstTest.RED);
		System.out.println("Three : " + ConstTest.THREE);
		
		if(ConstTest.RED == ConstTest.TWO) {
			System.out.println("........");
		}else {
			System.out.println("@@@@@");
		}
	*/	
		
		Color mycol = Color.valueOf("GREEN");  // Color.GREEN 와 같다.
		Count mycnt = Count.ONE;			   //  Count.valueOf("ONE") 와 같다.
		
		System.out.println("mycol : " + mycol.name());
		System.out.println("mycnt : " + mycnt.name());
		System.out.println();
		
		System.out.println("mycol ordinal : " + mycol.ordinal());
		System.out.println("mycnt ordinal : " + mycnt.ordinal());
		
		
		// 서로 다른 종류의 열거형끼리의 비교는 불가하다.
//		if(Color.RED == Count.THREE) {  
//			
//		}
		
		if(mycol == Color.GREEN) {
			System.out.println("같다...");
		}
		
		System.out.println();
		
		// 열거형을 switch문으로 비교하기
		// 열거형을 switch문으로 비교할 때는 switch문의 case문에
		// 지정하는 열거형 상수는 열거형 이름은 생략한 상수명만 기술해야 한다.
		switch(mycnt) {
			case ONE : System.out.println("ONE값"); break;
			case TWO : System.out.println("TWO값"); break;
			case THREE : System.out.println("THREE값"); break;
		}
		System.out.println();
		System.out.println("--------------------------------------");
		
		Season ss = Season.valueOf("봄");
		System.out.println("name : " + ss.name());
		System.out.println("ordinal : " + ss.ordinal());
		System.out.println("span : " + ss.getSpan());
		System.out.println("temp : " + ss.getTemp());
		System.out.println();
		
		// Season 열거형의 전체 상수 정보 출력하기
		for(Season time : Season.values()) {
			System.out.println(time.name() + " == " + time + " ==> "
						+ time.getSpan() + ", " + time.getTemp() );
		}
		System.out.println();
		

	}

}















