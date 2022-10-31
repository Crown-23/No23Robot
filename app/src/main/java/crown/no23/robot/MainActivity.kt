package crown.no23.robot

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import crown.no23.robot.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val viewBinding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // 若启用ViewBinding，那此处不能使用R文件引用布局，否则Toolbar会不显示label及icon
        setContentView(viewBinding.root)

        // 使用 FragmentContainerView 创建 NavHostFragment，或通过 FragmentTransaction 手动将 NavHostFragment 添加到您的 Activity 时，
        // 尝试通过 Navigation.findNavController(Activity, @IdRes int) 检索 Activity 的 onCreate() 中的 NavController 将失败。
        // 您应改为直接从 NavHostFragment 检索 NavController。
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController

        viewBinding.navView.setupWithNavController(navController)

        appBarConfiguration = AppBarConfiguration(setOf(R.id.blankFragment, R.id.versionFragment), viewBinding.drawerLayout)
        // 使用 Toolbar 时，Navigation 组件会自动处理导航按钮的点击事件，因此您无需替换 onSupportNavigateUp()。
        viewBinding.toolbar.setupWithNavController(navController, appBarConfiguration)
    }
}