package com.bootcamp.api_library.respository;

import com.bootcamp.api_library.model.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public interface LoanRepository extends JpaRepository<Loan, UUID> {
    List<Loan> findByMember(Member member);
    Optional<Loan> findByMemberAndBookAndReturnDateIsNull(Member member, Book book);
}
