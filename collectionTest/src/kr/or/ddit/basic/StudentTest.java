package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/*
문제) 학번, 이름, 국어점수, 영어점수, 수학점수, 총점, 등수를 멤버로 갖는 Student클래스를 만든다.
	이 클래스의 생성자에서는 학번, 이름, 국어점수, 영어점수, 수학점수만 매개변수로 받아서
	초기화 처리를 한다. (이 때 총점은 세 과목의 점수를 이용해서 초기화 한다.)
	
	이 Student객체는 List에 저장하여 관리한다.
	
	List에 저장된 데이터들을 학번의 오름차순으로 정렬할 수 있는 내부 정렬 기준을 구현하고,
	총점의 역순으로 정렬하는데 총점이 같으면 이름의 오름차순으로 정렬되도록 외부 정렬 기준 클래스를
	작성하여 정렬된 결과를 출력하시오.
	
	(단, 등수는 List에 전체 데이터가 추가된 후에 저장되도록 한다. ==> StudentTest클래스에서 작업)

*/
public class StudentTest {

	// 등수를 구하는 메서드
	public void createRank(ArrayList<Student> list) {
		for(Student std1 : list) {  // 기준데이터(등수 구할 데이터)를 구하기 위한 반복문
			
			int rank = 1;		// 처음에는 1등으로 설정해 놓고 시작한다.
			
			for(Student std2 :list) {	// 비교 대상을 나타내는 반복문
				
				// 기준 데이터 보다 비교 대상 데이터가 더 크면 rank값을 증가시킨다.
				if(std1.getTot() < std2.getTot()) {
					rank++;
				}
				
			}
			
			std1.setRank(rank);  // 구해진 등수를 기준 데이터의 rank변수에 저장한다.
		}
	}
	
	
	
	public static void main(String[] args) {
		StudentTest st = new StudentTest();
		
		ArrayList<Student> stdList = new ArrayList<Student>();
		
		stdList.add(new Student(1, "홍길동", 90, 95, 80));
		stdList.add(new Student(3, "성춘향", 90, 75, 70));
		stdList.add(new Student(7, "강감찬", 95, 95, 80));
		stdList.add(new Student(5, "변학도", 80, 95, 90));
		stdList.add(new Student(2, "일지매", 100, 85, 80));
		stdList.add(new Student(4, "이순신", 60, 65, 70));
		stdList.add(new Student(6, "이몽룡", 90, 100, 90));
		
		// 등수 구하는 메서드 호출
		st.createRank(stdList);
		
		System.out.println("정렬전...");
		for(Student std : stdList) {
			System.out.println(std);
		}
		System.out.println();
		
		Collections.sort(stdList);   // 내부 정렬 기준 (학번의 오름차순) 정렬
		
		System.out.println("학번의 오름차순 정렬후...");
		for(Student std : stdList) {
			System.out.println(std);
		}
		System.out.println();
		
		Collections.sort(stdList, new SortByTotal());
		
		
		System.out.println("총점의 역순으로 정렬후...");
		for(Student std : stdList) {
			System.out.println(std);
		}
		System.out.println();
		

	}

}

/*
	학번, 이름, 국어점수, 영어점수, 수학점수, 총점, 등수를 멤버로 갖는 Student클래스를 만든다.
	이 클래스의 생성자에서는 학번, 이름, 국어점수, 영어점수, 수학점수만 매개변수로 받아서
	초기화 처리를 한다. (이 때 총점은 세 과목의 점수를 이용해서 초기화 한다.)
	
	학번의 오름차순으로 정렬할 수 있는 내부 정렬 기준을 구현
*/
class Student implements Comparable<Student>{
	private int num;
	private String name;
	private int kor;
	private int eng;
	private int mat;
	private int tot;
	private int rank;
	
	public Student(int num, String name, int kor, int eng, int mat) {
		super();
		this.num = num;
		this.name = name;
		this.kor = kor;
		this.eng = eng;
		this.mat = mat;
		this.tot = kor + eng + mat;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getKor() {
		return kor;
	}

	public void setKor(int kor) {
		this.kor = kor;
	}

	public int getEng() {
		return eng;
	}

	public void setEng(int eng) {
		this.eng = eng;
	}

	public int getMat() {
		return mat;
	}

	public void setMat(int mat) {
		this.mat = mat;
	}

	public int getTot() {
		return tot;
	}

	public void setTot(int tot) {
		this.tot = tot;
	}

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	@Override
	public String toString() {
		return "Student [num=" + num + ", name=" + name + ", kor=" + kor + ", eng=" + eng + ", mat=" + mat + ", tot="
				+ tot + ", rank=" + rank + "]";
	}

	// 학번의 오름차순
	@Override
	public int compareTo(Student std) {
//		if(this.num > std.getNum()) {
//			return 1;
//		}else if(this.num < std.getNum()) {
//			return -1;
//		}else {
//			return 0;
//		}
		
		// Wrapper클래스 이용하기
		
		//return new Integer(this.num).compareTo(std.getNum());  // 방법1
		
		return Integer.compare(this.num, std.getNum());			 // 방법2
		
	}
	
	
}

/*
총점의 역순으로 정렬하는데 총점이 같으면 이름의 오름차순으로 정렬되도록 외부 정렬 기준 클래스를 작성
*/
class SortByTotal implements Comparator<Student>{
	@Override
	public int compare(Student std1, Student std2) {
		if(std1.getTot() == std2.getTot()) {
			return std1.getName().compareTo(std2.getName());
		}else {
			return Integer.compare(std1.getTot(), std2.getTot()) * -1;
		}
	}
}










