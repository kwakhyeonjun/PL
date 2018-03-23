import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Scanner {
	/**
	 * Token�� �Է°��� ���� �����(id/int)
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
	 * ��ū Ÿ�԰� � String(id, int)�� �������ִ��� �����ϴ� class
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
	 * �Է¹��� ����(Source�� initialize�ϴ� class
	 * @param source
	 */
	public Scanner(String source) {
		this.transM = new int[4][128];//�ƽ�Ű�ڵ带 ������� ������ �ȵɰͰ��� ����
		this.source = source == null ? "" : source;
		st = new StringTokenizer(source);//source�� StringTokenizer�� ��ū������ Ȯ��
		initTM();//initialize
	}
	/**
	 * ������¿� �Է�, ���߻��¸� �����ϴ� �޼ҵ�
	 * �ƽ�Ű�ڵ�(128��)�� �̿��ؼ� ���ڿ��� Ȯ����
	 * transM[�������][�Է�] = ���ο����;
	 */
	private void initTM() {//�ƽ�Ű�ڵ带 �̿��ؾ��ҰͰ��� ����
		for(int i = 0; i<128; i++) {//�ƽ�Ű�ڵ带 �ϳ��� �Է����ν� Ȯ����
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
	 * �Է¹��� ��ū�� �� char�� Ȯ���ؼ� finalstate�� Ȯ����.
	 * @return
	 */
	private Token nextToken() {
		int stateOld = 0, stateNew;
		
		//��ū�� �� �ִ��� �˻�
		if(!st.hasMoreTokens())	return null;
		
		//�� ���� ��ū�� ����
		String temp = st.nextToken();
		
		Token result = null;
		for(int i = 0; i<temp.length(); i++) {
			//���ڿ��� ���ڸ� �ϳ��� ������ ���� �Ǻ�
			stateNew = transM[stateOld][temp.charAt(i)];
			
			if(stateNew == -1) {
				//�Էµ� ������ ���°� reject�̹Ƿ� �����޼��� ��� �� return
				System.out.println(String.format("acceptState error %s\n", temp)); return null;
			}
			stateOld = stateNew;
		}
		for(TokenType t : TokenType.values()){//tokentype���� valuse�� ���� �ݺ�(2/3)
			if(t.finalState == stateOld) {//2�� 3�̶� ������
				result = new Token(t, temp);//result�� token���·� ����
				break;
			}
		}
		return result;
	}
	/**
	 * nextToken method���� �Է¹��� token�� finalstate�� �� �� �ִ�. �̸� �̿��ؼ� ��������� �����ϴ� list�� ������.
	 * Token������ List���
	 * @return
	 */
	public List<Token> tokenize(){//�Է¹��� st�� token�� ��ŭ �ݺ��ؼ� list���·� ��������. �翬�� add���·� �����ؾ���.
		List<Token> tokenlist = new ArrayList();
		for(int i = 0; st.hasMoreElements(); i++) {//st�� ����Ǿ��ִ� token����ŭ �ݺ���
			tokenlist.add(nextToken());//nextToken()���� ���� ������� list�� ������.
		}
		return tokenlist;//����� list�� return
	}
	
	public static void main(String[] args) {//����������� �Ѵܾ ������ �ؾ��ҵ�
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
