package ua.youdin.a2020giphyrestapi.data.localDB.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "repos")
data class Repo(
    @PrimaryKey @field:SerializedName("id") val id: String,  //This GIF's unique ID	"YsTs5ltWtEhnq"
    var request: String = "",
    @field:SerializedName("title") val title: String="",
    @field:SerializedName("url") val url: String, //The unique URL for this GIF	"http://giphy.com/gifs/confused-flying-YsTs5ltWtEhnq"
)
