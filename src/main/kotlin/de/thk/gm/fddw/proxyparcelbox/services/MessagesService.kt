package de.thk.gm.fddw.proxyparcelbox.services

import de.thk.gm.fddw.proxyparcelbox.models.Message
import org.springframework.stereotype.Service
import java.util.*

@Service
interface MessagesService {
    fun findByChatRoom(chatId: String): List<Message>
    fun save(message: Message): Message
}