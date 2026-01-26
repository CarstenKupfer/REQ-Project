package at.Technikum;

public class CustomerManager {

    public Customer[] customers = new Customer[50];
    public int[] balance = new int[50];
    public int count = 0;

    public void registerAccount(String name, String customerId) {
        if (find(customerId) != -1) return;
        customers[count] = new Customer(name, customerId);
        balance[count] = 0;
        count++;
    }

    private int find(String customerId) {
        for (int i = 0; i < count; i++) {
            if (customers[i] != null && customers[i].CustomerID.equals(customerId)) return i;
        }
        return -1;
    }

    public void setBalance(String customerId, int b) {
        int idx = find(customerId);
        if (idx == -1) throw new IllegalStateException("Customer not found");
        balance[idx] = b;
    }

    public int getBalance(String customerId) {
        int idx = find(customerId);
        if (idx == -1) throw new IllegalStateException("Customer not found");
        return balance[idx];
    }

    public void deduct(String customerId, int amount) {
        int idx = find(customerId);
        if (idx == -1) throw new IllegalStateException("Customer not found");
        balance[idx] -= amount;
    }
}
