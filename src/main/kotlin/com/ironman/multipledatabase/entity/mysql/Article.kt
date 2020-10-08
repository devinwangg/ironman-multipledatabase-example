package com.ironman.multipledatabase.entity.mysql

/**
 *
 * @author wei-xiang
 * @version 1.0
 * @date 2020/10/8
 */
import javax.persistence.*

@Entity
@Table(name = "article")
data class Article(

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "id")
        var id: Int = 0,

        @Column(name = "title")
        var title: String = "",

        @Column(name = "author")
        var author: String = ""
)