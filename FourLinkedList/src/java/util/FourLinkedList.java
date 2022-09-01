import java.util.*;

public class FourLinkedList<E> implements List<E> {
	
	public static class node<E>{
		private E data;
		node<E> prev, next, prev4, next4;
		
		//constructor of the node
		public node(E data, node<E> prev, node<E> next, node<E> prev4, node<E> next4) {
			this.data = data;
			this.next = next;
			this.prev = prev;
			this.next4 = next4;//this.next.next.next.next
			this.prev4 = prev4;//this.prev.prev.prev.prev
		}
		public node(E data, node<E> prev, node<E> next) {
			this(data, prev, next, null, null);
		}
		public node(E data) {
			this(data, null, null);
		}
		private void setNext(node<E> e) {
			this.next = e;
		}
		private void setPrev(node<E> e) {
			this.prev = e;
		}
		private void setNext4(node<E> e) {
			this.next4 = e;
		}
		private void setPrev4(node<E> e) {
			this.prev4 = e;
		}
	}
	
	//initial settings
	private node<E> head;
	private node<E> tail;
	private static int size;

	public FourLinkedList() {
		head = new node<E>(null, null, null);
		tail = new node<E>(null, head, null);
		head.setNext(tail);
	}	
	public boolean add (E e) {//add to the end of the list	
		add(size, e);
		size++;
		return true;
	}
		
	public void add(int index, E element) {
		if (index > size || index < 0) {
			throw new IndexOutOfBoundsException();
		}
		if(size() == 0) {
			node<E> curr = new node<E>(element, head, tail);
			head.setNext(curr);
			tail.setPrev(curr);
		}else if (size() < 4) {
			node<E> curr = new node<E>(element, getNode(index -1), getNode(index));
			curr.prev.setNext(curr);
			curr.next.setPrev(curr);
		}else {
			node<E> curr = new node<E>(element, getNode(index -1), getNode(index), getNode(index).prev4, null);
			curr.prev.setNext(curr);//connect to prev
			curr.next.setPrev(curr);//connect to next
			curr.setNext4(curr.prev.next4);//if curr.prev.next4 = null, this one will be null
			if (curr.prev4 != null) {//link prev4 one with curr
				curr.prev4.setNext4(curr);
				curr.next.setPrev4(curr.prev4.next);
			}
			if(curr.next4 != null) {
				curr.next4.setPrev4(curr);
				curr.prev.setNext4(curr.next4.prev);
			}
			if (curr.prev.prev.prev != null) {//link prev3 one with next1,next1 can be tail
				curr.prev.prev.prev.setNext4(curr.next);
				curr.next.setPrev4(curr.prev.prev.prev);
			}
			if (curr.prev.prev != null && curr.next.next != null) {//link prev2 one with next2
				curr.prev.prev.setNext4(curr.next.next);
				curr.next.next.setPrev4(curr.prev.prev);
			}
			if (curr.next.next.next != null) {//link prev one with next3, prev one can be head
				curr.prev.setNext4(curr.next.next.next);
				curr.next.next.next.setPrev4(curr.prev);
			}	
		}
		size++;
	}
	
	public void addFirst(E e) {
		throw new UnsupportedOperationException();
	}

	public void addLast(E e) {
		throw new UnsupportedOperationException();
	}
	
	public void clear() {
		node<E> curr = head.next;
		while(curr.next != tail) {
			curr = curr.next;
			curr.prev = null;
		}
		curr = null;
		size = 0;
	}
	
	public FourLinkedList<E> clone() {
		throw new UnsupportedOperationException();
	}
	
	public boolean contains(FourLinkedList<E> l) {
		throw new UnsupportedOperationException();
	}
	
	public Iterator<E> descendingIterator(){
		throw new UnsupportedOperationException();
	}
	
	public E element() {
		throw new UnsupportedOperationException();
	}
	
	public E get(int index) {
		node<E> curr = getNode(index);
		return curr.data;
		
	}
	
	public E getFirst() {
		throw new UnsupportedOperationException();
	}
	
	public E getLast() {
		throw new UnsupportedOperationException();
	}
	
	private node<E> getNode(int index) {//create a new node and put it to index location, return the new node
		if (index > size || index < 0) {
			throw new IndexOutOfBoundsException();
		}
		node<E> curr;
		if(index < size/2) {//to test if it is faster to start from head or tail
			curr = head.next;// if index is smaller and better to start with head
			for(int i = 0; i < index - 3; i += 4) {
				curr = curr.next4;
			}
			for(int i = 0; i < index % 4; i++) {
				curr = curr.next;
			}
		}else {//if the index is bigger and better to start with tail
			curr = tail;
			for(int i = size; i > index + 3; i -= 4) {
				curr = curr.prev4;
			}
			for(int i = size; i > index % 4; i--) {
				curr = curr.prev;
			}
		}
		return curr;
	}
	
	public int indexOf(FourLinkedList<E> l) {
		throw new UnsupportedOperationException();	
	}
	public ListIterator<E>	listIterator(int index){
		throw new UnsupportedOperationException();	
	}
	
	public boolean	offer(E e) {
		throw new UnsupportedOperationException();	
	}
	
	public boolean offerFirst(E e) {
		throw new UnsupportedOperationException();	
	}
	
	public boolean offerLast(E e){
		throw new UnsupportedOperationException();
	}
	
	public E peek() {
		throw new UnsupportedOperationException();	
	}
	
	public E peekFirst() {
		throw new UnsupportedOperationException();	
	}
	
	public E peekLast() {
		throw new UnsupportedOperationException();	
	}
	
	public E poll() {
		throw new UnsupportedOperationException();	
	}
	
	public E pollFirst() {
		throw new UnsupportedOperationException();	
	}
	
	public E pollLast() {
		throw new UnsupportedOperationException();	
	}
	
	public E pop() {
		throw new UnsupportedOperationException();	
	}
	public E push() {
		throw new UnsupportedOperationException();	
	}
	public E remove() {
		throw new UnsupportedOperationException();	
	}
	public E remove(int index) {
		node<E> curr = getNode(index);//move curr to index
		curr.prev.setNext(curr.next);
		curr.next.setPrev(curr.prev);
		if(curr.prev4 != null) {
			curr.prev4.setNext4(curr.next);//curr.next can be tail
			curr.next.setPrev4(curr.prev4);
		}
		if(curr.prev.prev.prev != null && curr.next.next != null) {
			curr.prev.prev.prev.setNext4(curr.next.next);
			curr.next.next.setPrev4(curr.prev.prev.prev);
		}
		if(curr.prev.prev != null && curr.next.next.next != null) {
			curr.prev.prev.setNext4(curr.next.next.next);
			curr.next.next.next.setPrev4(curr.prev.prev);
		}
		if(curr.next4 != null) {
			curr.prev.setNext4(curr.next4);//curr.prev can be head
			curr.next4.setPrev4(curr.prev);
		}
		size--;
		return curr.data;
	}
	
	public boolean remove(E e) {
		throw new UnsupportedOperationException();	
	}
	public E removeFirst() {
		throw new UnsupportedOperationException();	
	}
	public boolean removeFirstOccurrence(E e) {
		throw new UnsupportedOperationException();	
	}
	
	public E removeLast() {
		throw new UnsupportedOperationException();	
	}
	public boolean removeLastOccurrence(E e) {
		throw new UnsupportedOperationException();	
	}
	
	public E set(int index, E element) {
		throw new UnsupportedOperationException();	
	}
	
	public int size() {
		return size;
	}
	

	public FourLinkedList<E> toArray() {
		throw new UnsupportedOperationException();	
	}
	
	public <T> T[]	toArray(T[] a) {
		throw new UnsupportedOperationException();	
	}
	
	public String toString() {
		String list = "";
		node<E> e = head.next;
		while(e != null || e != tail) {
			list = list + e.data + " ";
			e = e.next;
		}
		return list;
	}
	public static void main(String[] args) {
		//testcase01
	}
	
}
