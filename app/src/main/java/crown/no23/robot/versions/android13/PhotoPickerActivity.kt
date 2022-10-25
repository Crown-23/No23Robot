package crown.no23.robot.versions.android13

import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import crown.no23.robot.R
import crown.no23.robot.databinding.ActivityPhotoPickerBinding

/**
 * Description：Photo picker(照片选择器)
 * 如果照片选择器在设备上不可用，支持库会自动调用 ACTION_OPEN_DOCUMENT intent 操作。搭载 Android 4.4（API 级别 19）或更高版本的设备支持此 intent。
 * 官方文档地址：https://developer.android.google.cn/training/data-storage/shared/photopicker
 */
class PhotoPickerActivity : AppCompatActivity() {
    private val viewBinding by lazy { ActivityPhotoPickerBinding.inflate(layoutInflater) }

    private lateinit var pickMedia: ActivityResultLauncher<PickVisualMediaRequest>
    private lateinit var pickMultipleMedia: ActivityResultLauncher<PickVisualMediaRequest>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(viewBinding.root)

        viewBinding.singleBtn.setOnClickListener { chooseSingleMedia() }
        viewBinding.multiBtn.setOnClickListener { chooseMultipleMedia() }

        // Registers a photo picker activity launcher in single-select mode.
        // LifecycleOwners must call register before they are STARTED.
        pickMedia = registerForActivityResult(ActivityResultContracts.PickVisualMedia()) { uri ->
            // Callback is invoked after the user selects a media item or closes the photo picker.
            if (uri != null) {
                // TODO: show result in TextView
            } else {
                Toast.makeText(this, R.string.cancel_choose, Toast.LENGTH_SHORT).show()
            }
        }

        // Registers a photo picker activity launcher in multi-select mode.
        // In this example, the app allows the user to select up to 5 media files.
        pickMultipleMedia = registerForActivityResult(ActivityResultContracts.PickMultipleVisualMedia(5)) { uris ->
            // Callback is invoked after the user selects media items or closes the photo picker.
            if (uris.isNotEmpty()) {
                // TODO: show result in TextView
            } else {
                Toast.makeText(this, R.string.cancel_choose, Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun chooseSingleMedia() {
        // Include only one of the following calls to launch(), depending on the types
        // of media that you want to allow the user to choose from.

        // Launch the photo picker and allow the user to choose images and videos.
        pickMedia.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageAndVideo))

        /*
        // Launch the photo picker and allow the user to choose only images.
        pickMedia.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))

        // Launch the photo picker and allow the user to choose only videos.
        pickMedia.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.VideoOnly))

        // Launch the photo picker and allow the user to choose only images/videos of a
        // specific MIME type, such as GIFs.
        val mimeType = "image/gif"
        pickMedia.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.SingleMimeType(mimeType)))
        */
    }

    private fun chooseMultipleMedia() {
        // For this example, launch the photo picker and allow the user to choose images
        // and videos. If you want the user to select a specific type of media file,
        // use the overloaded versions of launch(), as shown in the section about how
        // to select a single media item.
        pickMultipleMedia.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageAndVideo))
    }
}