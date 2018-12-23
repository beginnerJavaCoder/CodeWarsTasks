/*
Original task - https://www.codewars.com/kata/the-lift

Synopsis:
A multi-floor building has a Lift in it.
People are queued on different floors waiting for the Lift.
Some people want to go up. Some people want to go down.
The floor they want to go to is represented by a number
(i.e. when they enter the Lift this is the button they will press)

BEFORE (people waiting in queues)               AFTER (people at their destinations)
                   +--+                                          +--+
  /----------------|  |----------------\        /----------------|  |----------------\
10|                |  | 1,4,3,2        |      10|             10 |  |                |
  |----------------|  |----------------|        |----------------|  |----------------|
 9|                |  | 1,10,2         |       9|                |  |                |
  |----------------|  |----------------|        |----------------|  |----------------|
 8|                |  |                |       8|                |  |                |
  |----------------|  |----------------|        |----------------|  |----------------|
 7|                |  | 3,6,4,5,6      |       7|                |  |                |
  |----------------|  |----------------|        |----------------|  |----------------|
 6|                |  |                |       6|          6,6,6 |  |                |
  |----------------|  |----------------|        |----------------|  |----------------|
 5|                |  |                |       5|            5,5 |  |                |
  |----------------|  |----------------|        |----------------|  |----------------|
 4|                |  | 0,0,0          |       4|          4,4,4 |  |                |
  |----------------|  |----------------|        |----------------|  |----------------|
 3|                |  |                |       3|            3,3 |  |                |
  |----------------|  |----------------|        |----------------|  |----------------|
 2|                |  | 4              |       2|          2,2,2 |  |                |
  |----------------|  |----------------|        |----------------|  |----------------|
 1|                |  | 6,5,2          |       1|            1,1 |  |                |
  |----------------|  |----------------|        |----------------|  |----------------|
 G|                |  |                |       G|          0,0,0 |  |                |
  |====================================|        |====================================|

Rules:

Lift Rules:
- The Lift only goes up or down!
- Each floor has both UP and DOWN Lift-call buttons (
except top and ground floors which have only DOWN and UP respectively).
- The Lift never changes direction until there are no more people wanting
to get on/off in the direction it is already travelling.
- When empty the Lift tries to be smart. For example,
- If it was going up then it may continue up to collect the highest floor person wanting to go down.
- If it was going down then it may continue down to collect the lowest floor person wanting to go up.
- The Lift has a maximum capacity of people.
- When called, the Lift will stop at a floor even if it is full,
although unless somebody gets off nobody else can get on!
- If the lift is empty, and no people are waiting, then it will return to the ground floor.

People Rules:

- People are in "queues" that represent their order of arrival to wait for the Lift.
- All people can press the UP/DOWN Lift-call buttons.
- Only people going the same direction as the Lift may enter it, and they do so according to their "queue" order.
- If a person is unable to enter a full Lift,
they will press the UP/DOWN Lift-call button again after it has departed without them

Kata Task:

Get all the people to the floors they want to go to while obeying the Lift rules and the People rules.
Return a list of all floors that the Lift stopped at (in the order visited!).
NOTE: The Lift always starts on the ground floor (and people waiting on the ground floor may enter immediately).

I/O:

Input:

queues a list of queues of people for all floors of the building.
The height of the building varies.
0 = the ground floor.
Not all floors have queues.
Queue index [0] is the "head" of the queue.
Numbers indicate which floor the person wants go to.
capacity maximum number of people allowed in the lift.
Parameter validation - All input parameters can be assumed OK. No need to check for things like:
People wanting to go to floors that do not exist;
People wanting to take the Lift to the floor they are already on;
Buildings with < 2 floors;
Basements;

Output:

A list of all floors that the Lift stopped at (in the order visited!).

Example:
Refer to the example test cases https://www.codewars.com/kata/the-lift
 */
 
import java.util.ArrayList;

public class Dinglemouse {

    public static int[] theLift(final int[][] queues, final int capacity) {
        ArrayList<ArrayList<Integer>> currentQueues = copyQueues(queues);
        if(isQueuesAreEmpty(currentQueues)) return new int[]{0};
        return toArray(calculateListOfFloors(currentQueues, capacity));
    }

    //emulate lift's work and return the list of floors where he stayed
    private static ArrayList<Integer> calculateListOfFloors(ArrayList<ArrayList<Integer>> currentQueues, int capacity) {
        ArrayList<Integer> listOfFloors = new ArrayList<>();
        int quantityOfFloors = currentQueues.size();
        ArrayList<Integer> personsInTheLift = new ArrayList<>(capacity);

        int currentFloor = 0;
        int nextFloor;
        boolean isGoingUp = true;
        if (currentQueues.get(currentFloor).size() != 0) {
            for (int i = 0; i < currentQueues.get(currentFloor).size(); i++) {
                if (currentQueues.get(currentFloor).get(i) > currentFloor) {
                    pushPersons(currentQueues.get(currentFloor), currentFloor, personsInTheLift, capacity, isGoingUp);
                    break;
                }
            }
        }
        listOfFloors.add(0);
        while (true) {
            nextFloor = getNearestCall(currentQueues, quantityOfFloors, currentFloor, isGoingUp);
            if (personsInTheLift.isEmpty()) {
                if (nextFloor == -1) {
                    if(isQueuesAreEmpty(currentQueues)) break;
                    if (isGoingUp) currentFloor = quantityOfFloors;
                    else currentFloor = -1;
                    isGoingUp = !isGoingUp;
                    continue;

                }
                currentFloor = nextFloor;
                pushPersons(currentQueues.get(currentFloor), currentFloor, personsInTheLift, capacity, isGoingUp);
                if(listOfFloors.get(listOfFloors.size()-1) != currentFloor) listOfFloors.add(currentFloor);
            } else {
                int nextNeeded = getNearestNeededFloor(personsInTheLift, isGoingUp);
                if (nextFloor == -1) {
                    currentFloor = nextNeeded;
                    popPersons(personsInTheLift, currentFloor);
                    pushPersons(currentQueues.get(currentFloor), currentFloor, personsInTheLift, capacity, isGoingUp);
                    listOfFloors.add(currentFloor);
                } else {
                    if (isGoingUp) {
                        if (nextNeeded < nextFloor) {
                            currentFloor = nextNeeded;
                        } else currentFloor = nextFloor;
                    } else {
                        if (nextNeeded > nextFloor) {
                            currentFloor = nextNeeded;
                        } else currentFloor = nextFloor;
                    }
                    popPersons(personsInTheLift, currentFloor);
                    pushPersons(currentQueues.get(currentFloor), currentFloor, personsInTheLift, capacity, isGoingUp);
                    listOfFloors.add(currentFloor);
                }
            }
        }
        if(listOfFloors.get(listOfFloors.size() - 1) != 0) listOfFloors.add(0);

        return listOfFloors;
    }
    
    /*
    return the number of a floor nearest to the lift
    and that have calling person who want to go in the current direction
     */
    private static int getNearestCall(ArrayList<ArrayList<Integer>> currentQueues, int quantityOfFloors, int currentFloor, boolean isGoingUp) {
        if (isGoingUp) {
            for (int i = currentFloor + 1; i < quantityOfFloors; i++) {
                int size = currentQueues.get(i).size();
                if (size != 0) {
                    for (int j = 0; j < size; j++) {
                        if (currentQueues.get(i).get(j) > i) {
                            return i;
                        }
                    }
                }
            }
        } else {
            for (int i = currentFloor - 1; i >= 0; i--) {
                int size = currentQueues.get(i).size();
                if (size != 0) {
                    for (int j = 0; j < size; j++) {
                        if (currentQueues.get(i).get(j) < i) {
                            return i;
                        }
                    }
                }
            }
        }

        return -1; //when all highest or lowest floors have not persons who want to go in current direction
    }

    //return the number of a floor is nearest to the current floor and needed to persons, who placed in the lift
    private static int getNearestNeededFloor(ArrayList<Integer> personsInTheLift, boolean isGoingUp) {
        int nearest = personsInTheLift.get(0);

        if (isGoingUp) {
            for (int neededFloor : personsInTheLift) {
                if (neededFloor < nearest) nearest = neededFloor;
            }
        } else {
            for (int neededFloor : personsInTheLift) {
                if (neededFloor > nearest) nearest = neededFloor;
            }
        }

        return nearest;
    }

    private static void pushPersons(ArrayList<Integer> currentQueueOnFloor, int currentFloor,
                                    ArrayList<Integer> personsInTheLift, int capacity, boolean isGoingUp) {
        if (currentQueueOnFloor.size() == 0) return;
        int index = 0;
        if (isGoingUp) {
            while (index < currentQueueOnFloor.size()) {
                if (currentQueueOnFloor.get(index) > currentFloor) {
                    if (personsInTheLift.size() < capacity) {
                        personsInTheLift.add(currentQueueOnFloor.get(index));
                        currentQueueOnFloor.remove(index);
                    } else break;
                } else index++;
            }
        } else {
            while (index < currentQueueOnFloor.size()) {
                if (currentQueueOnFloor.get(index) < currentFloor) {
                    if (personsInTheLift.size() < capacity) {
                        personsInTheLift.add(currentQueueOnFloor.get(index));
                        currentQueueOnFloor.remove(index);
                    } else break;
                } else index++;
            }
        }
    }

    private static void popPersons(ArrayList<Integer> personsInTheLift, int currentFloor) {
        int index = 0;
        while (index < personsInTheLift.size()) {
            if(personsInTheLift.get(index) == currentFloor) personsInTheLift.remove(index);
            else index++;
        }
    }

    //check the floors for emptiness
    private static boolean isQueuesAreEmpty(ArrayList<ArrayList<Integer>> currentQueues) {
        for (ArrayList<Integer> queue : currentQueues) {
            if (queue.size() != 0) return false;
        }

        return true;
    }

    //copy initial int[][] queues to ArrayList<ArrayList<Integer>> currentQueues for possible to change them
    private static ArrayList<ArrayList<Integer>> copyQueues(int[][] queues) {
        int size = queues.length;
        ArrayList<ArrayList<Integer>> currentQueues = new ArrayList<>(size);
        for (int[] queue : queues) {
            currentQueues.add(toArrayList(queue));
        }
        return currentQueues;
    }

    private static ArrayList<Integer> toArrayList(int[] array) {
        ArrayList<Integer> list = new ArrayList<>(array.length);
        for (int tmp : array) {
            list.add(tmp);
        }
        return list;
    }

    private static int[] toArray(ArrayList<Integer> list) {
        int length = list.size();
        int[] array = new int[length];
        for (int i = 0; i < length; i++) {
            array[i] = list.get(i);
        }
        return array;
    }
}