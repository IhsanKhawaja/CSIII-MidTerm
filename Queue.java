import java.util.LinkedList;

public class Queue<E>{
    private LinkedList list;
    public Queue(){
        list = new LinkedList();
    }
    public boolean isEmpty(){
        return list.isEmpty();
    }
    public int size(){
        return list.size();
    }
    public E peek(){
        if(isEmpty()){
            return null;
        }
        return (E) list.get(0);
    }
    public E remove(){
        if(isEmpty()){
            return null;
        }
        E obj = (E) list.get(0);
        list.remove(0);
        return obj;
    }
    public boolean add(E item){
        return list.add(item);
    }
}
