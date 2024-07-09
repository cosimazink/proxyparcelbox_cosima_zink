package de.thk.gm.fddw.proxyparcelbox.services

import de.thk.gm.fddw.proxyparcelbox.models.Chat
import de.thk.gm.fddw.proxyparcelbox.models.Message
import de.thk.gm.fddw.proxyparcelbox.repositories.ChatsRepository
import de.thk.gm.fddw.proxyparcelbox.repositories.MessagesRepository
import org.springframework.stereotype.Service

@Service
class MessagesServiceImpl (private val messagesRepository: MessagesRepository, private val chatsRepository: ChatsRepository) : MessagesService{

    override fun findByChatRoom(chat: String): List<Message> = messagesRepository.findByChatId(chat)

    override fun save(message: Message): Message = messagesRepository.save(message)

    fun createAndSaveMessage(trackingNumber: String, sender: String, text: String) {

        val chat = chatsRepository.findByTrackingNumber(trackingNumber)
        val message = Message()
        message.chat = chat
        message.sender = sender
        message.text = text

        save(message)
    }
}