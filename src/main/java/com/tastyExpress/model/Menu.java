package com.tastyExpress.model;

public class Menu {

    private int menuId;
    private int restaurantId;
    private String itemName;
    private String description;
    private int price;             // integer price (₹150, ₹200, etc.)
    private double ratings;        // double is better than float
    private boolean available;     // true / false instead of string
    private String imageUrl;       // better naming

    public Menu() {}

    public Menu(int menuId, int restaurantId, String itemName, String description,
                int price, double ratings, boolean available, String imageUrl) {
        this.menuId = menuId;
        this.restaurantId = restaurantId;
        this.itemName = itemName;
        this.description = description;
        this.price = price;
        this.ratings = ratings;
        this.available = available;
        this.imageUrl = imageUrl;
    }

    public int getMenuId() {
        return menuId;
    }

    public void setMenuId(int menuId) {
        this.menuId = menuId;
    }

    public int getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(int restaurantId) {
        this.restaurantId = restaurantId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public double getRatings() {
        return ratings;
    }

    public void setRatings(double ratings) {
        this.ratings = ratings;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Override
    public String toString() {
        return "Menu{" +
                "menuId=" + menuId +
                ", restaurantId=" + restaurantId +
                ", itemName='" + itemName + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", ratings=" + ratings +
                ", available=" + available +
                ", imageUrl='" + imageUrl + '\'' +
                '}';
    }
}
