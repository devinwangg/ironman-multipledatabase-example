package com.ironman.multipledatabase.controller

import com.ironman.multipledatabase.repository.mysql.ArticleRepository
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/articles")
class MysqlArticleController (
        val storeRepository: ArticleRepository
){
    @GetMapping("/")
    @ResponseBody
    fun getAllStore(): ResponseEntity<Any>{
        return ResponseEntity.ok(storeRepository.findAll())
    }
}