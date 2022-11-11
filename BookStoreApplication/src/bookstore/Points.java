package bookstore;

/**
 *
 * @author Tanin
 */

public abstract class Points {
    private String status;
    
public Points(String status){
    this.status = status;
}

public static void redeem(CustomerStart temp){
    double cost = temp.totalCost(temp.getCart());
    int othercost = temp.getPoints()/100;
    if(cost > othercost){
        temp.setPoints(0);
        temp.setTotalCost(cost - othercost);
    }
    if(cost <= othercost){
        temp.setPoints(temp.getPoints() - (int)cost * 100);
        temp.setTotalCost(0);
    }
}
        
}
