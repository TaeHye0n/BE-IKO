package com.iko.iko.controller.member.dto.response;


import com.iko.iko.domain.entity.Member;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.Column;
import java.sql.Date;

@Getter
public class MemberResponseDto {

 //   private Integer memberId;

  //  private String name;

 //   private String readname;

  //  private Integer postCode;

 //   private String address;

  //  private String phone;

    private String email;

  //  private String birthday;

  //  private Integer point;

 //   private Date createdAt;

  //  private Date updatedAt;

    @Builder
    public MemberResponseDto(Member member) {
     //   this.memberId = member.getMemberId();
     //   this.name = member.getName();
    //    this.readname = member.getReadname();
   //     this.postCode = member.getPostCode();
   //     this.address = member.getAddress();
     //   this.phone = member.getPhone();
      //  this.birthday = member.getBirthday();
      //  this.point = member.getPoint();
        this.email = member.getEmail();
    //    this.createdAt = member.getCreatedAt();
     //   this.updatedAt = member.getUpdatedAt();
    }
}
