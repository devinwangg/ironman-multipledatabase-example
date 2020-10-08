package com.ironman.multipledatabase.entity.mssql

import javax.persistence.*

@Entity
@Table(name = "userData")
data class User(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "id")
        var id: Int = 0,

        @Column(name = "name")
        var name: String = ""
)