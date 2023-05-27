package com.example.read_textfile_csv

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.read_textfile_csv.databinding.ActivityMainBinding
import java.io.BufferedReader
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.InputStreamReader

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val fileCSV = "tempFile.csv"
    private val dataCSV = "Name, Age, City\n" +
            "John Doe, 30, New York\n" +
            "Jane Smith, 25, London\n" +
            "Alex Johnson, 40, Paris\n" +
            "Emily Brown, 35, Sydney"
    private val fileText = "tempFile2.text"
    private val dataText = "This is, temp file, that create to test that we can open it and use it"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        writeFile(fileCSV, dataCSV)
        writeFile(fileText, dataText)
        binding.buttonCSV.setOnClickListener {
            readFile(fileCSV)
        }
        binding.buttonText.setOnClickListener {
            readFile(fileText)
        }
        setContentView(binding.root)
    }

    private fun writeFile(fileName: String, data: String) {

        val fileOutputStream: FileOutputStream
        try {
            fileOutputStream = openFileOutput(fileName, Context.MODE_PRIVATE)
            fileOutputStream.write(data.toByteArray())
            fileOutputStream.close()
        } catch (e: Exception) {
            Log.d("file error", "Error $e")
        }
    }

    private fun readFile(fileName: String) {
        val fileInputStream: FileInputStream? = openFileInput(fileName)
        val inputStreamReader = InputStreamReader(fileInputStream)
        val bufferedReader = BufferedReader(inputStreamReader)
        val stringBuilder: StringBuilder = StringBuilder()
        var text: String?
        while (run {//read until line is null
                text = bufferedReader.readLine()
                text
            } != null) {
            stringBuilder.append(text)
        }
        binding.Text.text = stringBuilder
    }
}