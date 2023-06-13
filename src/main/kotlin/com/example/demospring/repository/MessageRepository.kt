package com.example.demospring.repository

import com.example.demospring.controller.response.Message
import org.springframework.stereotype.Repository


interface MessageRepository {
    fun findMessages(): List<Message>

    fun findMessageById(id: String): List<Message>

    fun save(message: Message)
}
