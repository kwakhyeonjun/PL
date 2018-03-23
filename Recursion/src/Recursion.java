public class Recursion {
	
	public static int fibonacci(int n) {
		if(n==1) return 1;//1번째의 경우 1
		else if(n==2) return 1;//2번째의 경우
		return fibonacci(n-2)+fibonacci(n-1);//1,2가 아니면 이전의 두 수열을 더함
	}
	
	public static String recursiveAnt(int n) {
		if(n==2) {
			return "11";//2인경우 11 return
		}
		else {
			return makeResult(recursiveAnt(n-1));//아닌경우에는 그 전의 수열을 구함
		}
	}
	
	public static String makeResult(String previous) {
		int count = 1;
		
		char[] ch = previous.toCharArray();//string을 char[]형태로 다시 저장
		StringBuffer str = new StringBuffer();//previous로부터 생기는 다음 수열을 저장함
		
		for(int i=0;i<previous.length();i++) {
			if(i+1==previous.length()) {//배열의 끝부분
				if(ch[i]==ch[i-1]) {//끝부분에서 서로 같은경우
					str.append(ch[i]);//반복되었던 숫자를 저장
					str.append(count);//세어진 count를 저장
				}else {//마지막 두개가 다른경우
					str.append(ch[i]);
					str.append(count);
				}
			}else {//끝부분이 아닌경우
				if(ch[i]==ch[i+1]) {
					count++;//서로 같은경우 count++
				}else {
					str.append(ch[i]);
					str.append(count);
					count = 1;//다 세었으니 다시 초기화
				}
			}
		}
		return str.toString();//str을 return

	}
	
	public static void main(String[] args) {
		System.out.println(fibonacci(10));
		System.out.println(recursiveAnt(10));
	}
}
