package com.example.demospring.controller

import com.example.demospring.controller.response.Message
import com.example.demospring.service.MessageService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class MessageController(
    val messageService : MessageService
) {
    @GetMapping("/name")
    fun name(@RequestParam("name") name: String) = "Hello, $name!"

//    @GetMapping("/")
//    fun index() = listOf(
//        Message("1", "Hello!"),
//        Message("2", "Bonjour!"),
//        Message("3", "Privet!"),
//    )

    @GetMapping("/")
    fun index(): List<Message> = messageService.findMessages()

    @PostMapping("/")
    fun post(@RequestBody message: Message) {
        messageService.save(message)
    }
    @GetMapping("/{id}")
    fun index(@PathVariable id: String): List<Message> =
        messageService.findMessageById(id)



}

