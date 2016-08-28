package com.wyx.chartdemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.wyx.chartdemo.activity.Simple1;
import com.wyx.chartdemo.activity.Simple2;
import com.wyx.chartdemo.activity.Simple3;
import com.wyx.chartdemo.activity.Simple4;

public class MainActivity extends AppCompatActivity {

  @Bind(R.id.steps) ListView steps;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    ButterKnife.bind(this);

    Class[] data = new Class[] { Simple1.class, Simple2.class, Simple3.class, Simple4.class };

    final ArrayAdapter<Class> adapter = new ArrayAdapter<>(this, android.R.layout.simple_expandable_list_item_1);
    adapter.addAll(data);
    steps.setAdapter(adapter);
    steps.setOnItemClickListener(new AdapterView.OnItemClickListener() {
      @Override
      public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(MainActivity.this, adapter.getItem(position));
        startActivity(intent);
      }
    });
  }
}
