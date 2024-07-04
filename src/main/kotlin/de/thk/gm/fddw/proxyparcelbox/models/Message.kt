package de.thk.gm.fddw.proxyparcelbox.models

import jakarta.persistence.*
import org.hibernate.annotations.GenericGenerator
import java.util.*

@Entity
@Table(name="APP_MESSAGE")
class Message () {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    var id: UUID = UUID.randomUUID()
    var createdAt: Date = Date()
    var sender: String = ""
    var text: String = ""

@ManyToOne
@JoinColumn(name = "chat_id", nullable = false)
lateinit var chat: Chat
}
