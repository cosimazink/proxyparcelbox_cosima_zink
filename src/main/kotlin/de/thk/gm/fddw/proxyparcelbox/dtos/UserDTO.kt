package de.thk.gm.fddw.proxyparcelbox.dtos

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.Max
import jakarta.validation.constraints.Min

class UserDTO {
    @Email
    @Min(5) @Max(50)
    var email: String = ""
    @Min(2) @Max(50)
    var name: String = ""
    @Min(8) @Max(60)
    var password: String = ""
}