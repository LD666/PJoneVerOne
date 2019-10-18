package com.myfirstapplication.pjoneverone.activity

import android.content.Intent
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.myfirstapplication.pjoneverone.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()

        var loadingTask = myTask()
        loadingTask.execute()

    }

    inner class myTask : AsyncTask<String, Double, String>(){

        var total = 5


        override fun doInBackground(vararg params: String): String {

            for(i in 0 ..total){
                Thread.sleep(100)
                //var e = "hhh"
                publishProgress(i.toDouble())
            }

            var str = "Welcome..."
            return str

        }


        override fun onPostExecute(result: String) {

            super.onPostExecute(result)
            progressBar.visibility = View.GONE
            text_view_loading.text = result

//            var myIntent = Intent(this@MainActivity, MainPageActivity::class.java)
            var myIntent = Intent(this@MainActivity, WelcomeActivity::class.java)
//            var myIntent = Intent(this@MainActivity, MyCurrentLocationActivity::class.java)
            startActivity(myIntent)

        }


    }
}
