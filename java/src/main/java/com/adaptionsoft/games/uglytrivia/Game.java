package com.adaptionsoft.games.uglytrivia;

import java.util.List;
import java.util.ArrayList;
import java.util.LinkedList;

public class Game {
	List<Player> players = new ArrayList<>();

	List<String> popQuestions = new LinkedList<>();
    List<String> scienceQuestions = new LinkedList<>();
    List<String> sportsQuestions = new LinkedList<>();
    List<String> rockQuestions = new LinkedList<>();

    int currentPlayer = 0;
    boolean isGettingOutOfPenaltyBox;
    
    public  Game(){
    	for (int i = 0; i < 50; i++) {
			popQuestions.addLast("Pop Question " + i);
			scienceQuestions.addLast("Science Question " + i);
			sportsQuestions.addLast("Sports Question " + i);
			rockQuestions.addLast("Rock Question " + i);
    	}
    }

	public boolean isPlayable() {
		return (howManyPlayers() >= 2);
	}

	public void addPlayer(String playerName) {
	    players.add(new Player(playerName));

	    System.out.println(playerName + " was added");
	    System.out.println("They are player number " + players.size());
	}
	
	public int howManyPlayers() {
		return players.size();
	}

	public void roll(int roll) {
		System.out.println(players.get(currentPlayer).getName() + " is the current player");
		System.out.println("They have rolled a " + roll);
		
		if (players.get(currentPlayer).isInPenaltyBox()) {
			if (roll % 2 != 0) {
				isGettingOutOfPenaltyBox = true;
				
				System.out.println(players.get(currentPlayer).getName() + " is getting out of the penalty box");
				extractedRoll(roll);
			} else {
				System.out.println(players.get(currentPlayer).getName() + " is not getting out of the penalty box");
				isGettingOutOfPenaltyBox = false;
				}
			
		} else {

			extractedRoll(roll);
		}
		
	}

	private void extractedRoll(int roll) {
		players.get(currentPlayer).setPlace(players.get(currentPlayer).getPlace() + roll);
		if (players.get(currentPlayer).getPlace() > 11) players.get(currentPlayer).setPlace(players.get(currentPlayer).getPlace() - 12);

		System.out.println(players.get(currentPlayer).getName()
				+ "'s new location is "
				+ players.get(currentPlayer).getPlace());
		System.out.println("The category is " + currentCategory());
		askQuestion();
	}

	private void askQuestion() {
		if (currentCategory().equals("Pop"))
			System.out.println(popQuestions.removeFirst());
		if (currentCategory().equals("Science"))
			System.out.println(scienceQuestions.removeFirst());
		if (currentCategory().equals("Sports"))
			System.out.println(sportsQuestions.removeFirst());
		if (currentCategory().equals("Rock"))
			System.out.println(rockQuestions.removeFirst());		
	}
	
	
	private String currentCategory() {
		if (players.get(currentPlayer).getPlace() == 0) return "Pop";
		if (players.get(currentPlayer).getPlace() == 4) return "Pop";
		if (players.get(currentPlayer).getPlace() == 8) return "Pop";
		if (players.get(currentPlayer).getPlace() == 1) return "Science";
		if (players.get(currentPlayer).getPlace() == 5) return "Science";
		if (players.get(currentPlayer).getPlace() == 9) return "Science";
		if (players.get(currentPlayer).getPlace() == 2) return "Sports";
		if (players.get(currentPlayer).getPlace() == 6) return "Sports";
		if (players.get(currentPlayer).getPlace() == 10) return "Sports";
		return "Rock";
	}

	public boolean wasCorrectlyAnswered() {
		if (players.get(currentPlayer).isInPenaltyBox()){
			if (isGettingOutOfPenaltyBox) {
				System.out.println("Answer was correct!!!!");
				return extractedWasCorrectlyAnswered();
			} else {
				currentPlayer++;
				if (currentPlayer == players.size()) currentPlayer = 0;
				return true;
			}
			
			
			
		} else {
		
			System.out.println("Answer was corrent!!!!");
			return extractedWasCorrectlyAnswered();
		}
	}

	private boolean extractedWasCorrectlyAnswered() {
		players.get(currentPlayer).setCoins(players.get(currentPlayer).getCoins() + 1);
		System.out.println(players.get(currentPlayer).getName()
				+ " now has "
				+ players.get(currentPlayer).getCoins()
				+ " Gold Coins.");

		boolean winner = didPlayerWin();
		currentPlayer++;
		if (currentPlayer == players.size()) currentPlayer = 0;

		return winner;
	}

	public boolean wrongAnswer(){
		System.out.println("Question was incorrectly answered");
		System.out.println(players.get(currentPlayer).getName() + " was sent to the penalty box");
		players.get(currentPlayer).setInPenaltyBox(true);
		
		currentPlayer++;
		if (currentPlayer == players.size()) currentPlayer = 0;
		return true;
	}


	private boolean didPlayerWin() {
		return !(players.get(currentPlayer).getCoins() == 6);
	}
}
