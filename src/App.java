@Data
@NoArgsConstructor
public class UserDto {
    private Long id;
    private String name;
    private String email;
    private String password;
    private String role;
}

public interface UserService {
    boolean registerUser(UserDto userDto);
    boolean loginUser(String email, String password);
    boolean leaveReview(UserDto userDto, String pizza, String review);
    boolean selectPizza(UserDto userDto, String pizza);
    boolean updatePizzaAvailability(String pizzaName, boolean isAvailable, UserDto userDto);
}

public interface EmailService {
    void sendConfirmationEmail(String name, String email);
}

@Data
@NoArgsConstructor
public class orderDto {
    private Long id;
    private String customerName;
    private String customerEmail;
    private List<PizzaItem> pizzaItems;
    private BigDecimal totalAmount;
    private OrderStatus status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}

public enum OrderStatus {
    Pending,
    Processing,
    Completed,
    Cancelled
}

@Data
@NoArgsConstructor
public class PizzaItem {
    private String name;
    private BigDecimal price;
    private int quantity;
}

public interface OrderService {
    boolean createOrder(orderDto, UserDto userDto);
    boolean processOrder(Long orderId, OrderStatus status, UserDto userDto);
    boolean saveOrder(OrderDto orderDto);
    OrderDto getOrderStatus(Long orderId, UserDto userDto);
    boolean cancelOrder(Long orderId, UserDto userDto);
}

public interface EmailService {
    void sendConfirmationEmail(OrderDto orderDto, UserDto userDto);
    void sendOrderCancellationNotification(OrderDto orderDto, UserDto userDto);
}

@Data
@NoArgsConstructor
public class PizzaDto {
    private Long id;
    private String name;
    private BigDecimal price;
    private String description;
    private String category;
}

public interface PizzaService {
    List<PizzaDto> getAllPizzas();
    PizzaDto getPizzaById(Long id);
    PizzaDto createPizza(PizzaDto pizza);
    PizzaDto updatePizza(Long id, PizzaDto pizza);
    void deletePizza(Long id);
}