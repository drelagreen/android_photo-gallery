package com.crafsed.webant_practice.api_stuff;

public class PojoList {

    String totalItems;

    String countOfPages;

    private Data[] data;

    private String itemsPerPage;

    public String getTotalItems() {
        return totalItems;
    }

    public void setTotalItems(String totalItems) {
        this.totalItems = totalItems;
    }

    public String getCountOfPages() {
        return countOfPages;
    }

    public void setCountOfPages(String countOfPages) {
        this.countOfPages = countOfPages;
    }

    public Data[] getData() {
        return data;
    }

    public void setData(Data[] data) {
        this.data = data;
    }

    public String getItemsPerPage() {
        return itemsPerPage;
    }

    public void setItemsPerPage(String itemsPerPage) {
        this.itemsPerPage = itemsPerPage;
    }

    @Override
    public String toString() {
        return "ClassPojo [totalItems = " + totalItems + ", countOfPages = " + countOfPages + ", data = " + data + ", itemsPerPage = " + itemsPerPage + "]";
    }
}

