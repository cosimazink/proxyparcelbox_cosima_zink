package de.thk.gm.fddw.proxyparcelbox.services

import de.thk.gm.fddw.proxyparcelbox.models.Message
import org.springframework.stereotype.Service
import java.util.*

@Service
interface MessagesService {
    fun findByChatRoom(chat: String): List<Message>
    fun save(message: Message): Message
    fun getMessagesByChatRoom(chat: String): List<Message>
}