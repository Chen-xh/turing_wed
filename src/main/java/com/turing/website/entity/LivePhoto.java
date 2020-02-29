package com.turing.website.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author Jack
 * @date 2019-06-24-18:00
 */
@Entity
@Table(name = "live_photo")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(value = {"handler","hibernateLazyInitializer","fieldHandler"})
public class LivePhoto implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "live_photo_id")
    private Long livePhotoId;
    /**
     * 存储图片的路径, 不存储二进制文件
     */
    @Column(name = "live_photo_loc", nullable = false, length = 255)
    private String livePhotoLoc;

    /**
     * 使用@ManyToOne来映射多对一的关联关系
     * 使用@JoinColumn来映射外键列的列名
     * fetch = LAZY , 懒加载, 表示查询信息时不会直接将关联属性放入其中
     * cascade = REMOVE , 当1的一端删除时, n的一端也会被删除
     */
    @JoinColumn(name = "live_id")
    @ManyToOne
    @JsonIgnoreProperties(value = "livePhotos")
    private Live live;

}
