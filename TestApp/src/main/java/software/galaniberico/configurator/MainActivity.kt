package software.galaniberico.configurator

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import software.galaniberico.configurator.activities.ConfigurationActivity
import software.galaniberico.configurator.facade.Configurator

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        Configurator
            .ensure("text", "Text Custom", "This text is used to ensure the configuration of Strings", "default")
            .ensure("int", "Int Custom", "This int is used to ensure the configuration of Ints", 0)
            .ensure("boolean", "Boolean Custom", "This boolean is used to ensure the configuration of Booleans", false)
            .ensure("float", "Float Custom", "This float is used to ensure the configuration of Floats", 0.0f)
            .ensure("long", "Long Custom", "This long is used to ensure the configuration of Longs", 0L)
            .ensure("multiset", "Set Custom multiList", "This set is used to ensure the configuration of Sets", setOf("Option 1", "Option 2", "Option 3"), setOf("Option 1"))
            .ensure("uniset", "Set Custom", "This set is used to ensure the configuration of Sets", setOf("Option 1", "Option 2", "Option 3"), "Option 1")

        findViewById<Button>(R.id.button).setOnClickListener {
            startActivity(Intent(this, ConfigurationActivity::class.java))
        }
    }
}