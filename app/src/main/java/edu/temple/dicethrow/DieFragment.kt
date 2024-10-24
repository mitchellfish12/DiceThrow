package edu.temple.dicethrow

import android.os.Bundle
import android.provider.Settings.Global.putInt
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import kotlin.random.Random

class DieFragment : Fragment() {

    val dieViewModel: DieViewModule by lazy {
        ViewModelProvider(requireActivity())[DieViewModule::class.java]
    }
    private val DIESIDE = "sidenumber"
    private val key = "mykey"
    var dieSides: Int = 6
    private var rollValue = 0
    lateinit var dieTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            it.getInt(DIESIDE).run {
                dieSides = this
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_die, container, false).apply {
            dieTextView = findViewById(R.id.dieTextView)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        dieViewModel.getDieRoll().observe(viewLifecycleOwner) {
            dieTextView.text = it.toString()
        }
        if (dieViewModel.getDieRoll().value == null) {
            throwDie()
        } else {
            dieTextView.text = rollValue.toString()
        }
    }

    fun throwDie() {
        dieTextView.text = ((Random.nextInt(dieSides)+1).toString())
    }

    companion object {
        fun newInstance(sides: Int) = DieFragment().apply {
            arguments = Bundle().apply { putInt(DIESIDE, sides) }
        }
    }
}

















