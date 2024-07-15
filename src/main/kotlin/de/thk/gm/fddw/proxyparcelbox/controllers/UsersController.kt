package de.thk.gm.fddw.proxyparcelbox.controllers

import de.thk.gm.fddw.proxyparcelbox.dtos.UserDTO
import de.thk.gm.fddw.proxyparcelbox.models.User
import de.thk.gm.fddw.proxyparcelbox.services.UsersService
import jakarta.validation.Valid
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import java.util.*

@Controller
class UsersController (
    private val usersService: UsersService
) {

    @GetMapping("/login")
    fun login(): String {
        return "users/login"
    }

    @GetMapping("/register")
    fun register(): String {
        return "users/register"
    }

    @GetMapping("/emailCheck")
    fun emailCheck(): String {
        return "users/emailCheck"
    }

    @PostMapping("/emailCheck")
    fun login(email: String): String {
        val user: User? = usersService.findByEmail(email)
        if(user != null){
            return "redirect:/login"
        } else {
            return "redirect:/register"
        }
    }

    @PostMapping("/register")
    fun createUser(userDTO: UserDTO, model: Model): String {
        var user: User = User()
        user.email = userDTO.email
        user.name = userDTO.name
        user.password = userDTO.password
        user.email = userDTO.email

        usersService.save(user)

        return "redirect:/login"
    }

    @PostMapping("/login")
    fun login(userDTO: UserDTO, model: Model): String {
        TODO()
        return "redirect:/"
    }
}