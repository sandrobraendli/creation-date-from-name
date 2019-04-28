package io.braendli.creation_date_from_name

import java.io.File

fun main(args: Array<String>) {
    val root = File(if (args.isEmpty()) ".." else args[0])
    val target = File("./processed/")
    val imagePrefix = "WhatsApp Image "

    root.walk().filter {
        it.name.startsWith(imagePrefix)
    }.forEach {
        it.copyTo(File(target, it.name))
    }

    target.walk().forEach {

    }
}