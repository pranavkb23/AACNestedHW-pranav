import structures.*;
import java.util.Scanner;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;


/**
 * Creates a mapping of all AACCategories
 * 
 * @author Catie Baker
 * @author Pranav K Bhandari
 */
public class AACMappings {

  // +--------+------------------------------------------------------------
  // | Fields |
  // +--------+
  private String catName;
  private AACCategory home;
  private AACCategory category;
  private AssociativeArray<String, AACCategory> allCategories;
  private AssociativeArray<String, String> imgCatMap;

  // +--------+------------------------------------------------------------
  // | Constructors|
  // +-------------+

  public AACMappings(String fileInput) throws Exception {
    // initialize home, allCategories, imgCatMap and open a file to read from
    this.home = new AACCategory("homeCat");
    this.allCategories = new AssociativeArray<String, AACCategory>();
    this.imgCatMap = new AssociativeArray<String, String>();
    File file = new File(fileInput);
    Scanner scanner = new Scanner(file);
    String input = scanner.nextLine();

    while (input != null) {
      if (input.startsWith(">")) {
        // Parsing item lines
        String[] parts = input.substring(1).trim().split(" ", 2);
        String imgLoc = parts[0];
        String textToSpeak = parts[1];
        // adding the image to the current category and then updating the category
        this.category.addItem(imgLoc, textToSpeak);
        allCategories.set(this.catName, this.category);
        
    } else {
        // Parsing category line
        String[] parts = input.trim().split(" ", 2);
        String imgLoc = parts[0];
        String curCat = parts[1];
        // adding the location to a map of all image locations and the category represented by them
        this.imgCatMap.set(imgLoc, curCat);
        this.catName = curCat;
        // adding the image location and name to the home category
        this.home.addItem(imgLoc, curCat);
        this.category = new AACCategory(curCat);
        // creating a new category and updating allCategories to include the new one
        this.allCategories.set(catName, this.category);
    }
    try {
      input = scanner.nextLine();
    } catch (Exception e) {
      input = null;
    }
    }
    scanner.close();
    this.catName = "";
    this.category = this.home;
  }

  // +--------+------------------------------------------------------------
  // | Methods|
  // +--------+

  /* 
  * Adds the mapping to the current or default category 
  */
  public void add(String imageLoc, String text) throws Exception {
    if (this.catName.equals("")){
      // Create a category if home
      // adding location and category to the imgCatMap
      this.imgCatMap.set(imageLoc, text);
      // adding the category to our home
      this.home.addItem(imageLoc, text);
      this.category = new AACCategory(text);
      // creating new category and adding it to our list of categories
      this.allCategories.set(text, this.category);
    } else {
      // add an image to the current category
      this.category.addItem(imageLoc, text);
      // update allCategories
      allCategories.set(this.catName, this.category);
    }
  }

  /*
  * Returns the current category
  */
  public String getCurrentCategory() {
    return this.catName;
  }

  /*
    * Returns an array of the images in the current category
    */
  public String[] getImageLocs() {
    if (this.catName.equals("")) {
      return this.home.getImages();
    }
    return this.category.getImages();
  }

  /*
    * Given the image location, returns the text associated with it.
    */
  public String getText(String imageLoc) throws Exception {
    if (this.catName.equals("")) {
      this.catName = this.imgCatMap.get(imageLoc);
      this.category = this.allCategories.get(this.catName);
      return this.catName;
    }
    return this.category.getText(imageLoc);
  }

/*
  * Determines if the image represents a category or text to speak
  */
  public boolean isCategory(String imageLoc) {
    return this.imgCatMap.hasKey(imageLoc);
  }

  /*
  * Resets the current category to the home category
  */
  public void reset() {
    this.catName = "";
    this.category = this.home;
  }

  /*
  * Writes the ACC mappings to a file.
  */
  public void writeToFile(String filename) throws IOException {
    String catLoc, location, name;
    String itemsInCat = "";
    // Open file for writing
    FileWriter writer = new FileWriter(filename);
    for (KVPair<String, String> pair : this.imgCatMap) {
      // go through each image location mapped to a category
       location = pair.getKey();
       name = pair.getValue();
       try {
        // get the string of a particular category, includes its contents
        itemsInCat = allCategories.get(name).toString();
      } catch (KeyNotFoundException e) {
        e.printStackTrace();
      }
      // combine the location, name and a newline, then write to our file.
       catLoc = location + " " + name + "\n";
       writer.write(catLoc); 
       writer.write(itemsInCat);
    }
    writer.close();
  }
}