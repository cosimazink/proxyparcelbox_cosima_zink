package de.thk.gm.fddw.proxyparcelbox.controllers

import de.thk.gm.fddw.proxyparcelbox.models.Chat
import de.thk.gm.fddw.proxyparcelbox.services.ChatService
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.ResponseBody

@Controller
class ChatController (private val chatService: ChatService) {

    data class ChatRequest(
        var trackingNumber: String,
        var email: String
    )

    @PostMapping("/chats")
    @ResponseBody
    fun createChat(@RequestBody chatRequest: ChatRequest) : String {
        val chat = Chat(chatRequest.trackingNumber)
        chat.email = chatRequest.email

        chatService.save(chat)

        return "Chat erstellt mit der Trackingnummer ${chat.trackingNumber} und der E-Mail ${chat.email}"
    }

}