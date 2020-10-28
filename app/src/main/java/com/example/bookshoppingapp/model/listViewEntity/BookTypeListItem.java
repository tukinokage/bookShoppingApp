package com.example.bookshoppingapp.model.listViewEntity;

import com.example.bookshoppingapp.model.BookTypeEnum;

//用于listvie
public class BookTypeListItem {
    public boolean getIsCheck() {
        return isCheck;
    }

    public void setIsCheck(boolean isCheck) {
        this.isCheck = isCheck;
    }

    public BookTypeEnum getBookTypeEnum() {
        return bookTypeEnum;
    }

    public void setBookTypeEnum(BookTypeEnum bookTypeEnum) {
        this.bookTypeEnum = bookTypeEnum;
    }

    private boolean isCheck;
   private BookTypeEnum bookTypeEnum;

}
