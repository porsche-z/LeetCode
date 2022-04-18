import java.util.*;

public class Solution {
    public ListNode mergeKLists(ListNode[] list){
        PriorityQueue<Entity> PQofHead = new PriorityQueue<>(new entityComparator());
        linkedList resWithEmptyHead = new linkedList();
        for(int i = 0; i < list.length; i++){
            if(list[i] != null){
                PQofHead.add(new Entity(list[i].val, i));
                list[i] = list[i].next;
            }
        }
        while (!PQofHead.isEmpty()){
            Entity curEntity = PQofHead.poll();
            resWithEmptyHead.addNode(new ListNode(curEntity.val));
            if(list[curEntity.index] != null){
                PQofHead.add(new Entity(list[curEntity.index].val, curEntity.index));
                list[curEntity.index] = list[curEntity.index].next;
            }
        }
        return resWithEmptyHead.head.next;
    }
}

class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}

class linkedList{//a linkedList with empty head
    ListNode head;
    ListNode tail;
    public linkedList(){
        head = new ListNode(-1, null);
        tail = head;
    }
    public void addNode(ListNode Node){
        tail.next = Node;
        tail = Node;
    }
}

class Entity{
    int val;
    int index;
    public Entity(int val, int index){
        this.val = val;
        this.index = index;
    }
}

class entityComparator implements Comparator<Entity>{
    @Override
    public int compare(Entity a, Entity b) {
       return a.val - b.val;
    }
}