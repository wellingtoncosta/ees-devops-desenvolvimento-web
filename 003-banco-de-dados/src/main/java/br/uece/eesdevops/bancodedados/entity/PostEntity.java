package br.uece.eesdevops.bancodedados.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "post")
public class PostEntity {

    @Id
    @GeneratedValue
    private long id;

    @Column
    private String title;

    @Column
    private String description;

    @Column
    private String date;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private List<PostCommentEntity> comments;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public List<PostCommentEntity> getComments() {
        return comments;
    }

    public void setComments(List<PostCommentEntity> comments) {
        this.comments = comments;
    }

}
