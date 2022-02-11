package com.revolhope.ui.features.onboard

import androidx.navigation.*
import com.revolhope.ui.common.base.BaseComposeActivity
import com.revolhope.ui.features.onboard.component.OnBoardComponent
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OnBoardActivity : BaseComposeActivity() {

    override val isNavHostActivity: Boolean = true

    override fun initialRoute(): NavRoute = navRoute(Route.SPLASH)

    override fun NavGraphBuilder.onCreateNavGraph(controller: NavHostController) {
        destinationOf(route = navRoute(Route.SPLASH)) {
            OnBoardComponent(navigate = { route: String -> controller.navigate(route = route) })
        }
        destinationOf(route = navRoute(Route.DASHBOARD)) {

        }
    }

    object Route {
        const val SPLASH = "splash"
        const val DASHBOARD = "dashboard"
    }
}