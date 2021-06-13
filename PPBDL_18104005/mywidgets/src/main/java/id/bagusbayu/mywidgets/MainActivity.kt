package id.bagusbayu.mywidgets

import android.app.job.JobInfo
import android.app.job.JobScheduler
import android.content.ComponentName
import android.content.Context
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnStart = findViewById<Button>(R.id.btn_start)
        val btnStop = findViewById<Button>(R.id.btn_stop)

        btnStart.setOnClickListener {
            val mServiceComponent = ComponentName(this,UpdateWidgetService::class.java)
            val builder = JobInfo.Builder(JOB_ID,mServiceComponent)
            builder.setRequiredNetworkType(JobInfo.NETWORK_TYPE_NONE)
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.N){
                builder.setPeriodic(900000) // 15 MINUTE
            } else builder.setPeriodic(SCHEDULE_OF_PERIOD) // 3 MINUTE
            val jobScheduler = getSystemService(Context.JOB_SCHEDULER_SERVICE) as JobScheduler
            jobScheduler.schedule(builder.build())

            Toast.makeText(this, "Job Service started", Toast.LENGTH_SHORT).show()
        }

        btnStop.setOnClickListener {
            val tm = getSystemService(Context.JOB_SCHEDULER_SERVICE) as JobScheduler
            tm.cancel(JOB_ID)
            Toast.makeText(this, "Job Service canceled", Toast.LENGTH_SHORT).show()

        }

    }

    companion object{
        private const val JOB_ID = 100
        private const val SCHEDULE_OF_PERIOD = 86000L
    }



}