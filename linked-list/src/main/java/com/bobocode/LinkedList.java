package com.bobocode;

import java.util.Objects;

/**
 * {@link LinkedList} is a list implementation that is based on singly linked generic nodes. A node is implemented as
 * inner static class {@link Node<T>}. In order to keep track on nodes, {@link LinkedList} keeps a reference to a head node.
 *
 * @param <T> generic type parameter
 */
public class LinkedList<T> implements List<T> {

    private Node<T> head = null;
    private int size = 0;

    /**
     * This method creates a list of provided elements
     *
     * @param elements elements to add
     * @param <T>      generic type
     * @return a new list of elements the were passed as method parameters
     */
    public static <T> List<T> of(T... elements) {
        List<T> newList = new LinkedList<>();
        for (T element : elements) {
            newList.add(element);
        }
        return newList;
    }

    /**
     * Adds an element to the end of the list
     *
     * @param element element to add
     */
    @Override
    public void add(T element) {
        if(head == null)
            head = new Node<>(element) ;
        else
        {
            Node<T> current = head;
            while(current.nextNode != null)
            {
                current = current.nextNode;
            }
            current.nextNode = new Node<>(element);
        }
        ++size;
    }

    /**
     * Adds a new element to the specific position in the list. In case provided index in out of the list bounds it
     * throws {@link IndexOutOfBoundsException}
     *
     * @param index   an index of new element
     * @param element element to add
     */
    @Override
    public void add(int index, T element) {
        if (index == 0) {
            Node<T> next = head;
            head = new Node<>(element);
            head.nextNode = next;
        }
        else {
            Node<T> beforeElement = moveToNode(index-1);
            Node<T> newNode = new Node<>(element);
            newNode.nextNode = beforeElement.nextNode;
            beforeElement.nextNode = newNode;
        }
        size++;
    }

    /**
     * Changes the value of an list element at specific position. In case provided index in out of the list bounds it
     * throws {@link IndexOutOfBoundsException}
     *
     * @param index   an position of element to change
     * @param element a new element value
     */
    @Override
    public void set(int index, T element) {
        moveToNode(index).element = element;
    }

    /**
     * Retrieves an elements by its position index. In case provided index in out of the list bounds it
     * throws {@link IndexOutOfBoundsException}
     *
     * @param index element index
     * @return an element value
     */
    @Override
    public T get(int index) {

        return moveToNode(index).element;
    }

    /**
     * Removes an elements by its position index. In case provided index in out of the list bounds it
     * throws {@link IndexOutOfBoundsException}
     *
     * @param index element index
     */
    @Override
    public void remove(int index) {
        if(index == 0)
            head = head.nextNode;
        else {
            Node<T> beforeNode = moveToNode(index-1);
            beforeNode.nextNode = beforeNode.nextNode.nextNode;
        }
        size--;
    }


    /**
     * Checks if a specific exists in he list
     *
     * @return {@code true} if element exist, {@code false} otherwise
     */
    @Override
    public boolean contains(T element) {
        Node<T> current = head;
        while (current != null) {
            if (current.element.equals(element))
                return true;
            else
                current = current.nextNode;
        }
        return false;
    }

    /**
     * Checks if a list is empty
     *
     * @return {@code true} if list is empty, {@code false} otherwise
     */
    @Override
    public boolean isEmpty() {
        return (size == 0);
    }

    /**
     * Returns the number of elements in the list
     *
     * @return number of elements
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Removes all list elements
     */
    @Override
    public void clear() {
        head = null;
        size = 0;
    }

    private Node<T> moveToNode(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index > (size-1))
            throw new IndexOutOfBoundsException();
        Node<T> current = head;
        for (int i = 0; i < index; i++) {
            current = current.nextNode;
        }
        return current;
    }

    private static class Node<T>{
        private T element;
        private Node<T> nextNode = null;

        public Node(T element) {
            this.element = element;
        }
    }
}
