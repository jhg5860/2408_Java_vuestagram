package com.example.vuestagram.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.apache.logging.log4j.util.Lazy;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.time.LocalDateTime;

@Getter
@Setter
@RequiredArgsConstructor
@Entity
@EnableJpaAuditing // 업데이트 딜리트 기능
@EntityListeners(AuditingEntityListener.class) // 뷰의 watch 기능 감시
@Table(name = "boards")
@SQLDelete(sql = "UPDATE boards SET updated_at = NOW(), deleted_at = NOW() WHERE board_id = ?")
@Where(clause = "deleted_at is NULL")
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "board_id")
    private Long boardId;

    @ManyToOne // EAGER (OTO MTO = 1 대 1 , 다수 대 1 ), Lazy (OTM , MTM = 1대 다수 , 다수 대 다수) 기본 Default EAGER
    @JoinColumn(name = "user_id")
    private User user; // 연결할 객체를 지정

    @Column(name = "content",nullable = false, length = 200)
    private String content;

    @Column(name = "img",nullable = false, length = 100)
    private String img;

    @Column(name = "likes",nullable = false, length = 11)
    private int likes;

    @CreatedDate
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;
}
