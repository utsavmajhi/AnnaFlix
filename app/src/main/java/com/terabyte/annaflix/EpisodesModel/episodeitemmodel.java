package com.terabyte.annaflix.EpisodesModel;

public class episodeitemmodel {

    String title;
    String videolink;
    String ex1;
    String ex2;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getVideolink() {
        return videolink;
    }

    public void setVideolink(String videolink) {
        this.videolink = videolink;
    }

    public String getEx1() {
        return ex1;
    }

    public void setEx1(String ex1) {
        this.ex1 = ex1;
    }

    public String getEx2() {
        return ex2;
    }

    public void setEx2(String ex2) {
        this.ex2 = ex2;
    }

    public String getEx3() {
        return ex3;
    }

    public void setEx3(String ex3) {
        this.ex3 = ex3;
    }

    public episodeitemmodel(String title, String videolink, String ex1, String ex2, String ex3) {
        this.title = title;
        this.videolink = videolink;
        this.ex1 = ex1;
        this.ex2 = ex2;
        this.ex3 = ex3;
    }

    String ex3;

}
