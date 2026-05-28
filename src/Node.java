public class Node {
    Question data;
    Node next;
    Node prev;

    public Node(Question data) {
        this.data = data;
        this.next = null;
        this.prev = null;
    }
}
