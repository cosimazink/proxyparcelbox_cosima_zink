package de.thk.gm.fddw.proxyparcelbox.controllers

import de.thk.gm.fddw.proxyparcelbox.models.User
import de.thk.gm.fddw.proxyparcelbox.services.UsersService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PostMapping

@Controller
class UsersController (
    private val usersService: UsersService
) {

    data class UserRequest(
        var email: String,
        var name: String
    )

    @GetMapping("/login")
    fun login(): String {
        return "users/login"
    }

    @PostMapping("/login")
    fun createUser(@ModelAttribute userRequest : UserRequest, model: Model): String {
        var user: User = User()
        user.email = userRequest.email
        user.name = userRequest.name
        usersService.save(user)
        return "redirect:/"
    }
}