package fi.tamk.tiko.jsonparser;

/**
 * Product class
 *
 * Used to in the JSON files to make JSON Objects
 *
 * @author Toni Vanttinen (toni.vanttinen@cs.tamk.fi)
 * @version V1
 * @since 2018-11-19
 */
public class Product {
    /**
     * ID of the product
     */
    private int id;

    /**
     * Name of the product
     */
    private String name;

    /**
     * How many products there are
     */
    private int count;

    /**
     * Constructor of the product class
     *
     * @param id int used to set the ID
     * @param name String used to set the name
     * @param count int used to set the count
     */
    public Product(int id, String name, int count) {
        this.id = id;
        this.name = name;
        this.count = count;
    }

    /**
     * Getter for the ID
     * @return ID number of the product
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the ID of the product
     * @param id int that replaces the current ID
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Getter for the name
     * @return the name of the product
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of product
     * @param name String that replaces the current name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Getter for the count of the Product
     * @return The count of the product
     */
    public int getCount() {
        return count;
    }

    /**
     * Sets the count of product
     * @param count int that replaces the current count
     */
    public void setCount(int count) {
        this.count = count;
    }
}
