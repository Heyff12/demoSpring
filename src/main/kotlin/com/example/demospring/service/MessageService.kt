package com.example.demospring.service

import com.example.demospring.controller.response.Message
import com.example.demospring.dao.MessageDao
import de.redsix.pdfcompare.CompareResult
import de.redsix.pdfcompare.CompareResultImpl
import de.redsix.pdfcompare.PdfComparator
import de.redsix.pdfcompare.env.SimpleEnvironment
import org.springframework.stereotype.Service
import java.awt.Color
import java.nio.file.Files
import java.nio.file.Paths
import java.util.*

@Service
class MessageService(
    private val dao: MessageDao
) {

    fun findMessages(): List<Message> = dao.findAll().toList()

    fun findMessageById(id: String): List<Message> = dao.findById(id).toList()
    fun save(message: Message){
        dao.save(message)
    }

    fun getAllFilesInResources(): String
    {
        val resourcePathVal = "/src/main/resources/templates"
        val projectDirAbsolutePath = Paths.get("").toAbsolutePath().toString()
        val resourcesPath = Paths.get(projectDirAbsolutePath, resourcePathVal)
//        val paths = Files.walk(resourcesPath)
////            .filter { item -> Files.isRegularFile(item) }
//            .filter { item -> item.toString().endsWith(".pdf") }
//            .forEach { item -> println("filename: $item") }
        return resourcesPath.toString()
    }
    fun comparePdf(expectedName: String, actualName: String, resultName: String): CompareResult{
        val resourcesPath = getAllFilesInResources()

        val expectedFilePath = "$resourcesPath/$expectedName"
        val actualFilePath = "$resourcesPath/$actualName"
        val resultFilePath = "$resourcesPath/$resultName"


        val result: CompareResult = PdfComparator(expectedFilePath, actualFilePath, CompareResultImpl())
            .withEnvironment(SimpleEnvironment()
                .setDPI(100)
                .setActualColor(Color.green)
                .setExpectedColor(Color.red))
            .compare()
        result.writeTo(resultFilePath)
        if (result.isNotEqual()) {
            System.out.println("Differences found!");
        }
        if (result.isEqual()) {
            System.out.println("No Differences found!");
        }
        if (result.hasDifferenceInExclusion()) {
            System.out.println("Differences in excluded areas found!");
        }
        result.getDifferences(); // returns page areas, where differences were found
        System.out.println(result.getDifferences());
        return result
    }

    fun <T : Any> Optional<out T>.toList(): List<T> =
        if (isPresent) listOf(get()) else emptyList()
}


