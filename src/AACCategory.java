import structures.*;

/**
 * @class AACCategory
 * Creates a category of images and their associated texts
 * 
 * @author Catie Baker
 * @author Pranav K Bhandari 
 */

public class AACCategory {

  // +--------+------------------------------------------------------------
  // | Fields |
  // +--------+
  private String name;
  private AssociativeArray<String, String> imageMap;

  // +--------+------------------------------------------------------------
  // | Constructors|
  // +-------------+
  
  /*
   * Creates a new empty category with name 
   */
  public AACCategory(String name) {
    this.name = name;
    this.imageMap = new AssociativeArray<String, String>();
  }

  // +--------+------------------------------------------------------------
  // | Methods|
  // +--------+

  /* 
   * Adds one mapping (given imageLoc to given text) to the category.
   */
  public void addItem(String imageLoc, String text) throws NullKeyException{
    this.imageMap.set(imageLoc, text);
  }

  /*
   * Returns the name of the category
   */
  public String getCategory() {
    return this.name;
  }

  /*
   * Returns an array comprising the images in this category
   */
  public String[] getImages() {
    String[] imagesArray = new String[this.imageMap.size()];
    int size = 0;
    for(KVPair<String, String> pair : this.imageMap) {
      imagesArray[size++] = pair.getKey(); 
    }
    return imagesArray;
  }

  /*
   * Determines if the provided images is stored in the category
   */
  public boolean hasImage(String imageLoc) {
    return this.imageMap.hasKey(imageLoc);
  }

  /*
   * Returns the text associated with imageLoc in this category
   */
  public String getText(String imageLoc) throws KeyNotFoundException {
    if (this.hasImage(imageLoc)) {
      return this.imageMap.get(imageLoc);
    }
    throw new KeyNotFoundException();
  }

  /*
   * Returns a String representing the category
   */
  public String toString() {
    String result = "";
    for (KVPair<String, String> pair : this.imageMap) {
      result = result + ">" + pair.getKey() + " " + pair.getValue() + "\n";
    }
    return result;
  }
} 