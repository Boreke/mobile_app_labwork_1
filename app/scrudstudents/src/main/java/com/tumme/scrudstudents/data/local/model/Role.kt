package com.tumme.scrudstudents.data.local.model

enum class Role (private val value:String) {
    Admin("admin"),
    Student("student"),
    Teacher("teacher");

    companion object {
        fun from(value: String) = Role.entries.firstOrNull { it.value == value }
    }

}