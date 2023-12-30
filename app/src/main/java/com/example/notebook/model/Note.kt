package com.example.notebook.model

import android.os.Parcel
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.versionedparcelable.ParcelField
import kotlinx.android.parcel.Parcelize
import java.io.Serializable


@Parcelize
@Entity
data class note(

    @PrimaryKey(autoGenerate = true) val uid: Int,
    @ColumnInfo(name = "title") val title: String?,
    @ColumnInfo(name = "content") var content: String?,
    @ColumnInfo(name = "date") val date: String?
):Parcelable
{ constructor(title: String?, content: String?, date: String?) : this(0,title,content,date)


}
