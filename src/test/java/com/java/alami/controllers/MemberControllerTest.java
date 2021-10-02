package com.java.alami.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.java.alami.dto.MemberDto;
import com.java.alami.entity.Member;
import com.java.alami.requests.MemberRequest;
import com.java.alami.services.MemberService;
import com.java.alami.vo.Pagination;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.zalando.problem.jackson.ProblemModule;
import org.zalando.problem.violations.ConstraintViolationProblemModule;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.CoreMatchers.is;

@WebMvcTest(controllers = MemberController.class)
@ActiveProfiles("test")
public class MemberControllerTest {

    @Autowired
    private MockMvc mockMvc;


    @MockBean
    private MemberService memberService;

    @Autowired
    private ObjectMapper objectMapper;

    private List<Member> memberDtoList;
    private org.springframework.data.domain.Pageable Pageable;

    @BeforeEach
    void setUp() {
        Date date = new Date();
        this.memberDtoList = new ArrayList<Member>();
        this.memberDtoList.add(new Member(1L,"Fajar Muzaky", "fajarmuzaky@metra.co.id", date, "Purwokerto" ));
        this.memberDtoList.add(new Member(2L,"Malvin Yosef", "malvin@metra.co.id", date, "Cilacap" ));
        this.memberDtoList.add(new Member(3L,"Samsul Hilal", "hilal@metra.co.id", date, "Banyumas" ));

        objectMapper.registerModule(new ProblemModule());
        objectMapper.registerModule(new ConstraintViolationProblemModule());
    }

    @Test
    public void shouldFetchAllMembers() throws Exception {
        Integer draw = null;
        Mockito.when(memberService.findAll(Pageable, draw)).thenReturn((Pagination<MemberDto>) memberDtoList);
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/members")
                        .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(status().isOk())
                        .andExpect(jsonPath("$.size()",is(memberDtoList.size())));
    }

    @Test
    public void shouldCreateNewMember() throws Exception {
//        Date date = new Date();
//        Member member = new Member(null, "fajar test", "joko.widodo@alami.id", date,"purwokerto");
//        MemberRequest memberRequest = new MemberRequest();
//        memberRequest.setName(member.getName());
//        memberRequest.setEmail(member.getEmail());
//        memberRequest.setDay_of_birth(member.getDate_of_birth());
//        memberRequest.setAddress(member.getAddress());
//
//        Mockito.when(memberService.createMember(memberRequest)).thenReturn(MemberDto.create(member));
//        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/api/members")
//                .contentType(MediaType.APPLICATION_JSON)
//                .accept(MediaType.APPLICATION_JSON)
//                .content(objectMapper.writeValueAsString(member));
//        mockMvc.perform(mockRequest)
//                .andExpect(status().isCreated())
//                .andExpect(jsonPath("$.email", is(member.getEmail())))
//                .andExpect(jsonPath("$.name", is(member.getName())))
//                .andExpect(jsonPath("$.date_of_birth", is(member.getDate_of_birth())))
//                .andExpect(jsonPath("$.address", is(member.getAddress())));
//
    }

}
