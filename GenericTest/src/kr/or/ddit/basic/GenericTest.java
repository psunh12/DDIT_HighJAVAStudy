package kr.or.ddit.basic;

/*
	제네릭 클래스 작성 방법
	형식)
	class 클래스명<제네릭타입글자>{
		
		private 제네릭타입글자 변수명;		// 변수 선언에 제네릭을 사용할 경우
		...
		
		
		접근제한자 제네릭타입글자 메서드명(){		// 메서드의 반환값에 제네릭을 사용할 경우
			...
			return 값;
		}
		
		// 메서드의 매개변수에 제네릭을 사용할 경우
		 
		접근제한자 반환값타입 메서드명(제네릭타입글자 변수명, ...){  
			~~~
		 	~~~
		 }
	}
	
	--  제네릭 타입 글자  --
	T 	==> Type
	K	==> Key
	V	==> Value
	E	==> Element

*/

// 제네릭을 사용한 경우
class MyGeneric<T>{
	private T value;
	
	public T getValue() {
		return value;
	}
	
	public void setValue(T value) {
		this.value = value;
	}
}




// 제네릭을 사용하지 않은 경우
class NonGenericTest{
	private Object value;
	
	public Object getValue() {
		return value;
	}
	
	public void setValue(Object value) {
		this.value = value;
	}
	
}

public class GenericTest {

	public static void main(String[] args) {
		NonGenericTest ng1 = new NonGenericTest();
		ng1.setValue("가나다라");
		
		NonGenericTest ng2 = new NonGenericTest();
		ng2.setValue(100);
		
		String strTemp = (String)ng1.getValue();
		System.out.println("문자열 반환값 : " + strTemp);
		
		int iTemp = (int)ng2.getValue();
		System.out.println("정수형 반환값 : " + iTemp);
		
		System.out.println("-----------------------------------");
		
		MyGeneric<String> mg1 = new MyGeneric<>();
		mg1.setValue("우리나라");
//		mg1.setValue(500);		// 제네릭으로 지정한 자료형과 다른 자료형은 사용 불가
		
		MyGeneric<Integer> mg2 = new MyGeneric<>();
		mg2.setValue(500);
//		mg2.setValue("eee");   // 오류...
		
		
		strTemp = mg1.getValue();
		iTemp = mg2.getValue();
		
		System.out.println("제네릭 - 문자열 반환값 : " + strTemp);
		System.out.println("제네릭 - 정수형 반환값 : " + iTemp);
		
		System.out.println("-----------------------------------");
		
		NonGenericTest ng3 = new NonGenericTest();
		ng3.setValue("ABCD");
				
		int test = (int)ng3.getValue();
		
		
		
		

	}

}
