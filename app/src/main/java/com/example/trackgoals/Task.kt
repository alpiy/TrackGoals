package com.example.trackgoals

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.trackgoals.model.UserData
import com.example.trackgoals.view.UserAdapter
import com.google.android.material.floatingactionbutton.FloatingActionButton

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class Task : Fragment() {

    private lateinit var addsBtn: FloatingActionButton
    private lateinit var recy: RecyclerView
    private lateinit var userList: ArrayList<UserData>
    private lateinit var userAdapter: UserAdapter

    private var param1: String? = null
    private var param2: String? = null

    private var onTaskAddedListener: OnTaskAddedListener? = null

    interface OnTaskAddedListener {
        fun onTaskAdded()
    }

    fun setOnTaskAddedListener(listener: OnTaskAddedListener) {
        onTaskAddedListener = listener
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        userList = ArrayList()
        userAdapter = UserAdapter(requireContext(), userList)

        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_task, container, false)

        addsBtn = view.findViewById(R.id.addingBtn)
        recy = view.findViewById(R.id.mRecycler)
        recy.layoutManager = LinearLayoutManager(context)
        recy.adapter = userAdapter
        addsBtn.setOnClickListener { addInfo() }

        return view
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun addInfo() {
        val inflater = LayoutInflater.from(requireContext())
        val v = inflater.inflate(R.layout.add_item, null)
        val mPekerjaan = v.findViewById<EditText>(R.id.mPekerjaan)
        val mTanggal = v.findViewById<EditText>(R.id.mTanggal)

        val addDialog = AlertDialog.Builder(requireContext())
        addDialog.setView(v)

        addDialog.setPositiveButton("OK") { dialog, _ ->
            val kerjaan = mPekerjaan.text.toString()
            val tanggal = mTanggal.text.toString()
            userList.add(UserData(userName = "Kerjaan : $kerjaan", userMb = "Tanggal : $tanggal"))
            userAdapter.notifyDataSetChanged()
            Toast.makeText(requireContext(), "Pekerjaan Telah Ditambahkan", Toast.LENGTH_SHORT).show()
            dialog.dismiss()

            onTaskAddedListener?.onTaskAdded()
        }

        addDialog.setNegativeButton("Cancel") { dialog, _ ->
            dialog.dismiss()
            Toast.makeText(requireContext(), "Cancel", Toast.LENGTH_SHORT).show()
        }

        val dialog = addDialog.create()
        dialog.show()
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            Task().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
