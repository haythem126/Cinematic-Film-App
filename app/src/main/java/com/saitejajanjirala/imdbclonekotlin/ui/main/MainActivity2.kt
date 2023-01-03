package com.saitejajanjirala.imdbclonekotlin.ui.main

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Patterns
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.youtube.LoginResult
import com.example.youtube.RetrofitInterface
import com.saitejajanjirala.imdbclonekotlin.R
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MainActivity2 : AppCompatActivity() {
    private var retrofit: Retrofit? = null
    private var retrofitInterface: RetrofitInterface? = null
    private val BASE_URL = "http://10.0.2.2:3000"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        retrofitInterface = retrofit!!.create(RetrofitInterface::class.java)
        findViewById<View>(R.id.login).setOnClickListener { handleLoginDialog() }
        findViewById<View>(R.id.signup).setOnClickListener { handleSignupDialog() }
    }

    private fun handleLoginDialog() {
        val view = layoutInflater.inflate(R.layout.login_dialog, null)
        val builder = AlertDialog.Builder(this)
        builder.setView(view).show()
        val loginBtn = view.findViewById<Button>(R.id.login)
        val emailEdit = view.findViewById<EditText>(R.id.emailEdit)
        val passwordEdit = view.findViewById<EditText>(R.id.passwordEdit)
        loginBtn.setOnClickListener {
            if (emailEdit.text.isEmpty()){
                Toast.makeText(
                    this@MainActivity2, "Email required!",
                    Toast.LENGTH_LONG
                ).show()
                return@setOnClickListener
            }
            if (passwordEdit.text.isEmpty()){
                Toast.makeText(
                    this@MainActivity2, "Password required!",
                    Toast.LENGTH_LONG
                ).show()
                return@setOnClickListener
            }
            if (!isValidEmail(emailEdit.text)){
                Toast.makeText(
                    this@MainActivity2, "Email invalide!",
                    Toast.LENGTH_LONG
                ).show()
                return@setOnClickListener
            }

            val map = HashMap<String?, String?>()
            map["email"] = emailEdit.text.toString()
            map["password"] = passwordEdit.text.toString()

            val call = retrofitInterface!!.executeLogin(map)
            call?.enqueue(object : Callback<LoginResult?> {
                override fun onResponse(
                    call: Call<LoginResult?>,
                    response: Response<LoginResult?>
                ) {
                    if (response.code() == 200) {

                        Toast.makeText(
                            this@MainActivity2, "Welcome To Cinematic Films",
                            Toast.LENGTH_LONG
                        ).show()
                        val i = Intent(this@MainActivity2 , HomeActivity::class.java)
                        startActivity(i)



                    } else if (response.code() == 404) {
                        Toast.makeText(
                            this@MainActivity2, "Wrong Credentials",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }

                override fun onFailure(call: Call<LoginResult?>, t: Throwable) {
                    Toast.makeText(
                        this@MainActivity2, t.message,
                        Toast.LENGTH_LONG
                    ).show()
                }
            })




        }
    }

    private fun handleSignupDialog() {
        val view = layoutInflater.inflate(R.layout.signup_dialog, null)
        val builder = AlertDialog.Builder(this)
        builder.setView(view).show()
        val signupBtn = view.findViewById<Button>(R.id.signup)
        val nameEdit = view.findViewById<EditText>(R.id.nameEdit)
        val emailEdit = view.findViewById<EditText>(R.id.emailEdit)
        val passwordEdit = view.findViewById<EditText>(R.id.passwordEdit)
        signupBtn.setOnClickListener {
            if (nameEdit.text.isEmpty()){
                Toast.makeText(
                    this@MainActivity2, "name required!",
                    Toast.LENGTH_LONG
                ).show()
                return@setOnClickListener
            }
            if (emailEdit.text.isEmpty()){
                Toast.makeText(
                    this@MainActivity2, "Email required!",
                    Toast.LENGTH_LONG
                ).show()
                return@setOnClickListener
            }
            if (passwordEdit.text.isEmpty()){
                Toast.makeText(
                    this@MainActivity2, "Password required!",
                    Toast.LENGTH_LONG
                ).show()
                return@setOnClickListener
            }
            if (!isValidEmail(emailEdit.text)){
                Toast.makeText(
                    this@MainActivity2, "Email invalide!",
                    Toast.LENGTH_LONG
                ).show()
                return@setOnClickListener
            }

            val map = HashMap<String?, String?>()
            map["name"] = nameEdit.text.toString()
            map["email"] = emailEdit.text.toString()
            map["password"] = passwordEdit.text.toString()
            val call = retrofitInterface!!.executeSignup(map)
            call?.enqueue(object : Callback<Void?> {
                override fun onResponse(call: Call<Void?>, response: Response<Void?>) {
                    if (response.code() == 200) {
                        Toast.makeText(
                            this@MainActivity2,
                            "Signed up successfully", Toast.LENGTH_LONG
                        ).show()
                    } else if (response.code() == 400) {
                        Toast.makeText(
                            this@MainActivity2,
                            "Already registered", Toast.LENGTH_LONG
                        ).show()
                    }
                }

                override fun onFailure(call: Call<Void?>, t: Throwable) {
                    Toast.makeText(
                        this@MainActivity2, t.message,
                        Toast.LENGTH_LONG
                    ).show()
                }
            })
        }
    }
    fun isValidEmail(target: CharSequence?): Boolean {
        return !TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches()
    }


}