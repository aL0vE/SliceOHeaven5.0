import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class SliceOHaven {
    private String orderID;
    private String pizzaIngredients;
    private double orderTotal;
    private String sides;
    private String drinks;
    private String extraCheese;
    private String pizzaSize;
    private boolean wantDiscount;
    private String birthdate;
    private long cardNumber;
    private String expiryDate;
    private int cvv;

    public static final String DEF_ORDER_ID = "DEF - SOH - 099";
    public static final String DEF_PIZZA_INGREDIENTS = "Mozzarella Cheese";
    public static final double DEF_ORDER_TOTAL = 15.00;

    public SliceOHaven() {
        this.orderID = DEF_ORDER_ID;
        this.pizzaIngredients = DEF_PIZZA_INGREDIENTS;
        this.orderTotal = DEF_ORDER_TOTAL;
        this.sides = "";
        this.drinks = "";
    }

    public SliceOHaven(String orderID, String pizzaIngredients, double orderTotal) {
        this.orderID = orderID;
        this.pizzaIngredients = pizzaIngredients;
        this.orderTotal = orderTotal;
        this.sides = "";
        this.drinks = "";
    }

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public String getPizzaIngredients() {
        return pizzaIngredients;
    }

    public void setPizzaIngredients(String pizzaIngredients) {
        this.pizzaIngredients = pizzaIngredients;
    }

    public double getOrderTotal() {
        return orderTotal;
    }

    public void setOrderTotal(double orderTotal) {
        this.orderTotal = orderTotal;
    }

    @Override
    public String toString() {
        StringBuilder receipt = new StringBuilder();
        receipt.append("Order ID: ").append(orderID).append("\n");
        receipt.append("Pizza Ingredients: ").append(pizzaIngredients).append("\n");
        receipt.append("Order Total: ").append(orderTotal).append("\n");
        receipt.append("Sides: ").append(sides).append("\n");
        receipt.append("Drinks: ").append(drinks).append("\n");
        receipt.append("Extra Cheese: ").append(extraCheese).append("\n");
        receipt.append("Pizza Size: ").append(pizzaSize).append("\n");
        return receipt.toString();
    }

    public void displayReceipt() {
        System.out.println(this);
    }

    public void processCardPayment() {
        Scanner scanner = new Scanner(System.in);
        String cardStr;
        long cardNumber;
        do {
            System.out.println("Enter your card number:");
            cardNumber = scanner.nextLong();
            cardStr = Long.toString(cardNumber);
            if (cardStr.length() != 14) {
                System.out.println("Invalid card number length. Please enter a 14 - digit number.");
            } else {
                long blacklistedNumber = 12345;
                if (cardNumber == blacklistedNumber) {
                    System.out.println("Card is blacklisted. Please use another card");
                } else {
                    break;
                }
            }
        } while (true);
        scanner.nextLine();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date now = new Date();
        Date expDate;
        String expiryDate;
        do {
            System.out.println("Enter the card's expiry date:");
            expiryDate = scanner.nextLine();
            try {
                expDate = dateFormat.parse(expiryDate);
                if (expDate.before(now)) {
                    System.out.println("Invalid date. Expiry date must be in the future.");
                } else {
                    break;
                }
            } catch (ParseException e) {
                System.out.println("Invalid date format");
            }
        } while (true);
        System.out.println("Enter the card's cvv number:");
        int cvv = scanner.nextInt();
        String lastFourDigits = cardStr.substring(cardStr.length() - 4);
        String cardNumberToDisplay = cardStr.substring(0, 1) + "****" + lastFourDigits;
        System.out.println("Card Number to Display: " + cardNumberToDisplay);
    }
    public void specialofTheDay(String pizzaOfTheDay, String sideOfTheDay, String specialPrice) {
        StringBuilder details = new StringBuilder();
        details.append("Pizza of the Day: ").append(pizzaOfTheDay).append("\n")
               .append("Side of the Day: ").append(sideOfTheDay).append("\n")
               .append("Special Price: ").append(specialPrice);
        System.out.println(details.toString());
    }

    public void takeOrder() {
        Scanner scanner = new Scanner(System.in);
        int[] ingChoice = new int[3];
        boolean validChoice;
        do {
            validChoice = true;
            System.out.println("Please pick any three of the following ingredients:");
            System.out.println("1. Mushroom");
            System.out.println("2. Paprika");
            System.out.println("3. Sun - dried tomatoes");
            System.out.println("4. Chicken");
            System.out.println("5. Pineapple");
            System.out.println("Enter any three choices (1, 2, 3,...) separated by spaces:");
            String[] inputs = scanner.nextLine().split(" ");
            if (inputs.length != 3) {
                validChoice = false;
                System.out.println("Please enter exactly three choices.");
                continue;
            }
            for (int i = 0; i < 3; i++) {
                try {
                    ingChoice[i] = Integer.parseInt(inputs[i]);
                    if (ingChoice[i] < 1 || ingChoice[i] > 5) {
                        validChoice = false;
                        System.out.println("Invalid choice(s). Please pick only from the given list:");
                        break;
                    }
                } catch (NumberFormatException e) {
                    validChoice = false;
                    System.out.println("Invalid input. Please enter valid numbers.");
                    break;
                }
            }
        } while (!validChoice);

        String[] ingredients = {"", "Mushroom", "Paprika", "Sun - dried tomatoes", "Chicken", "Pineapple"};
        pizzaIngredients = ingredients[ingChoice[0]] + ", " + ingredients[ingChoice[1]] + ", " + ingredients[ingChoice[2]];

        int sizeChoice;
        do {
            System.out.println("What size should your pizza be?");
            System.out.println("1. Large");
            System.out.println("2. Medium");
            System.out.println("3. Small");
            System.out.println("Enter only one choice (1, 2, or 3):");
            try {
                sizeChoice = scanner.nextInt();
                if (sizeChoice < 1 || sizeChoice > 3) {
                    System.out.println("Invalid choice. Please enter a valid option.");
                } else {
                    break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
            }
        } while (true);

        switch (sizeChoice) {
            case 1:
                pizzaSize = "Large";
                break;
            case 2:
                pizzaSize = "Medium";
                break;
            case 3:
                pizzaSize = "Small";
                break;
        }

        scanner.nextLine();
        System.out.println("Do you want extra cheese (Y/N):");
        extraCheese = scanner.nextLine();
        int sideDishChoice;
        do {
            System.out.println("Following are the side dish that go well with your pizza:");
            System.out.println("1. Calzone");
            System.out.println("2. Garlic bread");
            System.out.println("3. Chicken puff");
            System.out.println("4. Muffin");
            System.out.println("5. Nothing for me");
            System.out.println("What would you like? Pick one (1, 2, 3,...):");
            try {
                sideDishChoice = scanner.nextInt();
                if (sideDishChoice < 1 || sideDishChoice > 5) {
                    System.out.println("Invalid choice. Please enter a valid option.");
                } else {
                    break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
            }
        } while (true);

        String[] sideDishes = {"", "Calzone", "Garlic bread", "Chicken puff", "Muffin", "Nothing for me"};
        sides = sideDishes[sideDishChoice];

        scanner.nextLine();
        int drinkChoice;
        do {
            System.out.println("Choose from one of the drinks below. We recommend Coca Cola:");
            System.out.println("1. Coca Cola");
            System.out.println("2. Cold coffee");
            System.out.println("3. Cocoa Drink");
            System.out.println("4. No drinks for me");
            System.out.println("Enter your choice:");
            try {
                drinkChoice = scanner.nextInt();
                if (drinkChoice < 1 || drinkChoice > 4) {
                    System.out.println("Invalid choice. Please enter a valid option.");
                } else {
                    break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
            }
        } while (true);

        String[] drinksList = {"", "Coca Cola", "Cold coffee", "Cocoa Drink", "No drinks for me"};
        drinks = drinksList[drinkChoice];
        System.out.println("Would you like the chance to pay only half for your order? (Y/N):");
        String input = scanner.nextLine();
        wantDiscount = input.equalsIgnoreCase("Y");
        if (wantDiscount) {
            isItYourBirthday(scanner);
        } else {
            processCardPayment(); 
        }   
    }

    public void isItYourBirthday(Scanner scanner) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date now = new java.util.Date();
        boolean validDate;
        do {
            validDate = true;
            System.out.println("Enter your birthday (format: yyyy - MM - dd):");
            birthdate = scanner.nextLine();
            try {
                java.util.Date birthd = dateFormat.parse(birthdate);
                long diff = now.getTime() - birthd.getTime();
                long years = diff / (1000 * 60 * 60 * 24 * 365);
                if (years < 5 || years > 120) {
                    System.out.println("Invalid date. You are either too young or too dead to order. Please enter a valid date:");
                    validDate = false;
                }
            } catch (ParseException e) {
                System.out.println("Invalid date format");
                validDate = false;
            }
        } while (!validDate);
        java.time.LocalDate today = java.time.LocalDate.now();
        java.time.LocalDate birthLocalDate = java.time.LocalDate.parse(birthdate);
        if (birthLocalDate.getDayOfMonth() == today.getDayOfMonth() && birthLocalDate.getMonth() == today.getMonth()) {
            System.out.println("Congratulations! You pay only half the price for your order");
        } else {
            System.out.println("Too bad! You do not meet the conditions to get our 50% discount");
        }
    }

    public static void main(String[] args) {
        SliceOHaven order = new SliceOHaven();
        order.takeOrder();
        order.displayReceipt();
        order.specialofTheDay("Durian Pizza", "beef", "$9.99");
    }
}