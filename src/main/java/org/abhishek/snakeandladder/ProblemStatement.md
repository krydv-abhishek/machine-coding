# 🐍 Snake and Ladder Game

## 📘 Problem Statement

Create a **Snake and Ladder** application. The application should take input (from the **command line** or a **file**) in the following format:

### 🧾 Input Format

1. Number of **snakes** (`s`)
    - Followed by `s` lines, each containing 2 integers: **head** and **tail** positions of the snake.
2. Number of **ladders** (`l`)
    - Followed by `l` lines, each containing 2 integers: **start** and **end** positions of the ladder.
3. Number of **players** (`p`)
    - Followed by `p` lines, each containing a **player name**.

After taking these inputs, simulate the game and print all the moves using the format:

```
<player_name> rolled a <dice_value> and moved from <initial_position> to <final_position>
```

When someone wins the game, print:

```
<player_name> wins the game
```

---

## 🎲 Rules of the Game

- The board has **100 cells**, numbered from 1 to 100.
- Each player has a piece that starts **outside the board (position 0)**.
- A **six-sided die** (values 1 to 6) is used.
- Players take turns to roll the die.
- If a player rolls a value that causes them to exceed position 100, **they don’t move**.
- A player **wins** if they **land exactly** on position 100.
- If the piece lands on the **head of a snake**, it slides down to the **tail**.
- If the piece lands on the **start of a ladder**, it climbs up to the **end**.
- Chains of snakes and ladders should be followed recursively if applicable.

---

## 🧠 Assumptions

- There won't be a **snake at position 100**.
- No duplicate start positions for **snakes or ladders**.
- It is **possible to win** the game.
- Snakes and ladders **do not form infinite loops**.

---

## 📥 Sample Input

```
9
62 5
33 6
49 9
88 16
41 20
56 53
98 64
93 73
95 75
8
2 37
27 46
10 32
51 68
61 79
65 84
71 91
81 100
2
Gaurav
Sagar
```

---

## 📤 Sample Output

```
Gaurav rolled a 6 and moved from 0 to 6
Sagar rolled a 1 and moved from 0 to 1
Gaurav rolled a 6 and moved from 6 to 12
Sagar rolled a 4 and moved from 1 to 5
...
Sagar rolled a 3 and moved from 97 to 100
Sagar wins the game
```

---

## ✅ Expectations

- The code must be **working** and **demonstrable**
- It must be **functionally correct**
- Code should be:
    - Modular
    - Readable
    - Well-separated by concern
- Avoid writing everything in a single file
- Changes for new requirements should be **minimal**
- The game should be **testable via a `main` method**
- *(Optional)* Add unit tests
- No need for a GUI

---

## ✨ Optional Features (Bonus)

If time permits, consider adding:

- **Two dice** per move (sum between 2 and 12).
- **Custom board size** as input.
- Game continues until only **one player remains**.
- On rolling a **6**, the player gets another turn.
    - If **three consecutive 6s**, all are cancelled.
- Randomly **generate snakes and ladders** programmatically at startup.
