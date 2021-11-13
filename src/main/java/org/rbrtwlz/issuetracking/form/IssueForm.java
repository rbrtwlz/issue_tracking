package org.rbrtwlz.issuetracking.form;

//import javax.validation.constraints.Size;

public class IssueForm {

    // @Size(max = 255, message = "Only max {max} chars.")
    public String issueTitle;
    public String entryBody;

    public IssueForm() {

    }

    public IssueForm(String entryBody) {
        this.entryBody = entryBody;
    }

    public String getIssueTitle() {
        return issueTitle;
    }

    public void setIssueTitle(String issueTitle) {
        this.issueTitle = issueTitle;
    }

    public String getEntryBody() {
        return this.entryBody;
    }

    public void setEntryBody(String entryBody) {
        this.entryBody = entryBody;
    }

}