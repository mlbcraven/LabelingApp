package com.example.labelingapp


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.room.Room
import com.example.labelingapp.databinding.EntryFragmentBinding
import com.example.labelingapp.db.Entry
import com.example.labelingapp.db.EntryDB
import java.text.SimpleDateFormat
import java.util.*
import kotlin.concurrent.thread

class Entry_Fragment : Fragment(R.layout.entry_fragment) {
    private var binding : EntryFragmentBinding? =null
    private var WorkerSelected = ""
    private var WorkSelected = ""


    fun getCurrentDateTime(): String {
        val time = Calendar.getInstance().time
        val formatter = SimpleDateFormat("yyyy-MM-dd HH:mm")
        val current = formatter.format(time)
        return current}

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = EntryFragmentBinding.inflate(inflater,container,false)
        val view = binding!!.root
        val Worker: Spinner = binding!!.spnWorker
        ArrayAdapter.createFromResource(requireActivity() as MainActivity, R.array.Workers,android.R.layout.simple_spinner_item)
            .also { adapter -> adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                Worker.adapter = adapter}
            Worker.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
                override fun onItemSelected(adapterView : AdapterView<*>?, view: View?, position: Int, id: Long) {
                    WorkerSelected = adapterView?.getItemAtPosition(position).toString()
                }

                override fun onNothingSelected(p0: AdapterView<*>?) {

                }

            }
        val item : Spinner = binding!!.spnWorkList
        ArrayAdapter.createFromResource(requireActivity() as MainActivity, R.array.Items,android.R.layout.simple_spinner_item)
            .also { adapter -> adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                item.adapter = adapter
            item.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
                override fun onItemSelected(adapterView: AdapterView<*>?, view: View?, position: Int, id: Long) {
                    WorkSelected = adapterView?.getItemAtPosition(position).toString()
                }

                override fun onNothingSelected(p0: AdapterView<*>?) {

                }
            }


                val db = Room.databaseBuilder(requireActivity() as MainActivity,
                    EntryDB::class.java,"Database")
                    .fallbackToDestructiveMigration()
                    .build()


                val button = binding!!.btnEntry
                button.setOnClickListener {
                    val nItems = binding!!.EditNumberItem.text.toString()
                    var problems = binding!!.EditProblemsId.text.toString()
                    Toast.makeText(context, " Η καταχωρηση σας ηταν Επιτυχης",Toast.LENGTH_SHORT).show()
                    thread {
                        db.entryDao()?.insertAll(Entry(ID = 0,
                        WorkerSelected ,WorkSelected , nItems = nItems, problems = problems,getCurrentDateTime()))
                    }
                    binding!!.EditNumberItem.setText("")
                    binding!!.EditProblemsId.setText("")
                }

                val btnDelete = binding!!.btnDelete
                btnDelete.setOnClickListener {
                    thread {
                        db.entryDao()?.deleteUser(WorkerSelected)
                    }
                    Toast.makeText(context," User has been deleted", Toast.LENGTH_SHORT).show()
                }

        return view
    }



}
    override fun onDestroyView() {
        super.onDestroyView()
        return
    }
}