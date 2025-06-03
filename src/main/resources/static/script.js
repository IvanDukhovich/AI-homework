async function fetchExpenses() {
    const res = await fetch('/api/expenses');
    return await res.json();
}

async function fetchSummary() {
    const res = await fetch('/api/expenses/summary');
    return await res.json();
}

async function addExpense() {
    const category = document.getElementById('category').value;
    const amount = parseFloat(document.getElementById('amount').value);

    if (!category || !amount) {
        alert('Please fill in both category and amount!');
        return;
    }

    await fetch('/api/expenses', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ category, amount })
    });
    updateExpensesTable();
    clearForm();
}

function clearForm() {
    document.getElementById('category').value = '';
    document.getElementById('amount').value = '';
}

async function deleteExpense(index) {
    await fetch(`/api/expenses/${index}`, { method: 'DELETE' });
    updateExpensesTable();
}

async function updateExpensesTable() {
    const expenses = await fetchExpenses();
    const tbody = document.getElementById('expensesList');
    tbody.innerHTML = '';

    expenses.forEach((expense, index) => {
        const row = tbody.insertRow();
        row.insertCell(0).textContent = expense.category;
        row.insertCell(1).textContent = formatCurrency(expense.amount);
        
        const deleteCell = row.insertCell(2);
        const deleteButton = document.createElement('button');
        deleteButton.textContent = 'Delete';
        deleteButton.className = 'delete-btn';
        deleteButton.onclick = () => deleteExpense(index);
        deleteCell.appendChild(deleteButton);
    });
}

async function calculateExpenses() {
    const summary = await fetchSummary();
    document.getElementById('totalExpenses').textContent = 
        `Total Expenses: ${formatCurrency(summary.total)}`;
    document.getElementById('averageDaily').textContent = 
        `Average Daily Expense: ${formatCurrency(summary.averageDaily)}`;
    document.getElementById('topExpenses').textContent = 
        `Top 3 Expenses: ${formatTopExpenses(summary.top3)}`;
}

function formatCurrency(amount) {
    return `$${Number(amount).toLocaleString('en-US', {minimumFractionDigits: 2, maximumFractionDigits: 2})}`;
}

function formatTopExpenses(topExpenses) {
    if (!topExpenses || topExpenses.length === 0) return 'None';
    return topExpenses
        .map(expense => `${expense.category} (${formatCurrency(expense.amount)})`)
        .join(', ');
}

document.addEventListener('DOMContentLoaded', () => {
    updateExpensesTable();
}); 