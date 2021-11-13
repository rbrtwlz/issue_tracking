package org.rbrtwlz.issuetracking.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Issue {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long issueId;
    @ManyToOne
    @JoinColumn(name = "author_id")
    private Author author;
    @ManyToOne
    @JoinColumn(name = "coauthor_id")
    private Author coAuthor;
    private String title;
    private Date created;
    @OneToMany(mappedBy = "issue")
    private List<Entry> entries;

    public Issue() {
    }

    public Issue(Author author, Author coAuthor, String title, Date created) {
        this.author = author;
        this.coAuthor = coAuthor;
        this.title = title;
        this.created = created;
        this.entries = new ArrayList<>();
    }

    @Override
    public String toString() {
        return "Issue{" + "issueIdId=" + issueId + ", author=" + author.toString() + ", title='" + title + '\'' + '}';
    }

    public void addEntry(Entry entry) {
        this.entries.add(entry);
    }

    public Long getIssueId() {
        return issueId;
    }

    public String getAuthorName() {
        return author.getName();
    }

    public String getTitle() {
        return this.title;
    }

    public List<Entry> getEntries() {
        this.entries.sort((o1, o2) -> o1.getEntryId().compareTo(o2.getEntryId()));
        return this.entries;
    }

    public Date getCreated() {
        return created;
    }
}
