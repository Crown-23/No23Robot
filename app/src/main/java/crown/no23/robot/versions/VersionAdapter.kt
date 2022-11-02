package crown.no23.robot.versions

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import crown.no23.robot.databinding.ItemVersionBinding
import crown.no23.robot.jetpack.navigation.VersionFragmentDirections

/**
 * Description：
 */
class VersionAdapter : ListAdapter<Version, VersionAdapter.ViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class ViewHolder private constructor(private val binding: ItemVersionBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Version) {
            binding.versionBtn.text = item.name

            binding.versionBtn.setOnClickListener {
                val action = when (item) {
                    Version.ANDROID13 -> VersionFragmentDirections.actionVersionFragmentToPhotoPickerFragment()
                }
                itemView.findNavController().navigate(action)
            }
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val inflater = LayoutInflater.from(parent.context)
                val binding = ItemVersionBinding.inflate(inflater, parent, false)

                return ViewHolder(binding)
            }
        }
    }
}

class DiffCallback : DiffUtil.ItemCallback<Version>() {
    override fun areItemsTheSame(oldItem: Version, newItem: Version): Boolean {
        return oldItem.name == newItem.name
    }

    override fun areContentsTheSame(oldItem: Version, newItem: Version): Boolean {
        return oldItem == newItem
    }

}