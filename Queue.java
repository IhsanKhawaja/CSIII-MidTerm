import java.util.LinkedList;

public class Queue<E>{
    private LinkedList list;
    /*
    Sets the list to be a new LinkedList
     */
    public Queue(){
        list = new LinkedList();
    }
    /*
        Returns true if the list is empty and false if it isn't
     */
    public boolean isEmpty(){
        return list.isEmpty();
    }
    /*
        If its empty returns null if it isn't returns the first index of the list
     */
    public E getOldest(){
        if(isEmpty()){
            return null;
        }
        return (E) list.get(0);
    }
    /*
        If its empty removes the first thing
        If it isn't it gets the object being removed it removes the object then returns the removed object
     */
    public E remove(){
        if(isEmpty()){
            return null;
        }
        E obj = (E) list.get(0);
        list.remove(0);
        return obj;
    }
    /*
        Adds the new item to the end of the list
     */
    public void add(E item){
        list.add(item);
    }
}
