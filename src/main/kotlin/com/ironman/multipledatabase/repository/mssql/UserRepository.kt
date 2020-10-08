package com.ironman.multipledatabase.repository.mssql

/**
 *
 * @author wei-xiang
 * @version 1.0
 * @date 2020/10/8
 */
import com.ironman.multipledatabase.entity.mssql.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository : JpaRepository<User, Int>