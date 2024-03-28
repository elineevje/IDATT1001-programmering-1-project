/**
 * Enum class, representing category for items.
 */
public enum ItemCategory {

  /**
   * Defining the categories and the belonging int.
   */
  FLOOR_LAMINATE(1),
  WINDOWS(2),
  DOORS(3),
  LUMBER(4);

  /**
   * Defining the object variable for category number.
   */
  private final int categoryNumber;

  /**
   * Constructor for category number.

   * @param categoryNumber Category number, given as an int.
   */
  private ItemCategory(int categoryNumber) {
    this.categoryNumber = categoryNumber;
  }

  /**
   * Get method for category number.
   *
   * @return Category number.
   */
  public int getCategoryNumber() {
    return categoryNumber;
  }

  /**
   * Method to match the given category number with the category. Checks
   * for illegal arguments. Checks if the input category number matches
   * with the number of the registered categories.
   *
   * @param categoryNumber Category number, consisting of an int.
   * @return Category of item if it matches with the category number.
   */
  public static ItemCategory getCategoryFromNumber(int categoryNumber) {
    if (categoryNumber < 1 || categoryNumber > ItemCategory.values().length) {
      throw new IllegalArgumentException("Please enter a number between 1 and "
          + ItemCategory.values().length + ".");
    }

    for (ItemCategory c : ItemCategory.values()) {
      if (c.getCategoryNumber() == (categoryNumber)) {
        return c;
      }
    }
    return null;
  }

  /**
   * Get method to get the number of categories.

   * @return Number of categories.
   */
  public static int getNumberOfCategories() {
    return ItemCategory.values().length;
  }
}