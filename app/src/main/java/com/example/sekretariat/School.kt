package com.example.sekretariat

import java.util.*

 class School
{


     var students: Array<Student>? = null
     var teachers: Array<Teacher>? = null
     var groups: Array<Group>? = null
     var employees: Array<Employee>? = null

     open class Person
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
    }

    open class Group
    {
         var id :Int? = null
         var name:String? = null
    }

    open class Student : Person()
    {
         var grade :String? = null
         var groups :String? = null
    }

    open class Employee : Person()
    {
         var jobPosition :String? = null
         var workHours :String? = null
         var employmentDate: Date? = null

    }


    open class Teacher : Employee()
    {
         var supervisedClasses :String? = null
         var subjects :String? = null

    }




}