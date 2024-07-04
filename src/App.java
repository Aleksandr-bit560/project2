public interface UserRepository{
    boolean SaveUser(String name, String email, String password);
    boolean ValidateUser(String email, String password);
}

public interface EmailService{
    void SendConfirmationEmail(String name, String email);

}

public interface ReviewRepository{
    boolean SaveReview(String userId, String pizza, String review);
}

public interface CartRepository{
    boolean SavePizzaInCart(String userId, String pizza);
}

public interface AdminRepository {
    boolean updatePizzaAvailability(String pizzaName, boolean isAvailable);
}

public class User{
    private final UserRepository userRepository;
    private final EmailService emailService;
    private final ReviewRepository reviewRepository;
    private final CartRepository cartRepository;
    private final AdminRepository adminRepository;
    
    public User(UserRepository userRepository, EmailService emailService, ReviewRepository reviewRepository, CartRepository cartRepository, AdminRepository adminRepository){
        this.userRepository = userRepository;
        this.emailService = emailService;
        this.reviewRepository = reviewRepository;
        this.cartRepository = cartRepository;
        this.adminRepository = adminRepository;
    }

    public boolean registerUser(String name, String email, String password){
        if(name == null || email == null || password == null){
            return false;
        }

        boolean isSaved = userRepository.saveUser(name, email, password);
        if (isSaved){
            emailService.sendConfirmationEmail(name, email);
        }
        return isSaved;
    }

    public boolean loginUser(String email, String password){
        return userRepository.ValidateUser(email, password);
    }

    public boolean leaveReview(String userId, String pizza, String review){
        if (userId == null || pizza == null || review == null){
            return false;
        }
        return reviewRepository.SaveReview(userId, pizza, review);
    }

    public boolean selectpizza(String userId, String pizza){
        if (userId == null || pizza == null){
            return false;
        }
        return cartRepository.SavePizzaInCart(userId, pizza);
    }

    public boolean updatePizzaAvailability(String pizzaName, boolean isAvailable, User user) {
        if (!user.isAdmin()) {
            return false;
        }
        return adminRepository.updatePizzaAvailability(pizzaName, isAvailable);
    }
}

public class OrderProces {
    public void proces(Order order){
        if (order.isValid() && save (order)){
            sendConfirmationEmail(order);
            order.setStatus("Принят, ожидаем подтверждения");
        }
    }

    private boolean save(Order order){
        MySqConnection connection = new MySqConnection("");
        return true;
    } 

    private void sendConfirmationEmail(Order order){
        String name = order.getCustomerName();
        String email = order.getCustomerEmail();
    }
    public String getOrderStatus(Order order) {
                    return order.getStatus();
    }

    public void cancelOrder(Order order) {
        if (order.getStatus().equals("Принят, ожидаем подтверждения")) {
            order.setStatus("Отменен");
            saveOrder(order);
            sendCancellationEmail(order);
        } else {
        }
    }
    
    private void saveOrder(Order order) {
        MySqlConnection connection = new MySqlConnection("");
    }
    
    private void sendCancellationEmail(Order order) {
        String name = order.getCustomerName();
        String email = order.getCustomerEmail();
    }
}

public class Pizza {
    private String name;
    private String[] toppings;
    private double price;
    private int size;

    public Pizza(String name, String[] toppings, double price, int size) {
        this.name = name;
        this.toppings = toppings;
        this.price = price;
        this.size = size;
    }

    public String getName() {
        return name;
    }

    public String[] getToppings() {
        return toppings;
    }

    public double getPrice() {
        return price;
    }

    public int getSize() {
        return size;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setToppings(String[] toppings) {
        this.toppings = toppings;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void displayPizzaInfo() {
        System.out.println("Pizza Name: " + name);
        System.out.print("Toppings: ");
        for (String topping : toppings) {
            System.out.print(topping + ", ");
        }
        System.out.println("\nPrice: $" + price);
        System.out.println("Size: " + size + " inches");
    }
}