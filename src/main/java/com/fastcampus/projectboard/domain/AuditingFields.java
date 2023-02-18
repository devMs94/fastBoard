package com.fastcampus.projectboard.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Setter
@Getter
// 엔티티를 DB에 적용하기전 , 커스텀 콜백을 요청할수 있는 어노테이션
// auditing을 수행할때는 `AuditingEntityListener.class` 클래스를 인자로 넘긴다.
@EntityListeners(AuditingEntityListener.class)
// 엔티티의 공통 매핑 정보가 필요할 떄 주로 사용된다.
//즉 부모 클래스에 필드를 선언하고 단순히 속성만 받아서 사용
@MappedSuperclass
public class AuditingFields {
    // metadata
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @CreatedDate // 엔티티가 작성된 날짜, created된 날짜를 사용할수 있다.
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt; // 생성일시

    @CreatedBy
    @Column(nullable = false, length = 100)
    private String createdBy;   // 생성자

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @LastModifiedDate
    @Column(nullable = false)
    private LocalDateTime modifiedAt; // 수정일시

    @LastModifiedBy
    @Column(nullable = false, length = 100)
    private String modifiedBy; // 수정자

}
