package com.example.sekretariat

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.*
import kotlin.collections.ArrayList
import androidx.core.view.children
import com.google.gson.Gson
import java.io.*
import java.lang.Exception
import java.lang.reflect.Field
import java.lang.reflect.Modifier
import java.net.HttpURLConnection
import java.net.URL
import kotlin.reflect.full.memberProperties
import android.R.layout
import android.view.View
import android.widget.ArrayAdapter
import org.w3c.dom.Text


class MainActivity : AppCompatActivity() {

    lateinit var tableView:TableLayout
    lateinit var school:School
    lateinit var schoolView:School
    lateinit var spinner:Spinner
    lateinit var spinnerFilterType :Spinner
    lateinit var spinnerFilterField:Spinner
    lateinit var editTextFilterValue:EditText
    lateinit var checkBoxFilter: CheckBox


    fun createCell(text: String) : TextView{

        val TV = TextView(this)

        TV.setPadding(10,10,10,10)

        TV.text = text

        return TV

    }

    private fun updateView() {
        tableView.removeAllViews()
        when (spinner.selectedItemPosition) {
            0 -> {

                val adapter = ArrayAdapter(this, layout.simple_spinner_item, arrayOf("id","firstName","secondName","lastName","mothersName","maidenName","fathersName","gender","pesel","photo","birthDate","grade","groups"))


                val tempSelected = spinnerFilterField.selectedItemPosition
                spinnerFilterField.adapter = adapter
                spinnerFilterField.setSelection(tempSelected)

                val tableRow = TableRow(this);
                arrayOf("id","firstName","secondName","lastName","mothersName","maidenName","fathersName","gender","pesel","photo","birthDate","grade","groups").forEach { it1->
                    var TV = TextView(this)

                    TV.text = it1

                    TV.setOnClickListener {
                        Log.d("sorted by",it1)
                        val sorted = schoolView.students!!.sortedBy { it[it1] }

                        if(schoolView.students == sorted){
                             schoolView.students = sorted.reversed()
                        }
                        else{
                             schoolView.students = sorted
                        }

                        updateView()
                    }

                    TV.setPadding(10,10,10,10)

                    tableRow.addView(TV)
                }
                tableView.addView(tableRow)

                schoolView.students!!.forEach { it2->
                    val tableRow = TableRow(this);
                    arrayOf("id","firstName","secondName","lastName","mothersName","maidenName","fathersName","gender","pesel","photo","birthDate","grade","groups").forEach { it1 ->
                        var TV = TextView(this)

                        TV.text = it2[it1].toString()
                        TV.setPadding(10,10,10,10)


                        tableRow.addView(TV)
                    }
                    tableView.addView(tableRow)
                }

            }
            1 -> {
                val adapter = ArrayAdapter(this, layout.simple_spinner_item, arrayOf("id","firstName","secondName","lastName","mothersName","maidenName","fathersName","gender","pesel","photo","birthDate","jobPosition","workHours","employmentDate","supervisedClasses","subjects"))



                adapter.setDropDownViewResource(layout.simple_spinner_item)
                val tempSelected = spinnerFilterField.selectedItemPosition
                spinnerFilterField.adapter = adapter
                spinnerFilterField.setSelection(tempSelected)

                val tableRow = TableRow(this);
                arrayOf("id","firstName","secondName","lastName","mothersName","maidenName","fathersName","gender","pesel","photo","birthDate","jobPosition","workHours","employmentDate","supervisedClasses","subjects").forEach { it1->
                    var TV = TextView(this)

                    TV.text = it1

                    TV.setOnClickListener {
                        Log.d("sorted by",it1)
                        val sorted = schoolView.teachers!!.sortedBy { it[it1] }

                        if(schoolView.teachers == sorted){
                            schoolView.teachers = sorted.reversed()
                        }
                        else{
                            schoolView.teachers = sorted
                        }

                        updateView()
                    }

                    TV.setPadding(10,10,10,10)

                    tableRow.addView(TV)
                }
                tableView.addView(tableRow)

                schoolView.teachers!!.forEach { it2->
                    val tableRow = TableRow(this);
                    arrayOf("id","firstName","secondName","lastName","mothersName","maidenName","fathersName","gender","pesel","photo","birthDate","jobPosition","workHours","employmentDate","supervisedClasses","subjects").forEach { it1 ->
                        var TV = TextView(this)

                        TV.text = it2[it1].toString()
                        TV.setPadding(10,10,10,10)


                        tableRow.addView(TV)
                    }
                    tableView.addView(tableRow)
                }

            }
            2 -> {
                val adapter = ArrayAdapter(this, layout.simple_spinner_item, arrayOf("id","firstName","secondName","lastName","mothersName","maidenName","fathersName","gender","pesel","photo","birthDate","jobPosition","workHours","employmentDate"))



                adapter.setDropDownViewResource(layout.simple_spinner_item)
                val tempSelected = spinnerFilterField.selectedItemPosition
                spinnerFilterField.adapter = adapter
                spinnerFilterField.setSelection(tempSelected)

                val tableRow = TableRow(this);
                arrayOf("id","firstName","secondName","lastName","mothersName","maidenName","fathersName","gender","pesel","photo","birthDate","jobPosition","workHours","employmentDate").forEach { it1->
                    var TV = TextView(this)

                    TV.text = it1

                    TV.setOnClickListener {
                        Log.d("sorted by",it1)
                        val sorted = schoolView.employees!!.sortedBy { it[it1] }

                        if(schoolView.employees == sorted){
                            schoolView.employees = sorted.reversed()
                        }
                        else{
                            schoolView.employees = sorted
                        }

                        updateView()
                    }

                    TV.setPadding(10,10,10,10)

                    tableRow.addView(TV)
                }
                tableView.addView(tableRow)

                schoolView.employees!!.forEach { it2->
                    val tableRow = TableRow(this);
                    arrayOf("id","firstName","secondName","lastName","mothersName","maidenName","fathersName","gender","pesel","photo","birthDate","jobPosition","workHours","employmentDate").forEach { it1 ->
                        var TV = TextView(this)

                        TV.text = it2[it1].toString()
                        TV.setPadding(10,10,10,10)


                        tableRow.addView(TV)
                    }
                    tableView.addView(tableRow)
                }

            }
            else -> throw Error("createHeaders update failed")
        }
    }

    private fun compare(t1:String,type:Int,t2:String):Boolean{
        Log.d(t1,t2)
        Log.d("type",type.toString())
        Log.d("outcome",(t1 < t2).toString())
        return when(type){
            0 -> t1 < t2
            1 -> t1 == t2
            2 -> t1 > t2
            3 -> t1 != t2
            4 -> t1 <= t2
            5 -> t1 >= t2

            else -> throw Exception("Compare failed")
        }

    }

    private fun filterView(){
        if(!checkBoxFilter.isChecked) {
            schoolView.students = school.students
            schoolView.teachers = school.teachers
            schoolView.employees = school.employees
            updateView()
            return
        }
        when (spinner.selectedItemPosition) {
            0 -> {
                val arr = arrayOf("id","firstName","secondName","lastName","mothersName","maidenName","fathersName","gender","pesel","photo","birthDate","grade","groups")
                schoolView.students = school.students!!.filter{compare(it.get(arr[spinnerFilterField.selectedItemPosition]).toString(),spinnerFilterType.selectedItemPosition,editTextFilterValue.text.toString() )}
                updateView()
            }
            1 -> {
                val arr = arrayOf("id","firstName","secondName","lastName","mothersName","maidenName","fathersName","gender","pesel","photo","birthDate","jobPosition","workHours","employmentDate","supervisedClasses","subjects")
                schoolView.teachers = school.teachers!!.filter{compare(it.get(arr[spinnerFilterField.selectedItemPosition]).toString(),spinnerFilterType.selectedItemPosition,editTextFilterValue.text.toString() )}
                updateView()
            }
            2 -> {
                val arr = arrayOf("id","firstName","secondName","lastName","mothersName","maidenName","fathersName","gender","pesel","photo","birthDate","jobPosition","workHours","employmentDate")
                schoolView.employees = school.employees!!.filter{compare(it.get(arr[spinnerFilterField.selectedItemPosition]).toString(),spinnerFilterType.selectedItemPosition,editTextFilterValue.text.toString() )}
                updateView()
            }
            else -> throw Error("createHeaders update failed")
        }

    }

    private fun setupView(){

        var adapter = ArrayAdapter(this, layout.simple_spinner_item, arrayOf("Students","Teachers","Employees"))

        spinner.onItemSelectedListener =  object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                updateView()
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
            }
        }

        adapter.setDropDownViewResource(layout.simple_spinner_item)
        spinner.adapter = adapter

        adapter = ArrayAdapter(this, layout.simple_spinner_item, arrayOf("<","==",">","!=","<=",">="))

        spinnerFilterType.onItemSelectedListener =  object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                filterView()
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
            }
        }

        adapter.setDropDownViewResource(layout.simple_spinner_item)

        spinnerFilterType.adapter = adapter

        checkBoxFilter.setOnCheckedChangeListener { buttonView, isChecked -> filterView() }


    }

    private fun loadFromFile(){
        var fileContent ="no file"

        Thread {
            val urls = ArrayList<String>() //to read each line
            try {
                val url = URL("https://pastebin.com/raw/dRSHQ47m")
                val conn: HttpURLConnection = url.openConnection() as HttpURLConnection
                conn.setConnectTimeout(60000) // timing out in a minute
                val `in` = BufferedReader(InputStreamReader(conn.getInputStream()))

                var str: String
                while (`in`.readLine().also { str = it } != null) {
                    urls.add(str)
                }
                `in`.close()
            } catch (e: Exception) {
                Log.d("MyTag", e.toString())
            }

            fileContent = urls[0]


        }.start()
        while(fileContent == "no file")
            Log.d("fileContent",fileContent)
        school = Gson().fromJson(fileContent,School::class.java)
        schoolView = Gson().fromJson(Gson().toJson(school, School::class.java), School::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        tableView           = findViewById(R.id.studentsTableLayout)
        spinnerFilterType   = findViewById(R.id.spinnerFilterType)
        spinnerFilterField  = findViewById(R.id.spinnerFilterFiled)
        editTextFilterValue = findViewById(R.id.editTextFilterValue)
        spinner             = findViewById(R.id.spinner)
        checkBoxFilter      = findViewById(R.id.checkBoxFilter)



        setupView()

        loadFromFile()

        updateView()


    }


}
