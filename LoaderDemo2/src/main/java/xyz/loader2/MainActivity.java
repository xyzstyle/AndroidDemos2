package xyz.loader2;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.ListFragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import xyz.loader2.loader.AppEntry;
import xyz.loader2.loader.AppListLoader;
import java.util.List;


public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FragmentManager fm=getSupportFragmentManager();
        if(fm.findFragmentById(android.R.id.content)==null){
            AppListFragment list=new AppListFragment();
            fm.beginTransaction().add(android.R.id.content,list).commit();
        }

    }

    public static class AppListFragment extends ListFragment implements LoaderManager.LoaderCallbacks<List<AppEntry>>{

        private AppListAdapter mAdapter;
        private static final int LOADER_ID = 1;

        @Override
        public void onActivityCreated(@Nullable Bundle savedInstanceState) {
            super.onActivityCreated(savedInstanceState);
            setHasOptionsMenu(true);
            mAdapter=new AppListAdapter(getActivity());
            setEmptyText("No Applications");
            setListAdapter(mAdapter);
            setListShown(false);

            if (getLoaderManager().getLoader(LOADER_ID) == null) {
                Log.i("TAG", "Initializing the new Loader...");
            } else {
                Log.i("TAG", "Reconnecting with existing Loader (id '1')...");
            }
            getLoaderManager().initLoader(LOADER_ID, null, this);
        }

        @Override
        public Loader<List<AppEntry>> onCreateLoader(int id, Bundle args) {
            Log.i("TAG", "onCreateLoader()");
            return new AppListLoader(getActivity());
        }

        @Override
        public void onLoadFinished(Loader<List<AppEntry>> loader, List<AppEntry> data) {
            Log.i("TAG", "onLoadFinished()");
            mAdapter.setData(data);
            if(isResumed()){
                setListShown(true);
            }else {
                setListShownNoAnimation(true);
            }
        }

        @Override
        public void onLoaderReset(Loader<List<AppEntry>> loader) {
            Log.i("TAG", "onLoaderReset()");
            mAdapter.setData(null);

        }


        @Override
        public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
            inflater.inflate(R.menu.activity_main, menu);
        }

        @Override
        public boolean onOptionsItemSelected(MenuItem item) {
            switch (item.getItemId()) {
                case R.id.menu_configure_locale:
                    configureLocale();
                    return true;
            }
            return false;
        }

        /**
         * Notifies the Loader that a configuration change has has occurred (i.e. by
         * calling {@link Loader#onContentChanged()}).
         *
         * This feature was added so that it would be easy to see the sequence of
         * events that occurs when a content change is detected. Connect your
         * device via USB and analyze the logcat to see the sequence of methods that
         * are called as a result!
         */
        private void configureLocale() {
            Loader<AppEntry> loader = getLoaderManager().getLoader(LOADER_ID);
            if (loader != null) {
                startActivity(new Intent(Settings.ACTION_LOCALE_SETTINGS));
            }
        }
    }
}