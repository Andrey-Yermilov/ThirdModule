package DTO;

public class Order extends Entity {
    private String userName;
    private String userLogin;
    private String country;
    private double priceWithDiscount;
    private String tourType;
    private String isBurning;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public double getPriceWithDiscount() {
        return priceWithDiscount;
    }

    public void setPriceWithDiscount(double priceWithDiscount) {
        this.priceWithDiscount = priceWithDiscount;
    }

    public String getTourType() {
        return tourType;
    }

    public void setTourType(String tourType) {
        this.tourType = tourType;
    }

    public String getIsBurning() {
        return isBurning;
    }

    public void setIsBurning(String isBurning) {
        this.isBurning = isBurning;
    }
}
