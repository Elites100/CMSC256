package cmsc256;
import java.util.Arrays;


public class ArrayBasedStack <T> implements StackInterface <T>  {
    private T[] data;

    private int topOfStack;

    private static final int INITIAL_CAPACITY = 5;

    public ArrayBasedStack(){
        T[] tempStack= (T[])new Object[INITIAL_CAPACITY];
        data= tempStack;
    }

    //constructor
    public ArrayBasedStack(int capacity){
        if(capacity <= 0) {
            throw new EmptyStackException();
        }
        clear();
    }

    public void expandArray(){
        T[] copy = Arrays.copyOf(data, data.length * 2 );
        data = copy;
    }

    public boolean isArrayFull(){
        if(topOfStack>= data.length -1 ) {
            return true;
        }
        else
            return false;
    }

    @Override
    public void push(T newEntry) {
        if(isArrayFull()){
            expandArray();
        }

        topOfStack++;
        data[topOfStack] = newEntry;
    }

    @Override
    public T pop() {
        if(data.length == 0 || topOfStack <= 0) {
            throw new EmptyStackException();
        }
        return data[topOfStack--];
    }

    @Override
    public T peek() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        return data[topOfStack];
    }

    public boolean isEmpty() {
        if (topOfStack > 0) {
            return false;
        }
        return true;
    }

    @Override
    public void clear() {
        @SuppressWarnings("unchecked")

        T[] tempStack = (T[])new Object[INITIAL_CAPACITY];

        data = tempStack;

        topOfStack = -1;
    }
}