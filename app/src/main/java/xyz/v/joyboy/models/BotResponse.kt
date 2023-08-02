package xyz.v.joyboy.models

data class BotResponse(
    val expects_input: Boolean,
    val is_final: Boolean,
    val response: Response
)