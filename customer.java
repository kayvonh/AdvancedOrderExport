
/* Customer is a container object for individual envelope orders. Does a little bit of helpful string
preprocessing for MainWindow.h, mostly string formatting. */
public class customer {

    public String name;
    public String address1;
    public String address2;
    public String city;
    public String state;
    public String zip;
    public String country;
    public String orderNumber;
    public boolean shippedStatus;

    public String toString() {
        return name + "\n" + address1 + "\n" + address2 + "\n" + city + ", " + state + " " + zip
                + "\n" + country + "\n\n";
    }

    //Take string line from a Bigcartel orders.csv, set the customer equal to its data.
    public void fromBigCartel(String input) {
        String fields[] = input.split(",");         //split CSV into array of its data values. 
        orderNumber = fields[0];                    //prepopulate basic info
        address1 = fields[9];
        city = fields[11];
        state = fields[12];
        zip = fields[13];
        country = fields[14];
        name = fields[1] + " " + fields[2];         // combine names together. 
        
        /* boundary cases that require some simple processing.*/
        if (fields[8].compareTo("shipped") == 0) {  // has the order been shipped already?
            shippedStatus = true;
        } else {
            shippedStatus = false;
        }
        
        if (fields[10].compareTo("\"\"") != 0) {  //CSVs save the null character as "".
            address2 = fields[10];
        } else {
            address2 = "";                        // Convert address 2 to the null character.
        }
    }

    // Take string line from a Bigcartel orders.csv, set the customer equal to its data. 
    public void fromShopify(String input) {
        String fields[] = input.split(",");        //split CSV into array of its data values. 
        orderNumber = fields[0];
        name = fields[34];
        address1 = fields[37];
        city = fields[40];
        state = fields[42];
        zip = fields[41];
        country = fields[43];
        
         if (fields[38].compareTo("\"\"") != 0) {  //CSVs save the null character as "".
            address2 = fields[38];
        } else {
            address2 = "";                        // Convert address 2 to the null character.
        }
         
        if (fields[4].compareToIgnoreCase("unfulfilled") == 0) {  // has the order been shipped already?
            shippedStatus = true;
        } else {
            shippedStatus = false;
        }
    }
    // Used by the export process, this returns a value by an arbitrary position.
    public String printIdentifier(int identifier) {
        switch (identifier) {
            case 0:
                return name;
            case 1:
                return address1;
            case 2:
                return address2;
            case 3:
                return city + ", " + state + " " + zip;
            case 4:
                return country;
            default:
                return "ERROR";
        }
    }
}
