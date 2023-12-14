package com.wert.demo.repository

import com.wert.demo.User
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.RowMapper
import org.springframework.stereotype.Repository

@Repository
class UserRepository(
    private val template: JdbcTemplate
) {

    fun save(user: User) {
        val sql = "insert into users(id, name) values(?, ?)"
        template.update(sql, user.id, user.name)
    }

    fun findOneById(id: Int): User {
        val sql = "select * from users where id = ?"
        return template.queryForObject(sql, userRowMapper(), id)!!
    }

    fun findAll() = template.query("SELECT * FROM users", userRowMapper())

    private fun userRowMapper() = RowMapper { rs, _ ->
        User(
            rs.getInt("id"),
            rs.getString("name")
        )
    }

    fun delete(id: Int) {
        val sql = "delete from users where id=?"
        template.update(sql, id)
    }
}
