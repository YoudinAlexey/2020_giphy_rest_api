package ua.youdin.a2020giphyrestapi.data.giphyAPI.model


//data class GiphiDataModel(
//    var `data`: List<Data>?,
//    var meta: Meta?,
//    var pagination: Pagination
//)
//
//data class Data(
//    var analytics: Analytics?,
//    var analytics_response_payload: String?,
//    var bitly_gif_url: String?,
//    var bitly_url: String?,
//    var content_url: String?,
//    var embed_url: String?,
//    var id: String,  //This GIF's unique ID	"YsTs5ltWtEhnq"
//    var images: Images?, //An object containing data for various available formats and sizes of this GIF.
//    var import_datetime: String?,
//    var is_sticker: Int?,
//    var rating: String?,
//    var slug: String, //The unique slug used in this GIF's URL	"confused-flying-YsTs5ltWtEhnq"
//    var source: String?,
//    var source_post_url: String?,
//    var source_tld: String?,
//    var title: String,
//    var trending_datetime: String?,
//    var type: String?,
//    var url: String, //The unique URL for this GIF	"http://giphy.com/gifs/confused-flying-YsTs5ltWtEhnq"
//    var username: String?
//)
//
//data class Meta(
//    var msg: String?,
//    var response_id: String?,
//    var status: Int?
//)
//
data class Pagination(
    val count: Int?, //Total number of items returned.
    val offset: Int?,  //Position in pagination.
    val total_count: Int?  //Total number of items available (not returned on every endpoint).
)
//
//data class Analytics(
//    var onclick: Onclick?,
//    var onload: Onload?,
//    var onsent: Onsent?
//)
//data class Images(
//    var `480w_still`: WStill?,
//    var downsized: Downsized?,
//    var downsized_large: DownsizedLarge?,
//    var downsized_medium: DownsizedMedium?,
//    var downsized_small: DownsizedSmall?,
//    var downsized_still: DownsizedStill?,
//    var fixed_height: FixedHeight?, //Data on versions of this GIF with a fixed height of 200 pixels. Good for mobile use.	url: string  The publicly-accessible direct URL for this GIF for this size of the GIF.	"https://media1.giphy.com/media/cZ7rmKfFYOvYI/200.gif"
//    var fixed_height_downsampled: FixedHeightDownsampled?,
//    var fixed_height_small: FixedHeightSmall?,
//    var fixed_height_small_still: FixedHeightSmallStill?,
//    var fixed_height_still: FixedHeightStill?,
//    var fixed_width: FixedWidth?,
//    var fixed_width_downsampled: FixedWidthDownsampled?,
//    var fixed_width_small: FixedWidthSmall?,
//    var fixed_width_small_still: FixedWidthSmallStill?,
//    var fixed_width_still: FixedWidthStill?,
//    var looping: Looping?,
//    var original: Original?,
//    var original_mp4: OriginalMp4?,
//    var original_still: OriginalStill?,
//    var preview: Preview?,
//    var preview_gif: PreviewGif?,
//    var preview_webp: PreviewWebp?
//)
//
//data class Onclick(
//    var url: String?
//)
//
//data class Onload(
//    var url: String?
//)
//
//data class Onsent(
//    var url: String?
//)
//
//data class WStill(
//    var height: String?,
//    var size: String?,
//    var url: String?,
//    var width: String?
//)
//
//data class Downsized(
//    var height: String?,
//    var size: String?,
//    var url: String?,
//    var width: String?
//)
//
//data class DownsizedLarge(
//    var height: String?,
//    var size: String?,
//    var url: String?,
//    var width: String?
//)
//
//data class DownsizedMedium(
//    var height: String?,
//    var size: String?,
//    var url: String?,
//    var width: String?
//)
//
//data class DownsizedSmall(
//    var height: String?,
//    var mp4: String?,
//    var mp4_size: String?,
//    var width: String?
//)
//
//data class DownsizedStill(
//    var height: String?,
//    var size: String?,
//    var url: String?,
//    var width: String?
//)
//
//data class FixedHeight(
//    var height: String?,
//    var mp4: String?,
//    var mp4_size: String?,
//    var size: String?,
//    var url: String?,
//    var webp: String?,
//    var webp_size: String?,
//    var width: String?
//)
//
//data class FixedHeightDownsampled(
//    var height: String?,
//    var size: String?,
//    var url: String?,
//    var webp: String?,
//    var webp_size: String?,
//    var width: String?
//)
//
//data class FixedHeightSmall(
//    var height: String?,
//    var mp4: String?,
//    var mp4_size: String?,
//    var size: String?,
//    var url: String?,
//    var webp: String?,
//    var webp_size: String?,
//    var width: String?
//)
//
//data class FixedHeightSmallStill(
//    var height: String?,
//    var size: String?,
//    var url: String?,
//    var width: String?
//)
//
//data class FixedHeightStill(
//    var height: String?,
//    var size: String?,
//    var url: String?,
//    var width: String?
//)
//
//data class FixedWidth(
//    var height: String?,
//    var mp4: String?,
//    var mp4_size: String?,
//    var size: String?,
//    var url: String?,
//    var webp: String?,
//    var webp_size: String?,
//    var width: String?
//)
//
//data class FixedWidthDownsampled(
//    var height: String?,
//    var size: String?,
//    var url: String?,
//    var webp: String?,
//    var webp_size: String?,
//    var width: String?
//)
//
//data class FixedWidthSmall(
//    var height: String?,
//    var mp4: String?,
//    var mp4_size: String?,
//    var size: String?,
//    var url: String?,
//    var webp: String?,
//    var webp_size: String?,
//    var width: String?
//)
//
//data class FixedWidthSmallStill(
//    var height: String?,
//    var size: String?,
//    var url: String?,
//    var width: String?
//)
//
//data class FixedWidthStill(
//    var height: String?,
//    var size: String?,
//    var url: String?,
//    var width: String?
//)
//
//data class Looping(
//    var mp4: String?,
//    var mp4_size: String?
//)
//
//data class Original(
//    var frames: String?,
//    var hash: String?,
//    var height: String?,
//    var mp4: String?,
//    var mp4_size: String?,
//    var size: String?,
//    var url: String?,
//    var webp: String?,
//    var webp_size: String?,
//    var width: String?
//)
//
//data class OriginalMp4(
//    var height: String?,
//    var mp4: String?,
//    var mp4_size: String?,
//    var width: String?
//)
//
//data class OriginalStill(
//    var height: String?,
//    var size: String?,
//    var url: String?,
//    var width: String?
//)
//
//data class Preview(
//    var height: String?,
//    var mp4: String?,
//    var mp4_size: String?,
//    var width: String?
//)
//
//data class PreviewGif(
//    var height: String?,
//    var size: String?,
//    var url: String?,
//    var width: String?
//)
//
//data class PreviewWebp(
//    var height: String?,
//    var size: String?,
//    var url: String?,
//    var width: String?
//)