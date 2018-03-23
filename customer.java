

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
    public boolean shippedStatus;

    public String toString() {
        return name + "\n" + address1 + "\n" + address2 + "\n" + city + ", " + state + " " + zip
                + "\n" + country + "\n\n";
    }

    public void fromBigCartel(String input) {
        String fields[] = input.split(",");
        if (fields[8].compareTo("shipped") == 0) {
            shippedStatus = true;
        }else
        {
            shippedStatus = false;
        }
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

    public void fromShopify(String input) {
        String fields[] = input.split(",");
        if(fields[4].compareTo("unfulfilled")== 0)
        {
            shippedStatus = false;
        }else
        {
            shippedStatus = true;
        }
            
        orderNumber = fields[0];
        name = fields[34];
        address1 = fields[37];
        address2 = fields[38];
        city = fields[40];
        state = fields[42];
        zip = fields[41];
        country = fields[43];
    }

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
