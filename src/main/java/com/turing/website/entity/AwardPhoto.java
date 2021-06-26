package com.turing.website.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author CHEN
 * @date 2020/2/29 17:50
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "award_photo")
@JsonIgnoreProperties(value = {"handler","hibernateLazyInitializer","fieldHandler"})
public class AwardPhoto implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "award_photo_id")
    public Long awardPhotoId;
    @Column(name = "award_photo_loc", nullable = false)
    public String awardPhotoLoc;

    @JoinColumn(name = "award_id", referencedColumnName = "award_id")
    @ManyToOne
    public Award award;

}
