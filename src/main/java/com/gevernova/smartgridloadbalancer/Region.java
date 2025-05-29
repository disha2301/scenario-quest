package com.gevernova.smartgridloadbalancer;

// region class to represent the power grid region
public class Region implements Comparable<Region> {
    private String id;
    private int currentLoad;
    private int capacity;
    private boolean isCritical; // marks if this is a critical region (hospitals, emergency services etc.)

    // constructor
    public Region(String id, int currentLoad, int capacity, boolean isCritical) {
        this.id = id;
        this.currentLoad = currentLoad;
        this.capacity = capacity;
        this.isCritical = isCritical;
    }

    // getters
    public String getId() { return id; }
    public int getCurrentLoad(){ return currentLoad;}
    public int getCapacity(){ return capacity;}
    public boolean isCritical(){
        return isCritical;
    }

    // calculate load percentage
    public double getLoadPercentage(){
        return (double) currentLoad / capacity * 100;
    }

    // update the load (positive or negative)
    public void updateLoad(int delta){
        currentLoad +=delta;
    }

    // Compare regions based on criticality and load percentage for priority queue
    @Override
    public int compareTo(Region other){
        if(this.isCritical != other.isCritical){
            return other.isCritical ? 1 : -1; // critical region comes first
        }
        return Double.compare(other.getLoadPercentage(), this.getLoadPercentage()); // higher load comes first
    }

    @Override
    public String toString(){ // provide a string representation of an object
            return String.format("Region{id='%s', load=%d/%d (%.2f%%), critical=%s}",
                    id, currentLoad, capacity, getLoadPercentage(), isCritical);
        }
}
