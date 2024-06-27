package de.thk.gm.fddw.proxyparcelbox.controllers

import org.springframework.ui.Model
import de.thk.gm.fddw.proxyparcelbox.models.Chat
import de.thk.gm.fddw.proxyparcelbox.services.ChatsService
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.servlet.mvc.support.RedirectAttributes

@Controller
class ChatsController(
    private val chatsRestController: ChatsRestController,
    private val chatsService: ChatsService
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
    fun getChatById(@PathVariable("id") trackingNumber: String, model: Model, redirectAttributes: RedirectAttributes): String {
        /*val existingChat = chatsService.findById(chatRequest.trackingNumber)
        if (existingChat != null) {
            //model.addAttribute("error", "Ein Chat mit dieser Sendungsnummer existiert bereits.")
            return "chats/errorChat"
        }*/
        val chat: Chat? = chatsService.findById(trackingNumber)
        model.addAttribute("chat", chat)
        return "chats/showChat"
    }

    @PostMapping("/chats")
    fun createChat(@ModelAttribute chatRequest: ChatRequest, model: Model) : String {
        /*val existingChat = chatsService.findById(chatRequest.trackingNumber)
        if (existingChat != null) {
            //model.addAttribute("error", "Ein Chat mit dieser Sendungsnummer existiert bereits.")
            return "chats/errorChat"
        }*/

        val chat = Chat(chatRequest.trackingNumber)
        chat.email = chatRequest.email

        chatsRestController.saveChat(chat)

        return "redirect:/chats/${chat.trackingNumber}"
    }

}