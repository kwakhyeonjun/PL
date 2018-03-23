public class Recursion {
	
	public static int fibonacci(int n) {
		if(n==1) return 1;//1��°�� ��� 1
		else if(n==2) return 1;//2��°�� ���
		return fibonacci(n-2)+fibonacci(n-1);//1,2�� �ƴϸ� ������ �� ������ ����
	}
	
	public static String recursiveAnt(int n) {
		if(n==2) {
			return "11";//2�ΰ�� 11 return
		}
		else {
			return makeResult(recursiveAnt(n-1));//�ƴѰ�쿡�� �� ���� ������ ����
		}
	}
	
	public static String makeResult(String previous) {
		int count = 1;
		
		char[] ch = previous.toCharArray();//string�� char[]���·� �ٽ� ����
		StringBuffer str = new StringBuffer();//previous�κ��� ����� ���� ������ ������
		
		for(int i=0;i<previous.length();i++) {
			if(i+1==previous.length()) {//�迭�� ���κ�
				if(ch[i]==ch[i-1]) {//���κп��� ���� �������
					str.append(ch[i]);//�ݺ��Ǿ��� ���ڸ� ����
					str.append(count);//������ count�� ����
				}else {//������ �ΰ��� �ٸ����
					str.append(ch[i]);
					str.append(count);
				}
			}else {//���κ��� �ƴѰ��
				if(ch[i]==ch[i+1]) {
					count++;//���� ������� count++
				}else {
					str.append(ch[i]);
					str.append(count);
					count = 1;//�� �������� �ٽ� �ʱ�ȭ
				}
			}
		}
		return str.toString();//str�� return

	}
	
	public static void main(String[] args) {
		System.out.println(fibonacci(10));
		System.out.println(recursiveAnt(10));
	}
}
