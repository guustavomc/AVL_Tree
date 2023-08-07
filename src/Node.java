public class Node {
    Node right;
    Node left;
    int key;
    int height;

    Node(int key){
        this.key = key;
        this.height = 1;
    }
}
