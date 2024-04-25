# Poker Hands: Solving Euler Problem #54
<div align="center">
  <h1>
<img src="https://github.com/ahlem-phantom/poker-hands-euler-problem-54/assets/78981558/4de167de-d117-4868-be3d-58f8cefe3569" width="1000" height="300">
</h1>

</div>
<details>
  <summary>Table of Contents</summary>
  <ol>
    <li>
      <a href="#about-the-project">About The Project</a>
      <ul>
        <li><a href="#how-the-solution-works">How the solution works</a></li>
        <li><a href="#strengths-and-weaknesses">Strengths and Weaknesses</a></li>
        <li><a href="#new-technologies-or-approaches">New technologies or approaches</a></li>
      </ul>
    </li>
    <li>
      <a href="#project-roadmap">Project Implementation</a>
      <ul>
        <li><a href="#project-overview">Project Structure</a></li>
         <li><a href="#project-overview">Project Roadmap</a></li>
      </ul>
    </li>
  </ol>
</details>

<!-- ABOUT THE PROJECT -->
##  About The Project
This project is my unique take on solving <a href="https://projecteuler.net/problem=54">Problem #54 from Project Euler</a>. It is a Poker card game.  <br/>
So basiacally we have a file (can be found at ressources folder) containing 1000 line == 1000 round.
<div align="center">
<h1>
<img src="https://github.com/ahlem-phantom/poker-hands-54_euler/assets/78981558/078427e3-f9e8-4c06-b97d-495e7d661e9b" width="300" height="500">
</h1>
</div>

Each round is composed of 10 cards that should be divided to 2 hands related to 2 players. Each player should have 5 cards.
<div align="center">
<h1>
<img src="https://github.com/ahlem-phantom/poker-hands-54_euler/assets/78981558/ff9d1c5a-4acd-4953-b694-d24ac166b920" width="500" height="200">
</h1>
</div>

A card is represented by a string composed of two characters:

*   The first character denotes the card’s rank (e.g., “5” for Five, “A” for Ace).
    
*   The second character represents the card’s suit (e.g., “S” for Spades, “H” for Hearts).
    
A hand in poker consists of five cards. For instance, the following string represents a hand: `   8C TS KC 9H 4S   `

This hand includes:

*   8S ==> Eight of Spades
    
*   TS ==> Two of Spades
    
*   KC ==> King of Clubs
    
*   9H ==> Nine of Hearts
    
*   4S ==> Four of Spades
  
### Suits:
| Name    | Symbol | Character Value |
|---------|:------:|:---------------:|
| Spade   | ♠      | S               |
| Heart   | ♥      | H               |
| Club    | ♣      | C               |
| Diamond | ♦      | D               |

*Tab.1:* Suits Symbol Representation.

### Ranks:
| Name  | Symbol | Integer Value |
|-------|:------:|:-------------:|
| Two   | 2      | 2             |
| Three | 3      | 3             |
| Four  | 4      | 4             |
| Five  | 5      | 5             |
| Six   | 6      | 6             |
| Seven | 7      | 7             |
| Eight | 8      | 8             |
| Nine  | 9      | 9             |
| Ten   | T      | 10            |
| Jack  | J      | 11            |
| Queen | Q      | 12            |
| King  | K      | 13            |
| Ace   | A      | 14            |

*Tab.2:* Card suits and ranks.

The goal of this task is to calculate the number of times that player 1 wins. The winner is determined based on **the strength of their hands**.
| Hand Rank | Hand Name       | Definition / Example                                 | Consecutive      | Same Suit       |
|-----------|-----------------|------------------------------------------------------|-----------------|-----------------|
| 9         | Royal Flush     | Cards of Ten, Jack, Queen, King, Ace, in same suit | yes |yes |
| 8         | Straight Flush  | Cards of the same suit and consecutive ranks: J♠, T♠, 9♠, 8♠, 7♠ |yes |yes |
| 7         | Four of a Kind  | Four cards of the same rank: 9♠, 9♦, 9♥, 9♠          |no |no |
| 6         | Full House      | Three cards of the same rank, two of another: K♠, K♥, K♣, 9♥, 9♣ |no |no |
| 5         | Flush           | Cards of the same suit but not a straight: A♥, K♥, Q♥, T♥, 2♥|no |yes |
| 4         | Straight        | Cards of consecutive ranks but not a flush: 7♠, 6♦, 5♦, 4♥, 3♣ |yes |no |
| 3         | Three of a Kind | Three cards of the same rank, others distinct: A♠, A♦, A♣, T♢, 4♢ |no |no |
| 2         | Two Pair        | Two cards of the same rank and two of another: A♠, A♣, Q♣, Q♦, 4♣ |no |no |
| 1         | One Pair            | Two cards of the same rank, others distinct: J♥, J♥, T♦, 4♠, 2♥ |no |no |
| 0         | High Card       | Any kind of hand not mentioned above: highest value card: A♥, K♥, Q♦, J♠, 9♥ | - | - |

*Tab.3:* Poker hand ranks.

### How the solution works
The solution employs an **object-oriented programming** approach to simulate a poker game and **determine the winner of each hand**. At the core of my design are two main classes: the ` Card ` class and the ` Hand ` class. The Card class encapsulates the properties of individual cards, such as suit and number, providing a blueprint for creating and managing card objects. On the other hand, the Hand class represents an list of cards, offering functionalities to add or remove cards, evaluate hand ranks, and compare hands for determining winners.  At a high level, the program parses input hands represented as strings, evaluates the rank of each hand, and determines the winner based on the ranks and card values. It employs various methods to analyze hands, such as checking for pairs, flushes, straights, and high cards. Moreover, this solution takes into account scenarios where ties occur (hands of equal strength). 
The `getHandRank` method assesses the rank of each hand, allowing for comparisons to determine the winner.

<div align="center">
<h1>
<img src="https://github.com/ahlem-phantom/poker-hands-euler-problem-54/assets/78981558/ff553109-c486-4dc9-91eb-e5fdf5eb5e7b" >
</h1>

</div>

### Strengths and Weaknesses
One aspect I appreciate about my solution is its clarity and organization. By dividing functionality into separate classes (such as Card and Hand), the solution becomes more modular and easier to understand. Here are the key aspects I focused on:
- **Modular Design:** I structured the solution into separate utility methods for parsing, analyzing, and comparing hands. This modular approach makes the solution easier to maintain and understand.
- **Use of Streams:** While I had only a theoretical understanding of Java Streams before, I found them incredibly valuable in certain methods for their ability to enhance readability and conciseness. They were extremly useful especially for tasks like counting occurrences and get highest cards value, etc improving code clarity.
- **Error Handling:** I tired to implement error handling to ensure robustness by validating input and invalid scenarios. 

However, one potential drawback of the solution is its **complexity in Hand Ranking:**. While I acknowledge the current complexity, I believe it could be further reduced by breaking down the logic into smaller, more focused methods. One alternative approach I considered, but didn't have time to implement, was assigning a numerical value to each hand type with additional values assigned based on factors such as the highest card in the hand. This would allow for direct comparison based on assigned values, potentially simplifying the comparison process.




### New technologies or approaches
Initially, I struggled with understanding the game of poker as I wasn't familiar with card games.  However, I found the learning process to be enjoyable and interesting! I was fascinated by the logic involved in evaluating poker hands. The problem itself wasn't too difficult, but what mattered the most was how I structured the code to ensure simplicity, readability, and reduce complexity. Here are the things I employed:


- **Java Streams:** this helped me for tasks like mapping, filtering, and collecting data introduced a more functional programming approach to certain parts of the solution.
- **Unit Testing:** this helped to validate the functionality of various methods, ensuring that changes to the codebase maintain expected behavior.
- **Code Analysis Tool**: I used it to detect and rectify coding issues, code smells, and potential vulnerabilities in my code. So, I utilized these tools to calculate the complexity of the code and address any areas of concern. Although I had intended to work with SonarLint for code analysis, I resorted to Codalyze tool due to limitations in installing extensions. Also I used Visual Studio Code as my primary IDE.


<p align="right">(<a href="#top">back to top</a>)</p>


## Project Implementation

### Project Structure




    poker-hands-54_euler/
    ├── src/                                                 # Source code directory
    |   ├── main/
    |   |   └── java/
    |   |       └── com/
    |   |           ├── poker/
    |   |           |   ├── models/
    |   |           |   |   ├── Card.java                    # Class representing a playing card
    |   |           |   |   └── Hand.java                    # Class representing a hand of cards
    |   |           |   |   
    |   |           |   ├── utils/                           # Package for utility classes
    |   |           |   |   ├── FileUtils.java               # Utility class for file operations
    |   |           |   |   ├── CardUtils.java               # Utility class for card-specific operations
    |   |           |   |   └── HandUtils.java               # Utility class for hand-specific operations
    |   |           |   |   
    |   |           |   |   
    |   |           |   ├── services/                        # Package for utility classes
    |   |           |   |   └── HandService.java             # Utility class for hand comparision and evaluation
    |   |           |   |   
    |   |           |   └── LaunchGame.java                  # Main class for running the application
    |   |           |   
    |   |           └── resources/
    |   |                  └── 0054_poker.txt                # File that contains one-thousand random hands dealt to two players
    |   |    
    |   └── test                                             # Test source code containing automated tests (JUnit5)
    |       └── java/
    |           └── com/
    |                └── poker/
    |                   ├── models/
    |                   |  ├── CardTest.java
    |                   |  └── HandTest.java
    |                   |  
    |                   ├── utils/     
    |                   |  ├── FileUtilsTest.java
    |                   |  ├── CardUtilsTest.java
    |                   |  └── HandUtilsTest.java
    |                   |   
    |                   ├── services/                              
    |                   |   └── HandServiceTest.java               
    |                   |   
    |                   └── LaunchGameTest.java
    └── pom.xml
    └── README.md


## Project Roadmap

 - [x] Phase 1: Understanding the Poker Hands Problem
   - Problem Definition: Defined the problem of Euler #54 of the poker hands and its goals.
   - Poker Rules Review: I needed to understand the rules of poker, especially the ranking and comparison of poker hands.
   - Algorithm Design: I developed an algorithm for evaluating poker hands( just handwriting of a high level).
 
 - [x] Phase 2: Building Basic Classes
   - Card and Hand Classes: Implemented the Card and Hand classes.

 - [x] Phase 3: Parsing Poker Hands from Text File
   - File Reading: Implemented logic to read the poker hands text file.
   - Hand Parsing: Developed a method to parse each line into two sets of poker hands, one for each player.
   - Round Representation: Created a method to represent a round of poker using the Card and Hand objects (using parseCard and parseHand).

 - [x] Phase 4: Evaluating Poker Hands
    - Utility functions: Created functions like isSameSuit, isConsecutive, getHandHighCard etc.
    - Hand Evaluation: Wrote down the logic to evaluate and rank poker hands according to the rules of poker.

 - [x]  Phase 5: Testing and Optimization
   - Solution Testing: Continously tested all the elements thoroughly to ensure they worked as expected using JUnit5 tests.
   - Optimization: Continously refined and optimized the code for efficiency and readability.
