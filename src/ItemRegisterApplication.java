import java.util.ArrayList;
import java.util.Scanner;

/**
 * Class containing the application for item register.
 */
public class ItemRegisterApplication {

  /**
   * Defining the variables.
   */
  private final Scanner sc = new Scanner(System.in);
  private final ItemRegister itemRegister = new ItemRegister();

  private static final int ITEM_OVERVIEW = 1;
  private static final int ADD_TESTDATA = 2;
  private static final int ADD_ITEM = 3;
  private static final int SEARCH_ITEMS = 4;
  private static final int MAKE_CHANGES_TO_ITEM = 5;
  private static final int DELETE_ITEM = 6;
  private static final int EXIT = 7;

  public static void main(String[] args) {
    ItemRegisterApplication itemRegisterApplication = new ItemRegisterApplication();
    itemRegisterApplication.start();
  }

  /**
   * Method to show menu. Gives the user the option to enter a menu
   * input based on which function of the program they want to use. Using a
   * try/catch to reassure that it is only possible to enter an int between 1 and 7.

   * @return Menu input
   */
  private int showMenu() {
    String menu =
        """

            1. Item overview
            2. Add test data to the program
            3. Add a new item
            4. Search items
            5. Make changes to an item
            6. Delete a registered item
            7. Exit program
            """;
    System.out.println("\n---- Item register ----" + menu
        + "---------" + "\nPlease enter a number between 1 and 7.");
    int menuInput = 0;

    while (menuInput == 0) {
      try {
        menuInput = Integer.parseInt(sc.nextLine());
        if (menuInput < 1 || menuInput > 7) {
          throw new NumberFormatException("Number must be between 1 and 7.");
        }
      } catch (NumberFormatException e) {
        System.out.println("Please enter a number between 1 and 7.");
        menuInput = 0;
      }
    }
    return menuInput;
  }

  /**
   * Method to start the program. Using a switch statement inside a
   * while loop, so the program will keep on running until finished = true.
   * This only happens if the user chooses the switch case that exits the program.
   * Using the menu input from the user in the switch statement.
   */
  private void start() {
    boolean finished = false;

    while (!finished) {
      int menuInput = this.showMenu();
      switch (menuInput) {
        case ITEM_OVERVIEW -> printItems();
        case ADD_TESTDATA -> addTestData();
        case ADD_ITEM -> registerItem();
        case SEARCH_ITEMS -> findItem();
        case MAKE_CHANGES_TO_ITEM -> changesToItem();
        case DELETE_ITEM -> deleteItem();
        case EXIT -> {
          exitProgram();
          finished = true;
        }
        default -> {
          String tryAgain = ("You did not enter a number between 1 and 7. Please try again.");
          System.out.println(tryAgain);
        }
      }
    }
  }

  /**
   * Method used in the first switch case in start(). By using methods
   * from the ItemRegister class it lets the user choose between several
   * options to get an item overview. Using the same try/catch as in
   * showMenu() to make user the user only can input an int between 1 and 4.
   */
  private void printItems() {
    String choices = ("""

                1. Get the number of registered items
                2. Get a full overview of registered items
                3. Get a quick overview of registered items
                4. Go back to main menu
                """);
    System.out.println("\nPrint items\n" + "---------" + choices
        + "---------" + "\nPlease enter a number between 1 and 4.\n");
    int menuInput = 0;

    while (menuInput < 1 || menuInput > 4) {
      try {
        menuInput = Integer.parseInt(sc.nextLine());
      } catch (NumberFormatException e) {
        System.out.println("Please enter a number between 1 and 4.");
        menuInput = 0;
      }
    }

    switch (menuInput) {
      case 1 -> System.out.println("There are " + itemRegister.numberOfItems()
          + " items registered.");
      case 2 -> {
        String items = itemRegister.printItems();
        if (items.isBlank()) {
          System.out.println("There are no items registered.");
        } else {
          System.out.println(itemRegister.printItems());
        }
      }
      case 3 -> {
        String items = itemRegister.printItemsShortVersion();
        if (items.isBlank()) {
          System.out.println("There are no items registered.");
        } else {
          System.out.println(itemRegister.printItemsShortVersion());
        }
      }
      case 4 -> showMenu();
      default -> {
        String tryAgain = ("You did not enter a number between 1 and 4. Please try again.");
        System.out.println(tryAgain);
      }
    }
  }

  /**
   * Method used in the second switch case in start(). This adds the
   * test data to the program. Checks if any of the items are already
   * registered before adding.
   */
  private void addTestData() {
    boolean valid = false;
    try {
      ItemRegisterTestData.addTestData(itemRegister);
      ItemRegisterTestData.addTestData(itemRegister);
      valid = true;
    } catch (IllegalArgumentException e) {
      System.out.println(e.getMessage());
    }
    if (valid) {
      System.out.println("Test data added.");
    }
  }

  /**
   * Method used in the third switch case in start(). Using a try/catch to make
   * sure all the inputs are valid before registering the new item.
   */
  private void registerItem() {
    try {
      String itemNumber = readString("Item number: ");
      String itemName = readString("Item name: ");
      String itemDescription = readString("Item description: ");
      int price = readInt("Price: ");
      String brandName = readString("Brand name: ");
      double weight = readDouble("Weight in kg: ");
      double length = readDouble("Length in meters: ");
      double height = readDouble("Height in meters: ");
      String colour = readString("Colour: ");
      int numberOfItemsInStock = readInt("Number of items in stock: ");
      ItemCategory itemCategory = readCategory("""

          Category:
          1. Floor laminate
          2. Windows
          3. Doors
          4. Lumber
          Please enter a number between 1 and""" + " " + ItemCategory.getNumberOfCategories());
      Item item = new Item(itemNumber, itemName, itemDescription, price,
          brandName, weight, length, height, colour, numberOfItemsInStock, itemCategory);
      itemRegister.addItem(item);
      System.out.println("Item with item number " + itemNumber + " is successfully registered.");
    } catch (NumberFormatException e) {
      System.out.println("Invalid number input.");
    } catch (IllegalArgumentException e) {
      System.out.println("Error when registering item: " + e.getMessage());
      showMenu();
    }
  }

  /**
   * Method to read String input. Will be using this throughout the class.

   * @param message Message that tells the user what to input.
   * @return sc.nextLine()
   */
  private String readString(String message) {
    System.out.println(message);
    return sc.nextLine();
  }

  /**
   * Method to read integer input. Checks for illegal arguments.
   * Using a try/catch to make sure the input is an int.
   * Will be using this throughout the class.

   * @param message Message that tells the user what to input.
   * @return Menu input (an int).
   */
  private int readInt(String message) {
    System.out.println(message);
    Integer value = null;
    while (value == null) {
      try {
        value = Integer.parseInt(sc.nextLine());
        if (value < 0) {
          value = null;
          throw new IllegalArgumentException("Number must be at least 0.");
        }
      } catch (IllegalArgumentException e) {
        System.out.println("Input a valid whole number of at least 0.");
      }
    }
    return value;
  }

  /**
   * Method to read double input. Checks for illegal arguments.
   * Using a try/catch to make sure the input is a double.
   * Will be using this throughout the class.

   * @param message Message that tells the user what to input.
   * @return Menu input (a double).
   */
  private double readDouble(String message) {
    System.out.println(message);
    Double value = null;
    while (value == null) {
      try {
        value = Double.parseDouble(sc.nextLine());
        if (value <= 0) {
          value = null;
          throw new IllegalArgumentException("Number must be greater than 0.");
        }
      } catch (IllegalArgumentException e) {
        System.out.println("Input a valid number greater than 0.");
      }
    }
    return value;
  }

  /**
   * Method to read ItemCategory input. Checks for illegal arguments.
   * Will be using this throughout the class.

   * @param message Message that tells the user what to input.
   * @return Menu input (an itemCategory).
   */
  private ItemCategory readCategory(String message) {
    System.out.println(message);
    ItemCategory itemCategory = null;
    while (itemCategory == null) {
      try {
        itemCategory = ItemCategory.getCategoryFromNumber(Integer.parseInt(sc.nextLine()));
      } catch (IllegalArgumentException e) {
        System.out.println("Please enter a number between 1 and "
            + ItemCategory.getNumberOfCategories());
      }
    }
    return itemCategory;
  }

  /**
   * Method used in fourth switch case in start(), to find an item/items,
   * By using methods from the ItemRegister class it lets
   * the user choose between several options to find an item by.
   * Using the same try/catch as in showMenu() to make user the user
   * only can input an int between 1 and 5. Throws an IllegalArgumentException
   * in every switch case, except the fifth one, to make sure the input
   * from the user is valid.
   */
  private void findItem() {
    String choices = ("""

                1. Find item by item number
                2. Find item(s) by item description
                3. Find item(s) by number and description
                4. Find item(s) by itemCategory
                5. Go back to main menu
                """);
    System.out.println("\nSearch items\n" + "---------"
        + choices + "---------" + "\nPlease enter a number between 1 and 5.\n");
    int menuInput = 0;

    while (menuInput < 1 || menuInput > 5) {
      try {
        menuInput = Integer.parseInt(sc.nextLine());
      } catch (NumberFormatException e) {
        System.out.println("Please enter a number between 1 and 5.");
        menuInput = 0;
      }
    }

    switch (menuInput) {
      case 1 -> {
        String itemNumber = readString("Item number: ");
        try {
          Item item = itemRegister.itemByNumber(itemNumber);
          if (item != null) {
            System.out.println(item);
          } else {
            System.out.println("Item with item number '" + itemNumber + "' does not exist.");
          }
        } catch (IllegalArgumentException e) {
          System.out.println("Error when finding item: " + e.getMessage());
        }
      }
      case 2 -> {
        String itemDescription = readString("Item description: ");

        try {
          ArrayList<Item> items = itemRegister.itemsByDescription(itemDescription);
          if (items.size() > 0) {
            for (Item item : items) {
              System.out.println(item);
            }
          } else {
            System.out.println("No items with description '" + itemDescription + "' were found.");
          }
        } catch (IllegalArgumentException e) {
          System.out.println("Error when finding item: " + e.getMessage());
        }
      }
      case 3 -> {
        String itemNumber = readString("Item number: ");
        String itemDescription = readString("Item description: ");
        try {
          ArrayList<Item> items =
              itemRegister.itemsByNumberOrDescription(itemNumber, itemDescription);
          if (items.size() > 0) {
            for (Item item : items) {
              System.out.println(item);
            }
          } else {
            System.out.println("No items with " + itemNumber + " or description '"
                + itemDescription + "' were found.");
          }
        } catch (IllegalArgumentException e) {
          System.out.println("Error when finding item: " + e.getMessage());
        }
      }
      case 4 -> {
        ItemCategory itemCategory = readCategory("""

            Category:
            1. Floor laminate
            2. Windows
            3. Doors
            4. Lumber
            Please enter a number between 1 and""" + " " + ItemCategory.getNumberOfCategories());

        try {
          ArrayList<Item> items = itemRegister.itemByCategory(itemCategory);
          if (items.size() > 0) {
            for (Item item : items) {
              System.out.println(item);
            }
          } else {
            System.out.println("No items with itemCategory '" + itemCategory + "' were found.");
          }
        } catch (NumberFormatException e) {
          System.out.println("Invalid number input.");
        } catch (IllegalArgumentException e) {
          System.out.println("Error when finding item: " + e.getMessage());
        }
      }
      case 5 -> showMenu();
      default -> {
        String tryAgain = ("You did not enter a number between 1 and 5. Please try again.");
        System.out.println(tryAgain);
      }
    }
  }

  /**
   * Method used in the fifth switch case in start(), to make changes
   * to an item. By using methods from the ItemRegister class it lets
   * the user choose between several options for making changes to an
   * item. Using the same try/catch as in showMenu() to make user the
   * user only can input an int between 1 and 6. Throws an IllegalArgumentException
   * in every switch case, except the sixth one, to make sure the input from
   * the user is valid.
   */
  private void changesToItem() {
    String choices = ("""

                1. Increase number of the item in stock
                2. Decrease number of the item in stock
                3. Change price of an item
                4. Set a discount for an item
                5. Change description of an item
                6. Go back to main menu
                """);
    System.out.println("\nMake changes to an item\n" + "---------"
        + choices + "---------" + "\nPlease enter a number between 1 and 6.\n");
    int menuInput = 0;

    while (menuInput < 1 || menuInput > 6) {
      try {
        menuInput = Integer.parseInt(sc.nextLine());
      } catch (NumberFormatException e) {
        System.out.println("Please enter a number between 1 and 6.");
        menuInput = 0;
      }
    }

    switch (menuInput) {
      case 1 -> {
        String itemNumber = readString("Item number: ");
        int increase = readInt("Number of this item added to stock: ");
        try {
          itemRegister.increaseNumberOfItemInStock(itemNumber, increase);
          System.out.println("Number of items in stock is updated.");
        } catch (IllegalArgumentException e) {
          System.out.println("Error when increasing number of items in stock: " + e.getMessage());
        }
      }
      case 2 -> {
        String itemNumber = readString("Item number: ");
        int decrease = readInt("Number of this item removed from stock: ");
        try {
          itemRegister.decreaseNumberOfItemInStock(itemNumber, decrease);
          System.out.println("Number of items in stock is updated.");
        } catch (IllegalArgumentException e) {
          System.out.println("Error when decreasing number of items in stock: " + e.getMessage());
        }
      }
      case 3 -> {
        String itemNumber = readString("Item number: ");
        int newPrice = readInt("New price of item: ");
        try {
          itemRegister.changePrice(itemNumber, newPrice);
          System.out.println("New price is now registered.");
        } catch (IllegalArgumentException e) {
          System.out.println("Error when changing price of the item: " + e.getMessage());
        }
      }
      case 4 -> {
        String itemNumber = readString("Item number: ");
        int discount = readInt("Discount of item, given in percentage: ");
        try {
          itemRegister.discountPrice(itemNumber, discount);
          System.out.println("New price is now registered.");
        } catch (IllegalArgumentException e) {
          System.out.println("Error when giving price a discount: " + e.getMessage());
        }
      }
      case 5 -> {
        String itemNumber = readString("Item number: ");
        String itemDescription = readString("New description of item: ");
        try {
          itemRegister.changeDescription(itemNumber, itemDescription);
          System.out.println("Item description is changed.");
        } catch (IllegalArgumentException e) {
          System.out.println("Error when changing item description: " + e.getMessage());
        }
      }
      case 6 -> showMenu();
      default -> {
        String tryAgain = ("You did not enter a number between 1 and 6. Please try again.");
        System.out.println(tryAgain);
      }
    }
  }

  /**
   * Method used in the sixth switch case in start(), to delete an
   * item. By using the method from the ItemRegister class it lets the
   * user choose to delete an item, or go back to the main menu if they
   * no longer want to delete an item. Using the same try/catch as in
   * showMenu() to make user the user only can input an int between
   * 1 and 2. Throws an IllegalArgumentException in the first
   * switch case to make sure the input from the user is valid.
   */
  private void deleteItem() {
    String choices = ("""

        1. Delete item
        2. Go back to main menu
        """);
    System.out.println("\nDelete item\n" + "---------" + choices + "---------"
        + "\nPlease enter a number between 1 and 2\n");
    int menuInput = 0;

    while (menuInput < 1 || menuInput > 2) {
      try {
        menuInput = Integer.parseInt(sc.nextLine());
      } catch (NumberFormatException e) {
        System.out.println("Please enter a number between 1 and 2.");
        menuInput = 0;
      }
    }

    switch (menuInput) {
      case 1 -> {
        String itemNumber = readString("Item number of the item you want to delete: ");
        try {
          itemRegister.deleteItem(itemNumber);
          System.out.println("Item with the item number " + itemNumber + " is deleted.");
        } catch (IllegalArgumentException e) {
          System.out.println("Error when deleting item: " + e.getMessage());
        }
      }
      case 2 -> showMenu();
      default -> {
        String tryAgain = ("You did not enter a number between 1 and 2. Please try again.");
        System.out.println(tryAgain);
      }
    }
  }

  /**
   * Method used in the seventh switch case in start(), to exit
   * the program. Using the same try/catch as in showMenu() to make
   * sure the user only can input an int between 1 and 2. When choosing
   * to exit the program breaks out of the while-loop in start().
   */
  private void exitProgram() {
    String choices = """

                1. Yes
                2. No
                """;
    System.out.println("\nAre you sure you want to exit the program?\n"
        + choices + "---------" + "\nPlease enter a number between 1 and 2.\n");
    int menuInput = 0;

    while (menuInput < 1 || menuInput > 2) {
      try {
        menuInput = Integer.parseInt(sc.nextLine());
      } catch (NumberFormatException e) {
        System.out.println("Please enter a number between 1 and 2.");
        menuInput = 0;
      }
    }

    switch (menuInput) {
      case 1 -> {
        String exit = ("Exiting program ...");
        System.out.println(exit);
        System.exit(0);
      }
      case 2 -> showMenu();
      default -> {
        String tryAgain = ("You did not enter a number between 1 and 2. Please try again.");
        System.out.println(tryAgain);
      }
    }
  }
}


