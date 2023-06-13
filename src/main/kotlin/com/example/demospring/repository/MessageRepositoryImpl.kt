package com.example.demospring.repository

import com.example.demospring.controller.response.Message
import com.example.demospring.dao.MessageDao
import org.springframework.stereotype.Repository
import java.util.*

@Repository
class MessageRepositoryImpl(private var dao: MessageDao): MessageRepository {
    override fun findMessages(): List<Message> = dao.findAll().toList()

    override fun findMessageById(id: String): List<Message> = dao.findById(id).toList()
    override fun save(message: Message){
        dao.save(message)
    }

    fun <T : Any> Optional<out T>.toList(): List<T> =
        if (isPresent) listOf(get()) else emptyList()
}
