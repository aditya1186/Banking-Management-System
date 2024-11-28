# Banking Management System (BMS)

A robust, web-based Banking Management System designed to streamline core banking operations. This project is developed using **Java**, **JDBC**, **MySQL**, **Servlets**, and **JSP**, offering features for account creation, secure transactions, and real-time tracking.

## Features

- **Account Creation**: Create new accounts with details like name, Aadhaar number, mobile number, and initial deposit.
- **Deposit & Withdrawal**: Seamlessly manage deposits and withdrawals with real-time balance updates.
- **Fund Transfer**: Transfer money securely between accounts.
- **Transaction History**: View detailed transaction history with timestamps.
- **Real-Time Balance Check**: Check current account balance instantly.
- **Dynamic Account Number Generation**: Automatically generates unique account numbers for new accounts.
- **Responsive User Interface**: Built using JSP for dynamic and interactive web pages.

## Tech Stack

- **Frontend**: JSP, HTML, CSS
- **Backend**: Java Servlets
- **Database**: MySQL
- **Tools**: JDBC for database connectivity
- **Server**: Apache Tomcat

## Database Schema

1. **Accounts Table (`bms`)**
   - `accno` (Primary Key)
   - `cname`
   - `fname`
   - `adharno`
   - `mob`
   - `bal`

2. **Transactions Table (`transactions`)**
   - `id` (Primary Key)
   - `accno` (Foreign Key)
   - `operation_type` (Deposit/Withdrawal)
   - `amount`
   - `balance_after`
   - `timestamp`

## Setup Instructions

1. Clone the repository:
   ```bash
   git clone https://github.com/your-username/banking-management-system.git
