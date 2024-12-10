package com.example.tugascapstonepasa.data.respone

import com.google.gson.annotations.SerializedName

data class PasswordResponse(

	@field:SerializedName("data")
	val data: Data? = null,

	@field:SerializedName("status")
	val status: String? = null
)

data class Analysis(

	@field:SerializedName("isRandom")
	val isRandom: Boolean? = null,

	@field:SerializedName("hasNumbers")
	val hasNumbers: Boolean? = null,

	@field:SerializedName("hasSpecialChars")
	val hasSpecialChars: Boolean? = null,

	@field:SerializedName("criteriaCount")
	val criteriaCount: Int? = null,

	@field:SerializedName("hasMinLength")
	val hasMinLength: Boolean? = null,

	@field:SerializedName("hasLowerCase")
	val hasLowerCase: Boolean? = null,

	@field:SerializedName("hasUpperCase")
	val hasUpperCase: Boolean? = null
)

data class Data(

	@field:SerializedName("strengthLevel")
	val strengthLevel: Int? = null,

	@field:SerializedName("strengthName")
	val strengthName: String? = null,

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("analysis")
	val analysis: Analysis? = null,

	@field:SerializedName("recommendations")
	val recommendations: List<String>? = null
)
