/*
Original task - https://www.codewars.com/kata/80-s-kids-number-5-you-cant-do-that-on-television

There are just some things you can't do on television.
In this case, you've just come back from having a "delicious"
Barth burger and you're set to give an interview.
The Barth burger has made you queezy,
and you've forgotten some of the import rules of the "You Can't Do That on Television" set.

If you say any of the following words a large bucket of "water" will be dumped on you:
"water", "wet", "wash" This is true for any form of those words, like "washing", "watered", etc.
If you say any of the following phrases you will be doused in "slime": "I don't know", "slime"
If you say both in one sentence, a combination of water and slime, "sludge", will be dumped on you.
Write a function, bucketOf(str), that takes a string and determines what will be dumped on your head.
If you haven't said anything you shouldn't have, the bucket should be filled with "air".
The words should be tested regardless of case.

Examples:
bucketOf("What is that, WATER?!?") -> "water"
bucketOf("I don't know if I'm doing this right.") -> "slime"
bucketOf("You won't get me!") -> "air"
 */
 
public class EightiesKids5 {
    public static String bucketOf(String said) {
        String phrase = said.toLowerCase();
        boolean isWater = false;
        boolean isSlime = false;
        if(phrase.contains("water") || phrase.contains("wet") || phrase.contains("wash"))
            isWater = true;
        if(phrase.contains("i don't know") || phrase.contains("slime"))
            isSlime = true;

        if(isWater && isSlime) return "sludge";
        if(isWater) return "water";
        if(isSlime) return "slime";
        return "air";
    }
}