package com.jh.shopapi.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Value;

import javax.persistence.*;

@Table(name="member")
@NoArgsConstructor
@Setter
@Getter
public class Member {
    @Id
    @Column(name="member_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY) //userId는 자동으로 생성
    private Long memberId;

    @Column(length = 255)
    private String email;

    @Column(length = 50)
    private String name;
    
}
