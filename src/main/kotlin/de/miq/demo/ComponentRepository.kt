package de.miq.demo

import io.micronaut.data.jdbc.annotation.JdbcRepository
import io.micronaut.data.jdbc.runtime.JdbcOperations
import io.micronaut.data.model.query.builder.sql.Dialect
import io.micronaut.data.repository.GenericRepository
import java.util.*
import javax.transaction.Transactional

@JdbcRepository(dialect = Dialect.H2)
abstract class ComponentRepository(private val jdbcOperations: JdbcOperations): GenericRepository<Component, String> {

    @Transactional
    fun componentById(uuid: String): Optional<Component> {
        val sql = "select * from component as component where component.uuid = ?"
        return jdbcOperations.prepareStatement(sql) { statement ->
            statement.setString(1, uuid)
            val resultSet = statement.executeQuery()
            jdbcOperations.entityStream(resultSet, Component::class.java).findFirst()
        }
    }
}
