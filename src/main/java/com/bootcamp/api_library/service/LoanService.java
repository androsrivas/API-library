package com.bootcamp.api_library.service;

import com.bootcamp.api_library.exceptions.ResourceNotFoundException;
import com.bootcamp.api_library.model.Book;
import com.bootcamp.api_library.model.Member;
import com.bootcamp.api_library.respository.Book.BookRepository;
import com.bootcamp.api_library.respository.MemberRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class LoanService {
    private final MemberRepository memberRepository;
    private final BookRepository bookRepository;

    public LoanService(MemberRepository memberRepository, BookRepository bookRepository) {
        this.memberRepository = memberRepository;
        this.bookRepository = bookRepository;
    }

    @Transactional
    public Member borrowBook(UUID memberId, UUID bookId) {
        Optional<Member> memberOptional = memberRepository.findById(memberId);
        Optional<Book> bookOptional = bookRepository.findById(bookId);

        if(memberOptional.isPresent() && bookOptional.isPresent()) {
            Member member = memberOptional.get();
            Book book = bookOptional.get();

            member.getBorrowedBooks().add(book);
            memberRepository.save(member);
            return member;
        } else {
            throw new ResourceNotFoundException("Member or Book not found.");
        }
    }

    @Transactional
    public Member returnBook(UUID memberId, UUID bookId) {
        Optional<Member> memberOptional = memberRepository.findById(memberId);
        Optional<Book> bookOptional = bookRepository.findById(bookId);

        if(memberOptional.isPresent() && bookOptional.isPresent()) {
            Member member = memberOptional.get();
            Book book = bookOptional.get();

            member.getBorrowedBooks().remove(book);
            memberRepository.save(member);
            return member;
        } else {
            throw new ResourceNotFoundException("Member or Book not found.");
        }
    }
}
