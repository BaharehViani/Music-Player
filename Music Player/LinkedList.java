import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedList<T> implements ILinkedList<T> {

    private class Node {

        private String m_name;
        private File m_file;
        private Node m_next;
        private Node m_prev;
        private int m_timesPlayed;

        public Node(String name, File file, Node next, Node prev) {
            m_name = name;
            m_file = file;
            m_next = next;
            m_prev = prev;
            m_timesPlayed = 0;
        }

        public String getName() {
            return m_name;
        }
        public File getFile() {
            return m_file;
        }
        public Node getNext() {
            return m_next;
        }
        public void setNext(Node next) {
            m_next = next;
        }
        public Node getPrev() {
            return m_prev;
        }
        public void setPrev(Node prev) {
            m_prev = prev;
        }
        public int getTimesPlayed() {
            return m_timesPlayed;
        }
    }

    private class nameIterator implements Iterator<String> {

        ArrayList<String> arr = new ArrayList<String>();
        int i = -1;

        public nameIterator() {
            Node current = m_head.getNext();
            for (int i = 0; i < m_size; i++) {
                arr.add(current.getName());
                current = current.getNext();
            }
            arr.sort(String::compareToIgnoreCase);
        }

        @Override
        public boolean hasNext() {
            return i < arr.size()-1;
        }

        @Override
        public String next() {
            i++;
            if (i == arr.size()) {
                throw new NoSuchElementException();
            }
            return arr.get(i);
        }
    }

    private class shuffleIterator implements Iterator<String> {

        ArrayList<String> arr = new ArrayList<String>();
        int i = -1;

        public shuffleIterator() {
            Node current = m_head.getNext();
            for (int i = 0; i < m_size; i++) {
                arr.add(current.getName());
                current = current.getNext();
            }
            for (int i = 0; i < m_size-1; i++) {
                int randNum1 = (int) (Math.random() * m_size);
                int randNum2 = (int) (Math.random() * m_size);
                Collections.swap(arr, randNum1, randNum2);
            }
        }

        @Override
        public boolean hasNext() {
            return i < arr.size()-1;
        }

        @Override
        public String next() {
            i++;
            if (i == arr.size()) {
                throw new NoSuchElementException();
            }
            return arr.get(i);
        }
    }

    private class frequencyIterator implements Iterator<String> {

        ArrayList<String> arr = new ArrayList<String>();
        ArrayList<String> nameArray = new ArrayList<String>();
        ArrayList<Integer> freqArray = new ArrayList<Integer>();
        int i = -1;

        public frequencyIterator() {
            Node current = m_head.getNext();
            for (int i = 0; i < m_size; i++) {
                freqArray.add(current.getTimesPlayed());
                nameArray.add(current.getName());
                current = current.getNext();
            }
            for (int i = 0; i < m_size; i++) {
                String maxName = "";
                int max = -1;
                int k = 0;
                for (int j = 0; j < m_size-i; j++) {
                    if (freqArray.get(j) > max) {
                        max = freqArray.get(j);
                        maxName = nameArray.get(j);
                        k = j;
                    }
                }
                arr.add(maxName);
                freqArray.remove(k);
                nameArray.remove(k);
            }
        }

        @Override
        public boolean hasNext() {
            return i < arr.size()-1;
        }

        @Override
        public String next() {
            i++;
            if (i == arr.size()) {
                throw new NoSuchElementException();
            }
            return arr.get(i);
        }
    }

    private Node m_head;
    private Node m_tail;
    private int m_size;

    public LinkedList(){ 
        m_head = new Node(null, null, null, null); // head and tail are sentinels
        m_tail = new Node(null, null, null, null); // head and tail are sentinels
        m_head.setNext(m_tail);
        m_tail.setPrev(m_head);
        m_size = 0;
    }

    @Override
    public int size() {
        return m_size;
    }

    @Override
    public boolean isEmpty() {
        return m_size == 0;
    }

    @Override
    public void add(String name, String path) { // adds to first of the list
        // check if music exsists
        Node current = m_head.getNext();
        for (int i = 0; i < m_size; i++) {
            if (current.getFile().getPath().equals(path) || current.getName().equals(name)) {
                System.out.println("music already exists");
                return;
            }
            current = current.getNext();
        }
        //
        Node newNode = new Node(name, new File(path), m_head.getNext(), m_head);
        m_head.setNext(newNode);
        if (m_size == 0) {
            m_tail.setPrev(newNode);
        }
        newNode.getNext().setPrev(newNode);
        m_size++;
    }

    @Override
    public String remove(String name) {
        if (m_size == 0) {
            return null;
        }
        Node current = m_head.getNext();
        for (int i = 0; i < m_size; i++) {
            if (current.getName().equals(name)) {
                if (current.getPrev() == null) {
                    m_head.setNext(current.getNext());
                } else {
                    current.getPrev().setNext(current.getNext());
                }
                if (current.getNext() == null) {
                    m_tail.setPrev(current.getPrev());
                } else {
                    current.getNext().setPrev(current.getPrev());
                }
                m_size--;
                return current.getName();
            }
            current = current.getNext();
        }
        return null;
    }

    @Override
    public void play(String name) {
        Node current = m_head.getNext();
        for (int i = 0; i < m_size; i++) {
            if (current.getName().equals(name)) {
                current.m_timesPlayed++;
                return;
            }
            current = current.getNext();
        }
    }

    @Override
    public Iterator<String> iterateByName() {
        return new nameIterator();
    }

    @Override
    public Iterator<String> iterateByShuffle() {
        return new shuffleIterator();
    }

    @Override
    public Iterator<String> iterateByFrequency() {
        return new frequencyIterator();
    }

    @Override
    public String toString() {
        String str = "[ \n";
        Node current = m_head.getNext();
        for (int i = 0; i < m_size; i++) {
            str += " " + current.getName() + "\n";
            current = current.getNext();
        }
        str += "]";
        return str;
    }
}
