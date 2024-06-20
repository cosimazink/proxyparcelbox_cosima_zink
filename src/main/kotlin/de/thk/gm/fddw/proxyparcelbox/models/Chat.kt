package de.thk.gm.fddw.proxyparcelbox.models

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.util.*

@Entity
@Table(name="APP_CHAT")
class Chat ( val trackingNumber : String ) {
    @Id
    var id: String = trackingNumber
    var email: String = ""

    override fun toString(): String {
        return "Trackingnumber: $id Email: $email"
    }
}