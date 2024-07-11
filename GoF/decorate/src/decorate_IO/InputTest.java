package decorate_IO;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class InputTest {

	public static void main(String[] args) throws IOException {
		int c;
		
		try {
			String filePath = "src/decorate_IO/text.txt"; // 파일 경로를 실제 파일 위치로 설정
			
			// 변수 filePath를 사용
			InputStream in = new LowerCaseInputStream(new BufferedInputStream(new FileInputStream(filePath)));

			while((c = in.read()) >= 0) {
				System.out.print((char) c);
			}
			
			in.close();
		} catch(IOException e) {
			e.printStackTrace();
		}
	}

}
