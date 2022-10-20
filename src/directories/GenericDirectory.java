package directories;

import java.util.ArrayList;

/**
 * A generic class which is used to maintain an array list of generic objects.
 * @param <T> A generic object type.
 */
public class GenericDirectory<T> {
    protected ArrayList<T> directory;

    /**
     * A constructor for a GenericDirectory object.
     */
    public GenericDirectory() {
        directory = new ArrayList<>();
    }

    /**
     * A method which adds a generic object to a GenericDirectory object.
     * @param t A generic object.
     */
    public void addToDirectory(T t) {
        directory.add(t);
    }

    /**
     * A method which determines if a GenericDirectory object is empty or not.
     * @return A boolean true value if the GenericDirectory object is empty or a boolean false
     * value if the GenericDirectory is not empty.
     */
    public boolean isEmpty() {
        return directory.isEmpty();
    }

    /**
     * A method which returns a generic object at the specific element within a UserDirectory
     * object.
     * @param index An int which represents the index where the generic object is located within
     *              the GenericDirectory object.
     * @return A generic object.
     */
    public T get(int index) {
        return directory.get(index);
    }
}