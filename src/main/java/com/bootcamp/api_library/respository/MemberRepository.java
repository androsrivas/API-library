package com.bootcamp.api_library.respository;

import com.bootcamp.api_library.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface MemberRepository extends JpaRepository <Member, UUID> {
}
