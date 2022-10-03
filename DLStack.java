/**
 * DLStack.java
 * This class represents an extended stack ADT
 * Implemented using a doubly linked list 
 * @author Edwin Or
 */
public class DLStack<T> implements DLStackADT<T>{
	
	/* Attribute: */
    /* Reference to the node at the top of the stack*/
	private DoubleLinkedNode<T> top;
	/* Reference to the number of data items in the stack*/
	int numItems;
	
	/** Constructor: Creates an empty stack
	 * Sets top to null and numItems to zero
     */	
	public DLStack() {
		top = null;
		numItems = 0;
	}

	/** Method to add dataItems to top of stack
     */
	public void push(T dataItem) {
		//creates a stack containing dataItem
		DoubleLinkedNode<T> node = new DoubleLinkedNode<T>(dataItem);
        
		//if top is empty
        if(top == null) {
        	top = node;
        } 
        else {
        	//sets top to the next node
            top.setNext(node);
            node.setPrevious(top);
            top = node;
        }
        //adds numItems 
        numItems++;
	}

	/** Method to remove and returns the dataItem at the top of the stack
	 * Throws an exception if the stack is empty
     */
	public T pop() throws EmptyStackException {
		//if numitems is zero
		if(numItems == 0) {
			//throws emptystackexception
			throw new EmptyStackException("Invalid");
		}
        //setting pop variable as the top
        T pop = top.getElement();
        
        //if theres only one item, then sets the top as null or empty
        if(numItems == 1) {
            top = null;
        }
        //gets value of the top and sets it as null
        else {
            top = top.getPrevious(); 
            top.setNext(null);    
        }
        //removes numITems
        numItems--;
        //returns the top that was popped
        return pop;
	}

	/** Method to Removes and returns the k-th data item
	 * from the top of the stack.
     */
	public T pop(int k) throws InvalidItemException {
		//setting item variable as the top
		T item = top.getElement();
		//if k is out of bounds, display invaliditemexception
		if (k > numItems || k <= 0) {
			throw new InvalidItemException("Invalid");
		}
		
		//if k is the top value, it pops the stack
		if (k == 1) {
			item = this.pop();
		}
		else {
			//sets traverse variable as top
			DoubleLinkedNode<T> traverse = top;
			for (int i = 0; i < k-1; i++) {
				//keeps on gettingPrevious from traverse variable
				traverse = traverse.getPrevious();
			}
			//sets item from traverse variable
			item = traverse.getElement();
			//k equal to numitems, sets last element of the stack to null
			if(k == numItems) {
				traverse = traverse.getNext();
				traverse.setPrevious(null);
			}
			else {
				DoubleLinkedNode<T> reverseT = traverse.getPrevious();
				traverse = traverse.getNext();
				traverse.setPrevious(reverseT);
				reverseT.setNext(traverse);
			}
		}
		//removes one numItems
		numItems--;
		return item;
	}
	
	/** Method to peek the top of the stack
     */
	public T peek() throws EmptyStackException {
		//if the stack is empty, throw emptystackexception
		if (isEmpty()) {
			throw new EmptyStackException("Invalid"); 
		}
		//return top element
		return top.getElement();
	}
	/** Method to check if stack isEmpty or not
     */
	public boolean isEmpty() {
		return (numItems == 0);
	}
	/** Method to return numbers of items in stack
     */
	public int size() {
		return numItems;
	}
	/** Method to return the top of the stack
     */
	public DoubleLinkedNode<T> getTop() {
		return top;
	}
	/** Method to return a string in a format
     */
	public String toString() {
		String result = "";
	    DoubleLinkedNode current = top;

	    while (current != null)
	    {
	      result = result + (current.getElement()).toString() + "\n";
	      current = current.getNext();
	    }
	    return result;
	}
}