package com.contact.service;

import com.contact.entity.Contact;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Service
public class ContactServiceImpl implements ContactService {

    List<Contact> contacts =  List.of(new Contact(1L,"Ravi@abd.com","Ravi",121L),
            new Contact(2L,"Devendra@abd.com","Devendra",121L),
            new Contact(3L,"Madhu@abd.com","Madhu",121L));
    @Override
    public List<Contact> getContact(Long userId) {
        return contacts.stream().filter(contact -> contact.getUserId().equals(userId)).collect(Collectors.toList());
    }
}
