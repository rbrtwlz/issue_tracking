package org.rbrtwlz.issuetracking.controller;

import org.rbrtwlz.issuetracking.entity.*;
import org.rbrtwlz.issuetracking.form.EntryForm;
import org.rbrtwlz.issuetracking.form.IssueForm;
import org.rbrtwlz.issuetracking.service.AuthorService;
import org.rbrtwlz.issuetracking.service.IssueService;
import org.rbrtwlz.issuetracking.service.EntryService;
import org.rbrtwlz.issuetracking.service.UserService;
import org.rbrtwlz.issuetracking.user.CustomUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Date;
import java.util.List;

@Controller
public class IssueController {

    AuthorService authorService;
    IssueService issueService;
    EntryService entryService;
    UserService userService;

    @Autowired
    public IssueController(AuthorService authorService, IssueService issueService, EntryService entryService, UserService userService) {
        this.authorService = authorService;
        this.issueService = issueService;
        this.entryService = entryService;
        this.userService = userService;
    }

    @GetMapping(path = "/issues")
    public String overview(Model model) {
        CustomUserDetails userDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userDetails.getUser();
        Author author = this.authorService.getAuthorById(user.getAuthor().getAuthorId());
        List<Issue> issues = author.getIssues();
        model.addAttribute("userName", user.getFirstName());
        model.addAttribute("issues", issues);
        return "overview2";
    }

    @GetMapping(path = "/issues/new_issue")
    public String newIssue(Model model) {
        IssueForm issueForm = new IssueForm();
        model.addAttribute("issueForm", issueForm);
        return "new_issue";
    }

    @GetMapping(path = "/issues/{issueId}")
    public String issues(Model model, @PathVariable("issueId") Long issueId) {
        Issue issue = this.issueService.getIssueById(issueId);
        List<Entry> entries = issue.getEntries();
        model.addAttribute("issueTitle", issue.getTitle());
        model.addAttribute("entries", entries);
        EntryForm entryForm = new EntryForm();
        entryForm.setIssueId(issueId);
        model.addAttribute("entryForm", entryForm);
        return "issue2";
    }

    @PostMapping(value = "/issues/add_entry")
    public String submitTopic(@ModelAttribute(value = "entryForm") EntryForm entryForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "overview2";
        }
        CustomUserDetails userDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userDetails.getUser();
        System.out.println(user.getEmail());
        Author author = this.authorService.getAuthorById(user.getAuthor().getAuthorId());
        Issue issue = this.issueService.getIssueById(entryForm.getIssueId());
        Entry entry = new Entry(entryForm.getBody(), issue, author, new Date());
        this.entryService.saveEntry(entry);
        // return "issue";
        return "redirect:/issues/" + entryForm.getIssueId();
    }

    @PostMapping(value = "/issues/add_issue")
    public String submitTopic(@ModelAttribute(value = "issueForm") IssueForm issueForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "overview2";
        }
        CustomUserDetails userDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userDetails.getUser();
        Author author = this.authorService.getAuthorById(user.getAuthor().getAuthorId());
        Author supportAuthor = this.userService.getSupportUser().getAuthor();

        Issue issue = new Issue(author, supportAuthor, issueForm.getIssueTitle(), new Date());
        this.issueService.saveIssue(issue);

        author.addIssue(issue);
        supportAuthor.addIssue(issue);
        this.authorService.saveAuthor(author);
        this.authorService.saveAuthor(supportAuthor);

        System.out.println(issueForm.getEntryBody());
        Entry entry = new Entry(issueForm.getEntryBody(), issue, author, new Date());
        this.entryService.saveEntry(entry);
        issue.addEntry(entry);
        this.issueService.saveIssue(issue);

        return "redirect:/issues";
    }

}
