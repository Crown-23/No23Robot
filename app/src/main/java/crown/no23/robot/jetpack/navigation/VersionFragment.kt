package crown.no23.robot.jetpack.navigation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import crown.no23.robot.databinding.FragmentVersionBinding
import crown.no23.robot.versions.Version
import crown.no23.robot.versions.VersionAdapter

class VersionFragment : Fragment() {
    private lateinit var viewBinding: FragmentVersionBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewBinding = FragmentVersionBinding.inflate(inflater, container, false)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = VersionAdapter()
        viewBinding.versionRv.adapter = adapter
        adapter.submitList(Version.values().toList())
    }

}