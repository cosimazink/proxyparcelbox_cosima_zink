package de.thk.gm.fddw.todolist.controllers

import de.thk.gm.fddw.todolist.models.User
import de.thk.gm.fddw.todolist.dtos.UserDTO
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.PostMapping

@Controller
class UsersController(
    private val usersRestController: UsersRestController
) {
    @PostMapping("/users")
    fun createUser(userDTO: UserDTO): String {
        var user: User = User()
        user.email = userDTO.email
        usersRestController.saveUser(user)

        return "redirect:/"
    }

}
