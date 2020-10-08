package com.ironman.multipledatabase.controller

import com.ironman.multipledatabase.repository.mssql.UserRepository
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/users")
class MssqlUserController (
        val userRepository: UserRepository
){
    @GetMapping("/")
    @ResponseBody
    fun getAllUser(): ResponseEntity<Any>{
        return ResponseEntity.ok(userRepository.findAll())
    }
}