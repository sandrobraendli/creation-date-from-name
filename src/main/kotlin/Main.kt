package io.braendli.creation_date_from_name

import java.io.File
import java.nio.file.Files
import java.nio.file.attribute.BasicFileAttributeView
import java.nio.file.attribute.FileTime
import java.text.SimpleDateFormat

fun main(args: Array<String>) {
    val root = File(if (args.isEmpty()) ".." else args[0])
    val target = File("./processed/")
    val imagePrefix = "WhatsApp Image "
    val format = SimpleDateFormat("yyyy-MM-dd HH.mm.ss")

    root.walk().filter {
        it.name.startsWith(imagePrefix)
    }.forEach {
        it.copyTo(File(target, it.name))
    }

    target.walk().filter {
        !it.name.startsWith(target.name)
    }.forEach {
        val dateString = it.name.substring(15, 37).replace("at ", "")
        val attributes = Files.getFileAttributeView(it.toPath(), BasicFileAttributeView::class.java)

        val time = FileTime.fromMillis(format.parse(dateString).time)
        attributes.setTimes(time, time, time)
    }
}