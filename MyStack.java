import java.util.LinkedList;

public class MyStack<E>{
    private LinkedList<E> list;
    
    public MyStack(){
        list = new LinkedList<E>();
    }
    
    public E peek(){
        if(isEmpty()){
            return null;
        }
        return list.get(0);
    }
    
    public void push(E item){
        list.add(0, item);
    }
    
    public E pop(){
        if(isEmpty()){
            return null;
        }
        E temp = list.get(0);
        list.remove(0);
        return temp;
    }
    
    public boolean isEmpty(){
        return list.isEmpty();
    }
    
    public int size(){
        return list.size();
    }
}