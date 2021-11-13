package org.rbrtwlz.issuetracking.entity;

import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long authorId;
    private String name;
    private Boolean isSupportAuthor;
    @OneToMany(mappedBy = "author")
    private List<Issue> issues;

    public Author() {
    }
    @Autowired
    public Author(String name, Boolean isSupportUser) {
        this.name = name;
        this.isSupportAuthor = isSupportUser;
        this.issues = new ArrayList<>();
    }

    public void addIssue(Issue issue) {
        this.issues.add(issue);
    }

    @Override
    public String toString() {
        return "Author{" + "authorId=" + authorId + ", name='" + name + '\'' + '}';
    }

    public List<Issue> getIssues() {
        return this.issues;
    }

    public String getName() {
        return this.name;
    }

    public Long getAuthorId(){
        return this.authorId;
    }

    public boolean getIsSupportAuthor(){
        return this.isSupportAuthor;
    }
}
