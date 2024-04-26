package pathtest;

import java.io.File;
import java.io.IOException;

public class PathTest {

	public static void main(String[] args) throws IOException {
//		File f = new File("../../../../output/test.txt");
//		Files.write(f.toPath(),"테스트".getBytes());
//		
//		String path =System.getProperty("user.dir");
//		System.out.println(path);	// 전체경로 나옴

		// 다른경로 확인 시 root경로만 확인하면 됨.
//		String root ="D:\\D_setting\\A_TeachingMaterial\\03_HighJava\\workspace\\PathTest";
		String root = Config.path1;	//프롬프트 명령으로 뺌
		
		File dir = new File(root);
		findFolder(dir);
	}
	public static void findFolder(File f) {
		for (File sub : f.listFiles()) {
			// 재귀함수
			if(sub.isDirectory()) {
			System.out.println("폴더 : "+sub.getName());
			findFolder(sub);
		}else {
			System.out.println("\t"+f.getName());
		}
	}
  }
}
