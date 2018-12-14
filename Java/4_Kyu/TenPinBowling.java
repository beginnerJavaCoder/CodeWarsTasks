/*
Original task - https://www.codewars.com/kata/ten-pin-bowling

Ten-Pin Bowling
In the game of ten-pin bowling, a player rolls a bowling ball down a lane to knock over pins.
There are ten pins set at the end of the bowling lane.
Each player has 10 frames to roll a bowling ball down a lane and knock over as many pins as possible.
The first nine frames are ended after two rolls or when the player knocks down all the pins.
The last frame a player will receive an extra roll every time they knock down all ten pins;
up to a maximum of three total rolls.

The Challenge
In this challenge you will be given a string representing a player's ten frames.
It will look something like this: "X X 9/ 80 X X 90 8/ 7/ 44", where each frame is space-delimited,
'X' represents strikes, and '/' represents spares.
Your goal is take in this string of frames into a function called bowlingScore and return the players total score.

Scoring
The scoring for ten-pin bowling can be difficult to understand,
and if you're like most people, easily forgotten if you don't play often. Here is a quick breakdown:

Frames
In Ten-Pin Bowling there are ten frames per game.
Frames are the players turn to bowl, which can be multiple rolls.
The first 9 frames you get 2 rolls maximum to try to get all 10 pins down.
On the 10th or last frame a player will receive an extra roll each time
they get all ten pins down to a maximum of three total rolls.
Also on the last frame bonuses are not awarded for strikes and spares moving forward.

In this challenge, three frames might be represented like this: 54 72 44.
In this case, the player has three frames.
On their first frame they scored 9 points (5 + 4),
on their second frame they scored 9 points (7 + 2) and on their third frame they scored 8 points (4 + 4).
This is a very simple example of bowling scoring. It gets more complicated when we introduce strikes and spares.

Strikes
Represented in this challenge as 'X'
A strike is scored when a player knocks all ten pins down in one roll.
In the first 9 frames this will conclude the players turn and it will be scored as 10 points
plus the points received from the next two rolls.
So if a player were to have two frames X 54, the total score of those two frames would be 28.
The first frame would be worth 19 (10 + 5 + 4) and the second frame would be worth 9 (5 + 4).

A perfect game in bowling is 12 strikes in a row and would be represented like this "X X X X X X X X X XXX".
This adds up to a total score of 300.

Spares
Represented in this challenge as '/'
A spare is scored when a player knocks down all ten pins in two rolls.
In the first 9 frames this will be scored as 10 points plus the next roll.
So if a player were to have two frames 9/ 54, the total score of the two frames would be 24.
The first frame would be worth 15 (10 + 5) and the second frame would be worth 9 (5 + 4).
 */
 
public class Solution {
    public static int bowling_score(String frames) {
        String tenFrames = frames.replaceAll(" ", "");
        int result = 0;
        String current;
        String next;
        int frameNumber = 1;
        int index = 0;

        while (frameNumber < 10) {
            if(tenFrames.charAt(index) == 'X') {
                current = "X";
                index++;
            } else {
                current = tenFrames.substring(index, index + 2);
                index += 2;
            }
            next = tenFrames.substring(index, index + 2);

            result += calculateCurrentFrame(current, next);
            frameNumber++;
        }
        if(index != tenFrames.length()) result += calculateTenthFrame(tenFrames.substring(index));

        return result;
    }

    //using for calculate frame's score (any of first nine frames, because their rules are same).
    private static int calculateCurrentFrame(String current, String nextTwoBalls) {
        int frameScore = 0;
        if (current.equals("X")) {
            frameScore = 10;
            if (nextTwoBalls.charAt(0) == 'X' && nextTwoBalls.charAt(1) == 'X') {
                frameScore += 20;
            } else if(nextTwoBalls.charAt(0) == 'X' & nextTwoBalls.charAt(1) != 'X') {
                frameScore += 10 + Integer.parseInt(nextTwoBalls.charAt(1) + "");
            } else if (nextTwoBalls.charAt(1) == '/') {
                frameScore += 10;
            } else {
                frameScore += Integer.parseInt(nextTwoBalls.charAt(0) + "") + Integer.parseInt(nextTwoBalls.charAt(1) + "");
            }
        } else if (current.charAt(1) == '/') {
            if(nextTwoBalls.charAt(0) == 'X') {
                frameScore = 20;
            } else {
                frameScore = 10 + Integer.parseInt(nextTwoBalls.charAt(0) + "");
            }
        } else {
            frameScore = Integer.parseInt(current.charAt(0) + "") + Integer.parseInt(current.charAt(1) + "");
        }
		
        return frameScore;
    }

    private static int calculateTenthFrame(String frame) {
        int frameScore = 0;
        int prev = 0;
        for (int i = 0; i < frame.length(); i++) {
            if(frame.charAt(i) == 'X') {
                frameScore += 10;
                prev = 10;
                continue;
            }
            if (frame.charAt(i) == '/') {
                frameScore += 10 - prev;
                continue;
            }
            prev = Integer.parseInt(frame.charAt(i) + "");
            frameScore += prev;
        }
		
        return frameScore;
    }
}