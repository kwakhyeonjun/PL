import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Scanner {
	/**
	 * Token의 입력값에 따른 결과값(id/int)
	 * @author kwak0
	 *
	 */
	public enum TokenType{
		ID(3), INT(2);
		
		private final int finalState;
		
		TokenType(int finalState){
			this.finalState = finalState;
		}
	}
	/**
	 * 토큰 타입과 어떤 String(id, int)를 가지고있는지 저장하는 class
	 * @author kwak0
	 *
	 */
	public static class Token{
		public final TokenType type;
		public final String lexme;
		
		Token(TokenType type, String lexme){
			this.type = type;
			this.lexme = lexme;
		}
		
		public String toString() {
			return String.format("[%s: %s]", type.toString(), lexme);
		}
	}
	
	private int transM[][];
	private String source;
	private StringTokenizer st;
	/**
	 * 입력받은 문장(Source를 initialize하는 class
	 * @param source
	 */
	public Scanner(String source) {
		this.transM = new int[4][128];//아스키코드를 사용하지 않으면 안될것같은 냄새
		this.source = source == null ? "" : source;
		st = new StringTokenizer(source);//source를 StringTokenizer로 토큰단위로 확인
		initTM();//initialize
	}
	/**
	 * 현재상태와 입력, 나중상태를 저장하는 메소드
	 * 아스키코드(128개)를 이용해서 문자열을 확인함
	 * transM[현재상태][입력] = 새로운상태;
	 */
	private void initTM() {//아스키코드를 이용해야할것같은 냄새
		for(int i = 0; i<128; i++) {//아스키코드를 하나씩 입력으로써 확인함
			if(i>47&&i<=57) {//Digit(state2)
				transM[0][i]=2;
				transM[1][i]=2;
				transM[2][i]=2;
				transM[3][i]=3;
			}else if((i>=65&&i<91)||(i>=97&&i<123)) {//Alpah
				transM[0][i]=3;
				transM[1][i]=-1;
				transM[2][i]=-1;
				transM[3][i]=3;
			}else if(i==45) {//"-"
				transM[0][i]=1;
				transM[1][i]=-1;
				transM[2][i]=-1;
				transM[3][i]=-1;
			}else {
				transM[0|1|2|3][i]=-1;
			}
		}
	}
	/**
	 * 입력받은 토큰을 한 char씩 확인해서 finalstate를 확인함.
	 * @return
	 */
	private Token nextToken() {
		int stateOld = 0, stateNew;
		
		//토큰이 더 있는지 검사
		if(!st.hasMoreTokens())	return null;
		
		//그 다음 토큰을 받음
		String temp = st.nextToken();
		
		Token result = null;
		for(int i = 0; i<temp.length(); i++) {
			//문자열의 문자를 하나씩 가져와 상태 판별
			stateNew = transM[stateOld][temp.charAt(i)];
			
			if(stateNew == -1) {
				//입력된 문자의 상태가 reject이므로 에러메세지 출력 후 return
				System.out.println(String.format("acceptState error %s\n", temp)); return null;
			}
			stateOld = stateNew;
		}
		for(TokenType t : TokenType.values()){//tokentype안의 valuse를 꺼내 반복(2/3)
			if(t.finalState == stateOld) {//2나 3이랑 같으면
				result = new Token(t, temp);//result에 token형태로 저장
				break;
			}
		}
		return result;
	}
	/**
	 * nextToken method에서 입력받은 token의 finalstate를 알 수 있다. 이를 이용해서 결과값들을 저장하는 list를 생성함.
	 * Token형태의 List사용
	 * @return
	 */
	public List<Token> tokenize(){//입력받은 st의 token수 만큼 반복해서 list형태로 만들어야함. 당연히 add형태로 저장해야함.
		List<Token> tokenlist = new ArrayList();
		for(int i = 0; st.hasMoreElements(); i++) {//st에 저장되어있는 token수만큼 반복함
			tokenlist.add(nextToken());//nextToken()으로 나온 결과값을 list에 저장함.
		}
		return tokenlist;//저장된 list를 return
	}
	
	public static void main(String[] args) {//띄어쓰기기준으로 한단어씩 나눠서 해야할듯
		FileReader fr;
		String source = new String();
		try {
			fr = new FileReader("as02.txt");
			BufferedReader br = new BufferedReader(fr);
			source = br.readLine();
		}catch(IOException e) {
			e.printStackTrace();		
		}
		Scanner s = new Scanner(source);
		List<Token> tokens = s.tokenize();	
		System.out.println(tokens);
	}
}
