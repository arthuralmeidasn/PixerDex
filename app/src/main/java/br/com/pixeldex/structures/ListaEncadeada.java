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

    // --- REMOÇÕES ---

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