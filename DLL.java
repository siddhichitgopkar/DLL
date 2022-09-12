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
        return counter;
    } //size

    //2
    public boolean isEmpty() {
        if (this.size() == 0) return true;

        else return false;
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
        Node<E> temp = new Node<E>(head.next, null, (head.next).next);
        head = temp;
        temp = null;
        return first;
        counter--;
    } //removeFirst

    //8
    public E removeLast() {
        E last = tail.element;
        Node<E> temp = new Node<E>(tail.prev, (tail.prev).prev, null);
        tail = temp;
        temp = null;
        counter--;
    } //removeLast

    //9
    public String toString() {
        String list = "null";
        if (this.isEmpty()) return list;
        Node<E> current = head;
        list += "<--";
        for (int i = 0; i < size() - 1; i++) {
            list += String.valueOf(current);
            current = current.next;
            list += "<-->";
        } //while
        list += String.valueOf(tail.element);
        list += "--> null";
        return list;
    } //toString


    //10
    public DLL<E> clone() {
        DLL<E> clone = new DLL<E>();
        return clone;
    } // close

    //11
    public DLL<E> deepClone() {
        DLL<E> clone = new DLL<E>();
        return clone;
    } // deepClone

    //12
    public void insert (int index, E element) {
        Node<E> current = head;
        if (index == 0) {
            addFirst(element);
        } else if (index == (size() - 1)) {
            addLast(element);
        } else {
            for (int i = 0; i < index; i++) {
                current = current.next;
            } //for
            Node<E> temp = new Node<E>(element);
            Node<E> previous = current;
            Node<E> after = current.next;
            prev.next = temp;
            temp.prev = previous;
            temp.next = after;
            after.prev = temp;
        } //else
    } //insert

    //13
    public E get(int index) {

        Node<E> current = head;
        if (index > size() - 1 || index < size()) return null;
        for (int i = 0; i < index; i++) {
            current = current.next;
        } //for
        return current.element;
    } //get

    //14
    public E remove(int index) {
        Node<E> current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        } //for
        E elem = current.element;
        current.element = null;
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
