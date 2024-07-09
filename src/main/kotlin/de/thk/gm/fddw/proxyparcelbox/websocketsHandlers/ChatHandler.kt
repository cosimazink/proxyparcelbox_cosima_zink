package de.thk.gm.fddw.proxyparcelbox.websocketsHandlers

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.web.socket.CloseStatus
import org.springframework.web.socket.TextMessage
import org.springframework.web.socket.WebSocketSession
import org.springframework.web.socket.handler.TextWebSocketHandler
import de.thk.gm.fddw.proxyparcelbox.models.Message
import de.thk.gm.fddw.proxyparcelbox.services.ChatsService
import de.thk.gm.fddw.proxyparcelbox.services.MessagesService
import org.json.JSONObject
import org.springframework.web.util.UriComponents
import org.springframework.web.util.UriComponentsBuilder
import java.util.*
import kotlin.collections.ArrayList

class ChatHandler(val chatsService: ChatsService, val messagesService: MessagesService) : TextWebSocketHandler() {
    private var sessions : ArrayList<WebSocketSession> = ArrayList()
    override fun afterConnectionEstablished(session: WebSocketSession) {
        super.afterConnectionEstablished(session)
        sessions.add(session)
    }

    override fun handleTextMessage(session: WebSocketSession, message: TextMessage) {
        val chatMessage = Message()
        chatMessage.text = message.payload
        chatMessage.createdAt = Date()
        chatMessage.sender = session.id
        messagesService.save(chatMessage)

        for (chatSession in sessions) {
            if (chatSession.id != session.id) {
                chatSession.sendMessage(message)
            }
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

