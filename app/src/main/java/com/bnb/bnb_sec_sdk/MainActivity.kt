package com.bnb.bnb_sec_sdk

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        when (EncryptionLib.encryptContentToFile(this, "MY_ENCRYPTED_FILE_1", "MY_ENCRYPTED_FILE_1")) {
//            EncryptionLib.EncrpytionStatus.ENCRYPT_WRITE_OPERATION_SUCCESS -> Toast.makeText(
//                this,
//                "encryption success",
//                Toast.LENGTH_LONG
//            ).show()
//            EncryptionLib.EncrpytionStatus.ENCRYPT_WRITE_OPERATION_FAILED_FILE_ALREADY_EXIST -> Toast.makeText(
//                this,
//                "Encryption failed. File already exist.",
//                Toast.LENGTH_LONG
//            ).show()
//        }

//        EncryptionLib.readEncryptedFile(this, "MY_ENCRYPTED_FILE_1")
    }
}