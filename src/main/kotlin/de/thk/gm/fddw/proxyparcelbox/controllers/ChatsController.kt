package de.thk.gm.fddw.proxyparcelbox.controllers

import de.thk.gm.fddw.proxyparcelbox.models.Chat
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.ResponseBody

@Controller
class ChatsController (private val chatsRestController: ChatsRestController) {

    data class ChatRequest(
        var trackingNumber: String,
        var email: String
    )

    @PostMapping("/chats")
    @ResponseBody
    fun createChat(@ModelAttribute chatRequest: ChatRequest) : String {
        val chat = Chat(chatRequest.trackingNumber)
        chat.email = chatRequest.email

        chatsRestController.saveChat(chat)

        return "Chat erstellt mit der Trackingnummer ${chat.trackingNumber} und der E-Mail ${chat.email}"
    }

}