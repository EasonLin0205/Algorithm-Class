/*
HDU4310
Problem Description
When playing DotA with god-like rivals and pig-like team members, you have to face an embarrassing situation: All your
teammates are killed, and you have to fight 1vN.
There are two key attributes for the heroes in the game, health point (HP) and damage per shot (DPS). Your hero has almost
infinite HP, but only 1 DPS.
To simplify the problem, we assume the game is turn-based, but not real-time. In each round, you can choose one enemy hero
to attack, and his HP will decrease by 1. While at the same time, all the lived enemy heroes will attack you, and your HP
will decrease by the sum of their DPS. If one hero's HP fall equal to (or below) zero, he will die after this round, and
cannot attack you in the following rounds.
Although your hero is undefeated, you want to choose best strategy to kill all the enemy heroes with minimum HP loss.

Input
The first line of each test case contains the number of enemy heroes N (1 <= N <= 20). Then N lines followed, each contains
two integers DPSi and HPi, which are the DPS and HP for each hero. (1 <= DPSi, HPi <= 1000)

Output
Output one line for each test, indicates the minimum HP loss.
*/

import java.util.*;

class Main{
    public static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        while(input.hasNext()){
            Hero[] heroes = Hero.getHeroArray(input);
            System.out.println(Hero.getTotalDamageTaken(heroes));
        }
    }
}

class Hero implements Comparable<Hero>{
    int dps;
    int hp;
    double killPriority;  // 击杀的优先程度

    Hero(){}
    Hero(int dps,int hp){
        this.dps = dps;
        this.hp = hp;
        this.killPriority = (double)dps / hp;
    }

    static Hero[] getHeroArray(Scanner input){
        int n = input.nextInt();
        Hero[] heroes = new Hero[n];
        for(int i = 0;i<n;i++){
            int dps = input.nextInt();
            int hp = input.nextInt();
            heroes[i] = new Hero(dps,hp);
        }
        return heroes;
    }

    static int getTotalDamageTaken(Hero[] heroes){
        Arrays.sort(heroes);
        int[] prefixAnd = new int[heroes.length];
        int sum = 0;
        for(int i = 0;i<prefixAnd.length;i++){
            sum += heroes[i].dps;
            prefixAnd[i] = sum;
        }
        int res = 0;
        for(int i = heroes.length - 1;i>=0;i--){
            res += heroes[i].hp * prefixAnd[i];
        }
        return res;
    }

    @Override
    public int compareTo(Hero o) {
        return Double.compare(this.killPriority,o.killPriority);
    }
}