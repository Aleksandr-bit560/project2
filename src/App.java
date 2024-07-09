@Data
@NoArgsConstructor
public class UserDto {

    @NotNull
    @Min(1)
    private Long id;

    @NotBlank
    @Size(min = 3, max = 50)
    private String name;

    @NotBlank
    @Email 
    private String email;

    @NotBlank
    @Size(min = 3, max = 50)
    private String password;

    @NotBlank
    private String role;
}

public interface UserService {
    boolean registerUser(@Valid UserDto userDto);
    boolean loginUser(@NotBlank String email, @NotBlank String password);
    boolean leaveReview(@Valid UserDto userDto, @NotBlank String pizza, @NotBlank String review);
    boolean selectPizza(@Valid UserDto userDto, @NotBlank String pizza);
    boolean updatePizzaAvailability(@NotBlank String pizzaName, boolean isAvailable, @Valid UserDto userDto);
}

public interface EmailService {
    void sendConfirmationEmail(String name, String email);
}

@Data
@NoArgsConstructor
public class orderDto {

    @NotNull
    private Long id;

    @NotBlank
    private String customerName;

    @NotBlank
    @Email
    private String customerEmail;

    @NotEmpty
    private List<PizzaItem> pizzaItems;

    @NotNull
    @Positive
    private BigDecimal totalAmount;

    @NotNull
    private OrderStatus status;

    @NotNull
    private LocalDateTime createdAt;

    @NotNull
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
@Validated
public class PizzaDto {

    @NotNull
    private Long id;

    @NotBlank
    @Size(min = 3, max = 50)
    private String name;

    @NotNull
    @Positive
    private BigDecimal price;

    @NotBlank
    @Size(min = 10, max = 200)
    private String description;

    @NotBlank
    @Size(min = 10, max = 20)
    private String category;
}

public interface PizzaService {
    List<PizzaDto> getAllPizzas();

    @NotNull
    PizzaDto getPizzaById(@NotNull @Positive Long id);

    @NotNull
    PizzaDto createPizza(@Valid @NotNull PizzaDto pizza);

    @NotNull
    PizzaDto updatePizza(@NotNull @Positive Long id, @Valid @NotNull PizzaDto pizza);

    void deletePizza(@NotNull @Positive Long id);
}