import java.lang.reflect.*;

public class DLL<E> {

    //node class
    public class Node<E> {

        private E element;
        private Node<E> prev;
        private Node<E> next;

        public Node() {
            this.element = null;
            this.prev = null;
            this.next = null;
        } // Node<E>()

        public Node(E element) {
            this.element = element;
            this.prev = null;
            this.next = null;
        } // Node<E>(E element)

        public Node(E element, Node<E> prev, Node<E> next) {
            this.element = element;
            this.prev = prev;
            this.next = next;
        } // Node<E>(E element, Node<E> prev, Node<E> next)

        public void setElement(E element) {
            this.element = element;
        } // setElement

        public E getElement() {
            return this.element;
        } // getElement

        public void setPrev(Node<E> prev) {
            this.prev = prev;
        } // setPrev

        public Node<E> getPrev() {
            return this.prev;
        } // getPrev

        public void setNext(Node<E> next) {
            this.next = next;
        } // setNext

        public Node<E> getNext() {
            return this.next;
        } // getNext
    } // Node<E>


    private Node<E> head;
    private Node<E> tail;
    private int counter;

    //constructor
    public DLL() {
        head = null;
        tail = null;
        counter = 0;
    } //DLL

    //1
    public int size() {
        return counter;
    } //size

    //2
    public boolean isEmpty() {
	boolean isEmpty = false;
        if (size() == 0) {
	    isEmpty = true;
	} else {
	    isEmpty = false;
	} // if
	return isEmpty;
    } //isEmpty

    //3
    public E first() {
        return head.getElement();
    } //first

    //4
    public E last() {
        return tail.getElement();
    } //first

    //5
    public void addFirst(E element) {
        Node<E> newHead = new Node<E>(element);
        if (isEmpty() == true) {
            head = newHead;
            tail = newHead;
        } else {
            head.setPrev(newHead);
            newHead.setNext(head);
            head = newHead;
        } //else
        counter++;
    } //addFirst

    //6
    public void addLast(E element) {
        Node <E> newTail = new Node<E>(element);
        if (isEmpty() == true) {
            head = newTail;
            tail = newTail;
        } else {
            tail.setNext(newTail);
	    newTail.setPrev(tail);
	    tail = newTail;
        } //else
        counter++;
    } //addLast

    //7
    public E removeFirst() {
        E first = first();
        Node<E> oldFirst = head;
        head = head.getNext();
        head.setPrev(null);
	oldFirst.setNext(null);
	oldFirst.setElement(null);
	counter--;
        return first;
    } //removeFirst

    //8
    public E removeLast() {
        E last = last();
	Node<E> oldTail = tail;
	tail = tail.getPrev();
        tail.setNext(null);
	oldTail.setPrev(null);
	oldTail.setElement(null);
        counter--;
        return last;
    } //removeLast

    //9
    public String toString() {
        String list = "null";
        if (this.isEmpty()) return list;
        Node<E> current = head;
        list += " <-- ";
        for (int i = 0; i < size() - 1; i++) {
            list += current.getElement().toString();
            current = current.getNext();
            list += " <--> ";
        } //while
        list += tail.getElement().toString();
        list += " --> null";
        return list;
    } //toString


    //10
    public DLL clone() {
	DLL<E> clone = new DLL<E>();
	clone.addFirst(this.head.getElement());
        Node<E> current = this.head.getNext();
        Node<E> previous = clone.head;
        for (int i = 1; i < size() - 1; i++) {
            Node<E> newNode = new Node(current.getElement());
            previous.setNext(newNode);
            newNode.setPrev(previous);
            current = current.getNext();
	    previous = previous.getNext();
        } //for
        clone.addLast(current.getElement());
        return clone;
    } // clone

    //11
    public DLL deepClone() {
	DLL<E> deepClone = new DLL<E>();
	Node<E> current = this.head;
	if (!this.isEmpty()) {
	    try {
	        E elementCopy;
		Method m = current.getElement().getClass().getMethod("clone");
		for (int i = 0; i < size(); i++) {
		    m = current.getElement().getClass().getMethod("clone");
		    elementCopy = (E) m.invoke(current.getElement());
		    deepClone.addLast(elementCopy);
		    current = current.getNext();
		} //for
	    } catch(Exception e) {
		e.printStackTrace();
	    } // try-catch
	} // if
        return deepClone;
    } // deepClone

    //12
    public void insert (int index, E element) {
        Node<E> current = this.head;
        if (index == 0) {
            addFirst(element);
            counter++;
            return;
        } else if (index == size() - 1) {
            addLast(element);
            counter++;
            return;
        } else {
            for (int i = 1; i < index; i++) {
                if (current.getNext() != null) current = current.getNext();
            } //for
            Node<E> temp = new Node<E>(element);
            Node<E> after = current.getNext();
	    current.setNext(temp);
	    temp.setPrev(current);
	    temp.setNext(after);;
	    after.setPrev(temp);
        } //else
        counter++;
    } //insert

    //13
    public E get(int index) {
        Node<E> current = head;
        for (int i = 0; i < index; i++) {
            if (current.getNext() != null) current = current.getNext();
        } //for
        return current.getElement();
    } //get

    //14
    public E remove(int index) {
        if (index == 0) {
            removeFirst();
            return first();
        } else if (index == size() - 1) {
            removeLast();
            return last();
        } else {
            DLL<E> deepClone = new DLL<E>();
            Node<E> current = this.head;
            Node<E> newhead = null;
            E out = null;
            if (!this.isEmpty()) {
                try {
                    E elementCopy;
                    Method m = current.element.getClass().getMethod("clone");
                    for (int i = 0; i < size(); i++) {
                        if (i == index) {
                            out = current.element;
                            current = current.next;
                        } else {
                            m = current.element.getClass().getMethod("clone");
                            elementCopy = (E) m.invoke(current.element);
                            deepClone.addLast(elementCopy);
                            current = current.next;
                        } //else
                    } //for
                } catch(Exception e) {
                    e.printStackTrace();
                } // try-catch
            } // if
            this.head = deepClone.head;
            counter--;
            return out;
        } //else
    } // remove

    //15
    public void remove(Node<E> x) {
        Node<E> previous = x.getPrev();
        Node<E> after = x.getNext();
        previous.setNext(after);
        after.setPrev(previous);
        x.setPrev(null);
        x.setNext(null);
        counter--;
        /*
          Node<E> current = head;
          int index = 0;
          for (int i = 0; i < size(); i++) {
          if (current.element == x.element) {
          index = i;
          } //if
          } //for
          remove(index);
         */
    } // remove

    //16
    public Node<E> find(E element) {
        Node<E> current = head;
        Node<E> out = new Node<E>();
        int size = size();
        for (int i = 0; i < size; i++) {
            if (current.getElement() == element) {
                out = current;
                return out;
            } //if
        } //for
        return null;
    } // find

    //17
    public void swap(Node<E> x, Node<E> y) {
	    Node<E> temp = x;
	    x.setNext(y.getNext());
	    x.setPrev(y.getPrev());
	    y.setNext(temp.getNext());
	    y.setPrev(temp.getPrev());
    } // swap

    //18
    public void clear() {
        Node<E> current = head;
        int size = size();
        for (int i = 0; i < size; i++) {
            current.setElement(null);
            current = current.getNext();
        } //for
        counter = 0;
    } // clear

    //19
    public E set(int index, E element) {
        E out;
        Node<E> current = head;
        for (int i = 0; i < index; i++) {
            current = current.getNext();
        } //for
        out = current.getElement();
        current.setElement(element);
        return out;
    } // set
} //DLL
