package com.avhad

import com.avhad.data.Player
import com.avhad.data.Room
import java.util.concurrent.ConcurrentHashMap

class DrawingServer {

    val rooms = ConcurrentHashMap<String,Room>()
    val players = ConcurrentHashMap<String, Player>()

    fun playerJoined(player: Player){
        players[player.clientId] = player
    }

    fun getRoomWithClientId(clientId:String):Room?{
        val filteredRoom = rooms.filterValues { room ->
            room.players.find { player -> player.clientId == clientId } != null
        }

        return if (filteredRoom.isEmpty()) null else filteredRoom.values.toList()[0]
    }
}