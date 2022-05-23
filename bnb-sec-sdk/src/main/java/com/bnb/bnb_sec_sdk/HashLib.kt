package com.bnb.bnb_sec_sdk

import java.math.BigInteger
import java.security.MessageDigest

class HashLib {
    companion object {
        fun getHashString(input: String, hashType: HashType): String {
            val algorithm = when (hashType) {
                HashType.MD5 -> "MD5"
                HashType.SHA1 -> "SHA-1"
                HashType.SHA256 -> "SHA-256"
            }

            return MessageDigest
                .getInstance(algorithm)
                .digest(input.toByteArray())
                .fold("") { str, it -> str + "%02x".format(it) }
        }
    }

    enum class HashType{
        MD5,
        SHA1,
        SHA256
    }
}