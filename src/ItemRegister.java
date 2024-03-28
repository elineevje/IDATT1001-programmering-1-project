import java.util.ArrayList;

/**
 * Class representing item register. Holds the items, as well as methods used in the Client class.
 */
public class ItemRegister {

  /**
   * ArrayList made from the Item class.
   */
  private final ArrayList<Item> items;

  /**
   * Constructor for item register.
   */
  public ItemRegister() {
    items = new ArrayList<>();
  }

  /**
   * Deep copy constructor for item register.

   * @param itemRegister Item register.
   */
  public ItemRegister(ItemRegister itemRegister) {
    items = new ArrayList<>();

    for (Item item : itemRegister.items) {
      items.add(new Item(item));
    }
  }

  /**
   * Boolean used in addTestData() in the ItemRegisterTestData class
   * to check if an item already exists or not.

   * @param itemNumber Unique item number, compares this with the
   *                  new item to check if it already exists or not.
   * @return Null, meaning that the item does not exist.
   */
  public boolean itemNotExists(String itemNumber) {
    return itemByNumber(itemNumber) == null;
  }

  /**
   * Method to print items using the toString method in the item class.

   * @return Items converted to a string.
   */
  public String printItems() {
    StringBuilder allItems = new StringBuilder();

    for (Item item : items) {
      allItems.append(item.toString()).append("\n");
    }
    return allItems.toString();
  }

  /**
   * Method to print items using the toStringShort method in the item class.

   * @return Items converted to string.
   */
  public String printItemsShortVersion() {
    StringBuilder itemsShort = new StringBuilder();

    for (Item item : items) {
      itemsShort.append(item.toStringShort());
    }
    return itemsShort.toString();
  }

  /**
   * Method to find the number of registered items, by finding the size of the arrayList items.

   * @return Size of the arrayList items.
   */
  public int numberOfItems() {
    return items.size();
  }

  /**
   * Method to add a new item to the register.

   * @param item Item.
   */
  public void addItem(Item item) {
    if ((itemByNumber(item.getItemNumber()) != null)) {
      throw new IllegalArgumentException("Item with item number "
          + item.getItemNumber() + " already exists.\n");
    } else {
      items.add(item);
    }
  }

  /**
   * Method to delete an item. Checks for illegal arguments.
   * Uses the itemByNumber(itemNumber) method to check if the item exists.
   * If it does, it will be removed from the ArrayList items.

   * @param itemNumber Item number of the item you want to delete.
   */
  public void deleteItem(String itemNumber) {
    if (itemNumber.isBlank()) {
      throw new IllegalArgumentException("Item number can not be empty.");
    }
    Item item = itemByNumber(itemNumber);

    if (item == null) {
      throw new IllegalArgumentException("Item with item number "
          + itemNumber + " does not exist.");
    } else {
      items.remove(item);
    }
  }

  /**
   * Method to increase the number of items in stock of a specific item.
   * Checks for illegal arguments. Uses the itemByNumber(itemNumber) method
   * to check if the item exists. If it does, it will set the new
   * number.

   * @param itemNumber Item number, used to identify the item you want to change.
   * @param increasedNumber The number added to stock.
   */
  public void increaseNumberOfItemInStock(String itemNumber, int increasedNumber) {
    if (increasedNumber <= 0) {
      throw new IllegalArgumentException("Number of added items in stock must be greater than 0.");
    }
    Item item = itemByNumber(itemNumber);

    if (item == null) {
      throw new IllegalArgumentException("Item with item number "
          + itemNumber + " does not exist.");
    } else {
      item.setNumberOfItemsInStock(item.getNumberOfItemsInStock() + increasedNumber);
    }
  }

  /**
   * Method to decrease the number of items in stock of a specific item.
   * Checks for illegal arguments. Uses the itemByNumber(itemNumber) method to
   * check if the item exists. If it does it will first make sure that the
   * decreased number is less than the number of items already in stock, and
   * then set the new number.

   * @param itemNumber Item number, used to identify the item you want to change.
   * @param decreasedNumber The number decreased from stock.
   */
  public void decreaseNumberOfItemInStock(String itemNumber, int decreasedNumber) {
    if (decreasedNumber <= 0) {
      throw new IllegalArgumentException("Number of added items in stock must be greater than 0.");
    }
    Item item = itemByNumber(itemNumber);

    if (item == null) {
      throw new IllegalArgumentException("Item with item number "
          + itemNumber + " does not exist.");
    } else {
      if (decreasedNumber > item.getNumberOfItemsInStock()) {
        throw new IllegalArgumentException(
            "The number of items decreased can not be greater than the number already in stock.");
      } else {
        item.setNumberOfItemsInStock(item.getNumberOfItemsInStock() - decreasedNumber);
      }
    }
  }

  /**
   * Method to change the price of a specific item. Checks for
   * illegal arguments. Uses the itemByNumber(itemNumber) method
   * to check if the item exists. If it does, it will set the
   * new price.

   * @param itemNumber Item number, used to identify the item you want to change.
   * @param newPrice Updated price of the item with the given item number.
   */
  public void changePrice(String itemNumber, int newPrice) {
    if (newPrice < 0) {
      throw new IllegalArgumentException("Price must be greater than 0.");
    }
    Item item = itemByNumber(itemNumber);

    if (item == null) {
      throw new IllegalArgumentException("Item with item number "
          + itemNumber + " does not exist.");
    } else {
      item.setPrice(newPrice);
    }
  }

  /**
   * Method to give an item a discount price. Checks for illegal
   * arguments. Uses the itemByNumber(itemNumber) method to check
   * if the item exists. If it does, it will set the new
   * price.
   *
   * @param itemNumber Item number, used to identify the item you want to change.
   * @param discount Discount that will be given to the price, given in percentage.
   */
  public void discountPrice(String itemNumber, double discount) {
    if (discount <= 0 || discount >= 100) {
      throw new IllegalArgumentException("Discount percentage must be greater than 0.");
    }
    Item item = itemByNumber(itemNumber);

    if (item == null) {
      throw new IllegalArgumentException("Item with item number "
          + itemNumber + " does not exist.");
    } else {
      int newPrice = (int) ((1 - (discount / 100)) * item.getPrice());
      item.setPrice(newPrice);
    }
  }

  /**
   * Method to change description of a specific item. Checks for
   * illegal arguments. Uses the itemByNumber(itemNumber)
   * method to check if the item exists. If it does, it will set the new
   * description.

   * @param itemNumber Item number, used to identify the item you want to change.
   * @param newItemDescription Updated item description of the item with the given item number.
   */
  public void changeDescription(String itemNumber, String newItemDescription) {
    if (newItemDescription.isBlank()) {
      throw new IllegalArgumentException("Item description can not be empty.");
    }
    Item item = itemByNumber(itemNumber);

    if (item == null) {
      throw new IllegalArgumentException("Item with item number "
          + itemNumber + " does not exist.");
    } else {
      item.setItemDescription(newItemDescription);
    }
  }

  /**
   * Method to find an item by the item number. Checks for illegal
   * arguments. Checks if the input item number matches with any of
   * the item numbers already registered.

   * @param itemNumber Item number of the item you want to find.
   * @return Item with the given item number, but only if the item is already in the register.
   */
  public Item itemByNumber(String itemNumber) {
    if (itemNumber.isBlank()) {
      throw new IllegalArgumentException("Item number can not be empty.");
    }

    for (Item item : items) {
      if (item.getItemNumber().equalsIgnoreCase(itemNumber)) {
        return item;
      }
    }
    return null;
  }

  /**
   * Method to find an item or items by the item description. Checks for
   * illegal arguments. Checks if the input item description is contained in
   * any of the already registered item descriptions. Using contains(), instead of
   * equals(), lets the user find an item without having to input the full,
   * precise description of an item.

   * @param itemDescription Item description of the item you want to find.
   * @return ArrayList consisting of the item, or items, that contains the
   *         same item description as input. Can be more than one since the item only
   *         needs to contain the input of the user.
   */
  public ArrayList<Item> itemsByDescription(String itemDescription) {
    if (itemDescription.isBlank()) {
      throw new IllegalArgumentException("Item description can not be empty.");
    }
    ArrayList<Item> foundItems = new ArrayList<Item>();

    for (Item item : items) {
      if (item.getItemDescription().toLowerCase().contains(itemDescription.toLowerCase())) {
        foundItems.add(item);
      }
    }
    return foundItems;
  }

  /**
   * Method to find an item by item number and description. Will first look
   * for items by the item number, by using the itemByNumber(itemNumber) method.
   * If this is not successful it will look for items by description, by using the
   * itemsByDescription(itemDescription) method.

   * @param itemNumber Item number of the item you want to find.
   * @param itemDescription Item description of the item you want to find.
   * @return ArrayList consisting of the item, or items, with the given number and/or description.
   */
  public ArrayList<Item> itemsByNumberOrDescription(String itemNumber, String itemDescription) {
    ArrayList<Item> foundItems = new ArrayList<Item>();
    Item item = itemByNumber(itemNumber);

    if (item != null) {
      foundItems.add(item);
    } else {
      foundItems.addAll(itemsByDescription(itemDescription));
    }
    return foundItems;
  }

  /**
   * Method to find an item or items by the category. Checks for
   * illegal arguments. Checks if the category matches with the category of
   * any of the registered items. If it does, it will be added to an ArrayList.

   * @param itemCategory The category from where you want to find items.
   * @return ArrayList containing the item, or items, in the given category.
   */
  public ArrayList<Item> itemByCategory(ItemCategory itemCategory) {
    if (itemCategory == null) {
      throw new IllegalArgumentException("Item category can not be empty.");
    }
    ArrayList<Item> foundItems = new ArrayList<Item>();

    for (Item item : items) {
      if (item.getCategory().equals(itemCategory)) {
        foundItems.add(item);
      }
    }
    return foundItems;
  }
}
