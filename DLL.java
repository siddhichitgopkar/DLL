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
        if (this.size() == 0) return true;
        else return false;
    } //isEmpty

    //3
    public E first() {
        return head.element;
    } //first

    //4
    public E last() {
        return tail.element;
    } //first

    //5
    public void addFirst(E element) {
        Node<E> temp = new Node<E>(element);
        if (this.isEmpty()) {
            head = temp;
            tail = temp;
        } else {
            head.prev = temp;
            temp.next = head;
            head = temp;
        } //else
        counter++;
    } //addFirst

    //6
    public void addLast(E element) {
        Node <E> temp = new Node<E>(element);
        if (this.isEmpty()) {
            head = temp;
            tail = temp;
        } else {
            tail.next = temp;
            temp.prev = tail;
            tail = temp;
        } //else
        counter++;
    } //addLast

    //7
    public E removeFirst() {
        E first = head.element;
        Node<E> temp = new Node<E>(head.next.element, null, head.next.next);
        head = temp;
        temp = null;
	counter--;
        return first;
    } //removeFirst

    //8
    public E removeLast() {
        E last = tail.element;
        Node<E> temp = new Node<E>(tail.prev.element, tail.prev.prev, null);
        tail = temp;
        temp = null;
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
            list += current.element.toString();
            current = current.next;
            list += " <--> ";
        } //while
        list += tail.element.toString();
        list += " --> null";
        return list;
    } //toString


    //10
    public DLL clone() {
	DLL<E> clone = new DLL<E>();
	clone.addFirst(this.head.element);
        Node<E> current = this.head.next;
        Node<E> previous = clone.head;
        for (int i = 1; i < size() - 1; i++) {
            Node<E> newNode = new Node(current.element);
            previous.next = newNode;
            newNode.prev = previous;
            current = current.next;
	    previous = previous.next;
        } //for
        clone.addLast(current.element);
        return clone;
    } // clone

    //11
    public DLL deepClone() {
	DLL<E> deepClone = new DLL<E>();
	Node<E> current = this.head;
	if (!this.isEmpty()) {
	    try {
	        E elementCopy;
		Method m = current.element.getClass().getMethod("clone");
		for (int i = 0; i < size(); i++) {
		    System.out.println(current.element);
		    m = current.element.getClass().getMethod("clone");
		    elementCopy = (E) m.invoke(current.element);
		    deepClone.addLast(elementCopy);
		    current = current.next;
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
                if (current.next != null) current = current.next;
            } //for
            Node<E> temp = new Node<E>(element);
            Node<E> after = current.next;
	    current.next = temp;
	    temp.prev = current;
	    temp.next = after;
	    after.prev = temp;
        } //else
	counter++;
    } //insert

    //13
    public E get(int index) {
        Node<E> current = head;
        for (int i = 0; i < index; i++) {
            if (current.next != null) current = current.next;
        } //for
        return current.element;
    } //get

    //14
    public E remove(int index) {
        Node<E> current = head;
	E elem = current.element;
	if (index == 0) {
	    removeFirst();
	    counter--;
	    return first();
	} else if (index == size() - 1) {
	    removeLast();
	    counter--;
	    return last();
        } else {
	    for (int i = 0; i < index; i++) {
		if (current.next != null) current = current.next;
	    } //for
	    elem = current.element;
	    Node<E> previous = current.prev;
	    Node<E> after = current.next;
	    previous.next = after;
	    after.prev = previous;
	    current.prev = null;
	    current.next = null;
	    current.element = null;
	}
	counter--;
        return elem;
    } // remove

    //15
    public void remove(Node<E> x) {
        Node<E> previous = x.prev;
        Node<E> after = x.next;
        previous.next = after;
        after.prev = previous;
        x.prev = null;
        x.next = null;
        counter--;
    } // remove

    //16
    public Node<E> find(E element) {
        Node<E> current = head;
        Node<E> out = new Node<E>();
        int size = size();
        for (int i = 0; i < size; i++) {
            if (current.element == element) {
                out = current;
                return out;
            } //if
        } //for
        return null;
    } // find

    //17
    public void swap(Node<E> x, Node<E> y) {
	    Node<E> temp = x;
	    x.next = y.next;
	    x.prev = y.prev;
	    y.next = temp.next;
	    y.prev = temp.prev;
    } // swap

    //18
    public void clear() {
        Node<E> current = head;
        int size = size();
        for (int i = 0; i < size; i++) {
            current.element = null;
            current = current.next;
        } //for
        counter = 0;
    } // clear

    //19
    public E set(int index, E element) {
        E out;
        Node<E> current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        } //for
        out = current.element;
        current.element = element;
        return out;
    } // set
} //DLL
