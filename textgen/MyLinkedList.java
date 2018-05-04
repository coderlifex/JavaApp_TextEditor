package textgen;

import java.util.AbstractList;

/**
 * A class that implements a doubly linked list
 */
public class MyLinkedList<E> extends AbstractList<E> {
	LLNode<E> head;
	LLNode<E> tail;
	int size;

	public MyLinkedList() {
		head = new LLNode(null);
		tail = new LLNode(null);
		head.next = tail;
		tail.prev = head;
		size = 0;
	}

	public boolean add(E element) {
		if (element == null)
			throw new NullPointerException();
		LLNode node = new LLNode(element);
		tail.prev.next = node;
		node.prev = tail.prev;
		node.next = tail;
		tail.prev = node;
		size++;
		return true;
	}

	public E get(int index) {
		if (index < 0 || index > size - 1) {
			throw new IndexOutOfBoundsException();
		}
		LLNode find = head;
		for (int i = 0; i <= index; i++)
			find = find.next;
		return (E) find.data;
	}

	public void add(int index, E element) {
		if (element == null)
			throw new NullPointerException();
		if (index < 0 || index > size) {
			throw new IndexOutOfBoundsException();
		}
		LLNode node = new LLNode(element);
		LLNode find = head;
		for (int i = 0; i < index; i++) {
			find = find.next;
		}
		node.next = find.next;
		find.next.prev = node;
		find.next = node;
		node.prev = find;
		size++;

	}
	
	public int size() {
		return size;
	}

	public E remove(int index) {
		if (index < 0 || index > size - 1)
			throw new IndexOutOfBoundsException();
		LLNode find = head;
		for (int i = 0; i <= index; i++) {
			find = find.next;
		}
		find.prev.next = find.next;
		find.next.prev = find.prev;
		size--;
		return (E) find.data;
	}
	
	public E set(int index, E element) {
		if (element == null)
			throw new NullPointerException();
		if (index < 0 || index > size - 1)
			throw new IndexOutOfBoundsException();
		LLNode find = head;
		for (int i = 0; i <= index; i++) {
			find = find.next;
		}
		find.data = element;
		return element;
	}
}

class LLNode<E> {
	LLNode<E> prev;
	LLNode<E> next;
	E data;

	public LLNode(E e) {
		this.data = e;
		this.prev = null;
		this.next = null;
	}

}
