package MainPackage;

public class Item {
    String name;
    String serialNumber;
    double value;

    public String getName() {
        //gets the name of the current item
        return name;
    }

    public String getSerialNumber() {
        //gets the id of the current item
        return serialNumber;
    }

    public double getValue() {
        //gets the value of the current item
        return value;
    }

    public boolean checkSerialNumberFormat(String sn) {
        //ensures the string has a length of 13
        if(sn.length() != 13) {
            return false;
        }
        //ensures that first character is a letter (char 0)
        if(!Character.isLetter(sn.charAt(0))) {
            return false;
        }
        //ensures that characters 1, 5, 9 is a hyphen
        if(!((sn.charAt(1) == '-') && (sn.charAt(5) == '-') && (sn.charAt(9) == '-'))) {
            return false;
        }
        //ensures that characters 2, 3, 4, 6, 7, 8, 10, 11, 12
        if(!(Character.isLetterOrDigit(sn.charAt(2)) && Character.isLetterOrDigit(sn.charAt(3)) &&
                Character.isLetterOrDigit(sn.charAt(4)) && Character.isLetterOrDigit(sn.charAt(6)) &&
                Character.isLetterOrDigit(sn.charAt(7)) && Character.isLetterOrDigit(sn.charAt(8)) &&
                Character.isLetterOrDigit(sn.charAt(10)) && Character.isLetterOrDigit(sn.charAt(11)) &&
                Character.isLetterOrDigit(sn.charAt(12)))) {
            return false;
        }
        return true;
    }
}
