package ucne.edu.Victor_Ferreiras_AP2_P1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import dagger.hilt.android.AndroidEntryPoint
import ucne.edu.Victor_Ferreiras_AP2_P1.navigation.AppNavHost
import ucne.edu.Victor_Ferreiras_AP2_P1.ui.theme.PrimerParcialTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PrimerParcialTheme {
                Surface(
                    modifier = Modifier.fillMaxSize()
                ) {
                    AppNavHost()
                }
            }
        }
    }
}
