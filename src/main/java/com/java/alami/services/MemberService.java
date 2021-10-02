package com.java.alami.services;

import com.java.alami.dao.MemberRepository;
import com.java.alami.dto.MemberDto;
import com.java.alami.entity.Member;
import com.java.alami.exception.MemberRegistrationException;
import com.java.alami.requests.MemberRequest;
import com.java.alami.vo.Pagination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MemberService {
    private final MemberRepository memberRepository;

    @Autowired
    public MemberService(MemberRepository memberRepository){
        this.memberRepository = memberRepository;
    }

    public Pagination<MemberDto> findAll(Pageable pageable, Integer draw){
        Page<Member> membersPage = memberRepository.findAll(pageable);
        return new Pagination<>(draw,
                Math.toIntExact(memberRepository.count()),
                memberRepository.findAll().size(),
                memberRepository.findAll(pageable).map(MemberDto::create).getContent());
    }

    public Member toMembers(MemberRequest memberRequest, Member member){
        member.setName(memberRequest.getName());
        member.setEmail(memberRequest.getEmail());
        member.setDate_of_birth(memberRequest.getDay_of_birth());
        member.setAddress(memberRequest.getAddress());
        return memberRepository.save(member);
    }

    public MemberDto createMember(MemberRequest memberRequest) {
        Member member = memberRepository.findByEmail(memberRequest.getEmail());
        if (member != null){
            throw new MemberRegistrationException("Member with email " + memberRequest.getEmail() + " already exists");
        }
        Member memberCreate = toMembers(memberRequest, new Member());
        return MemberDto.create(memberCreate);
    }

    public Member getMemberById(Long id) {
        Optional<Member> member = memberRepository.findById(id);
        if(member.isEmpty()) {
            throw new RuntimeException("cannot found user with id" + id);
        }

        return member.get();
    }
}
