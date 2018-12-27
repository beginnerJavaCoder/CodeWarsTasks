/*
Original task - https://www.codewars.com/kata/linked-lists-append

Linked Lists - Append
Write an append() function which appends one linked list to another. 
The head of the resulting list should be returned.

var listA = 1 -> 2 -> 3 -> null
var listB = 4 -> 5 -> 6 -> null
append(listA, listB) === 1 -> 2 -> 3 -> 4 -> 5 -> 6 -> null
If both listA and listB are null, return null. 
If one list is null and the other is not, simply return the non-null list.
 */
 
class Node {

	int data;
	Node next = null;

	Node(final int data) {
	    this.data = data;
	}

	public Node(int data, Node next) {
	    this.data = data;
	    this.next = next;
	}

	public static Node append(Node listA, Node listB) {
        if(listA == null & listB == null) return null;
        if(listA == null) return listB;
        Node tailOfListA = listA;
        while(tailOfListA.next != null) tailOfListA = tailOfListA.next;
        tailOfListA.next = listB;
		return listA;
	}
}