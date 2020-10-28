package com.example.bookshoppingapp.model.listViewEntity;

//用于listview
public class CartItem implements Cloneable {

    private static final long serialVersionUID = 1L;

    private String itemId;

    private String bookId;
    private String bookName;
    private String iconPath;
    private int num;

    private float cost;



    private float singlePrice;

    private boolean isChecked;


    public float getSinglePrice() {
        return singlePrice;
    }

    public void setSinglePrice(float singlePrice) {
        this.singlePrice = singlePrice;
    }
    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getIconPath() {
        return iconPath;
    }

    public void setIconPath(String iconPath) {
        this.iconPath = iconPath;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public float getCost() {
        return cost;
    }

    public void setCost(float cost) {
        this.cost = cost;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }




    @Override
    public CartItem clone() throws CloneNotSupportedException {
        CartItem cartItem = (CartItem) super.clone();
        return  cartItem;
    }



}
