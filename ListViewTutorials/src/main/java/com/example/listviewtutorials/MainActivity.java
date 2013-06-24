package com.example.listviewtutorials;

import android.content.Context;
import android.os.Bundle;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

    ListView listView;
    Context mContext;
    String[] planets;

    @Override
    public LayoutInflater getLayoutInflater() {
        return super.getLayoutInflater();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = this;

        listView = (ListView) findViewById(R.id.listView);

        planets = getResources().getStringArray(R.array.planetsArray);

        ArrayAdapter adapter = new PlanetsAdapter(this, R.layout.listview_item, R.id.listText, planets);
        View header = getLayoutInflater().inflate(R.layout.planets_header_view, null);

        listView.addHeaderView(header);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new ListClickHandler());

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    private class ListClickHandler implements AdapterView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
            TextView listText = (TextView) view.findViewById(R.id.listText);
            String text = listText.getText().toString();
            Toast.makeText(mContext, text, Toast.LENGTH_SHORT).show();
        }
    }

    private class PlanetsAdapter extends ArrayAdapter<String> {
        String[] planets;
        public PlanetsAdapter(Context context, int resource, int textViewResourceId, String[] planets) {
            super(context, resource, textViewResourceId, planets);
            this.planets = planets;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View viewLeft = getLayoutInflater().inflate(R.layout.listview_item, null);
            View viewRight = getLayoutInflater().inflate(R.layout.listview_item_right, null);

            String planet = planets[position];

            if (position % 2 == 0) {
                ((TextView) viewLeft.findViewById(R.id.listText)).setText(planet);
                convertView = viewLeft;
            } else {
                ((TextView) viewRight.findViewById(R.id.listText)).setText(planet);
                convertView = viewRight;
            }

            return convertView;
        }
    }
}
