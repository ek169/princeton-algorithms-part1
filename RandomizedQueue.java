import java.util.Iterator;
import java.util.Random;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {

    private int numberItems = 0;
    private Item[] itemArray = (Item[]) new Object[1];
    private Random random;

    public RandomizedQueue() {
        random = new Random();
    }

    public boolean isEmpty() {
        return numberItems == 0;
    }

    public int size() {
        return numberItems;
    }

    public void enqueue(Item item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }
        push(item);
    }

    public Item dequeue() {
        int location = random.nextInt(numberItems);
        return pop(location);
    }

    public Item sample() {
        if (numberItems == 0) {
            throw new NoSuchElementException();
        }
        int location = random.nextInt(numberItems);
        return itemArray[location];
    }

    private void shuffle() {
        for (int i = 0; i < numberItems; i++) {
            int intA = random.nextInt(numberItems);
            int intB = random.nextInt(numberItems);
            if (intA != intB) {
                Item temp = itemArray[intA];
                itemArray[intA] = itemArray[intB];
                itemArray[intB] = temp;
            }

        }
    }

    public Iterator<Item> iterator() {
        shuffle();
        return new ListIterator();
    }



    private class ListIterator implements Iterator<Item> {

        private int current = 0;

        public boolean hasNext() {
            return itemArray[current] != null;
        }

        public void remove() { throw new UnsupportedOperationException(); }

        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            Item item = itemArray[current];
            current++;
            return item;
        }

    }

    private void push(Item item) {
        if (numberItems == itemArray.length) {
            resize(2 * itemArray.length);
        }
        itemArray[numberItems] = item;
        numberItems++;
    }

    private void resize(int capacity) {
        Item[] newArray = (Item[]) new Object[capacity];
        for (int i = 0; i < numberItems; i++) {
            newArray[i] = itemArray[i];
        }
        itemArray = newArray;
    }

    private Item pop(int location) {
        Item item = itemArray[location];
        if (numberItems > 0 && numberItems == itemArray.length/4) {
            resize(itemArray.length/2);
        }
        if (location == numberItems-1) {
            itemArray[location] = null;
        } else {
            itemArray[location] = itemArray[numberItems-1];
            itemArray[numberItems-1] = null;
        }
        numberItems--;
        return item;
    }

    public static void main(String[] args) {
        // For testing
    }
}
