package br.uece.eesdevops.bancodedados.entity;

import javax.persistence.*;

@Entity
@Table(name = "post_comment")
public class PostCommentEntity {

    @Id
    @GeneratedValue
    private long id;

    @Column
    private String author;

    @Column
    private String description;

    @Column
    private String date;

    @ManyToOne(fetch = FetchType.LAZY)
    private PostEntity post;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public PostEntity getPost() {
        return post;
    }

    public void setPost(PostEntity post) {
        this.post = post;
    }

}
