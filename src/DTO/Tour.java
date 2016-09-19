package DTO;

/**
 * tour DTO
 */
public class Tour extends Entity {
    private String type;
    private String country;
    private double price;
    private String burning;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getBurning() {
        return burning;
    }

    public void setBurning(String burning) {
        this.burning = burning;
    }
}
