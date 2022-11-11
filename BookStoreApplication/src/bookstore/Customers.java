package bookstore;

/**
 *
 * @author Tanin
 */

public class Customers {
    private String username;
    private String password;
    private int points;
    private String status;

    public Customers(String username, String password, int points) {
        this.username = username;
        this.password = password;
        this.points = points;
    }

    public String getUsername() {
        return username;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public int getPoints() {
        return points;
    }
    
    public void setPoints(double totalCost) {
        points += (int)totalCost * 10;
        setStatus();
    }
    
    public String getStatus() {
        return status;
    }

    public void setStatus() {
        if (getPoints() < 1000) {
            this.status = "Silver";
        } else {
            this.status = "Gold";
        }
    }
}