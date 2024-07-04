package de.thk.gm.fddw.proxyparcelbox.services

import de.thk.gm.fddw.proxyparcelbox.models.Message
import de.thk.gm.fddw.proxyparcelbox.repositories.MessagesRepository
import org.springframework.stereotype.Service

@Service
class MessagesServiceImpl (private val messagesRepository: MessagesRepository) : MessagesService{
    override fun findByChatRoom(chatId: String): List<Message> = messagesRepository.findByChatId(chatId)

    override fun save(message: Message): Message = messagesRepository.save(message)
}