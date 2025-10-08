package com.example.dev_app_mobile
//Habilitar Compose en el build.gradle
dependencies {buildFeatures {
    compose truekotlinOptions {
        jvmTarget = "1.8"
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