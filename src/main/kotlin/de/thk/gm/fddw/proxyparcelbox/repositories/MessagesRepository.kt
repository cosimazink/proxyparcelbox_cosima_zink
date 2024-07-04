package de.thk.gm.fddw.proxyparcelbox.repositories

import de.thk.gm.fddw.proxyparcelbox.models.Message
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface MessagesRepository : CrudRepository<Message, UUID> {
    fun findByChatId(chatId: String): List<Message>
}