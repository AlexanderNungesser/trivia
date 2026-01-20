package com.adaptionsoft.games.uglytrivia.event.events;

public sealed interface GameEvent
        permits PlayerJoined, PlayerChanged, PlayerRolled, PlayerMoved, PlayerGoesToPenaltyBox, PlayerLeftPenaltyBox, PlayerStaysInPenaltyBox, QuestionAsked, AnswerCorrect, AnswerWrong, PlayerGotCoins, PlayerWon {
}


