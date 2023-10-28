package com.example.gipolanactivity4

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.SharedPreferences
import android.widget.CheckBox
import android.widget.EditText
import android.widget.RadioGroup
import androidx.appcompat.app.AppCompatDelegate
import com.example.gipolanactivity4.databinding.Activity4Binding

class Activity4 : AppCompatActivity() {
    private lateinit var binding: Activity4Binding
    private lateinit var sharedPrefs: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        sharedPrefs = getSharedPreferences("SettingsPrefs", MODE_PRIVATE)
        val isDarkMode = sharedPrefs.getInt("themeSelection", R.id.radioLight) == R.id.radioDark
        val mode = if (isDarkMode) AppCompatDelegate.MODE_NIGHT_YES else AppCompatDelegate.MODE_NIGHT_NO
        AppCompatDelegate.setDefaultNightMode(mode)

        super.onCreate(savedInstanceState)
        binding = Activity4Binding.inflate(layoutInflater)
        setContentView(binding.root)
        title = "Activity 4"

        val themeRadioGroup = binding.themeRadioGroup
        val notificationsCheckbox = binding.allowNotificationsCheckbox
        val emailEditText = binding.emailEditText
        val nicknameEditText = binding.nicknameEditText
        val saveButton = binding.saveButton

        loadSettings(themeRadioGroup, notificationsCheckbox, emailEditText, nicknameEditText)

        saveButton.setOnClickListener {
            saveSettings(themeRadioGroup, notificationsCheckbox, emailEditText, nicknameEditText)
            recreate()
        }
    }

    private fun loadSettings(themeRadioGroup: RadioGroup, notificationsCheckbox: CheckBox, emailEditText: EditText, nicknameEditText: EditText) {
        themeRadioGroup.check(sharedPrefs.getInt("themeSelection", R.id.radioLight))
        notificationsCheckbox.isChecked = sharedPrefs.getBoolean("notificationsEnabled", false)
        emailEditText.setText(sharedPrefs.getString("email", ""))
        nicknameEditText.setText(sharedPrefs.getString("nickname", ""))
    }

    private fun saveSettings(themeRadioGroup: RadioGroup, notificationsCheckbox: CheckBox, emailEditText: EditText, nicknameEditText: EditText) {
        val editor = sharedPrefs.edit()
        editor.putInt("themeSelection", themeRadioGroup.checkedRadioButtonId)
        editor.putBoolean("notificationsEnabled", notificationsCheckbox.isChecked)
        editor.putString("email", emailEditText.text.toString())
        editor.putString("nickname", nicknameEditText.text.toString())
        editor.apply()
    }
}
