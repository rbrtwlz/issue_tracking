package org.rbrtwlz.issuetracking.service;

import org.rbrtwlz.issuetracking.entity.Author;
import org.rbrtwlz.issuetracking.entity.Issue;
import org.rbrtwlz.issuetracking.repository.IssueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class IssueService {
    private IssueRepository issueRepository;

    @Autowired
    IssueService(IssueRepository issueRepository) {
        this.issueRepository = issueRepository;
    }

    public List<Issue> getAllIssues() {
        return this.issueRepository.findAll();
    }

    public void saveIssue(Issue issue) {
        this.issueRepository.save(issue);
    }

    public Issue getIssueById(Long issueId) {
        Optional<Issue> optIssue = this.issueRepository.findById(issueId);
        if (!optIssue.isPresent()) {
            throw new IllegalStateException("issueId does not exist!");
        }
        return optIssue.get();
    }

}
