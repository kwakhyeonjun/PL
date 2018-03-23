import java.util.*;

public class RecursionLinkedList {
	private Node head;
	private static char UNDEF = Character.MIN_VALUE;
	/**
	  * ���Ӱ� ������ ��带 ����Ʈ�� ó������ ����
	  */
	private void linkFirst(char element) {
		head = new Node(element, head);
	}
	/**
	 * �־��� Node x�� ���������� ����� Node�� �������� ���Ӱ� ������ ��带 ����
	 * @param element
	 * @param x
	 */
	private void linkLast(char element, Node x) {
		if(x==null) {//����ִ� ����� ��� linkFirst��
			linkFirst(element);
		}else {
			while(x.next!=null){//�̿��� ��쿡�� �� ������ ������ �� �ڿ�
				x = x.next;
			}
			x.next = new Node(element,x.next);//������ �������� ����
		}
	}
	/**
	 * ���� ����� ���� Node�� ���Ӱ� ������ ��带 ����
	 * @param element
	 * @param pred
	 */
	private void linkNext(char element, Node pred) {
		Node next = pred.next;
		pred.next = new Node(element, next);
	}
	/**
	 * ����Ʈ�� ù��° ���� ����
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
	 * ���� Node�� ���� Node ���� ����
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
	 * x��忡�� index��ŭ ������ Node��ȯ
	 * @param index
	 * @param x
	 * @return
	 */
	private Node node(int index, Node x) {
		if(x==null) {
			throw new IndexOutOfBoundsException("Empty node");//�� ����� ��� ����ִٰ� �˸�
		}else if(index==0){
			return head;
		}else {
			Node temp = head;//������ ���
			for(int i = 0; i<index-1; i++) {
				temp = temp.next;//index�������� �̵�
			}
			return temp.next;//index��°�� �ش��ϴ� ��带 return
		}
	}
	/**
	 * ���κ��� �������� ����Ʈ�� ��� ���� ��ȯ
	 * @param x
	 * @return
	 */

	private int length(Node x) {//x�� ��ĭ�� �̵��ϸ鼭 count�� �ø�
		if(x==null){
			return 0;//�������� 0�� return
		}
		return 1+length(x.next);//��ĭ���� 1������
	}
	/**
	 * ���κ��� �����ϴ� ����Ʈ�� ���� ��ȯ
	 * @param x
	 * @return
	 */
	private String toString(Node x) {
		if(x==null) {
			return "";//base case
		}else {
			return x.item+" "+toString(x.next);//x�� item�� ����ϰ� ���� ��带 ���
		}
	}
	/**
	 * ���� ����� ���� ������ ����Ʈ�� �������� �Ųٷ� ����
	 * @param x
	 * @param pred
	 */
	private void reverse(Node x, Node pred) {
		if(x.next != null) {//x�� ������ ��尡 �ƴҶ�
			reverse(x.next, x);//����ĭ���� �̵��Ѵ�.
		}else {//��������� ������ ������ �̵�������
			this.head = x;//���� ������� ����� head�� x�� ���´�.
		}
		if(pred==null) {//������ ��尡 ���°��
			x.next = null;//x�� ����Ű�� ���� ��带 �����.
		}else {//������ ��尡 �ִ°��
			x.next = pred;//�ƴѰ�쿡�� ���� �ڸ��� �ٲ۴�.
		}
	}
	/**
	 * ���Ҹ� �־��� ��ġ�� �߰�
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
