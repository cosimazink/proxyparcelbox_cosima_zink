package de.thk.gm.fddw.proxyparcelbox.configs

import de.thk.gm.fddw.proxyparcelbox.websocketsHandlers.ChatHandler
import de.thk.gm.fddw.proxyparcelbox.websocketsHandlers.EchoHandler
import org.springframework.context.annotation.Configuration
import org.springframework.web.socket.config.annotation.EnableWebSocket
import org.springframework.web.socket.config.annotation.WebSocketConfigurer
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry

@Configuration
@EnableWebSocket
class WebSocketConfig () : WebSocketConfigurer {
    override fun registerWebSocketHandlers(registry: WebSocketHandlerRegistry) {
        registry.addHandler(EchoHandler(),"/echo")
        registry.addHandler(ChatHandler(),"/chat")
    }
}