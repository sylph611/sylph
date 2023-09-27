package com.sylph.bobmukja.api.domain.entity;

import com.sylph.bobmukja.global.base.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "placeCategory")
public class PlaceCategory extends BaseEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 10)
    @Comment("타입")
    private String type;

    @Column(length = 50)
    @Comment("상위 코드")
    private String upperCode;

    @Column(length = 50)
    @Comment("코드")
    private String code;

    @Column(length = 50)
    @Comment("값")
    private String value;

}
