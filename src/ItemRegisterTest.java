import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Class for testing.
 */
public class ItemRegisterTest {

  /**
   * Creates a new item1, and a copy of item1 by using the copy constructor
   * in Item.
   */
  Item item1 = new Item("ABC123", "Door", "Tall and big",
      2000, "IKEA", 12.3, 1.1, 3.1,
      "black", 100, ItemCategory.DOORS);
  Item item2 = new Item(item1);
  ItemRegister itemRegister = new ItemRegister();

  /**
   * This will be printed before the test.
   */
  @BeforeClass
  public static void start() {
    System.out.println("Starting unit test");
  }

  /**
   * This will be printed after the test.
   */
  @AfterClass
  public static void completed() {
    System.out.println("Unit test completed");
  }

  /**
   * Test to check if the get methods of Item are
   * working as they should.
   */
  @Test
  public void testCreatedItem() {
    System.out.println("Testing item creation");
    assertEquals(item1.getItemNumber(), "ABC123");
    assertEquals(item1.getItemName(), "Door");
    assertEquals(item1.getItemDescription(), "Tall and big");
    assertEquals(item1.getPrice(), 2000);
    assertEquals(item1.getBrandName(), "IKEA");
    assertEquals(item1.getWeight(), 12.3, 1e-6);
    assertEquals(item1.getLength(), 1.1, 1e-6);
    assertEquals(item1.getHeight(), 3.1, 1e-6);
    assertEquals(item1.getColour(), "black");
    assertEquals(item1.getNumberOfItemsInStock(), 100);
    assertEquals(item1.getCategory(), ItemCategory.DOORS);
  }

  /**
   * Test to check if item1 equals item2.
   */
  @Test
  public void testEqualItems() {
    System.out.println("Testing item copy");
    assertEquals(item1.getItemNumber(), item2.getItemNumber());
    assertEquals(item1.getItemName(), item2.getItemName());
    assertEquals(item1.getItemDescription(), item2.getItemDescription());
    assertEquals(item1.getPrice(), item2.getPrice());
    assertEquals(item1.getBrandName(), item2.getBrandName());
    assertEquals(item1.getWeight(), item2.getWeight(), 1e-6);
    assertEquals(item1.getLength(), item2.getLength(), 1e-6);
    assertEquals(item1.getHeight(), item2.getHeight(), 1e-6);
    assertEquals(item1.getColour(), item2.getColour());
    assertEquals(item1.getNumberOfItemsInStock(), item2.getNumberOfItemsInStock());
    assertEquals(item1.getCategory(), item2.getCategory());
  }

  /**
   * Test to check that the set methods are working as they should.
   */
  @Test
  public void testItemUpdate() {
    System.out.println("Testing item update");
    item1.setPrice(2100);
    assertEquals(item1.getPrice(), 2100);

    item1.setItemDescription("small");
    assertEquals(item1.getItemDescription(), "small");

    item1.setNumberOfItemsInStock(200);
    assertEquals(item1.getNumberOfItemsInStock(), 200);
  }

  /**
   * Test to check that the methods in the Item register class are working.
   */
  @Test
  public void testItemRegister() {
    System.out.println("Testing item register operations");
    ItemRegisterTestData.addTestData(itemRegister);

    assertTrue(itemRegister.numberOfItems() > 0);

    itemRegister.increaseNumberOfItemInStock("9898ABC", 10);
    assertEquals(itemRegister.itemByNumber("9898ABC").getNumberOfItemsInStock(), 990);

    itemRegister.decreaseNumberOfItemInStock("12BK5", 10);
    assertEquals(itemRegister.itemByNumber("12BK5").getNumberOfItemsInStock(), 90);

    itemRegister.changePrice("12BK5", 1700);
    assertEquals(itemRegister.itemByNumber("12BK5").getPrice(), 1700);

    itemRegister.changeDescription("12BK5", "Cool");
    assertEquals(itemRegister.itemByNumber("12BK5").getItemDescription(), "Cool");

    itemRegister.discountPrice("9898ABC", 10);
    assertEquals(itemRegister.itemByNumber("9898ABC").getPrice(), 360);

    itemRegister.deleteItem("BE394ER");
    assertNull(itemRegister.itemByNumber("BE394ER"));
  }
}

