package de.thk.gm.fddw.proxyparcelbox.services

import de.thk.gm.fddw.proxyparcelbox.models.Chat
import de.thk.gm.fddw.proxyparcelbox.repositories.ChatsRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class ChatServiceImpl (private val chatsRepository: ChatsRepository) : ChatService {
    override fun findById(id: String): Chat? {
        return chatsRepository.findByIdOrNull(id)
    }

    override fun findAll(): List<Chat> {
        return chatsRepository.findAll().toList()
    }

    override fun findByEmail(email: String): Chat? {
        return chatsRepository.findByEmail(email)
    }

    override fun save(chats: Chat) {
        chatsRepository.save(chats)
    }

    override fun delete(chats: Chat) {
        chatsRepository.delete(chats)
    }
}