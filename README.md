# Arkanoid
## Introduction
Final product of a 5-part-semester project  as part of an OOP course.
The game contains four levels with increasing difficulty,
The player will win the game if he breaks all the blocks in each level and he will lose if all the balls fall.

The implementation of the game includes using GUI and OOPs methodology such as:
  * Polymorphism and Inheritance.
  * Usage of design patterns such as Observer, Factory, etc.



Level in the game for example:

<img width="582" alt="image" src="https://user-images.githubusercontent.com/117023310/227772276-849aaf51-de42-4df0-80b1-63cdb683302b.png">


## Installation

Install Apache Ant Link to a guide: [Apache Ant Installation](https://ant.apache.org/manual/install.html)

After it, run the following commands in the terminal:
```
$ git clone https://github.com/Sagi1500/Arkanoid.git
ant compile
ant run
-Dargs="args" run
```
The args is the levels choose by the player, for example:
```
ant -Dargs="1 3 2 1 9 1 3 4 3" run
```




