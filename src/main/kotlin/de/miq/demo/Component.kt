package de.miq.demo

import io.micronaut.data.annotation.MappedEntity
import javax.persistence.Id

@MappedEntity
data class Component(@Id var id: String = "")
