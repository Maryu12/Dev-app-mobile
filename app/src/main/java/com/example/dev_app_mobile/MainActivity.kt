package com.example.dev_app_mobile
//Habilitar Compose en el build.gradle
dependencies {buildFeatures {
    compose truekotlinOptions {
        jvmTarget = "1.8"
    }
    // Modelo: representa los datos que se mostrarán en la pantalla
    data class GreetingModel(val name: String)

} //Gestiona el estado y la lógica de la UI.
import androidx.lifecycle.ViewModel
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValueimport androidx.lifecycle.ViewModel
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValueimport androidx.activity.viewModels

    // Actividad principal: punto de entrada de la app
class MainActivity : ComponentActivity() {
        // Instancia del ViewModel para la pantalla
    private val viewModel: GreetingViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Configura la UI usando Jetpack Compose
        setContent {
            DevappmobileTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    // Llama a la pantalla principal y le pasa el ViewModel
                    GreetingScreen(
                        viewModel = viewModel,
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}
    // Composable: define la UI y consume el estado del ViewModel
@Composable
fun GreetingScreen(viewModel: GreetingViewModel, modifier: Modifier = Modifier) {
    val greeting = viewModel.greeting
    Column(modifier = modifier) {
        Text(text = "Hello ${greeting.name}!")
        Button(onClick = { viewModel.updateName("Jetpack Compose") }) {
            Text("Cambiar nombre")
        }
    }
}
    // ViewModel: gestiona el estado y la lógica de la UI
class GreetingViewModel : ViewModel() {
        // Estado observable para Compose
    var greeting by mutableStateOf(GreetingModel("Android"))
        private set
        // Función para actualizar el nombre
    fun updateName(newName: String) {
        greeting = GreetingModel(newName)
    }
}

class GreetingViewModel : ViewModel() {
    var greeting by mutableStateOf(GreetingModel("Android"))
        private set

    fun updateName(newName: String) {
        greeting = GreetingModel(newName)
    }
}
composeOptions {
    kotlinCompilerExtensionVersion = "1.6.1"
}//Dependencias de Jetpack Compose
    implementation "androidx.activity:activity-compose:1.8.2"
    implementation "androidx.compose.ui:ui:1.6.1"
    implementation "androidx.compose.material:material:1.6.1"
    implementation "androidx.compose.material3:material3:1.2.0"
    implementation "androidx.compose.ui:ui-tooling-preview:1.6.1"
    debugImplementation "androidx.compose.ui:ui-tooling:1.6.1"
}
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DevappmobileTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    DevappmobileTheme {
        Greeting("Android")
    }
}