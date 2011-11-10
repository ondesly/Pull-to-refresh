package com.example.pulltorefresh;

import com.example.pulltorefresh.PullToRefresh.OnRefreshListener;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;

public class Main extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		String[] items = {"Item 0", "Item 1", "Item 2", "Item 3", "Item 4",
				"Item 5", "Item 6", "Item 7", "Item 8", "Item 9"};
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, android.R.id.text1, items);

		final PullToRefresh listView = (PullToRefresh) findViewById(R.id.listview);
		listView.setAdapter(adapter);
		listView.setOnRefreshListener(new OnRefreshListener() {

			@Override
			public void onRefresh() {
				new Thread(new Runnable() {

					@Override
					public void run() {
						try {
							Thread.sleep(2000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						runOnUiThread(new Runnable() {

							@Override
							public void run() {
								listView.onRefreshComplete();
							}

						});
					}

				}).start();
			}

		});
	}

}