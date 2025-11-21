package br.com.pixeldex.structures;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ListaEncadeada<T> implements Iterable<T> {
    private Node<T> head; 
    private Node<T> tail; 
    private int size;
    public ListaEncadeada() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    public void addFirst(T data) {
        Node<T> newNode = new Node<>(data);
        if (isEmpty()) {
            head = newNode;
            tail = newNode;
        } else {
            newNode.setNext(head);
            head = newNode;
        }
        size++;
    }

    public void addLast(T data) {
        if (isEmpty()) {
            addFirst(data);
        } else {
            Node<T> newNode = new Node<>(data);
            tail.setNext(newNode);
            tail = newNode;
            size++;
        }
    }

    public void addAt(int index, T data) {
        if (index < 0 || index > size) throw new IndexOutOfBoundsException("Índice inválido");
        
        if (index == 0) {
            addFirst(data);
            return;
        }
        if (index == size) {
            addLast(data);
            return;
        }

        Node<T> current = head;
        for (int i = 0; i < index - 1; i++) {
            current = current.getNext();
        }

        Node<T> newNode = new Node<>(data);
        newNode.setNext(current.getNext());
        current.setNext(newNode);
        size++;
    }

    public T removeFirst() {
        if (isEmpty()) throw new NoSuchElementException("Lista vazia");
        
        T data = head.getData();
        head = head.getNext();
        size--;
        
        if (size == 0) tail = null; 
        
        return data;
    }

    public T removeLast() {
        if (isEmpty()) throw new NoSuchElementException("Lista vazia");
        if (size == 1) return removeFirst();

        Node<T> current = head;
        while (current.getNext() != tail) {
            current = current.getNext();
        }

        T data = tail.getData();
        tail = current;
        tail.setNext(null);
        size--;
        
        return data;
    }

    public T removeAt(int index) {
        if (index < 0 || index >= size) throw new IndexOutOfBoundsException("Índice inválido");
        if (index == 0) return removeFirst();
        if (index == size - 1) return removeLast();

        Node<T> current = head;
        for (int i = 0; i < index - 1; i++) {
            current = current.getNext();
        }

        Node<T> nodeToRemove = current.getNext();
        T data = nodeToRemove.getData();
        current.setNext(nodeToRemove.getNext());
        size--;

        return data;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }
    
    public T get(int index) {
        if (index < 0 || index >= size) throw new IndexOutOfBoundsException();
        Node<T> current = head;
        for (int i = 0; i < index; i++) {
            current = current.getNext();
        }
        return current.getData();
    }
    
    public void reverse() {
        if (size <= 1) return;

        Node<T> previous = null;
        Node<T> current = head;
        Node<T> next = null;

        tail = head;

        while (current != null) {
            next = current.getNext(); 
            current.setNext(previous);
            previous = current;     
            current = next;
        }
        head = previous;
    }

    public void unique() {
        if (size <= 1) return;

        Node<T> current = head;
        
        while (current != null) {
            Node<T> runner = current;
            while (runner.getNext() != null) {
                if (current.getData().equals(runner.getNext().getData())) {
                    runner.setNext(runner.getNext().getNext());
                    size--;

                    if (runner.getNext() == null) {
                        tail = runner;
                    }
                } else {
                    runner = runner.getNext();
                }
            }
            current = current.getNext();
        }
    }

    public void move(int from, int to) {
        if (from < 0 || from >= size || to < 0 || to >= size) {
            throw new IndexOutOfBoundsException("Índices inválidos");
        }
        if (from == to) return;

        T data = removeAt(from);

        if (from < to) {
             if (to > size) to = size; 
        }
        addAt(to, data);
    }

    public ListaEncadeada<T> slice(int start, int end) {
        if (start < 0 || end > size || start > end) {
            throw new IndexOutOfBoundsException("Intervalo inválido");
        }

        ListaEncadeada<T> subList = new ListaEncadeada<>();
        
        Node<T> current = head;
        int index = 0;

        while (index < start) {
            current = current.getNext();
            index++;
        }

        while (index < end) {
            subList.addLast(current.getData());
            current = current.getNext();
            index++;
        }

        return subList;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private Node<T> current = head;

            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public T next() {
                if (!hasNext()) throw new NoSuchElementException();
                T data = current.getData();
                current = current.getNext();
                return data;
            }
        };
    }
}