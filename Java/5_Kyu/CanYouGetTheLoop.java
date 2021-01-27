/*
Original task - https://www.codewars.com/kata/52a89c2ea8ddc5547a000863

You are given a node that is the beginning of a linked list. 
This list always contains a tail and a loop.
Your objective is to determine the length of the loop.

// Use the `getNext()` method to get the following node.
node.getNext()

Note: do NOT mutate the nodes!
 */
 
import java.util.ArrayList;

public class LoopInspector {
    public int loopSize(Node node) {
        int index = 0;
        ArrayList<Node> list = new ArrayList<>();
        while (true) {
            list.add(node);
            index++;
            node = node.getNext();
            for (int i = 0; i < list.size(); i++) {
                if (node == list.get(i)) {
                    return index - i;
                }
            }
        }
    }
}
