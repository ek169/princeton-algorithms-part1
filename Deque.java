import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {

    private Node start;
    private Node end;
    private int totalItems = 0;

    public Deque() {
        start = null;
        end = null;
    }

    private class Node {
        private Item item;
        private Node towardsFront;
        private Node towardsEnd;
    }

    public boolean isEmpty() {
        return totalItems == 0;
    }

    public int size() {
        return totalItems;

    }

    public void addFirst(Item item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }

        Node addedItem = new Node();
        addedItem.item = item;

        if (isEmpty()) {
            start = addedItem;
            end = addedItem;
        } else {
            addedItem.towardsEnd = start;
            start.towardsFront = addedItem;
            start = addedItem;
        }

        totalItems++;
    }

    public void addLast(Item item)  {
        if (item == null) {
            throw new IllegalArgumentException();
        }

        Node addedItem = new Node();
        addedItem.item = item;

        if (isEmpty()) {
            start = addedItem;
            end = addedItem;
        } else {
            addedItem.towardsFront = end;
            end.towardsEnd = addedItem;
            end = addedItem;
        }
        totalItems++;

    }

    public Item removeFirst() {
        if (start == null) {
            throw new NoSuchElementException();
        }

        Item item = start.item;

        if (totalItems != 1) {
            start = start.towardsEnd;
            start.towardsFront = null;
        }
        totalItems--;
        return item;
    }

    public Item removeLast() {
        if (end == null) {
            throw new NoSuchElementException();
        }
        Item item = end.item;
        if (totalItems != 1) {
            end = end.towardsFront;
            end.towardsEnd = null;
        }
        totalItems--;
        return item;
    }

    public Iterator<Item> iterator() { return new ListIterator(); }

    private class ListIterator implements Iterator<Item> {

        private Node current = start;

        public boolean hasNext() {
            return current != null;
        }

        public void remove() { throw new UnsupportedOperationException(); }

        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            Item item = current.item;
            current = current.towardsEnd;
            return item;
        }

    }

    public static void main(String[] args) {
        // For testing
    }

}























