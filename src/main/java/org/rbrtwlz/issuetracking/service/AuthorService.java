package org.rbrtwlz.issuetracking.service;

import org.rbrtwlz.issuetracking.entity.Author;
import org.rbrtwlz.issuetracking.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorService {
    private AuthorRepository authorRepository;

    @Autowired
    AuthorService(AuthorRepository authorRepository){
        this.authorRepository = authorRepository;
    }

    public List<Author> getAllIssues(){
        return this.authorRepository.findAll();
    }

    public Author getAuthorById(Long authorId){
        Optional<Author> optAuthor= this.authorRepository.findById(authorId);
        if(!optAuthor.isPresent()){
            throw new IllegalStateException("authorId does not exist!");
        }
        return optAuthor.get();
    }

    public void saveAuthor(Author author){
        this.authorRepository.save(author);
    }

}
