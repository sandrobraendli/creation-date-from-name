package io.braendli.whatsapp_exif_fix

import java.io.File

fun main(args: Array<String>) {
    val root = File(if (args.isEmpty()) ".." else args[0])

    root.walk().filter {
        it.name.startsWith("WhatsApp Image ")
    }.forEach {
        it.copyTo(File("./processed/${it.name}"))
    }
}