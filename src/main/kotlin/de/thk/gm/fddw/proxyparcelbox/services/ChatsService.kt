package de.thk.gm.fddw.proxyparcelbox.services

import de.thk.gm.fddw.proxyparcelbox.models.Chat
import org.springframework.stereotype.Service

@Service
interface ChatsService {
    fun findById(id: String): Chat?
    fun findAll(): List<Chat>
    fun findByEmail(email: String): Chat?
    fun save(chat: Chat)
    fun delete(chat: Chat)
}