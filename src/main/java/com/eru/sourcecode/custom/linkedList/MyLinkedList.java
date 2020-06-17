package com.eru.sourcecode.custom.linkedList;

/**
 * 实简单现一个LinkedList
 * Created by eru on 2020/6/17.
 */
public class MyLinkedList<E> {

    /** Node size */
    private int size;

    /** 头节点 */
    private Node<E> first;

    /** 尾节点 */
    private Node<E> last;

    /**
     * 根据传入的对象删除Node
     * @param o 传入的对象
     * @return 被删除的Node.item
     */
    public E remove(Object o){
        int index = indexOf(o);
        checkRange(index);
        return unlink(index);
    }

    /**
     * 根据传入的下标删除Node
     * @param index 下标
     * @return 被删除的Node.item
     */
    public E remove(int index){
        checkRange(index);
        return unlink(index);
    }

    /**
     * 1.删除的Node为头节点
     * 2.删除的Node为尾结点
     * 3.删除的Node不为头节点也不为尾结点
     * @param index 角标
     * @return Node.item
     */
    private E unlink(int index) {
        Node<E> current = binarySearch(index);
        final E item = current.item;
        final Node<E> prevNode = current.prev;
        final Node<E> nextNode = current.next;

        if (prevNode == null){
            first = nextNode;
        }else{
            prevNode.next = nextNode;
            current.next = null;
        }

        if (nextNode == null){
            last = prevNode;
        }else{
            nextNode.prev = prevNode;
            current.prev = null;
        }

        size--;
        return item;
    }

    /**
     * 根据object查找对应的下标
     * @param o 传入的对象
     * @return 下标
     */
    public int indexOf(Object o){
        int index = 0;
        if (o == null){
            for (Node<E> f = first; f != null; f = f.next){
                if (f.item == null){
                    return index;
                }
                index++;
            }
        }else{
            for (Node<E> f = first; f != null; f = f.next){
                if (o.equals(f.item)){
                    return index;
                }
                index++;
            }
        }
        return -1;
    }

    /**
     * 根据角标获取Node的值
     * @param index 角标
     * @return E
     */
    public E get(int index){
        checkRange(index);
        return binarySearch(index).item;
    }

    private void checkRange(int index) {
        if (index >= size || index < 0){
            throw new IndexOutOfBoundsException("角标越界index=" + index + "size=" + size);
        }
    }

    /**
     * 二分查找
     * @param index 角标
     * @return Node
     */
    Node<E> binarySearch(int index){
        if (index < (size >> 1)){
            Node<E> f = first;
            for (int i = 0; i < index; i++) {
                f = f.next;
            }
            return f;
        }else{
            Node<E> l = last;
            for (int i = size - 1; i > index; i--) {
                l = l.prev;
            }
            return l;
        }
    }

    /**
     * 添加e到最前
     * @param e element
     * @return boolean
     */
    public boolean addFirst(E e){
        LinkFirst(e);
        return true;
    }

    void LinkFirst(E e){
        final Node<E> f = first;
        Node<E> newNode = new Node<>(null, f, e);
        first = newNode;
        if (f == null){
            last = newNode;
        }else{
            f.prev = newNode;
        }
        size++;
    }

    /**
     * 添加e到最后
     * @param e element
     * @return boolean
     */
    public boolean addLast(E e){
        linkLast(e);
        return true;
    }

    void linkLast(E e) {
        // 如果尾结点为null, 这时候头尾节点都是null, 那么新增的node就是尾结点(也是头节点)
        // 不为空那么尾结点指向新node
        final Node<E> l = last;
        Node<E> newNode = new Node<>(l, null, e);
        last = newNode;
        if (l == null){
            first = newNode;
        }else{
            l.next = newNode;
        }
        size++;
    }

    /**
     * 链表中存储的Node
     */
    class Node<E>{
        /** 前一个node */
        private Node<E> prev;

        /** 后一个node */
        private Node<E> next;

        /** 存储的元素 */
        private E item;

        public Node(Node<E> prev, Node<E> next, E item) {
            this.prev = prev;
            this.next = next;
            this.item = item;
        }
    }
}
