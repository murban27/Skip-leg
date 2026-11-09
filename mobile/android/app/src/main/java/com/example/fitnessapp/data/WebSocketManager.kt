package com.example.fitnessapp.data

import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.WebSocket
import okhttp3.WebSocketListener
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class WebSocketManager @Inject constructor() {
    private val client = OkHttpClient()
    private var webSocket: WebSocket? = null
    private var listener: WebSocketListener? = null

    fun connect(url: String, webSocketListener: WebSocketListener) {
        this.listener = webSocketListener
        val request = Request.Builder().url(url).build()
        webSocket = client.newWebSocket(request, webSocketListener)
    }

    fun disconnect() {
        webSocket?.close(1000, "Disconnecting")
        webSocket = null
    }

    fun sendMessage(message: String) {
        webSocket?.send(message)
    }
}