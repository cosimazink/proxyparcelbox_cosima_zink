package de.thk.gm.fddw.proxyparcelbox.controllers

import de.thk.gm.fddw.proxyparcelbox.services.ChatsService
import de.thk.gm.fddw.proxyparcelbox.services.EmailService
import de.thk.gm.fddw.proxyparcelbox.services.ParcelService
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.ResponseBody

@Controller
class EmailsController (val chatsService: ChatsService, val emailService: EmailService, val parcelService: ParcelService) {

    @GetMapping("/emails/sendemailform")
    fun sendEmailForm() : String {
        return "emails/sendemailform"
    }

    @PostMapping("/emails")
    fun sendEmail(receiver: String, subject: String, message: String) : String {
        emailService.sendSimpleMessage(receiver, subject, message)
        return "redirect:/emails/sendemailform"
    }

    @GetMapping("/demo/testparcelservice/{trackingNumber}")
    @ResponseBody
    fun testParcelService(@PathVariable trackingNumber : String) : String {
        return parcelService.getEmailByTrackingNumber(trackingNumber)
    }
}