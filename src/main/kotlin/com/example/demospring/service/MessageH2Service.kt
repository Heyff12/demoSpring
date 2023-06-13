package com.example.demospring.service
import com.example.demospring.controller.response.Message
import org.springframework.stereotype.Service
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.query
import java.util.*

@Service
class MessageH2Service(val db: JdbcTemplate) {
//    fun findMessages(): List<Message> = db.query("select * from messages") { response, _ ->
//        Message(response.getString("id"), response.getString("text"))
//    }

//    fun save(message: Message){
//        db.update("insert into messages values ( ?, ? )",
//            message.id, message.text)
//    }

    fun findMessages(): List<Message> = db.query("select * from messages") { response, _ ->
        Message(response.getString("id"), response.getString("text"))
    }

   fun findMessageById(id: String): List<Message> = db.query("select * from messages where id = ?", id) { response, _ ->
        Message(response.getString("id"), response.getString("text"))
    }

    fun save(message: Message){
        val id = message.id ?: UUID.randomUUID().toString()
        db.update("insert into messages values ( ?, ? )",
            id, message.text)
    }
}

