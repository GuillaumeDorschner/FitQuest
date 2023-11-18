import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.workout.R

class FooterFragment : Fragment() {

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_footer, container, false)

        val homeButton: ImageButton = view.findViewById(R.id.ButtonHome)
        val activityButton: ImageButton = view.findViewById(R.id.ButtonActivity)
        val newWorkoutButton: ImageButton = view.findViewById(R.id.ButtonNewWorkout)
        val favoritesButton: ImageButton = view.findViewById(R.id.ButtonFavoris)
        val profileButton: ImageButton = view.findViewById(R.id.ButtonProfil)

        homeButton.setOnClickListener {
            showToast("Home Button Clicked")
        }

        activityButton.setOnClickListener {
            showToast("Activity Button Clicked")
        }

        newWorkoutButton.setOnClickListener {
            showToast("New Workout Button Clicked")
        }

        favoritesButton.setOnClickListener {
            showToast("Favorites Button Clicked")
        }

        profileButton.setOnClickListener {
            showToast("Profile Button Clicked")
        }

        return view
    }

    private fun showToast(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }
}