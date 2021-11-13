package org.rbrtwlz.issuetracking.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Entry {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long entryId;
    private String text;
    private Date created;
    @ManyToOne
    @JoinColumn(name = "issue_id")
    private Issue issue;
    @ManyToOne
    @JoinColumn(name = "author_id")
    private Author author;

    public Entry() {
    }

    public Entry(String text, Issue issue, Author author, Date created) {
        this.text = text;
        this.issue = issue;
        this.author = author;
        this.created = created;
    }

    public Long getEntryId() {
        return entryId;
    }

    public String getText() {
        return text;
    }

    public String getAuthorName() {
        return author.getName();
    }

    public Date getCreated() {
        return created;
    }

    public boolean isSupportEntry(){
        return this.author.getIsSupportAuthor();
    }
}
