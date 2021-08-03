package de.miq.demo

import io.micronaut.data.annotation.MappedEntity
import io.micronaut.data.annotation.MappedProperty
import javax.persistence.Id

@MappedEntity
data class Component(@Id @MappedProperty(value = "foo") var id: String = "")
