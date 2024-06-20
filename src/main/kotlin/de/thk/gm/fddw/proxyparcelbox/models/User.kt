package de.thk.gm.fddw.todolist.models

import jakarta.persistence.*
import org.hibernate.annotations.GenericGenerator
import java.util.*


@Entity
@Table(name="APP_USER")
class User {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name="uuid2", strategy = "uuid2")
    var id: UUID = UUID.randomUUID()
    var email: String = ""

    override fun toString(): String {
        return "Id: $id, Email: $email"
    }
}