package com.avhad

import io.ktor.server.engine.*
import io.ktor.server.netty.*
import com.avhad.plugins.*
import com.avhad.session.DrawingSession
import io.ktor.application.*
import io.ktor.sessions.*
import io.ktor.util.*

fun main() {
    embeddedServer(Netty, port = 8080, host = "0.0.0.0") {
        configureSessionManagement()
        configureRouting()
        configureSerialization()
        configureSockets()
        configureMonitoring()
    }.start(wait = true)
}

private fun Application.configureSessionManagement() {
    install(Sessions) {
        cookie<DrawingSession>("SESSION")
    }
    intercept(ApplicationCallPipeline.Features) {
        if (call.sessions.get<DrawingSession>() == null) {

            val clientId = call.parameters["client_id"] ?: ""
            call.sessions.set(DrawingSession(clientId, generateNonce()))
        }
    }
}
