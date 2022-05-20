package com.bnb.bnb_sec_sdk

import java.math.BigInteger
import java.security.MessageDigest

class HashLib {
    companion object {
        fun getMD5HashString(input:String): String {
            val md = MessageDigest.getInstance("MD5")
            return BigInteger(1, md.digest(input.toByteArray())).toString(16).padStart(32, '0')
        }

        fun getSHA256HashString(input:String): String {
            val sha256 = MessageDigest.getInstance("SHA-256")
            return BigInteger(1, sha256.digest(input.toByteArray())).toString(16).padStart(32, '0')
        }
    }
}