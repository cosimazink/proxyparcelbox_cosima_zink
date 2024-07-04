package de.thk.gm.fddw.proxyparcelbox.websocketsHandlers

import org.springframework.web.socket.CloseStatus
import org.springframework.web.socket.TextMessage
import org.springframework.web.socket.WebSocketSession
import org.springframework.web.socket.handler.TextWebSocketHandler

class ChatHandler() : TextWebSocketHandler() {
    private var sessions : ArrayList<WebSocketSession> = ArrayList()
    override fun afterConnectionEstablished(session: WebSocketSession) {
        super.afterConnectionEstablished(session)
        sessions.add(session)
    }

    override fun handleTextMessage(session: WebSocketSession, message: TextMessage) {
        for (session in sessions) {
            session.sendMessage(message)
        }
    }

    override fun afterConnectionClosed(session: WebSocketSession, status: CloseStatus) {
        for (chatSession in sessions) {
            if (chatSession.id == session.id) {
                sessions.remove(chatSession)
            }
        }
    }
}