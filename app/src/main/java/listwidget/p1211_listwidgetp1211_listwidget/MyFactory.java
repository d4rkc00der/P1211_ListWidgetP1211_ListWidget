package listwidget.p1211_listwidgetp1211_listwidget;

import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.icu.text.DateFormat;
import android.icu.text.SimpleDateFormat;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by user on 05.10.2016.
 */

public class MyFactory implements RemoteViewsService.RemoteViewsFactory {

    ArrayList<String> data;
    Context context;
    SimpleDateFormat sdf;
    int widgetID;

    @RequiresApi(api = Build.VERSION_CODES.N)
    MyFactory(Context ctx, Intent intent) {
        this.context = ctx;
        sdf = new SimpleDateFormat("HH:mm:ss");
        widgetID = intent.getIntExtra(AppWidgetManager.EXTRA_APPWIDGET_ID,AppWidgetManager.INVALID_APPWIDGET_ID);

    }
    @Override
    public void onCreate() {
        data = new ArrayList<String>();
    }

    public int getCount() {
        return data.size();
    }
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onDataSetChanged() {
        data.clear();
        data.add(sdf.format(new Date(System.currentTimeMillis())));
        data.add(String.valueOf(hashCode()));
        data.add(String.valueOf(widgetID));
        for (int i = 0; i < 15; i++) {
            data.add("Item "+i);
        }
    }

    @Override
    public void onDestroy() {

    }



    @Override
    public RemoteViews getViewAt(int position) {
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.item);
        views.setTextViewText(R.id.tvItemText,data.get(position));
        return views;
    }

    @Override
    public RemoteViews getLoadingView() {
        return null;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public long getItemId(int position) {
        return position;
}

    @Override
    public boolean hasStableIds() {
        return true;
    }
}
