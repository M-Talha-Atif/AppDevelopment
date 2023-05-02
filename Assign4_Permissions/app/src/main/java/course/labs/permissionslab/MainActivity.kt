package course.labs.permissionslab


import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.edit
import androidx.databinding.DataBindingUtil
import com.google.android.material.snackbar.Snackbar
import course.labs.common.showSnackbar
import course.labs.permissionslab.databinding.ActivityMainBinding


// My name is Muhammad Talha Atif
// Section V1
// ID F2021266573

// Assignment number 4 . Permissions
class MainActivity : AppCompatActivity() {
 
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        binding = ActivityMainBinding.inflate(layoutInflater)
//        setContentView(binding.root)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)



        binding.fab.setOnClickListener {
            onButtonClick(PERMISSIONS[0],12)
        }
        binding.fab2.setOnClickListener {

            onButtonClick(PERMISSIONS[1],13)
        }
        val prefs = getPreferences(Context.MODE_PRIVATE)
        val isPermissionAsked = prefs.getBoolean(KEY_PERMISSION,false)
        if(!isPermissionAsked){
//            requestCPermissionLauncher.launch(PERMISSIONS[1])
//            requestPermissionLauncher.launch(PERMISSIONS[0])
            requestPermissionsLauncher.launch(PERMISSIONS)

            prefs.edit{
                putBoolean(KEY_PERMISSION,true)
            }
        }

    }
    private val requestPermissionsLauncher: ActivityResultLauncher<Array<out String>> =
        registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) {
        }




    private fun onButtonClick( PERMISSION : String, reqCode : Int) {

    when {

        checkSelfPermission(PERMISSION) == PackageManager.PERMISSION_GRANTED -> {
            if (reqCode == 12)
                perFormAction(12)
            else
                perFormAction(13)
        }


        shouldShowRequestPermissionRationale(PERMISSION) -> {
            val myPermissionString = if (reqCode == 12)
                R.string.need_permission_string
            else R.string.need_c_permission_string

            binding.root.showSnackbar(
                myPermissionString,
                Snackbar.LENGTH_INDEFINITE,
                android.R.string.ok
            ) {
                if (reqCode == 12)
                requestPermissionLauncher.launch(PERMISSION)
                else    requestCPermissionLauncher.launch(PERMISSION)
            }
        }

        // User hasn't been asked for permission yet.
        else -> {
            if(reqCode==12)
            // Launch the permission request launcher.
            requestPermissionLauncher.launch(PERMISSION)
            else
            requestCPermissionLauncher.launch(PERMISSION)

        }
    }
    }


    // Create the permission request launcher.
    // If permission granted, dial the phone number.
    private val requestPermissionLauncher: ActivityResultLauncher<String> =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
            if (isGranted) {
                perFormAction(12)
            } else {
                Toast.makeText(
                    this, getString(R.string.need_permission_string),
                    Toast.LENGTH_SHORT
                ).show()
            }


        }
    private val requestCPermissionLauncher: ActivityResultLauncher<String> =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
            if (isGranted) {
                perFormAction(13)
            } else {
                Toast.makeText(
                    this, getString(R.string.need_c_permission_string),
                    Toast.LENGTH_SHORT
                ).show()
            }
        }


    private fun perFormAction(reqCode: Int) {

        // Action_Dial ask whether to make call or not and action_call directly calls at number
        if(reqCode==12) {
            val intent = Intent(ACTION_CALL_PHONE)
            // tel:5554 this is used to get uniform resource locator value
            intent.data = Uri.parse(PHONE_NUMBER_URI)

           // intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)
        }
        else
            startActivity(Intent(ACTION_CAMERA))

    }


    companion object {


        private const val ACTION_CALL_PHONE = "android.intent.action.CALL"
        private const val ACTION_CAMERA = MediaStore.ACTION_IMAGE_CAPTURE
        private const val PHONE_NUMBER_URI = "tel:5554"
        private val PERMISSIONS = arrayOf("android.permission.CALL_PHONE","android.permission.CAMERA")
        private const val KEY_PERMISSION = "permission_asked"
//        private const val CALL_PERMISSION = Manifest.permission.CALL_PHONE
//        private const val CAMERA_PERMISSION = Manifest.permission.CAMERA

    }
}
