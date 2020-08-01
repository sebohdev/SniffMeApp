package de.seboh.sniffmeapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Make text view scrollable
        activity_main_feedback.movementMethod = ScrollingMovementMethod()

        // Btn Reset Handling
        activity_main_btn_reset.setOnClickListener {
            activity_main_feedback.text = ""
        }

        // Btn Send Handling
        activity_main_btn_send.setOnClickListener {

            // Waiting for Response
            activity_main_feedback.text = "Wait for response ..."

            // Instantiate the RequestQueue.
            val queue = Volley.newRequestQueue(this)
            val url = "https://jsonplaceholder.typicode.com/todos/1"

            // Request a string response from the provided URL.
            val stringRequest = StringRequest(Request.Method.GET, url,
                Response.Listener<String> { response ->
                    // Display response
                    activity_main_feedback.text = response
                },
                Response.ErrorListener { error ->
                    activity_main_feedback.text = error.localizedMessage
                })

            // Add the request to the RequestQueue.
            stringRequest.setShouldCache(false) // Avoid caching
            queue.add(stringRequest)
        }

    }
}