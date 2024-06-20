package de.thk.gm.fddw.proxyparcelbox.controllers

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.ResponseBody

@Controller
class ChatController {

    @PostMapping("/demo/users")
    @ResponseBody
    fun createChat(trackingNumber: String, email: String) : String = trackingNumber + email

}