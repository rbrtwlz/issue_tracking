package org.rbrtwlz.issuetracking.form;

//import javax.validation.constraints.Size;

public class EntryForm {

    // @Size(max = 255, message = "Only max {max} chars.")
    public String body;
    public Long issueId;

    public EntryForm() {

    }

    public EntryForm(String body) {
        this.body = body;
    }

    public String getBody() {
        return this.body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Long getIssueId() {
        return issueId;
    }

    public void setIssueId(Long issueId) {
        this.issueId = issueId;
    }
}