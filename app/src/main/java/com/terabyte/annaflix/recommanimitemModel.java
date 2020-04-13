package com.terabyte.annaflix;

public class recommanimitemModel {

    String title;
    String year;
    String ratings;
    String imageurl;
    String stars;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getRatings() {
        return ratings;
    }

    public void setRatings(String ratings) {
        this.ratings = ratings;
    }

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }

    public String getStars() {
        return stars;
    }

    public void setStars(String stars) {
        this.stars = stars;
    }

    public String getDescrip() {
        return descrip;
    }

    public void setDescrip(String descrip) {
        this.descrip = descrip;
    }

    public String getExtra2() {
        return extra2;
    }

    public void setExtra2(String extra2) {
        this.extra2 = extra2;
    }

    public recommanimitemModel(String title, String year, String ratings, String imageurl, String stars, String descrip, String extra2) {
        this.title = title;
        this.year = year;
        this.ratings = ratings;
        this.imageurl = imageurl;
        this.stars = stars;
        this.descrip = descrip;
        this.extra2 = extra2;
    }

    String descrip;
    String extra2;

}
