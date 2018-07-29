# tic-tac-toe
This is a scalable, multiplayer tic tac toe game.

In this game, Board size is scalable rather than 3*3. It can be between 3*3 to 10*10.
Another feature of this tic tac toe game is multiplayer playing. There is two real player and one computer. All three player will play their character one by one. Their characters are also configurable.
User inputs must be in format of `<integer_between_1_and_board_size>,<integer_between_1_and_board_size>`
First integer is the x position of board tiles, Second integer is the y position of board files.
For instance, if board size is 8*8;
- right-lower corner can be played with 8,8 input value.
- left-upper corner can be played with 1,1 input value.

General Tic-Tac-Toe game rules: [Tic-Tac-Toe Wikipedia Rules](https://en.wikipedia.org/wiki/Tic-tac-toe)
## Requirements
- JDK 1.8 or above
- Maven 3 or above

## Versions
### 1.0.3
- Config File properties exceptions are handled gently.
### 1.0.2
- Diagonal and column based win calculated
- Related messages have been shown to user.
### 1.0.1
Bugs are solved:
- 0 in input after comma lead to an exception
- Computer created position bigger than board size
### 1.0.0
- Application first release

## How to Run the game
First, you must generate jar file with `maven:install` goal. After, you run this goal, it will generate a jar file with `-jar-with-dependencies` suffix in `target` folder.
This is the runnable jar file of this project. You should run this jar file on `command prompt` by standard java execution command:

`java -jar tic-tac-toe-<version_number>-jar-with-dependencies.jar`

This will execute application by default configuration.
There is another usage to configure game:

`java -jar tic-tac-toe-<version_number>-jar-with-dependencies.jar <configuration_file_path>`

`<version_number>` in jar name should be changed with related version number. Such as: 1.0.0

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

## Design
In this project, MVC and Observer Pattern has been applied. Visual explanation is always easy to understand, So, Here is a design diagram:
![design diagram](Design%20Diagram.png)