package com.example.pma_1.Classes;

public class CustomSpinnerItem {

    private String spinnerText; //text
    private String langCode;    //language code
    private int spinnerImage;   //spinner image is int type -> number on resource id of drawables, array of images and we get index, image actually

    public CustomSpinnerItem(String spinnerText, String langCode, int spinnerImage) {
        this.spinnerText = spinnerText;
        this.langCode = langCode;
        this.spinnerImage = spinnerImage;
    }

    public String getSpinnerText() {
        return spinnerText;
    }

    public String getLangCode() { return langCode; }

    public int getSpinnerImage() {
        return spinnerImage;
    }
}
