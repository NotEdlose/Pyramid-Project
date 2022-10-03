/**
 * FindPath.java:
 * This class computes a path from entrance of the pyramid 
 * to all treasure chambers
 * @author Edwin Or
 */
public class FindPath {  
	/* Attribute: */
    /* Reference to creating an object from map class*/
    private Map pyramidMap;

    /** Constructor: Creates object from the map class
	 * passes in the parameter FileName
	 * @param string fileName
     */	
    public FindPath(String fileName){ 

        try {
            this.pyramidMap = new Map(fileName);

        } 
        catch (Exception e) {
            e.getMessage();
        }
    }
    
	/** Method to find a path from the entrance to all treasure chambers 
	 * Throws an exception if the stack is empty
     */
    public DLStack<Chamber> path() {  
    	//creates an empty stack for path variable
        DLStack<Chamber> path = new DLStack<Chamber>();
        //sets the entrance variable
        Chamber entrance = pyramidMap.getEntrance();
        //sets the number of treasureChambers
        int treasure = pyramidMap.getNumTreasures();
        int discovered = 0;
        
        //push the entrance variable into the path stack
        path.push(entrance);
        //mark the entrance variable as pushed
        entrance.markPushed();
        
        //while statement if path stack is empty
        while(!path.isEmpty()) { 
        	//peeks the top stack
            Chamber top = path.peek();
            
            //checks if top chamber is a treasure chamber and if all the treasure chambers are found, breaks while loop
            if(top.isTreasure() && discovered == treasure) {
                break; 
            }
            else {
            	//creates best variable from best chamber passing through top
                Chamber best = this.bestChamber(top);    
                
                //if best varaible is null
                if(best == null) {
                	//pops path variable 
                    best = path.pop(); 
                    best.markPopped();
                } 
                else {
                	//best variable is pushed
                    path.push(best);
                    //marked as pushed
                    best.markPushed();    
                    //if the treasure chamber is found, adds to discovered treasure chamber 
                    if(best.isTreasure()) {
                        discovered++;
                    }
                }
            }
            
        }
        //returns stack on which way to go
        return path;
    }
	/** Method to return pyramidMap
     */
    public Map getMap() {
        return pyramidMap;
    }
    
	/** Method to check if the chamber is dim
	 * @param passes through currentChamber
     */
    public boolean isDim(Chamber currentChamber) {
    	//sets dim as false
        boolean dim = false;
        
        //if current chamber is not null
        if(currentChamber != null) {
        	for(int i = 0; i < 6; i++) {
        		//creates an object neighbour
        		Chamber neighbour = currentChamber.getNeighbour(i);
        		//checks if current chamber is not sealed, not lighter, not null and is lighted, chamber is dim
        		if(!currentChamber.isSealed() && !currentChamber.isLighted() && neighbour != null && neighbour.isLighted()) {
        			dim = true;
                }
        	}

        }    
        //returns if chamber is dim or not
    	return dim;
    }
    
	/** Method to select the best chamber to move from current chamber
     */
    public Chamber bestChamber(Chamber currentChamber) {
    	//sets chamber to 6
    	int chambers = 6;
    	//sets treasure and dim is false
    	boolean treasure = false;
    	boolean dim = false;
    	
    	for (int i = 5; i >= 0; i--) {
    		//create an object from current chamber
			Chamber neighbour = currentChamber.getNeighbour(i);
			//if object is null, continues
			if(neighbour == null) {
				continue;
			}
			//if object is not marked and has a treasure, sets treasure is true
			else if (!neighbour.isMarked()) {
				if(neighbour.isTreasure()) {
					treasure = true;
					chambers = i;
				}
				//if object is lighter and treasure is false, sets chamber as dim
				else if(neighbour.isLighted() && treasure == false) {
					dim = true;
					chambers = i;
				}
				//if object is dim and treasure is false and dim is false
				else if (isDim(neighbour) && treasure == false && dim == false) {
					chambers = i;
					
				}
			}
			
    	}
    	//if chambers is equal to 6 returns null
    	if (chambers == 6) {
    		return null;
    	}
    	//returns the path to the best chamber
    	else {
    		return currentChamber.getNeighbour(chambers);
    	}
    }
}