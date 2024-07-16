package de.thk.gm.fddw.proxyparcelbox.services

import org.springframework.beans.factory.annotation.Value
import org.springframework.mail.SimpleMailMessage
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.stereotype.Service

@Service
class EmailServiceImpl (private var emailSender: JavaMailSender) : EmailService {

    @Value("\${spring.mail.username}")
    private lateinit var sender : String

    override fun sendSimpleMessage(email: String, subject: String, message: String) {
        val mail : SimpleMailMessage = SimpleMailMessage()
        mail.setTo(email)
        mail.subject = subject
        mail.from = sender
        mail.text = message
        emailSender.send(mail)
    }

    override fun sendConfirmationEmail(email: String, trackingnumber: String) {
        val mail : SimpleMailMessage = SimpleMailMessage()
        mail.subject = "Abo-Bestätigung"
        mail.text = "Sie haben den Chat mit der Sendungsnummer ${trackingnumber}. erfolgreich abonniert."
        mail.setTo(email)
        mail.from = sender
        emailSender.send(mail)
    }

    override fun sendAbonnementConfirmation(email: String, trackingnumber: String, message: String) {
        val mail : SimpleMailMessage = SimpleMailMessage()
        mail.subject = "ProxyParcelBox: Eine neue Nachricht im Chat mit der Sendungsnummer ${trackingnumber}."
        mail.text = message
        mail.setTo(email)
        mail.from = sender
        emailSender.send(mail)
    }
}