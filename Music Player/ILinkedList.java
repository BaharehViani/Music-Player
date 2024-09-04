import java.util.Iterator;

public interface ILinkedList<T> {

    public int size();
    public boolean isEmpty();

    public void add(String name, String path);
    public String remove(String name);
    public void play(String name);

    public Iterator<String> iterateByName();
    public Iterator<String> iterateByShuffle();
    public Iterator<String> iterateByFrequency();

    public static <T> LinkedList<T> create(){
        return new LinkedList<T>();
    }
}