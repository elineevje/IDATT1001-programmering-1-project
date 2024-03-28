/**
 * Class representing item.
 */
public class Item {

  /**
   * Defining all the object variables for item. Final variables does not have any mutator methods
   * and will not be changed. The other variables will have mutator methods, which gives them the
   * opportunity to be able to be changed.
   */
  private final String itemNumber;
  private final String itemName;
  private String itemDescription;
  private final String brandName;
  private final String colour;
  private int price;
  private final double weight;
  private final double length;
  private final double height;
  private int numberOfItemsInStock;
  private final ItemCategory itemCategory;

  /**
   * Constructor for item, containing all the information about an item.

   * @param itemNumber Unique number for the item, consisting of letters and/or numbers.
   * @param itemName Name of item.
   * @param itemDescription Description of item.
   * @param price Price of item, given in kr.
   * @param brandName Brand name of item.
   * @param weight Weight of item, given in kg.
   * @param length Length of item, given in m.
   * @param height Height of item, given in m.
   * @param colour Colour of item.
   * @param numberOfItemsInStock Number of items in stock.
   * @param itemCategory Category of item.
   */
  public Item(String itemNumber, String itemName, String itemDescription,
              int price, String brandName, double weight, double length, double height,
              String colour, int numberOfItemsInStock, ItemCategory itemCategory) {
    checkIllegalArguments(itemNumber, itemName, itemDescription, price, brandName, weight,
        length, height, colour, numberOfItemsInStock);
    this.itemNumber = itemNumber;
    this.itemName = itemName;
    this.itemDescription = itemDescription;
    this.price = price;
    this.brandName = brandName;
    this.weight = weight;
    this.length = length;
    this.height = height;
    this.colour = colour;
    this.numberOfItemsInStock = numberOfItemsInStock;
    this.itemCategory = itemCategory;
  }

  /**
   * Deep copy constructor for item.

   * @param item Item.
   */
  public Item(Item item) {
    checkIllegalArguments(item.itemNumber, item.itemName, item.itemDescription,
        item.price, item.brandName, item.weight, item.length, item.height,
        item.colour, item.numberOfItemsInStock);
    this.itemNumber = new String(item.getItemNumber());
    this.itemName = new String(item.getItemName());
    this.itemDescription = new String(item.getItemDescription());
    this.price = item.getPrice();
    this.brandName = new String(item.getBrandName());
    this.weight = item.getWeight();
    this.length = item.getLength();
    this.height = item.getHeight();
    this.colour = new String(item.getColour());
    this.numberOfItemsInStock = item.getNumberOfItemsInStock();
    this.itemCategory = item.getCategory();
  }

  /**
   * Get method for item number.

   * @return Item number.
   */
  public String getItemNumber() {
    return itemNumber;
  }

  /**
   * Get method for item name.

   * @return Item name.
   */
  public String getItemName() {
    return itemName;
  }

  /**
   * Get method for item description.

   * @return Item description.
   */
  public String getItemDescription() {
    return itemDescription;
  }

  /**
   * Get method for price of item.

   * @return Price of item.
   */
  public int getPrice() {
    return price;
  }

  /**
   * Get method for brand name of item.

   * @return Brand name of item.
   */
  public String getBrandName() {
    return brandName;
  }

  /**
   * Get method for weight of item.

   * @return Weight of item in kg.
   */
  public double getWeight() {
    return weight;
  }

  /**
   * Get method for length of item.

   * @return Length of item in m.
   */
  public double getLength() {
    return length;
  }

  /**
   * Get method for height of item.

   * @return Height of item in m.
   */
  public double getHeight() {
    return height;
  }

  /**
   * Get method for colour of item.

   * @return Colour of item.
   */
  public String getColour() {
    return colour;
  }

  /**
   * Get method for number of items in stock.

   * @return Number of items in stock.
   */
  public int getNumberOfItemsInStock() {
    return numberOfItemsInStock;
  }

  /**
   * Get method for category of item.

   * @return Category of item.
   */
  public ItemCategory getCategory() {
    return itemCategory;
  }

  /**
   * Set method for updated price of item. Checks for
   * illegal arguments.

   * @param newPrice New price of item in kr.
   */
  public void setPrice(int newPrice) {
    if (newPrice < 0) {
      throw new IllegalArgumentException("Price must be at least 0 kr.");
    } else {
      this.price = newPrice;
    }
  }

  /**
   * Set method for updated item description. Checks for
   * illegal arguments.

   * @param newItemDescription New description of item.
   */
  public void setItemDescription(String newItemDescription) {
    if (newItemDescription.isBlank()) {
      throw new IllegalArgumentException("Please enter an item description.");
    } else {
      this.itemDescription = newItemDescription;
    }
  }

  /**
   * Set method for updated number of items in stock. Checks for
   * illegal arguments.

   * @param newNumberOfItemsInStock New number of items in stock.
   */
  public void setNumberOfItemsInStock(int newNumberOfItemsInStock) {
    if (newNumberOfItemsInStock < 0) {
      throw new IllegalArgumentException("Stock must be greater than 0.");
    } else {
      this.numberOfItemsInStock = newNumberOfItemsInStock;
    }
  }

  /**
   * To string method for item, consisting of all the variables in item.

   * @return String for item, including all the information about an item.
   */
  @Override
  public String toString() {
    return "\nItem\n--------------------\n"
        + "Item number: " + getItemNumber() + "\n"
        + "Item name: " + getItemName() + "\n"
        + "Item description: " + getItemDescription() + "\n"
        + "Price: " + getPrice() + "\n"
        + "Brand name: " + getBrandName() + "\n"
        + "Weight: " + getWeight() + "\n"
        + "Length: " + getLength() + "\n"
        + "Height: " + getHeight() + "\n"
        + "Colour: " + getColour() + "\n"
        + "Number of items in stock: " + getNumberOfItemsInStock() + "\n"
        + "Category: " + getCategory() + "\n";
  }

  /**
   * To string method for item, consisting of the most relevant variables in item.

   * @return String for item, including only the most relevant information about an item.
   */
  public String toStringShort() {
    return "\nItem\n--------------------\n"
        + "Item number: " + getItemNumber() + "\n"
        + "Item name: " + getItemName() + "\n"
        + "Price: " + getPrice() + "\n"
        + "Brand name: " + getBrandName() + "\n"
        + "Number of items in stock: " + getNumberOfItemsInStock() + "\n"
        + "Category: " + getCategory() + "\n";
  }

  /**
   * Method to check if any of the variables in Item contains any illegal arguments.

   * @param itemNumber Unique number for the item, consisting of letters and/or numbers.
   * @param itemName Name of item.
   * @param itemDescription Description of item.
   * @param price Price of item, given in kr.
   * @param brandName Brand name of item.
   * @param weight Weight of item, given in kg.
   * @param length Length of item, given in m.
   * @param height Height of item, given in m.
   * @param colour Colour of item.
   * @param numberOfItemsInStock Number of items in stock.
   */
  private void checkIllegalArguments(String itemNumber, String itemName, String itemDescription,
                                     int price, String brandName, double weight, double length,
                                     double height, String colour, int numberOfItemsInStock) {
    if (itemNumber.isBlank()) {
      throw new IllegalArgumentException("Item number can not be empty.");
    }
    if (itemName.isBlank()) {
      throw new IllegalArgumentException("Item name can not be empty.");
    }
    if (itemDescription.isBlank()) {
      throw new IllegalArgumentException("Item description can not be empty.");
    }
    if (price < 0) {
      throw new IllegalArgumentException("Price must be at least 0 kr.");
    }
    if (brandName.isBlank()) {
      throw new IllegalArgumentException("Brand name can not be empty.");
    }
    if (weight <= 0) {
      throw new IllegalArgumentException("Weight must be greater than 0 kilograms.");
    }
    if (length <= 0) {
      throw new IllegalArgumentException("Length must be greater than 0 meters.");
    }
    if (height <= 0) {
      throw new IllegalArgumentException("Height must be greater than 0 meters.");
    }
    if (colour.isBlank()) {
      throw new IllegalArgumentException("Colour can not be empty.");
    }
    if (numberOfItemsInStock < 0) {
      throw new IllegalArgumentException("Number of items in stock must be at least 0.");
    }
  }
}

