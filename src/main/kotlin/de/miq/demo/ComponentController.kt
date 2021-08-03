package de.miq.demo

import io.micronaut.http.MediaType
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.PathVariable
import io.micronaut.scheduling.TaskExecutors
import io.micronaut.scheduling.annotation.ExecuteOn
import javax.inject.Inject

@ExecuteOn(TaskExecutors.IO)
@Controller("/component")
internal class ComponentController {

    @Inject
    lateinit var componentRepository: ComponentRepository

    @Get(value = "/{uuid}", produces = [MediaType.TEXT_PLAIN])
    fun icon(uuid: String): String? {
        println("Request for uuid $uuid")

        return componentRepository.componentById(uuid)
            .map { it.id }
            .orElse(null)
    }
}
