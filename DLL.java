import java.lang.reflect.*;

//======================================================
// DLL<E> defines a doubly linked list implementation
//======================================================
public class DLL<E> {

    //======================================================
    // Node<E> defines the node implementation utilized by
    // the DLL
    //======================================================
    public class Node<E> {

	//======================================================
	// element = the value stored in the node
	//    prev = the node preceding the current node
	//    next = the node succeeding the current node
	//======================================================
        private E element;
        private Node<E> prev;
        private Node<E> next;

	//======================================================
	// Node constructor:
	// - initializes all instance variables to null
	//======================================================
        public Node() {
            this.element = null;
            this.prev = null;
            this.next = null;
        } // default Node constructor
	
	//======================================================
	// Node constructor:
	// - initializes this.element to element
	// - initializes all other instance variables to null
	//======================================================
        public Node(E element) {
            this.element = element;
            this.prev = null;
            this.next = null;
        } // Node

	// ======================================================
	// Node constructor:
	// - initializes this.element to element
	// - initializes this.prev to prev
	// - initializes this.next to next
	//======================================================
        public Node(E element, Node<E> prev, Node<E> next) {
            this.element = element;
            this.prev = prev;
            this.next = next;
        } // Node

	//======================================================
	// Sets this.element to element
	//======================================================
        public void setElement(E element) {
            this.element = element;
        } // setElement

	//======================================================
	// Returns this.element
	//======================================================
        public E getElement() {
            return this.element;
        } // getElement

	//======================================================
	// Sets this.prev to prev
	//======================================================
        public void setPrev(Node<E> prev) {
            this.prev = prev;
        } // setPrev

	//======================================================
	// Returns this.prev
	//======================================================
        public Node<E> getPrev() {
            return this.prev;
        } // getPrev

	//======================================================
	// Sets this.next to next
	//======================================================
        public void setNext(Node<E> next) {
            this.next = next;
        } // setNext

	//======================================================
	// Returns this.next
	//======================================================
        public Node<E> getNext() {
            return this.next;
        } // getNext

	//======================================================
	// Returns a String representing the element in the
	// calling Node
	//======================================================
	public String toString() {
	    return element.toString();
	} // toString
	
    } // Node<E>

    //======================================================
    //    head = the first node in the DLL
    //    tail = the last node in the DLL
    // counter = the number of nodes in the DLL
    //======================================================
    private Node<E> head;
    private Node<E> tail;
    private int counter;

    //======================================================
    // DLL constructor:
    // - initializes all instance variables to null
    //======================================================
    public DLL() {
        head = null;
        tail = null;
        counter = 0;
    } // default DLL constructor

    //======================================================
    // Returns the current number of elements in the DLL
    //======================================================
    public int size() {
        return counter;
    } // size

    //======================================================
    // Returns true if size() == 0 and false otherwise
    //======================================================
    public boolean isEmpty() {
	boolean isEmpty = false;
        if (size() == 0) {
	    isEmpty = true;
	} else {
	    isEmpty = false;
	} // if
	return isEmpty;
    } // isEmpty

    //======================================================
    // Returns the first element in DLL
    //======================================================
    public E first() {
        return head.getElement();
    } // first

    //======================================================
    // Returns the last element in DLL
    //======================================================
    public E last() {
        return tail.getElement();
    } // last

    //======================================================
    // Adds a new Node containing element to the head of
    // the DLL and sets head = newHead
    //======================================================
    public void addFirst(E element) {
        Node<E> newHead = new Node<E>(element);
        if (isEmpty() == true) {
            head = newHead;
            tail = newHead;
        } else {
            head.setPrev(newHead);
            newHead.setNext(head);
            head = newHead;
        } // if
        counter++;
    } // addFirst

    //======================================================
    // Adds a new Node containing element to the tail of
    // the DLL and sets tail = newTail
    //======================================================
    public void addLast(E element) {
        Node <E> newTail = new Node<E>(element);
        if (isEmpty() == true) {
            head = newTail;
            tail = newTail;
        } else {
            tail.setNext(newTail);
	    newTail.setPrev(tail);
	    tail = newTail;
        } // if
        counter++;
    } // addLast

    //======================================================
    // Removes the head of the DLL, sets head = head.next,
    // and returns the removed element
    //======================================================
    public E removeFirst() {
        E first = first();
        Node<E> oldFirst = head;
        head = head.getNext();
        head.setPrev(null);
	oldFirst.setNext(null);
	oldFirst.setElement(null);
	counter--;
        return first;
    } // removeFirst

    //======================================================
    // Removes the tail of the DLL, sets tail = tail.prev,
    // and returns the removed element
    //======================================================
    public E removeLast() {
        E last = last();
	Node<E> oldTail = tail;
	tail = tail.getPrev();
        tail.setNext(null);
	oldTail.setPrev(null);
	oldTail.setElement(null);
        counter--;
        return last;
    } // removeLast

    //======================================================
    // Prints the DLL in readable format
    //======================================================
    public String toString() {
        String list = "null";
        if (this.isEmpty()) return list;
        Node<E> current = head;
        list += " <-- ";
        for (int i = 0; i < size() - 1; i++) {
            list += current.getElement().toString();
            current = current.getNext();
            list += " <--> ";
        } // for
        list += tail.getElement().toString();
        list += " --> null";
        return list;
    } // toString


    //======================================================
    // Makes a shallow copy of the DLL with new Nodes con-
    // taining references to the same and equal elements
    // and returns the shallow copy
    //======================================================
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
        } // for
        clone.addLast(current.getElement());
        return clone;
    } // clone

    //======================================================
    // Makes a deep copy of the DLL with new Nodes contain-
    // ing references to new and equal elements and returns
    // the deep copy
    //======================================================
    public DLL deepClone() {
	DLL<E> deepClone = new DLL<E>();
	Node<E> current = this.head;
	if (!this.isEmpty()) {
	    try {
	        E elementCopy;
		Method clone = current.getElement().getClass().getMethod("clone");
		for (int i = 0; i < size(); i++) {
		    elementCopy = (E) clone.invoke(current.getElement());
		    deepClone.addLast(elementCopy);
		    current = current.getNext();
		} //for
	    } catch(Exception e) {
		e.printStackTrace();
	    } // try
	} // if
        return deepClone;
    } // deepClone
    
    //======================================================
    // Inserts element at Node index in the DLL
    //======================================================
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
        } // if
        counter++;
    } // insert

    //======================================================
    // Returns the element at Node index in the DLL and
    // returns that element
    //======================================================
    public E get(int index) {
        Node<E> current = head;
        for (int i = 0; i < index; i++) {
            if (current.getNext() != null) current = current.getNext();
        } //for
        return current.getElement();
    } // get

    //======================================================
    // Removes the element at Node index in the DLL and
    // returns that element
    //======================================================
    public E remove(int index) {
	Node<E> previous = head;
	Node<E> current = head;
	E removed = head.getElement();
        if (index == 0) {
            return removeFirst();
        } else if (index == size() - 1) {
            return removeLast();
        } else {
	    for(int i = 0; i < index; i++) {
		previous = current;
		current = current.getNext();
	    } // for
	    previous.setNext(current.getNext());
	    (current.getNext()).setPrev(previous);
	    removed = current.getElement();
	    current.setNext(null);
	    current.setPrev(null);
	    current.setElement(null);
	} // if;
	counter--;
	return removed;
    } // remove

    //======================================================
    // Removes the first Node in the list whose element
    // matches that of Node x
    //======================================================
    public void remove(Node<E> x) {
	Node<E> current = head;
	int indexToRemove = 0;
	for (int i = 0; i < size(); i++) {
	    if (current.getElement() == x.getElement()) {
		indexToRemove = i;
	    } //if
	} //for
	remove(indexToRemove);
    } // remove

    //======================================================
    // Locates the first Node in the DLL that contains
    // element and returns that Node, otherwise returns
    // null
    //======================================================
    public Node<E> find(E element) {
        Node<E> current = head;
	try {
	    Method equals = current.getElement().getClass().getMethod("equals", current.getElement().getClass());
	    while(current != null) {
		if ((boolean)equals.invoke(current.getElement(), element)) {
		    return current;
		} // if
		current = current.getNext();
	    } // while
	} catch (Exception e) {
	    e.printStackTrace();
	} // try
	return null;
    } // find

    //======================================================
    // Swaps the positions of Node x and Node y in the DLL
    //======================================================
    public void swap(Node<E> x, Node<E> y) {
	Node<E> n1 = head, n2 = head;
	/*if (x == head) head = y;
	if (y == head) head = x;
	if (x == tail) tail = y;
	if (y == tail) tail = x;*/
	while(n1 != x) n1 = n1.getNext();
	while(n2 != y) n2 = n2.getNext();
	Node<E> n1Prev = n1.getPrev(), n1Next = n1.getNext();
	System.out.println(n1Prev.getElement());
	System.out.println(n1Next.getElement());
	System.out.println(n2.getPrev().getElement());
	System.out.println(n2.getNext().getElement());
	n1.setPrev(n2.getPrev());
	n1.setNext(n2.getNext());
	System.out.println(n1Prev.getElement());
	System.out.println(n1Next.getElement());
	n2.setPrev(n1Prev);
	n2.setNext(n1Next);
	System.out.println(n2.getPrev().getElement());
	System.out.println(n2.getNext().getElement());
    } // swap

    //======================================================
    // Sets all Nodes in the DLL to null to prepare for
    // garbage collection
    //======================================================
    public void clear() {
        Node<E> current = head;
        int size = size();
        for (int i = 0; i < size; i++) {
            if(current.getNext() != null) remove(i);
        } //for
        counter = 0;
    } // clear

    //======================================================
    // Sets the value at Node index to element and returns
    // the old value at that Node
    //======================================================
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
