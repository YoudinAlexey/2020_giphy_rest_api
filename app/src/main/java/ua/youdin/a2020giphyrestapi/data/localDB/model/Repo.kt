package ua.youdin.a2020giphyrestapi.data.localDB.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "repos")
data class Repo(
    @PrimaryKey val id: String,  //This GIF's unique ID	"YsTs5ltWtEhnq"
    var request: String = "",
    val title: String = "",
    val urlFixedHeight: String = "",
    val urlFixedHeightSmall: String = "",
    val urlOriginal: String = "",
    val urlFixedWidthSmall: String = "",
    val isDelete:Boolean = false,
) : Parcelable
