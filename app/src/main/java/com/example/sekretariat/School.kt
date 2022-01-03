package com.example.sekretariat

import android.util.Log
import java.util.*
import kotlin.reflect.jvm.internal.impl.load.kotlin.JvmType

class School
{
     var students: List<Student>? = null
     var teachers: List<Teacher>? = null
     var groups: List<Group>? = null
     var employees: List<Employee>? = null



    open class Group
    {
         var id :Int? = null
         var name:String? = null
    }

    open class Student
    {
        var firstName:String? = null
        var id: Int? = null
        var secondName:String? = null
        var lastName :String? = null
        var mothersName :String? = null
        var maidenName :String? = null
        var fathersName :String? = null
        var gender:String? = null
        var pesel:String? = null
        var photo:String? = null
        var birthDate: Date? = null
        var grade :String? = null
        var groups :String? = null
        operator fun get(key: String) : Comparable<Any> {
            for (field in this::class.java.declaredFields) {
                if (field.name == key)
                    return (field.get(this)?:"null") as Comparable<Any>
            }
            throw IllegalArgumentException()
        }
    }

    open class Employee
    {
        var id: Int? = null
        var firstName:String? = null
        var secondName:String? = null
        var lastName :String? = null
        var mothersName :String? = null
        var maidenName :String? = null
        var fathersName :String? = null
        var gender:String? = null
        var pesel:String? = null
        var photo:String? = null
        var birthDate: Date? = null
        var jobPosition :String? = null
        var workHours :String? = null
        var employmentDate: Date? = null
        operator fun get(key: String) : Comparable<Any> {
            for (field in this::class.java.declaredFields) {
                if (field.name == key)
                    return (field.get(this)?:"null") as Comparable<Any>
            }
            throw IllegalArgumentException()
        }

    }


    open class Teacher
    {
        var id: Int? = null
        var firstName:String? = null
        var secondName:String? = null
        var lastName :String? = null
        var mothersName :String? = null
        var maidenName :String? = null
        var fathersName :String? = null
        var gender:String? = null
        var pesel:String? = null
        var photo:String? = null
        var birthDate: Date? = null
        var jobPosition :String? = null
        var workHours :String? = null
        var employmentDate: Date? = null
        var supervisedClasses :String? = null
        var subjects :String? = null
        operator fun get(key: String) : Comparable<Any> {
            for (field in this::class.java.declaredFields) {
                if (field.name == key)
                    return (field.get(this)?:"null") as Comparable<Any>
            }
            throw IllegalArgumentException()
        }

    }




}