package com.example.movietwo.domain.mapper

interface Mapper<in T : Any, out R : Any> {
    fun map(dataIn: T): R
}