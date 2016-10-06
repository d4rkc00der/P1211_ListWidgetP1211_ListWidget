package listwidget.p1211_listwidgetp1211_listwidget;

import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.widget.RemoteViewsService;

/**
 * Created by user on 05.10.2016.
 */

public class MyService extends RemoteViewsService {
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        return new MyFactory(getApplicationContext(),intent);
    }
}
