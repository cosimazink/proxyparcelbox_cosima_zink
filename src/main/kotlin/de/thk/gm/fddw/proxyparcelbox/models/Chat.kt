package de.thk.gm.fddw.proxyparcelbox.models

import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name="APP_CHAT")
class Chat ( val trackingnumber : String ) {
    @Id
    var id: String = trackingnumber
    var email: String = ""
    var emailUser: String = ""

    override fun toString(): String {
        return "Trackingnumber: $id Email: $email"
    }
}