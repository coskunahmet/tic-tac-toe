# tic-tac-toe
This is a scalable, multiplayer tic tac toe game.
## How to Run the game
First, you must generate jar file with `maven:install` goal. After, you run this goal, it will generate a jar file with `-jar-with-dependencies` suffix in `target` folder.
This is the runnable jar file of this project. You should run this jar file on `command prompt` by standard java execution command:

`java -jar tic-tac-toe-1.0-SNAPSHOT-jar-with-dependencies.jar`

This will execute application by default configuration.
There is another usage to configure game:

`java -jar tic-tac-toe-1.0-SNAPSHOT-jar-with-dependencies.jar <configuration_file_path>`

`<configuration_file_path>` should be the path of a configuration file which is formatted like this:

```
size.of.game.board=7
first.player.character=K
second.player.character=S
computer.player.character=Z
empty.tile.char=~
```

`size.of.game.board` should assigned to an integer value

`first.player.character` should assigned to a single character which will be shown character of first player on game board
 
`second.player.character` should assigned to a single character which will be shown character of second player on game board
 
`computer.player.character` should assigned to a single character which will be shown character of computer on game board

`empty.tile.char` should assigned to a single character which will be shown character of empty tiles on game board

Default(no passed argument execution) configuration has these values:

```
size.of.game.board=3
first.player.character=F
second.player.character=S
computer.player.character=C
empty.tile.char=~
```