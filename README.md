# Exam Simulator — Java DSA Project

A Java Swing-based quiz application that uses a **Doubly Linked List** as its core data structure to navigate between questions.

## Features
- Login screen with name input
- Rules screen before the exam starts
- Quiz screen with Next / Previous navigation (powered by Doubly Linked List)
- 50-50 Lifeline (removes 2 wrong options)
- Score screen at the end
- Score history saved to `history.txt`
- Supports loading custom questions from `questions.txt`

## Project Structure
```
ExamSimulator/
└── src/
    ├── Main.java              # Entry point
    ├── Question.java          # Question data class
    ├── Node.java              # Node for doubly linked list
    ├── DoublyLinkedList.java  # Doubly linked list implementation
    ├── Login.java             # Login screen
    ├── Rules.java             # Rules screen
    ├── Quiz.java              # Quiz screen
    └── Score.java             # Score screen
```

## How to Run
1. Open the project in IntelliJ IDEA
2. Right-click `Main.java` → Run 'Main.main()'

## Custom Questions Format
Create a `questions.txt` file in the project root. Each line should follow this format:
```
Question text;Option1;Option2;Option3;Option4;CorrectAnswer
```
Example:
```
What is the full form of OOP?;Object Oriented Programming;Only One Program;Output Of Program;None;Object Oriented Programming
```

## Data Structure Used
**Doubly Linked List** — each question is stored as a node with `next` and `prev` pointers, allowing the user to move forward and backward through questions.
