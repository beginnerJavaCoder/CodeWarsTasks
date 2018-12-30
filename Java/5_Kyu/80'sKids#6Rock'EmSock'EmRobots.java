/*
Original task - https://www.codewars.com/kata/80-s-kids-number-6-rock-em-sock-em-robots

You and your friends have been battling it out with your Rock 'Em, Sock 'Em robots,
but things have gotten a little boring.
You've each decided to add some amazing new features to your robot and automate them to battle to the death.

Each robot will be represented by an object.
You will be given two robot objects, and an object of battle tactics and how much damage they produce.
Each robot will have a name, hit points, speed, and then a list of battle tacitcs they are to perform in order.
Whichever robot has the best speed, will attack first with one battle tactic.
Your job is to decide who wins.

Example:
robot1.getName() => "Rocky"
robot1.getHealth() => 100
robot1.getSpeed() => 20
robot1.getTactics() => ["punch", "punch", "laser", "missile"]

robot2.getName() => "Missile Bob"
robot2.getHealth() => 100
robot2.getSpeed() => 21
robot2.getTactics() => ["missile", "missile", "missile", "missile"]

tactics = {
  "punch" => 20,
  "laser" => 30,
  "missile" => 35
}

fight(Robot robot1, Robot robot2, Map<String, Integer> tactics) -> "Missile Bob has won the fight."
robot2 uses the first tactic, "missile" because he has the most speed.
This reduces robot1's health by 35. Now robot1 uses a punch, and so on.

Rules
A robot with the most speed attacks first. If they are tied, the first robot passed in attacks first.
Robots alternate turns attacking. Tactics are used in order.
A fight is over when a robot has 0 or less health or both robots have run out of tactics.
A robot who has no tactics left does no more damage, but the other robot may use the rest of his tactics.
If both robots run out of tactics, whoever has the most health wins. Return the message "{Name} has won the fight."
If both robots run out of tactics and are tied for health, the fight is a draw. Return "The fight was a draw."

Robot class is immutable.
 */
 
import java.util.Map;

public class EightiesKids6 {

    public static String fight(Robot robot1, Robot robot2, Map<String, Integer> tactics) {
        if(robot2.getSpeed() > robot1.getSpeed())
            return queuedFight(robot2, robot1, tactics);
        return queuedFight(robot1, robot2, tactics);
    }

    private static String queuedFight(Robot first, Robot second, Map<String, Integer> tactics) {
        String[] tactics1 = first.getTactics();
        String[] tactics2 = second.getTactics();
        int health1 = first.getHealth();
        int health2 = second.getHealth();

        int rounds = Math.min(tactics1.length, tactics2.length);
        for (int i = 0; i < rounds; i++) {
            health2 -= tactics.get(tactics1[i]);
            if(health2 <= 0) return first.getName() + " has won the fight.";
            health1 -= tactics.get(tactics2[i]);
            if(health1 <= 0) return second.getName() + " has won the fight.";
        }
        if (tactics1.length != tactics2.length) {
            if (tactics1.length > tactics2.length) {
                for (int i = rounds; i < tactics1.length; i++) {
                    health2 -= tactics.get(tactics1[i]);
                    if (health2 <= 0) return first.getName() + " has won the fight.";
                }
            } else {
                for (int i = rounds; i < tactics2.length; i++) {
                    health1 -= tactics.get(tactics2[i]);
                    if(health1 <= 0) return second.getName() + " has won the fight.";
                }
            }
        }
        if(health1 > health2) return first.getName() + " has won the fight.";
        if(health2 > health1) return second.getName() + " has won the fight.";

        return "The fight was a draw.";
    }
}