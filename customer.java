
package advancedorderexport;

/**
 * *****************************************
 */
/* Customer                                 */
/*------------------------------------------*/
/* Contains address information, order time */
/* and other relevant info about individual */
/* orders.                                  */
/**
 * *****************************************
 */

public class customer {

    public String name;
    public String address1;
    public String address2;
    public String city;
    public String state;
    public String zip;
    public String country;
    public String orderNumber;

    public String toString() {
        return name + "\n" + address1 + "\n" + address2 + "\n" + city + ", " + state + " " + zip
                + "\n" + country + "\n\n";
    }

    public void fromBigCartel(String input) {
        String fields[] = input.split(",");
        orderNumber = fields[0];
        name = fields[1] + " " + fields[2];
        address1 = fields[9];
        if (fields[10].compareTo("\"\"") != 0) {
            address2 = fields[10];
        } else {
            address2 = "";
        }
        city = fields[11];
        state = fields[12];
        zip = fields[13];
        country = fields[14];
    }

    public void fromShopify(String input){
        String fields[] = input.split(",");
        orderNumber = fields[0];
        name = fields[34];
        address1 = fields[37];
        address2 = fields[38];
        city = fields[40];
        state = fields[42];
        zip = fields[41];
        country = fields[43];
    }
}
