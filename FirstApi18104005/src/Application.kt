package com.first_api18104005

import io.ktor.application.*
import io.ktor.response.*
import io.ktor.request.*
import io.ktor.routing.*
import io.ktor.http.*
import io.ktor.gson.*
import io.ktor.features.*
import com.fasterxml.jackson.databind.*
import io.ktor.jackson.*

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

@Suppress("unused") // Referenced in application.conf
@kotlin.jvm.JvmOverloads
fun Application.module(testing: Boolean = false) {
    install(ContentNegotiation) {
        gson {
        }

        jackson {
            enable(SerializationFeature.INDENT_OUTPUT)
        }
    }

    routing {
        get("/") {
            call.respondText("HELLO SE ITTP!", contentType = ContentType.Text.Plain)
        }

        get("/json/gson") {
            call.respond(mapOf("hello" to "world"))
        }

        get("/json/jackson") {
            call.respond(mapOf("hello" to "world"))
        }

        get("/student") {
            val students = listOf(
                Student(nim = 123456, nama = "Steve Roger", prodi = "Rekayasa Perangkat Lunak"),
                Student(nim = 123457, nama = "TonyStark", prodi = "Rekayasa Perangkat Lunak")
            )
            call.respond(
                mapOf("error" to false, "message" to "success", "data" to students)
            )
        }

        get("/character") {
            val character = listOf(
                Character(0, "Aether", 17, "Anemo", "Mondstrat"),
                Character(1, "Lumine", 17, "Anemo", "Mondstrat"),
                Character(2, "Lisa", 25, "Electro", "Mondstrat"),
                Character(3, "Jean", 25, "Anemo", "Mondstrat"),
                Character(4, "Diluc", 25, "Pyro", "Mondstrat"),
                Character(5, "Kaeya", 25, "Cryo", "Mondstrat"),
                Character(6, "Fischl", 25, "Electro", "Mondstrat"),
                Character(7, "Keqing", 25, "Electro", "Liyue"),
                Character(8, "Ganyu", 25, "Cryo", "Liyue"),
                Character(9, "Eula", 25, "Cryo", "Mondstrat"),
            )
            call.respond(
                mapOf("error" to false, "message" to "success", "data" to character)
            )
        }
    }
}

