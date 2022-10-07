package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {

    MemberService memberService;
    MemoryMemberRepository memberRepository;

    @BeforeEach
    public void beforeEach(){
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }

    @AfterEach
    public void afterEach(){
        memberRepository.clearStore();
    }
    @org.junit.jupiter.api.Test
    void 회원가입() {
        //given 주어지고
        Member member = new Member();
        member.setName("hello");

        //when 실행했을 때 (검증)
        Long saveId = memberService.join(member);

        //then 결과가 나와야 해
        Member findMember = memberService.findOne(saveId).get();
        Assertions.assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @org.junit.jupiter.api.Test
    public void 중복_회원_예외(){
        //given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        //when
        memberService.join(member1);
        assertThrows(IllegalStateException.class, () -> memberService.join(member2));
//        try{
//            memberService.join(member2);
//            fail();
//        } catch (IllegalStateException e){
//            //성공
//            assertThat(e.getMessage()).isEqualTo()
//        }

    }

    @org.junit.jupiter.api.Test
    void findMembers() {
    }

    @org.junit.jupiter.api.Test
    void findOne() {
    }
}