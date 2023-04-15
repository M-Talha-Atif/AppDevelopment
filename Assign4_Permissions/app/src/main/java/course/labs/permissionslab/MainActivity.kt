package course.labs.permissionslab

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
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

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.fab.setOnClickListener {
            onButtonClick()
        }
    }

    private fun onButtonClick() {

        when {
            //TODO: If the permission has already been granted dial the number.
            // [Hint]: Use checkSelfPermission to check if the permission has been granted, and then call the dialPhoneNumber() method.

            //TODO: Remove the variable - PERMISSION, after completing the above block.

            // If the permission has already been granted dial the number.
            // Use checkSelfPermission to check if the permission has been granted,
            // and then call the dialPhoneNumber() method.
            // This code is used to check Self Permission whether permission has been granted
            // It will execute and call diaPhoneNumber() function in case permission is awarded
            // by user. Other wise it will check if user has already denied
            checkSelfPermission(CALL_PERMISSION ) == PackageManager.PERMISSION_GRANTED -> {
                dialPhoneNumber() // Lambda expression
            }

            //TODO: Remove the variable - PERMISSION, after completing the above block.

             // I removed variable permission.

            // According to me, we are using static/companion object permission variable here
            // That's why there's no need to defined PERMISSION variable at upper place before when


            // Check if the user has previously denied permission and
            // therefore a rationale should be provided to help the user
            // understand the importance of granting this permission.
            shouldShowRequestPermissionRationale(CALL_PERMISSION ) -> {
                binding.root.showSnackbar(
                    R.string.need_permission_string,
                    Snackbar.LENGTH_INDEFINITE,
                    android.R.string.ok
                ) {
                    requestPermissionLauncher.launch(CALL_PERMISSION )
                }
            }

            // User hasn't been asked for permission yet.
            else -> {
                // Launch the permission request launcher.
                requestPermissionLauncher.launch(CALL_PERMISSION )
            }
        }
    }

    // Create the permission request launcher.
    // If permission granted, dial the phone number.
    private val requestPermissionLauncher: ActivityResultLauncher<String> =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
            if (isGranted) {
                dialPhoneNumber()
            } else {
                Toast.makeText(
                    this, getString(R.string.need_permission_string),
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

    private fun dialPhoneNumber() {
        // Create an Intent with the appropriate action and data.
        //TODO: Create an Intent with the appropriate action and data.
        // [Hint]:Implicitly start an Activity to call the desired Phone Number.

        // Self explored things
        // Action_Dial ask whether to make call or not and action_call directly calls at number

        val intent = Intent(ACTION_CALL_PHONE)

        // tel:5554 this is used to get uniform resource locator value
        intent.data = Uri.parse(PHONE_NUMBER_URI)


        /* to create a new task
        According to me, I explored that task is actually a holder of activities
        It holds them in stack. In this case, we can say that a new task (stack) will be created
        and we added calling phone activity in it. As there's no other activity than calling phone
        in new task so it will on top as stack contains only 1 activity
        Above comments are explored things that helps me to understand about my syntax written

        */

         /* we can use addFlags, setFlags too to set flag
         setFlags as there is s so we can add other flags too
         addFlags is just adding into list or array or linked list any container u can assume
         Examples:
         intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
         intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
         setFlags is actually setter function that we studied it's convention
         set for setting/specifying and get for output/getting
          */

        // Here we set flags of intent class object to new task
        // we can use

        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(intent)
    }
    // In c++ , we used static keyword to hold static data whose life is till destruction of program
    // In classes if we have static data members, it will behave same of all objects of those classes
    // Similarly. here we defined ACTION_CALL_PHONE whose intent to be perform action of calling a number
    // Here, URI is uniform resources locator
    // Actually, it has 2 parts scheme and location. Like web page https://www.umt.com/
    // here https is scheme that is protocol that defines how to access the location

    // Similarly here tel is scheme and 5554 is location which is phone number
    // tel tells us that it's used to make telephone call

    // As we studied in class about PERMISSIONS , here I defined permission variable to access the
    // manifest file call_phone permission. It's actually uit
    companion object {
        //TODO: Create a companion object that contains and defines
        // the ACTION string for dialing a phone number without user input,
        // the PERMISSION needed for doing this,
        // and PHONE_NUMBER_URI to be used in the Intent (the desired number is 5554).

        // here i've used 5554 as phone number because it was given as desired in file
        // here const mean constant whole value can't be changed
        // private means can't be inherited or to be used only in defined class
        // val means also not to be override and can't be ref
        // difference is const is used in companion objects and it's compile time
        // and val can take value at run time like val num : Int.. It can be initialized later
        private const val ACTION_CALL_PHONE = "android.intent.action.CALL"
        private const val PHONE_NUMBER_URI = "tel:5554"
        private const val CALL_PERMISSION = Manifest.permission.CALL_PHONE
    }
}
