package com.yuong.demo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.contrarywind.listener.OnItemSelectedListener;
import com.contrarywind.view.WheelView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private WheelView wheelView;
    private int mIndex;
    private String mLastDateTime;
    private List<String> optionsItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        wheelView = findViewById(R.id.wheelview);
        wheelView.setCyclic(false);
        wheelView.setItemsVisibleCount(7);

        optionsItems = getOptionsItems(DateTimeUtil.getCurrentDate());
        wheelView.setAdapter(new ArrayWheelAdapter(optionsItems));
        wheelView.setCurrentItem(mIndex);
        wheelView.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(int index) {
                Toast.makeText(MainActivity.this, "当前位置：" + index + "    日期：" + optionsItems.get(index), Toast.LENGTH_SHORT).show();
                if (index <= 14 || index >= 47) {
                    mLastDateTime = optionsItems.get(index);
                    optionsItems = getOptionsItems(mLastDateTime);
                    wheelView.setAdapter(new ArrayWheelAdapter(optionsItems));
                    wheelView.setCurrentItem(mIndex);
                }
            }
        });
    }

    private List<String> getOptionsItems(String date) {
        final List<String> optionsItems = new ArrayList<>();
        List<DateTimeModel> dateTimeModels = DateTimeUtil.getDateTimeModels(date);
        for (int i = 0; i < dateTimeModels.size(); i++) {
            DateTimeModel temp = dateTimeModels.get(i);
            optionsItems.add(temp.getDate());
            if (temp.getDate().equals(date)) {
                mIndex = i;
            }
        }
        Log.e("MainActivity", dateTimeModels.toString());
        return optionsItems;
    }
}
