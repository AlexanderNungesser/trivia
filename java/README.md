# Refactoring - LSP

## Inhaltsverzeichnis

1. [Golden-Copy](#golden-copy)

2. [Dice](#dice)<br>
   2.1. [Strategy-Pattern](#strategy-pattern)

3. [Questions](#questions)<br>
   3.1. [Category](#category)<br>
   3.2. [Factory-Pattern](#factory-pattern)

4. [Players](#players)<br>
   4.1. [Factory-Pattern](#factory-pattern-1)<br>
   4.2. [State-Pattern](#state-pattern)

5. [Answers](#answers)<br>
   5.1. [Strategy-Pattern](#strategy-pattern-1)

6. [Events/Output](#eventsoutput)<br>
   6.1. [Observer-Pattern](#observer-pattern)


## Golden-Copy

Für eine _Golden-Copy_ würde ich zum einen die schon existierende **Konsolen-Ausgabe** nehmen und zum anderen die **Characterization-Tests**, die man anlegt, um das Refactoring abzusichern.
Damit die Konsolen-Ausgabe aber auch immer die gleiche ist, muss man den vom Zufall behafteten Würfel und die zufälligen Antworten durch feste Reihenfolgen/Werte ersetzen.
Um das zu erreichen, eignet sich das Strategy-Pattern für die Art des im Spiel verwendeten Würfels und die Antworten der Spieler.
Außerdem sollten immer die gleichen Spieler und immer die gleichen Fragen vorkommen, um den gleichen Spielverlauf reproduzieren zu können.
Hierfür eignet sich das Factory-Pattern.
Hinzu kommt, dass ein Spieler in eine _"Penalty-Box"_ kommen kann, was wiederum einen Zustand des Spielers beschreibt.
Aufgrund dessen ist zusätzlich das State-Pattern auf den Spieler anwendbar.
Um die Konsolen-Ausgaben von der Spiel-Logik zu trennen, ist das Observer-Pattern von Nutzen.

## Dice

Für bessere Testbarkeit, habe ich die [FixedDiceStrategy](./src/main/java/com/adaptionsoft/games/uglytrivia/dice/FixedDiceStrategy.java) hinzugefügt, um das Würfeln von nur einem bestimmten Wert zu ermöglichen.
Für eine immer noch gute Testbarkeit, aber flexibleren Spielverlauf, habe ich die [ScriptedDiceStrategy](./src/main/java/com/adaptionsoft/games/uglytrivia/dice/ScriptedDiceStrategy.java) hinzugefügt.
Um die ursprüngliche Spiel-Logik beibehalten zu können, dient die [RandomDiceStrategy](./src/main/java/com/adaptionsoft/games/uglytrivia/dice/RandomDiceStrategy.java)

### Strategy-Pattern

Das Strategy-Pattern ist beim Würfel perfekt anwendbar, da es Verhalten beschreibt und jeder Würfel ein anderes aufweist.
Außerdem ist dadurch die Spiel-Logik einfach erweiterbar. Will man einen D20-Würfel nutzen statt einer D6, ändert man einfach die Seitenanzahl.
Will man einen ganz eigenen Würfel, mit einem komplexen Verhalten, nutzen, kann man eine neue Strategy-Class schreiben und diese nutzen.

## Questions



### Category

### Factory-Pattern

## Players

### Factory-Pattern

### State-Pattern

## Answers

### Strategy-Pattern

## Events/Output

### Observer-Pattern

