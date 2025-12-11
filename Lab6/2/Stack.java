public class Stack<T> {
    private T[] data;
    private int size;
    private static final int DEFAULT_CAPACITY = 10;

    public Stack() {
        this(DEFAULT_CAPACITY);
    }

    @SuppressWarnings("unchecked")
    public Stack(int capacity) {
        data = (T[]) new Object[capacity];
        size = 0;
    }

    public void push(T element) {
        if (size == data.length) {
            resize();
        }
        data[size++] = element;
    }

    public T pop() {
        if (isEmpty()) {
            throw new RuntimeException("Стек пуст");
        }
        T element = data[--size];
        data[size] = null;
        return element;
    }

    public T peek() {
        if (isEmpty()) {
            throw new RuntimeException("Стек пуст");
        }
        return data[size - 1];
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    @SuppressWarnings("unchecked")
    private void resize() {
        T[] newData = (T[]) new Object[data.length * 2];
        System.arraycopy(data, 0, newData, 0, size);
        data = newData;
    }

    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>(5);
        stack.push(1);
        stack.push(2);
        stack.push(3);
        System.out.println("Верхний элемент: " + stack.peek());
        System.out.println("Извлечён: " + stack.pop());
        System.out.println("Новый верхний: " + stack.peek());
        stack.push(4);
        System.out.println("Размер стека: " + stack.size());
        System.out.println("Стек пуст? " + stack.isEmpty());
    }
}