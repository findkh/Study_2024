package barista_abstract;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class TeaWithHook extends CaffeineBeverage{

	@Override
	void addCondiments() {
		System.out.println("레몬을 추가하는 중...");
	}
	
	@Override
	void brew() {
		System.out.println("찻잎을 우려내는 중...");
	}
	
	public boolean customerWantsCondiments() {
		String answer = getUserInput();
		
		if(answer.toLowerCase().startsWith("y")) {
			return true;
		} else {
			return false;
		}
	}
	
	private String getUserInput() {
		String answer = null;
		System.out.print("홍체에 레몬을 넣을까요? (y/n)?");
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		try {
			answer = in.readLine();
		} catch(IOException e) {
			e.printStackTrace();
		}
		
		if(answer == null) {
			return "no";
		}
		
		return answer;
	}
}
