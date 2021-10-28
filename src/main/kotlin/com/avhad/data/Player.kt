package com.avhad.data

import io.ktor.http.cio.websocket.*

data class Player(
    val clientId: String,
    var socket: WebSocketSession,
    val userName: String,
    val isDrawing: Boolean,
    var score:Int = 0,
    var rank:Int = 0,
)
