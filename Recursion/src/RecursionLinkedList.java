import java.util.*;

public class RecursionLinkedList {
	private Node head;
	private static char UNDEF = Character.MIN_VALUE;
	/**
	  * 새롭게 생성된 노드를 리스트의 처음으로 연결
	  */
	private void linkFirst(char element) {
		head = new Node(element, head);
	}
	/**
	 * 주어진 Node x의 마지막으로 연결된 Node의 다음으로 새롭게 생성된 노드를 연결
	 * @param element
	 * @param x
	 */
	private void linkLast(char element, Node x) {
		if(x==null) {//비어있는 노드인 경우 linkFirst로
			linkFirst(element);
		}else {
			while(x.next!=null){//이외의 경우에는 맨 마지막 노드까지 간 뒤에
				x = x.next;
			}
			x.next = new Node(element,x.next);//마지막 다음노드로 생성
		}
	}
	/**
	 * 이전 노드의 다음 Node로 새롭게 생성된 노드를 연결
	 * @param element
	 * @param pred
	 */
	private void linkNext(char element, Node pred) {
		Node next = pred.next;
		pred.next = new Node(element, next);
	}
	/**
	 * 리스트의 첫번째 원소 삭제
	 * @return
	 */
	private char unlinkFirst() {
		Node x = head;
		char element = x.item;
		head = head.next;
		x.item = UNDEF;
		x.next = null;
		return element;
	}
	/**
	 * 이전 Node의 다음 Node 연결 삭제
	 * @param pred
	 * @return
	 */
	private char unlinkNext(Node pred) {
		Node x = pred.next;
		Node next = x.next;
		char element = x.item;
		x.item = UNDEF;
		x.next = null;
		pred.next = next;
		return element;
	}
	/**
	 * x노드에서 index만큼 떨어진 Node반환
	 * @param index
	 * @param x
	 * @return
	 */
	private Node node(int index, Node x) {
		if(x==null) {
			throw new IndexOutOfBoundsException("Empty node");//빈 노드일 경우 비어있다고 알림
		}else if(index==0){
			return head;
		}else {
			Node temp = head;//포인터 노드
			for(int i = 0; i<index-1; i++) {
				temp = temp.next;//index이전까지 이동
			}
			return temp.next;//index번째에 해당하는 노드를 return
		}
	}
	/**
	 * 노드로부터 끝까지의 리스트의 노드 갯수 변환
	 * @param x
	 * @return
	 */

	private int length(Node x) {//x를 한칸씩 이동하면서 count를 늘림
		if(x==null){
			return 0;//마지막에 0을 return
		}
		return 1+length(x.next);//한칸마다 1씩증가
	}
	/**
	 * 노드로부터 시작하는 리스트의 내용 반환
	 * @param x
	 * @return
	 */
	private String toString(Node x) {
		if(x==null) {
			return "";//base case
		}else {
			return x.item+" "+toString(x.next);//x의 item을 출력하고 다음 노드를 출력
		}
	}
	/**
	 * 현재 노드의 이전 노드부터 리스트의 끝까지를 거꾸로 실행
	 * @param x
	 * @param pred
	 */
	private void reverse(Node x, Node pred) {
		if(x.next != null) {//x가 마지막 노드가 아닐때
			reverse(x.next, x);//다음칸으로 이동한다.
		}else {//결과적으로 마지막 노드까지 이동했을때
			this.head = x;//새로 만들어질 노드의 head를 x로 놓는다.
		}
		if(pred==null) {//이전의 노드가 없는경우
			x.next = null;//x가 가리키는 다음 노드를 지운다.
		}else {//이전의 노드가 있는경우
			x.next = pred;//아닌경우에는 둘의 자리를 바꾼다.
		}
	}
	/**
	 * 원소를 주어진 위치에 추가
	 * @param element
	 * @return
	 */
	public boolean add(char element) {
		if(head==null) {
			linkFirst(element);
		}else {
			linkLast(element, head);
		}
		return true;
	}
	
	public void add(int index, char element) {
		if(!(index>=0 && index<=size()))
			throw new IndexOutOfBoundsException(""+index);
		if(index==0)
			linkFirst(element);
		else
			linkNext(element, node(index-1, head));
	}
	
	public char get(int index) {
		if(!(index>=0 && index<size()))
			throw new IndexOutOfBoundsException(""+index);
		return node(index, head).item;
	}
	
	public char remove(int index) {
		if(!(index>=0 && index<size()))
			throw new IndexOutOfBoundsException(""+index);
		if(index==0)
			return unlinkFirst();
		return unlinkNext(node(index-1, head));
	}
	
	public void reverse() {
		reverse(head, null);
	}
	
	public int size() {
		return length(head);
	}
	
	public String toString() {
		if(head==null)
			return "[]";
		return "["+toString(head)+"]";
	}
	
	private static class Node{
		char item;
		Node next;
		
		Node(char element, Node next){
			this.item = element;
			this.next = next;
		}
	}
}
