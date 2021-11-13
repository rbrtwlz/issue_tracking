package org.rbrtwlz.issuetracking.service;

import org.rbrtwlz.issuetracking.entity.Entry;
import org.rbrtwlz.issuetracking.repository.EntryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EntryService {

    private EntryRepository entryRepository;

    public EntryService(EntryRepository entryRepository){
        this.entryRepository = entryRepository;
    }

    public List<Entry> getEntries(){
        return this.entryRepository.findAll();
    }

    public void saveEntry(Entry entry){
        this.entryRepository.save(entry);
    }
}
