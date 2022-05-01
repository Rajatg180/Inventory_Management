package com.example.authenticationdemo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        val btnRegister =findViewById<Button>(R.id.register_btn)
        val registerEmail=findViewById<EditText>(R.id.et_register_email)
        val registerPassword=findViewById<EditText>(R.id.et_register_password)
        btnRegister.setOnClickListener {
            //to check editText is empty or not
            when{
                TextUtils.isEmpty(registerEmail.text.toString().trim(){it <=' '})->{
                    Toast.makeText(this@RegisterActivity,"Please Enter Email.",Toast.LENGTH_SHORT).show()

                }
                TextUtils.isEmpty(registerPassword.text.toString().trim(){it <=' '})->{
                    Toast.makeText(this@RegisterActivity,"Please Enter Password.",Toast.LENGTH_SHORT).show()
                }
                else->{
                    //trim is used to remove white spaces from both the ends of sting
                    val email:String=registerEmail.text.toString().trim{ it <= ' '}
                    val password:String=registerPassword.text.toString().trim{ it <= ' '}

                    //create an instance and create a register a user with email and password
                    FirebaseAuth.getInstance().createUserWithEmailAndPassword(email,password)
                        .addOnCompleteListener(
                            OnCompleteListener<AuthResult>{ task ->
                                //If the registration is successfully done
                                if(task.isSuccessful){
                                    //Firebase register user
                                    val firebaseUser : FirebaseUser = task.result!!.user!!
                                    Toast.makeText(this@RegisterActivity,"You are registered successfully.",Toast.LENGTH_SHORT).show()
                                    /**
                                     * here the new user register is automatically signed in so we just sign out
                                     * and send him to Main screen with user id and email that user have used for register
                                     *
                                     */
                                    val intent =Intent(this@RegisterActivity,MainActivity::class.java)
                                    //when we press back button it will not come back to previous activity
                                    intent.flags=Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                                    intent.putExtra("user_id",firebaseUser.uid)
                                    intent.putExtra("email_id",email)
                                    startActivity(intent)
                                    finish()

                                }else{
                                    // if registering is not successful then show error message
                                    Toast.makeText(this@RegisterActivity,task.exception!!.message.toString(),Toast.LENGTH_SHORT).show()


                                }

                            }
                        )
                }


            }

        }
    }
}