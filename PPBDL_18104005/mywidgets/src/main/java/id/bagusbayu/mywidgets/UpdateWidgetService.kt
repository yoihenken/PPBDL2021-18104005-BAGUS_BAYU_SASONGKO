package id.bagusbayu.mywidgets

import android.app.job.JobParameters
import android.app.job.JobService
import android.appwidget.AppWidgetManager
import android.content.ComponentName
import android.widget.RemoteViews

class UpdateWidgetService : JobService() {
    override fun onStartJob(p0: JobParameters): Boolean {
        val manager = AppWidgetManager.getInstance(this)
        val view = RemoteViews(packageName, R.layout.random_number_widget)
        val theWidget = ComponentName(this, RandomNumberWidget::class.java)
        val lastUpdate = "Random : " + NumberGenerator.generate(100)
        view.setTextViewText(R.id.appwidget_text, lastUpdate)
        manager.updateAppWidget(theWidget, view)
        jobFinished(p0, false)
        return true
    }

    override fun onStopJob(p0: JobParameters): Boolean = false
}