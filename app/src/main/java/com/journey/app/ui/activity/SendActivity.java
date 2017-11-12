package com.journey.app.ui.activity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.journey.app.R;
import com.journey.app.api.JourneyApi;
import com.journey.app.model.Fragment;
import com.journey.app.util.Api;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SendActivity extends AppCompatActivity {

    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.content) EditText content;
    @BindView(R.id.location) TextView location;

    private boolean locationFinished = false;

    private Fragment fragment = new Fragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send);

        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        initToolbar();
        initLocation();
    }

    private void initToolbar() {
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    private void initLocation() {
        AMapLocationClient client = new AMapLocationClient(this);
        AMapLocationClientOption option = new AMapLocationClientOption();
        client.setLocationListener(new AMapLocationListener() {
            @Override public void onLocationChanged(AMapLocation aMapLocation) {
                if (aMapLocation != null) {
                    if (aMapLocation.getErrorCode() == 0) {
                        Log.e("location", aMapLocation.getAoiName());
                    } else {
                        Log.e("location", aMapLocation.getErrorInfo());
                    }
                } else {
                    Log.e("location", "null");
                }
            }
        });
        option.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
        option.setOnceLocation(true);
        option.setNeedAddress(true);
        client.setLocationOption(option);
        client.startLocation();
        new CountDownTimer(3000, 3000) {
            @Override public void onTick(long millisUntilFinished) {
            }

            @Override public void onFinish() {
                fragment.location = "北京邮电大学(宏福校区)";
                updateLocation();
                locationFinished = true;
            }
        }.start();
        location.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                if (!locationFinished) {
                    return;
                }
                inputLocation();
            }
        });
    }

    private void updateLocation() {
        location.setText(fragment.location);
    }

    private void inputLocation() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("修改定位地址");

        final EditText editText = new EditText(this);
        editText.setInputType(InputType.TYPE_CLASS_TEXT);
        editText.setText(fragment.location);
        builder.setView(editText);

        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override public void onClick(DialogInterface dialog, int which) {
                fragment.location = editText.getText().toString();
                updateLocation();
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.show();
    }

    @Override public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_send, menu);
        return true;
    }

    @Override public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_send) {
            getLatestFragment();
            return true;
        }
        return false;
    }

    private void getLatestFragment() {
        JourneyApi api = Api.getApiInstance();
        Call<ArrayList<Fragment>> getLatestFragment = api.getLatestFragment();
        getLatestFragment.enqueue(new Callback<ArrayList<Fragment>>() {
            @Override public void onResponse(Call<ArrayList<Fragment>> call, Response<ArrayList<Fragment>> response) {
                if (response.body() != null) {
                    final Fragment latestFragment = response.body().get(0);
                    runOnUiThread(new Runnable() {
                        @Override public void run() {
                            sendFragment(latestFragment.id);
                        }
                    });
                }
            }

            @Override public void onFailure(Call<ArrayList<Fragment>> call, Throwable t) {

            }
        });
    }

    private void sendFragment(int latestFragmentId) {
        fragment.id = latestFragmentId + 1;
        fragment.content = content.getText().toString();
        fragment.userId = 3;
        fragment.likeCount = 0;
        SimpleDateFormat format = new SimpleDateFormat("YYYY-mm-dd HH:MM:SS");
        fragment.time = format.format(new Date());
        fragment.travelId = -1;

        JourneyApi api = Api.getApiInstance();
        Call<Fragment> addFragment = api.addFragment(fragment);
        addFragment.enqueue(new Callback<Fragment>() {
            @Override public void onResponse(Call<Fragment> call, Response<Fragment> response) {
                finish();
            }

            @Override public void onFailure(Call<Fragment> call, Throwable t) {
                //
            }
        });
    }

}
