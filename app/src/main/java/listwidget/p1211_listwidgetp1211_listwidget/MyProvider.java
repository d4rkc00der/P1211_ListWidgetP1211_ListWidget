package listwidget.p1211_listwidgetp1211_listwidget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.os.Build;
import android.provider.Settings;
import android.support.annotation.RequiresApi;
import android.widget.RemoteViews;

import java.util.Date;

/**
 * Created by user on 05.10.2016.
 */

@RequiresApi(api = Build.VERSION_CODES.N)
public class MyProvider extends AppWidgetProvider {

    SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");

    public void onUpdate(Context context, AppWidgetManager appWidgetManager,
                         int[] appWidgets) {
        super.onUpdate(context,appWidgetManager,appWidgets);

        for( int i: appWidgets) {
            updateWidget(context,appWidgetManager,i);
        }
    }

    void updateWidget(Context context, AppWidgetManager appWidgetManager,
                     int appWidgetId) {
        RemoteViews rv = new RemoteViews(context.getPackageName(),
                R.layout.widget);

        setUpdateTV(rv,context,appWidgetId);

        setList(rv,context,appWidgetId);
        setListClick(rv,context,appWidgetId);

        appWidgetManager.updateAppWidget(appWidgetId,rv);
    }

    void setUpdateTV(RemoteViews rv, Context context, int appWidgetId) {
        rv.setTextViewText(R.id.tvUpdate, sdf.format(new Date(System.currentTimeMillis())));
        Intent updIntent  = new Intent(context,MyProvider.class);
        updIntent.setAction(AppWidgetManager.ACTION_APPWIDGET_UPDATE);
        updIntent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS, new int[] {appWidgetId});
        PendingIntent updPIntent = PendingIntent.getBroadcast(context,appWidgetId,updIntent,0);
        rv.setOnClickPendingIntent(R.id.tvUpdate,updPIntent);
    }

    void setList(RemoteViews remoteViews, Context context, int appWidgetId) {
        Intent adapter = new Intent(context,MyService.class);
        adapter.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID,appWidgetId);
        remoteViews.setRemoteAdapter(R.id.lvList,adapter);
    }

    void setListClick(RemoteViews rv,Context context, int appWidgetId) {

    }
}
