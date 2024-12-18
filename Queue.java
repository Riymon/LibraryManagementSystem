import java.util.LinkedList;

public class Queue<T> { // Declare the class as generic with <T>
    private LinkedList<T> list; // Use T as the type parameter

    public Queue() {
        list = new LinkedList<>();
    }

    // Add an element to the queue
    public void enqueue(T data) {
        list.add(data);
    }

    // Remove and return the front element
    public T dequeue() {
        return list.isEmpty() ? null : list.removeFirst();
    }

    // Check if the queue is empty
    public boolean isEmpty() {
        return list.isEmpty();
    }

    // Peek at the front element without removing
    public T peek() {
        return list.isEmpty() ? null : list.getFirst();
    }

    // Remove an element by condition
    public boolean removeIf(java.util.function.Predicate<T> condition) {
        return list.removeIf(condition);
    }
}
