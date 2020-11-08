package model;

import annotation.Id;
import annotation.ManyToOne;
import annotation.Table;

import java.io.Serializable;
@Table("video")
public class Video implements Serializable {
    @Id
    private int id;
    @ManyToOne(fk = "chapterId",fkClassName = "model.Chapter")
    private Chapter chapter;
    private String url;

    public Video(int id, Chapter chapter, String url) {
        this.id = id;
        this.chapter = chapter;
        this.url = url;
    }

    public Video() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Chapter getChapter() {
        return chapter;
    }

    public void setChapter(Chapter chapter) {
        this.chapter = chapter;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
