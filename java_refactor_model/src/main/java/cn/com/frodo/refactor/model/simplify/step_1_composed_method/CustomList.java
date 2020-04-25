package cn.com.frodo.refactor.model.simplify.step_1_composed_method;

/**
 * 把一个方法分解成一组行为的时候，要保证这些行为在细节相似的层面上.
 *
 * @author frodoking
 */
public class CustomList {
    // *****原始做法******
    private boolean readOnly = false;
    private int size;
    Object[] elements = new Object[10];

    public void add(Object element) {
        if (!readOnly) {
            int newSize = size + 1;
            if (newSize > elements.length) {
                Object[] newElements = new Object[elements.length + 10];
                for (int i = 0; i < size; i++) {
                    newElements[i] = elements[i];
                }

                elements = newElements;
            }

            elements[size++] = element;
        }
    }

    // *********refactor**********
    private final static int GROWTH_INCREMENT = 10;

    public void add2(Object element) {
        if (readOnly) {
            return;
        }

        if (atCapacity())
            grow();

        addElement(element);
    }

    private boolean atCapacity() {
        return (size + 1) > elements.length;
    }

    private void grow() {
        Object[] newElements = new Object[elements.length + GROWTH_INCREMENT];
        for (int i = 0; i < size; i++) {
            newElements[i] = elements[i];
        }

        elements = newElements;
    }

    private void addElement(Object element) {
        elements[size++] = element;
    }
}
