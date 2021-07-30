package de.miq.demo

import io.micronaut.http.HttpResponse
import io.micronaut.http.HttpStatus
import io.micronaut.http.MediaType
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.PathVariable
import io.micronaut.http.simple.SimpleHttpResponseFactory
import io.micronaut.scheduling.TaskExecutors
import io.micronaut.scheduling.annotation.ExecuteOn
import javax.inject.Inject

@ExecuteOn(TaskExecutors.IO)
@Controller("/component")
class ComponentController {

    @Inject
    lateinit var componentRepository: ComponentRepository

    @Get("/{uuid}")
    fun icon(@PathVariable uuid: String): HttpResponse<String> {
        println("Request for uuid $uuid")
        val component = componentRepository.componentById(uuid)
        if (!component.isPresent) {
            return HttpResponse.notFound()
        }
        return SimpleHttpResponseFactory().status<String>(HttpStatus.OK, component.get().uuid).contentType(MediaType.TEXT_PLAIN)
    }
}
