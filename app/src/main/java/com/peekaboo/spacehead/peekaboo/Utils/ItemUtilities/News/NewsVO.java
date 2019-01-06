package com.peekaboo.spacehead.peekaboo.Utils.ItemUtilities.News;

import java.util.ArrayList;

public class NewsVO {

  private String title;
  private String author;
  private String description;
  private String url;
  private String poster;
  private String publishedDate;
  public String source;

  public String getSource() {
    return source;
  }

  public void setSource(String source) {
    this.source = source;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getAuthor() {
    return author;
  }

  public void setAuthor(String author) {
    this.author = author;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public String getPoster() {
    return poster;
  }

  public void setPoster(String poster) {
    this.poster = poster;
  }

  public String getPublishedDate() {
    return publishedDate.substring(0,10);
  }

  public void setPublishedDate(String publishedDate) {
    this.publishedDate = publishedDate;
  }
}
