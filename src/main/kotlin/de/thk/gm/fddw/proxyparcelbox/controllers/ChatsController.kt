package de.thk.gm.fddw.proxyparcelbox.controllers

import org.springframework.ui.Model
import de.thk.gm.fddw.proxyparcelbox.models.Chat
import de.thk.gm.fddw.proxyparcelbox.models.Message
import de.thk.gm.fddw.proxyparcelbox.services.ChatsService
import de.thk.gm.fddw.proxyparcelbox.services.MessagesService
import de.thk.gm.fddw.proxyparcelbox.services.MessagesServiceImpl
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException
import org.springframework.web.servlet.mvc.support.RedirectAttributes

@Controller
class ChatsController(
    private val chatsRestController: ChatsRestController,
    private val chatsService: ChatsService,
    private val messagesService: MessagesService,
    private val messagesServiceImpl: MessagesServiceImpl
) {

    data class ChatRequest(
        var trackingNumber: String,
        var email: String
    )

    @GetMapping("/")
    fun getIndex(model: Model): String {
        return "index"
    }

    @GetMapping("/chats/{id}")
    fun getChatQR(@PathVariable("id") trackingNumber: String, model: Model, redirectAttributes: RedirectAttributes): String {
        val chat: Chat? = chatsService.findById(trackingNumber)
        model.addAttribute("chat", chat)
        return "chats/showChat"
    }

    @GetMapping("/messages/{trackingnumber}")
    fun getChatByTrackingNumber(@PathVariable("trackingnumber") trackingNumber: String, model: Model): String {
        val chat: Chat? = chatsService.findById(trackingNumber)
        model.addAttribute("chat", chat)

        if(chat == null) throw ResponseStatusException(HttpStatus.NOT_FOUND)
        val messages = messagesService.getMessagesByChatRoom(chat)
        model.addAttribute("messages", messages)
        return "chats/showMessages"
    }

    @GetMapping("/messages/{trackingnumber}/chat")
    @ResponseBody
    fun getChatMessages(@PathVariable("trackingnumber") trackingNumber: String): List<Message> {
        val chat : Chat? = chatsService.findById(trackingNumber)
        return if (chat != null) {
            messagesService.getMessagesByChatRoom(chat)
        } else {
            emptyList()
        }
    }

    @PostMapping("/messages/{trackingnumber}")
    fun saveMessages(@PathVariable("trackingnumber") trackingNumber: String, @RequestBody message: Message): ResponseEntity<Message> {
        val chat = chatsService.findById(trackingNumber)
        return if (chat == null) {
            throw ResponseStatusException(HttpStatus.NOT_FOUND);
        } else {
            message.chat = chat
            messagesServiceImpl.createAndSaveMessage(trackingNumber, message.sender, message.text)
            ResponseEntity.ok().build()
        }
    }

    @PostMapping("/chats")
    fun createChat(@ModelAttribute chatRequest: ChatRequest, model: Model) : String {
        val chat = Chat(chatRequest.trackingNumber)
        chat.email = chatRequest.email

        chatsRestController.saveChat(chat)

        return "redirect:/chats/${chat.id}"
    }

}