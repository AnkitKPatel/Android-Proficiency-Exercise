package com.ankit.testappankit;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.ankit.testappankit.adapters.FeedAdapter;
import com.ankit.testappankit.adapters.model.Feed;
import com.ankit.testappankit.adapters.pojo.FeedPOJO;
import com.ankit.testappankit.utils.NoConnectivityException;

import org.json.JSONException;

import java.net.SocketTimeoutException;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * The type Main activity.
 */
public class MainActivity extends AppCompatActivity {
    private static ArrayList<Feed> feedList = new ArrayList<>();
    private Context mContext = this;
    private View root;
    private SwipeRefreshLayout srl_feeds;
    private RecyclerView rv_feeds;
    private Toolbar toolbar;
    private TextView tv_title;
    private FeedAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initComponents();
        initListeners();

        loadFeeds();
    }


    // Initialize the components to be used later in the activity
    private void initComponents() {
        root = findViewById(R.id.root);
        rv_feeds = findViewById(R.id.rv_feeds);
        srl_feeds = findViewById(R.id.srl_feeds);
        srl_feeds.setColorSchemeResources(R.color.colorPrimary, R.color.colorAccent);

        setToolbar();
    }

    // Setting the Toolbar component
    private void setToolbar() {
        toolbar = findViewById(R.id.toolbar);
        tv_title = findViewById(R.id.tv_title);
        tv_title.setText(R.string.app_name);
        setSupportActionBar(toolbar);
    }

    // Initialize the Event Listeners
    private void initListeners() {
        srl_feeds.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadFeeds();
            }
        });
    }

    // Initialize the List Adapter and setting it to the RecyclerView
    private void prepareList() {
        mAdapter = new FeedAdapter(feedList, mContext);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(mContext);
        rv_feeds.setLayoutManager(mLayoutManager);
        rv_feeds.setItemAnimator(new DefaultItemAnimator());
        rv_feeds.setAdapter(mAdapter);
    }

    // Prepare the API client to call the API and handle response
    private void loadFeeds() {
        //Start showing the loading of the data
        srl_feeds.setRefreshing(true);
        APIInterface apiService = APIClient.getClient().create(APIInterface.class);
        Call<FeedPOJO> call = apiService.getFeeds();
        call.enqueue(new Callback<FeedPOJO>() {
            @Override
            public void onResponse(Call<FeedPOJO> call, Response<FeedPOJO> response) {
                try {
                    if (response.isSuccessful()) {
                        FeedPOJO feedPojo = response.body();
                        // Setting the Toolbar title by the value we get in the API as title
                        tv_title.setText(feedPojo.getTitle());
                        if (feedList.size() > 0) {
                            //clearing the old feedlist if it contains any value
                            feedList.clear();
                        }
                        // adding all the feed objects in the feedList
                        feedList.addAll(feedPojo.getFeeds());
                        prepareList();
                    } else {
                        // in case of unsuccessful API call, show appropriate message to the user
                        Log.e("ERROR", response.message());
                        Snackbar.make(root, mContext.getString(R.string.CallFailedWithError, response.message()), Snackbar.LENGTH_SHORT).show();
                    }
                    // remove showing the loading of the data
                    srl_feeds.setRefreshing(false);
                } catch (Exception E) {
                    // Error handling
                    Log.e("ERROR", E.toString());
                    Snackbar.make(root, mContext.getString(R.string.CallFailedWithError, E.toString()), Snackbar.LENGTH_SHORT).show();
                    srl_feeds.setRefreshing(false);
                }
            }

            @Override
            public void onFailure(Call<FeedPOJO> call, Throwable t) {
                // Handle the cases of failure by showing appropriate messages to the users
                if (t instanceof NoConnectivityException) {
                    Snackbar.make(root, R.string.noInternetConnection_AlertMessage, Snackbar.LENGTH_LONG).show();
                } else if (t instanceof JSONException) {
                    Snackbar.make(root, R.string.JSONException, Snackbar.LENGTH_LONG).show();
                } else if (t instanceof SocketTimeoutException) {
                    Snackbar.make(root, R.string.SocketTimeoutException, Snackbar.LENGTH_LONG).show();
                } else
                    Snackbar.make(root, R.string.SomethingWentWrong, Snackbar.LENGTH_SHORT).show();

                srl_feeds.setRefreshing(false);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_close:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
