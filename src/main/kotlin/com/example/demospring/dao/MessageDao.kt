package com.example.demospring.dao

import com.example.demospring.controller.response.Message
import org.springframework.data.repository.CrudRepository

interface MessageDao : CrudRepository<Message, String>
