public class DLL<E> {

    public class Node<E> {

	private E element;
	private Node<E> prev;
	private Node<E> next;

	public Node<E>() {
	    this.element = null;
	    this.prev = null;
	    this.next = null;
	} // Node<E>()

	public Node<E>(E element) {
	    this.element = element;
	    this.prev = null;
	    this.next = null;
	} // Node<E>(E element)

	public Node<E>(E element, Node<E> prev, Node<E> next) {
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
    public DLL<E>() {
        head = null;
        tail = null;
        counter = 0;
    } //DLL<E>

    //1
    public int size() {
        int size = 0;
        if (head == null) {
            return size;
        } //if
        while (head != null) {
            size++;
            head = head.next;
        } //while
        return size;
    } //size

    //2
    public boolean isEmpty() {
        boolean empty = false;
        if (head == null || head.next == tail || tail.prev == head) {
            rempty = true;
        } //if
        return empty;
    } //isEmpty

    //3
    public E first() {
        return head;
    } //first

    //4
    public E last() {
        return tail;
    } //first

    //5
    public void addFirst(E element) {
        Node<E> temp = new Node<E>();
        temp.element = element;
        temp.next = null;
        temp.prev = null;
        if (head == null) { //should i call isEmpty() in the if statement instead?
            head = temp;
        } else {
            head.prev = temp;
            temp.next = head;
            head = temp;
        } //else
    } //adFirst

    //6
    public void addLast(E element) {
        Node <E> tmep = new Node<E>();
        temp.element = element;
        temp.next = null;
        temp.prev = null;
        if (head == null) { //should i call isEmpty() in the if statement instead here too?
            head = temp;
        } else {
            tail.next = temp;
            temp.prev = tail;
            tail = temp;
        } //else
    } //addLast

    //7
    public E removeFirst() {
        E first = head.element;
        Node<E> temp = new Node<E>();
        temp = head;
        head = head.next;
        temp = null;
        head.prev = null;
        return first;
    } //removeFirst

    //8
    public E removeLast() {
        E last = tail.element;
        Node<E> temp = new Node<E>();
        temp = tail;
        tail = tail.prev;
        temp = null;
        tail.next = null;
    } //removeLast

    //9
    public String toString() {
        String list = "null";
        if (head == null) { //should i call isEmpty() here?
            return list;
        } //if
        E temp = head.element;
        list += "<--";
        while (temp != tail.element) {
            list += String.valueOf(temp);
            head = head.next;
            temp = head.element;
            list += "<-->";
        } //while
        list += String.valueOf(tail.element);
        list += "--> null";
        return list;
    } //toString

    //12
    public void insert (int index, E element) {
        int size = size();
        if (index == 0) {
            addFirst(element);
        } else if (index == (size - 1)) {
            addLast(element);
        } else {
            Node<E> temp = new Node<E>();
            Node<E> after = new Node<E>();
            Node<E> prev = new Node<E>();
            for (int i = 0; i < index; i++) {
                head = head.next;
            } //for
            prev = head;
            after = head.next;
            prev.next = temp;
            temp.prev = prev;
            temp.next = after;
            after.prev = temp;
        } //else
    } //insert

    //13
    public E get(int index) {
        int size = size();
        if (index > size - 1) {
            return null;
        } //if
        for (int i = 0; i < index; i++) {
            head = head.next;
        } //for
        return head.element;

    } //get

} //DLL
