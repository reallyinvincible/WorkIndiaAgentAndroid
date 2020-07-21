package com.exuberant.workindia.ui.auth

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.exuberant.workindia.R
import com.exuberant.workindia.ui.home.HomeActivity
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.bottomsheet_add_task.*

class NewAgentBottomSheet: BottomSheetDialogFragment()  {

    private lateinit var authActivity: AuthActivity

    override fun onAttach(context: Context) {
        super.onAttach(context)
        authActivity = context as AuthActivity
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.bottomsheet_add_agent, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialize()
    }

    private fun initialize() {

    }


}