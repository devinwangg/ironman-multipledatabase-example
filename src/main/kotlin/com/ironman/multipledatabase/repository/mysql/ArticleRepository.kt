package com.ironman.multipledatabase.repository.mysql

/**
 *
 * @author wei-xiang
 * @version 1.0
 * @date 2020/10/8
 */
import com.ironman.multipledatabase.entity.mysql.Article
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ArticleRepository : JpaRepository<Article, Int>