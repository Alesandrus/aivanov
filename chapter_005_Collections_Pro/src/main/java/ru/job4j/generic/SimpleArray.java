package ru.job4j.generic;

/**
 * SimpleArray.
 *
 * @author Alexander Ivanov
 * @since 31.03.2017
 * @version 1.0
 */
public class SimpleArray<E> {

    /**
     * Array of objects.
     */
    private Object[] arr;

    /**
     * Number of array elements.
     */
    private int size = 0;

    /**
     * Load capacity.
     */
    private int capacity;

    /**
     * Class constructor.
     */
    public SimpleArray() {
        capacity = 10;
        this.arr = new Object[capacity];
    }

    /**
     * method for increase array's capacity.
     * @return true if array is increased.
     */
    private boolean increaseCapacity() {
        if (size > capacity * 0.7) {
            Object[] newArr = new Object[capacity * 2];
            System.arraycopy(arr, 0, newArr, 0, size);
            this.arr = newArr;
            capacity *= 2;
            return true;
        } else {
            return false;
        }
    }

    /**
     * Adding element to array.
     * @param element element for adding.
     */
    public void add(E element) {
        increaseCapacity();
        arr[size++] = element;
    }

    /**
     * Deleting element from array.
     * @param index of element for delete it.
     * @return deleted element.
     */
    public E delete(int index) {
        if (index >= this.size || index < 0) {
            throw new IndexOutOfBoundsException();
        }
        E delElement = (E) arr[index];
        System.arraycopy(arr, index + 1, arr, index, this.size - index - 1);
        arr[--size] = null;
        return delElement;
    }

    /**
     * Getting element from array.
     * @param index of element for getting.
     * @return element.
     */
    public E get(int index) {
        if (index >= this.size || index < 0) {
            throw new IndexOutOfBoundsException();
        }
        return (E) arr[index];
    }

    /**
     * Update reference by index to new element.
     * @param index for updating.
     * @param element for setting.
     */
    public void update(int index, E element) {
        if (index >= this.size || index < 0) {
            throw new IndexOutOfBoundsException();
        }
        arr[index] = element;
    }

    /**
     * Getter for size.
     * @return size.
     */
    public int getSize() {
        return size;
    }

//delete main
    public static void main(String[] args) {
        SimpleArray<String> array = new SimpleArray<>();
        array.add("1");
        array.add("2");
        array.add("3");
        array.add("4");
        array.add("5");
        array.add("6");
        array.delete(5);
        System.out.println(array.getSize());
        System.out.println(array.capacity);
        for (int i = 0; i < array.getSize(); i++) {
            System.out.println(array.get(i));
        }
        array.update(2, "Top");
        for (int i = 0; i < array.getSize(); i++) {
            System.out.println(array.get(i));
        }
        byte[] a = "Ы".getBytes();
        for (byte r : a) {
            System.out.println(Byte.toUnsignedInt(r));
        }
    }
}
