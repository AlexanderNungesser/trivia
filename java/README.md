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
Damit die Konsolen-Ausgabe aber auch immer die gleiche ist, muss man den vom Zufall behafteten Würfel und die zufälligen Antworten durch feste Reihenfolgen oder Werte ersetzen.
Um das zu erreichen, eignet sich das Strategy-Pattern für die Art des im Spiel verwendeten Würfels und die Antworten der Spieler.
Außerdem sollten immer die gleichen Spieler und immer die gleichen Fragen vorkommen, um den gleichen Spielverlauf reproduzieren zu können, hierfür eignet sich das Factory-Pattern.
Hinzu kommt, dass ein Spieler in eine _"Penalty-Box"_ kommen kann, was wiederum einen Zustand des Spielers beschreibt.
Aufgrund dessen ist zusätzlich das State-Pattern auf den Spieler anwendbar.
Um die Konsolen-Ausgaben von der Spiel-Logik zu trennen, ist das Observer-Pattern von Nutzen.

## Dice

Für bessere Testbarkeit, habe ich die [FixedDiceStrategy](src/main/java/com/adaptionsoft/games/uglytrivia/dice/FixedDiceStrategy.java) hinzugefügt, um das Würfeln von nur einem bestimmten Wert zu ermöglichen.
Für eine immer noch gute Testbarkeit, aber flexibleren Spielverlauf, habe ich die [ScriptedDiceStrategy](src/main/java/com/adaptionsoft/games/uglytrivia/dice/ScriptedDiceStrategy.java) hinzugefügt.
Um die ursprüngliche Spiel-Logik beibehalten zu können, dient die [RandomDiceStrategy](src/main/java/com/adaptionsoft/games/uglytrivia/dice/RandomDiceStrategy.java)

### Strategy-Pattern

Das Strategy-Pattern ist beim Würfel perfekt anwendbar, da es Verhalten beschreibt und jeder Würfel ein anderes aufweist.
Außerdem ist dadurch die Spiel-Logik einfach erweiterbar. Will man einen D20-Würfel nutzen statt einer D6, ändert man einfach die Seitenanzahl.
Will man einen ganz eigenen Würfel, mit einem komplexen Verhalten, nutzen, kann man eine neue Strategy-Class schreiben und diese nutzen.

## Questions

Pro Feld auf dem Spielbrett gibt es eine Fragen-Kategorie, von der es einen Stapel an Karten gibt.
Daher habe ich ein Record [Question](src/main/java/com/adaptionsoft/games/uglytrivia/question/Question.java) implementiert.
In der ursprünglichen Spiel-Logik gibt es die Kategorien _"Pop"_,_"Science"_,_"Sports"_ und _"Rock"_, die sich in genau dieser Reihenfolge auf dem Spielbrett wiederholt haben.
Der Inhalt der Fragen besteht dabei immer aus der Kategorie und einer Nummer, beginnend bei 0, z.B. _"Science Question 0"_.

### Category

Für die Kategorien der Fragen habe ich einen Enum angelegt. Basierend auf diesem Enum ([Category](src/main/java/com/adaptionsoft/games/uglytrivia/question/Category.java)) arbeitet das Question-Factory-Pattern.

### Factory-Pattern

Um die Fragen wie in der ursprünglichen Spiel-Logik erstellen zu können, aber auch um Erweiterbarkeit zu implementieren, nutze ich das Factory-Pattern ([DefaultQuestionFactory](src/main/java/com/adaptionsoft/games/uglytrivia/question/DefaultQuestionFactory.java)).
Dabei wird basierend auf dem Enum der Kategorien eine Map aus Listen angelegt. Die Map beinhaltet die Kategorien als Keys und die Fragen-Stapel als Listen.

## Players

Das Spiel wird von Spielern gespielt, die jeweils eine Spielfigur auf dem Spielfeld führen. Daher habe ich eine eigene Klasse für einen [Player](src/main/java/com/adaptionsoft/games/uglytrivia/player/Player.java) angelegt.
Der aktive Spieler würfelt, zieht nach Augenzahl und beantwortet eine Frage von der Kategorie, die das Feld beinhaltet, auf dem er gelandet ist.
Beantwortet er die Frage korrekt, bekommt er eine Münze, beantwortet er sie falsch, wird er auf ein Straffeld geschickt.
Um vom Straffeld wegzukommen, muss laut ursprünglicher Spiel-Logik, eine ungerade Zahl gewürfelt werden, sonst bleibt der Spieler auf dem Straffeld.

### Factory-Pattern

Um die Erstellung der Spieler flexibel zu gestalten, nutze ich auch hier das Factory-Pattern ([DefaultPlayerFactory](src/main/java/com/adaptionsoft/games/uglytrivia/player/factory/DefaultPlayerFactory.java)).
Somit kann die ursprüngliche Spiel-Logik erhalten aber auch leicht erweitert werden.

### State-Pattern

Da das sein eines Spielers auf einem Straffeld ein Zustand ist, nutze ich das State-Pattern.
Dadurch ist auch ein benutzerdefiniertes Verhalten bei einem Zustandswechsel zwischen [DefaultNormalState](src/main/java/com/adaptionsoft/games/uglytrivia/player/state/DefaultNormalState.java) und [DefaultPenaltyBoxState](src/main/java/com/adaptionsoft/games/uglytrivia/player/state/DefaultPenaltyBoxState.java) möglich.

## Answers

Die Spieler müssen Fragen beantworten, um Münzen zu bekommen.
Ursprünglich sind es wahr oder falsch Fragen, ohne richtigen Inhalt.
In der ursprünglichen Spiel-Logik wird das Beantworten von Fragen und die Evaluierung der Antworten durch Zufall entschieden ([RandomAnswerStrategy](src/main/java/com/adaptionsoft/games/uglytrivia/answer/RandomAnswerStrategy.java)).

### Strategy-Pattern

Hierfür habe ich das Strategy-Pattern verwendet, da das Beantworten von Fragen und die Evaluierung der Antworten als Verhalten gilt.
Dieses Verhalten ist abhängig von der Korrektheit der Antwort des Spielers.
Für bessere Testbarkeit habe ich, wie beim Würfel, Strategien angelegt, mit denen ein Wert festgelegt werden kann ([SameAnswerStrategy](src/main/java/com/adaptionsoft/games/uglytrivia/answer/SameAnswerStrategy.java)) und eine Folge definiert werden kann ([ScriptedAnswerStrategy](src/main/java/com/adaptionsoft/games/uglytrivia/answer/ScriptedAnswerStrategy.java)).

## Events/Output

Ursprünglich ist das Spiel mit Konsolen-Ausgaben versehen.
Diese sind jedoch überall verstreut und nicht an einem Ort gebündelt.
Daher entschied ich mich für das Observer-Patter und einer Event-Logik.
Alle Ausgaben sind eventbasiert und nun auch durch andere Ausgabe-Methoden erweiterbar und für den Benutzer darstellbar.

### Observer-Pattern

Ich habe [Events](src/main/java/com/adaptionsoft/games/uglytrivia/event/events), bei denen eine Ausgabe stattfinden soll definiert.
Diese Events werden vom [Observer](src/main/java/com/adaptionsoft/games/uglytrivia/event/ConsoleGameObserver.java), der sich beim [EventBus](src/main/java/com/adaptionsoft/games/uglytrivia/event/EventBus.java) subscript hat, beobachtet und entsprechend behandelt, wenn diese auslösen.