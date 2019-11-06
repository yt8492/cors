package com.yt8492

import io.ktor.application.*
import io.ktor.features.CORS
import io.ktor.features.CallLogging
import io.ktor.features.origin
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpMethod
import io.ktor.http.HttpStatusCode
import io.ktor.response.*
import io.ktor.routing.*
import io.ktor.util.toMap

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

@Suppress("unused") // Referenced in application.conf
@kotlin.jvm.JvmOverloads
fun Application.module(testing: Boolean = false) {
    install(CallLogging)

    install(CORS) {
        anyHost()
        allowSameOrigin = false
        method(HttpMethod.Put)
    }

    routing {
        get("/") {
            checkOrigin(call)
            val res = call.request
                .headers
                .toMap()
                .entries
                .joinToString(",\n") {
                    "${it.key}: ${it.value.joinToString()}"
                }
            call.respond(HttpStatusCode.OK, res)
        }

        post("/" ) {
            checkOrigin(call)
            val res = call.request
                .headers
                .toMap()
                .entries
                .joinToString(",\n") {
                    "${it.key}: ${it.value.joinToString()}"
                }
            call.respond(HttpStatusCode.OK, res)
        }

        put("/") {
            checkOrigin(call)
            val res = call.request
                .headers
                .toMap()
                .entries
                .joinToString(",\n") {
                    "${it.key}: ${it.value.joinToString()}"
                }
            call.respond(HttpStatusCode.OK, res)
        }

        patch("/") {
            checkOrigin(call)
            val res = call.request
                .headers
                .toMap()
                .entries
                .joinToString(",\n") {
                    "${it.key}: ${it.value.joinToString()}"
                }
            call.respond(HttpStatusCode.OK, res)
        }

        delete("/") {
            checkOrigin(call)
            val res = call.request
                .headers
                .toMap()
                .entries
                .joinToString(",\n") {
                    "${it.key}: ${it.value.joinToString()}"
                }
            call.respond(HttpStatusCode.OK, res)
        }
    }
}

private fun Application.checkOrigin(call: ApplicationCall) {
    val origin = call.request.headers.getAll(HttpHeaders.Origin)?.singleOrNull()
    val requestOrigin = "${call.request.origin.scheme}://${call.request.origin.host}:${call.request.origin.port}"
    log.debug("origin: $origin, requestOrigin: $requestOrigin")
}
