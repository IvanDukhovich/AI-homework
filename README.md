# Expense Calculator MVC

A simple Spring Boot MVC web application to calculate and analyze monthly expenses.

## Features
- Add new expenses (category and amount)
- View all expenses in a table
- Calculate and display:
  - Total amount of expenses
  - Average daily expense (over 30 days)
  - Top 3 largest expenses
- Delete expenses from the list

## Technologies Used
- Java 17+
- Spring Boot 3
- Thymeleaf (server-side HTML rendering)
- Gradle

## Getting Started

### Prerequisites
- Java 17 or higher
- Gradle (or use the Gradle wrapper if present)

### Build and Run

1. **Clone the repository** (if needed):
   ```sh
   git clone <your-repo-url>
   cd <project-directory>
   ```
2. **Run the application:**
   ```sh
   gradle bootRun
   ```
   or, if using the wrapper:
   ```sh
   ./gradlew bootRun
   ```
3. **Open your browser:**
   Go to [http://localhost:8080/](http://localhost:8080/)

## Usage
- Fill in the category and amount, then click "Add Expense".
- The table will show all your expenses.
- The summary section displays the total, average daily, and top 3 expenses.
- Click "Delete" to remove an expense from the list.

## Project Structure
```
├── src/
│   ├── main/
│   │   ├── java/com/expense/mvc/
│   │   │   ├── ExpenseCalculatorMvcApplication.java
│   │   │   ├── controller/ExpenseMvcController.java
│   │   │   └── model/
│   │   │       ├── Expense.java
│   │   │       ├── ExpenseModel.java
│   │   │       └── ExpenseSummary.java
│   │   └── resources/
│   │       ├── templates/expense.html
│   │       └── static/style.css
├── build.gradle
├── .gitignore
└── README.md
```

## API Testing

Automated tests were run against [https://fakestoreapi.com/products](https://fakestoreapi.com/products) using RestAssured and JUnit:

- **Test objectives:**
  - Verify server response code is 200
  - Confirm each product has:
    - `title` (not empty)
    - `price` (not negative)
    - `rating.rate` (not exceeding 5)
  - Generate a list of products containing defects

**Test Results (as of last run):**
- Number of tests: **1**
- Failures: **0**
- Success rate: **100%**
- All products passed the validation checks.

## SQL Queries

**1. Calculate the total sales volume for March 2024:**
```sql
SELECT SUM(amount) AS total_sales_march_2024
FROM orders
WHERE order_date >= '2024-03-01' AND order_date < '2024-04-01';
```

**2. Find the customer who spent the most overall:**
```sql
SELECT customer, SUM(amount) AS total_spent
FROM orders
GROUP BY customer
ORDER BY total_spent DESC
LIMIT 1;
```

**3. Calculate the average order value for the last three months (Jan, Feb, Mar 2024):**
```sql
SELECT AVG(amount) AS avg_order_value_last_3_months
FROM orders
WHERE order_date >= '2024-01-01' AND order_date < '2024-04-01';
```

## License
This project is for educational/demo purposes. 