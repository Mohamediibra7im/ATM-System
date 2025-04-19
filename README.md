# 🏦 ATM System

A simple ATM system built in Java that allows users to register, login, and perform basic banking operations like deposits and withdrawals. The system uses JSON for data persistence, ensuring that account information is saved between application runs.

## ✨ Features

- 🔐 **Account Registration & Login**
  - Create new bank accounts
  - Secure PIN-based authentication
- 💰 **Banking Operations**
  - 💵 Deposit funds
  - 💸 Withdraw funds
  - 📊 Check account balance
  - 📝 View account details
- 📁 **Data Persistence**
  - All accounts are saved to `accounts.json`
  - Data persists between application runs
- 🧾 **Transaction Receipts**
  - Printable receipts with transaction IDs

## 🚀 Getting Started

### Prerequisites

- Java JDK 17 or later
- Basic understanding of Java programming

### Installation

1. Clone the repository:
   ```bash
   git clone https://github.com/yourusername/ATM-System.git
   ```
2. Navigate to the project directory:
   ```bash
   cd ATM-System
   ```
3. Compile the Java files:
   ```bash
   javac *.java
   ```
4. Run the application:
   ```bash
   java Main
   ```

## 📋 Usage

1. **Main Menu Options**:
   - `1. Register` - Create a new bank account
   - `2. Login` - Access an existing account

2. **Account Operations**:
   - `1. Account Detail` - View account information
   - `2. Check Balance` - View current balance
   - `3. Deposit` - Add funds to your account
   - `4. Withdraw` - Remove funds from your account
   - `5. Exit` - Log out and print receipt

## 📂 File Structure

```
ATM-System/
├── Account.java      # Account class with all banking operations
|-- Account.class     # Compiled class for Account.java
|-- Main.class        # Compiled class for Main.java
├── Main.java         # Main application with user interface
└── accounts.json     # Data file storing all accounts (auto-generated)
```

## 🛠️ Technical Details

- **Data Storage**: Accounts are stored in JSON format
- **Input Validation**: 
  - Account numbers must be numeric
  - PIN verification
  - Amount validation for deposits/withdrawals
- **Error Handling**: 
  - Invalid input detection
  - Insufficient funds checks

