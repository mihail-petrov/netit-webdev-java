package data_structure;

import util.CustomCollectionUtil;

public class CustomStack {

    private Object[] stackCollection;
    private int stackSize            = 10;
    private int topPointer           = 0;
    private int stackCollectionIndex = 0;

    public CustomStack() {
        this.stackCollection = new Object[this.stackSize];
    }

    public void push(Object element) {

        try {
            this.stackCollection[this.stackCollectionIndex] = element; // 0
        }
        catch (ArrayIndexOutOfBoundsException exception) {

            // 1. Copy collection from original array collection to temporal collection
            Object[] temporalStackCollection = CustomCollectionUtil.copyCollection(this.stackCollection);

            // 2. Create new base collection with double base size
            this.stackSize       = this.stackSize * 2;
            this.stackCollection = new Object[this.stackSize];
            this.stackCollection = CustomCollectionUtil.mergeCollections(this.stackCollection, temporalStackCollection);
        }
        finally {
            this.topPointer = this.stackCollectionIndex;
            this.stackCollectionIndex++;
        }
    }

    // remove from stack and return the element
    public Object pop() {

        Object topElement = this.peek();
        removeTopElement();
        return topElement;
    }

    // reteurn the element withot removing it from the Stack
    public Object peek() {
        return this.stackCollection[this.topPointer];
    }

    public boolean isEmpty() {
        return this.topPointer < 0;
    }

    private void removeTopElement() {

        this.stackCollection[this.topPointer] = null;
        this.topPointer--;
        this.stackCollectionIndex--;
    }
}
