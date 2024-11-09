package com.emudhra.emra.subscriber.dto;

public class Captcha {
    private String text;
    private String image;

    public Captcha(String text,String image) {
        this.text = text;
        this.image = image;
    }

    public String getText() {
        return text;
    }

    public String getImage() {
      
    	return image;
    }
}
