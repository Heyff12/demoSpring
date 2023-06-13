package com.example.demospring.service

import com.example.demospring.controller.response.Message
import com.example.demospring.dao.MessageDao
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.verify
import org.assertj.core.api.Assertions.assertThat

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(MockKExtension::class)
class MessageServiceTest {

    @InjectMockKs
    private lateinit var messageService: MessageService

    @MockK
    private lateinit var dao: MessageDao


    private val messageId = "6090b8f0-39fa-4e65-a716-334387baafeb"
    private val messageName = "hello!"

    private val messages = listOf(
        Message(messageId, messageName)
    )

    @Test
    fun `should find all the messages`() {
        every { dao.findAll() } returns messages
        val result = messageService.findMessages()
        verify { dao.findAll() }
        assertThat(result[0].id).isEqualTo(messageId)
    }

    @Test
    fun `should return true when compare the same pdf`() {
        val expectedName = "page1.pdf"
        val actualName = "page2.pdf"
        var result = messageService.comparePdf(expectedName,actualName,"result",)
        assertThat(result.isEqual).isTrue()
    }

    @Test
    fun `should return false when compare the same pdf with only 1 page`() {
        val expectedName = "page1.pdf"
        val actualName = "page3.pdf"
        var result = messageService.comparePdf(expectedName,actualName,"result1",)
        assertThat(result.isEqual).isTrue()
    }

    @Test
    fun `should return false when compare the different pdf with multiple pages`() {
        val expectedName = "declaration1.pdf"
        val actualName = "declaration2.pdf"
        var result = messageService.comparePdf(expectedName,actualName,"result4",)
        assertThat(result.isEqual).isFalse()
    }
}

