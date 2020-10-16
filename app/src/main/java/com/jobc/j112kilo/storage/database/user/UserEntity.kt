package com.jobc.j112kilo.storage.database.user

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.jobc.j112kilo.data.model.EntityModel
import com.jobc.j112kilo.storage.database.utils.ConverterBoolean

@Entity(tableName = "user")
data class UserEntity (
    @ColumnInfo(name = "id")
    @PrimaryKey(autoGenerate = false)
    val id: Int = 1,

    @ColumnInfo(name = "role") val userRole: String = "",
    @ColumnInfo(name = "country") val country: String = "",
    @ColumnInfo(name = "path_avatar") val pathAvatar: String = "",
    @ColumnInfo(name = "first_name") val userFirstName: String = "",
    @ColumnInfo(name = "sur_name") val userSurName: String = "",
    @ColumnInfo(name = "gender") val userGender: String = "",
    @ColumnInfo(name = "city") val cityOfResidence: String = "",

    @ColumnInfo(name = "agreement")
    @TypeConverters(ConverterBoolean::class) val documentApprovalAgreement: Boolean = false,

    @ColumnInfo(name = "policy")
    @TypeConverters(ConverterBoolean::class) val documentApprovalPolicy: Boolean = false,

    @ColumnInfo(name = "phone") val userPhone: String = "",

    @ColumnInfo(name = "phone_accept")
    @TypeConverters(ConverterBoolean::class) val acceptUserPhone: Boolean = false,

    @ColumnInfo(name = "email") val userEmail: String = "",

    @ColumnInfo(name = "email_Accept")
    @TypeConverters(ConverterBoolean::class) val acceptUserEmail: Boolean = false,

    @ColumnInfo(name = "verification_contacts")
    @TypeConverters(ConverterBoolean::class) val verificationContacts: Boolean = false,

    @ColumnInfo(name = "verification_person_data")
    @TypeConverters(ConverterBoolean::class) val verificationPersonData: Boolean = false,

    @ColumnInfo(name = "license_issued_by") val licenseIssuedBy: String = "",
    @ColumnInfo(name = "license_date_of_issue") val licenseDataOfIssue: String = "",
    @ColumnInfo(name = "license_series") val licenseSeries: String = "",
    @ColumnInfo(name = "license_number") val licenseNumber: String = "",
    @ColumnInfo(name = "path_photo_license_first") val pathToPhotoFirstLicense: String ="",
    @ColumnInfo(name = "path_photo_license_second") val pathToPhotoSecondLicense: String ="",
    @ColumnInfo(name = "verification_license") val verificationLicense: Boolean = false,
    @ColumnInfo(name = "verification__cars") val verificationCars: Boolean = false
) : EntityModel