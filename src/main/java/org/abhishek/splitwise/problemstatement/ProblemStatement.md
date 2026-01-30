# 💰 Splitwise

## 📌 Problem Statement

Create an **expense sharing application** to track expenses and split them among users. The app should maintain balances showing who owes how much to whom.

---

## 🧾 Example

You live with 3 friends:

- **You**: `User1 (u1)`
- **Flatmates**: `User2 (u2)`, `User3 (u3)`, `User4 (u4)`

### ✅ Scenario 1: Split Equally

Electricity bill of Rs. 1000 paid by `u1`, split equally among `u1, u2, u3, u4`:

```
Input: u1 1000 4 u1 u2 u3 u4 EQUAL
```

Each person owes `250` to `User1`:

- `User2` owes `User1`: 250
- `User3` owes `User1`: 250
- `User4` owes `User1`: 250

---

### ✅ Scenario 2: Split by Exact Amount

You buy items for `u2` and `u3`. Total = Rs. 1250, with differing amounts:

```
Input: u1 1250 2 u2 u3 EXACT 370 880
```

Balances:

- `User2` owes `User1`: 620 (`250 + 370`)
- `User3` owes `User1`: 1130 (`250 + 880`)
- `User4` owes `User1`: 250

---

### ✅ Scenario 3: Split by Percent

`User4` pays for an outing. `u1` is paying for 2 people. Total = Rs. 1200:

```
Input: u4 1200 4 u1 u2 u3 u4 PERCENT 40 20 20 20
```

Breakdown:

- `User1` owes `User4`: 480
- `User2` owes `User4`: 240
- `User3` owes `User4`: 240

Updated balances:

- `User1` owes `User4`: 230 (`250 - 480`)
- `User2` owes `User1`: 620
- `User2` owes `User4`: 240
- `User3` owes `User1`: 1130
- `User3` owes `User4`: 240

---

## 📋 Requirements

### 👤 User

Each user should have:

- `userId`
- `name`
- `email`
- `mobile number`

---

### 💸 Expense Types

- **EQUAL**
- **EXACT**
- **PERCENT**

Users can:

- Add any amount
- Choose any type of split
- Split with any number of users
- Use decimals (up to 2 places)

### Validation

- **PERCENT**: total must be **100**
- **EXACT**: total must equal the **expense amount**

---

## 📊 Balances

- Show balances for a single user or for all users
- Display only non-zero balances
- Round off to **two decimal places**
- In case of uneven division, assign the larger share to the **first user**

---

## 🧑‍💻 Input Format

1. **Expense**
   ```
   EXPENSE <paid-by> <amount> <no-of-users> <user-list> <EQUAL/EXACT/PERCENT> <values-if-needed>
   ```

2. **Show all balances**
   ```
   SHOW
   ```

3. **Show balances for a specific user**
   ```
   SHOW <user-id>
   ```

---

## 📤 Output Format

For each balance:

```
<user-id-x> owes <user-id-y>: <amount>
```

If no balances:

```
No balances
```

---

## 🧪 Sample Input

```
SHOW
SHOW u1
EXPENSE u1 1000 4 u1 u2 u3 u4 EQUAL
SHOW u4
SHOW u1
EXPENSE u1 1250 2 u2 u3 EXACT 370 880
SHOW
EXPENSE u4 1200 4 u1 u2 u3 u4 PERCENT 40 20 20 20
SHOW u1
SHOW
```

---

## 📈 Sample Output

```
No balances
No balances
User4 owes User1: 250
User2 owes User1: 250
User3 owes User1: 250
User4 owes User1: 250
User2 owes User1: 620
User3 owes User1: 1130
User4 owes User1: 250
User1 owes User4: 230
User2 owes User1: 620
User3 owes User1: 1130
User1 owes User4: 230
User2 owes User1: 620
User2 owes User4: 240
User3 owes User1: 1130
User3 owes User4: 240
```

---

## ✅ Expectations

- Working and testable code
- Functional correctness
- **Modular** and **readable** code
- **Separation of concerns**
- Avoid writing everything in a single file
- Code should support future feature addition
- Include a main method for easy testing
- *(Optional)* Unit tests
- No GUI needed

---

## 🔧 Optional Features

> Add only if time permits or design for them:

- Add **expense name**, **notes**, **attachments**
- Support `SHARE` based split (e.g., `SHARE 2 1 1 1`)
- Show **passbook** or transaction history per user
- **Simplify debts** (e.g., `A owes B`, `B owes C` → `A owes C`)