package com.discounty.discounty;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuAdapter;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.baoyz.swipemenulistview.SwipeMenuListView;
import com.discounty.ActivityMain;
import com.discounty.R;
import com.discounty.Utils;

import java.util.Collections;
import java.util.List;

import de.keyboardsurfer.android.widget.crouton.Crouton;
import de.keyboardsurfer.android.widget.crouton.Style;


public class MainListViewActivity extends Activity {

    private List<Information> listData;
    private MainListViewAdapter listAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_view);

        listData = ActivityMain.getData();

        SwipeMenuListView listView = (SwipeMenuListView) findViewById(R.id.listView);

        listAdapter = new MainListViewAdapter(listData);

        listView.setAdapter(new SwipeMenuAdapter(getApplicationContext(), listAdapter));

        SwipeMenuCreator creator = new SwipeMenuCreator() {

            @Override
            public void create(SwipeMenu menu) {
                // create "open" item
                SwipeMenuItem openItem = new SwipeMenuItem(
                        getApplicationContext());
                // set item background
                openItem.setBackground(new ColorDrawable(Color.rgb(0xC9, 0xC9,
                        0xCE)));
                // set item width
                openItem.setWidth(dp2px(90));
                // set item title
                openItem.setTitle("Open");
                // set item title font size
                openItem.setTitleSize(18);
                // set item title font color
                openItem.setTitleColor(Color.WHITE);
                // add to menu
                menu.addMenuItem(openItem);

                // create "delete" item
                SwipeMenuItem deleteItem = new SwipeMenuItem(
                        getApplicationContext());
                // set item background
                deleteItem.setBackground(new ColorDrawable(Color.rgb(0xF9,
                        0x3F, 0x25)));
                // set item width
                deleteItem.setWidth(dp2px(90));
                // set a icon
                deleteItem.setIcon(R.drawable.ic_delete);
                // add to menu
                menu.addMenuItem(deleteItem);
            }
        };
        // set creator
        listView.setMenuCreator(creator);

        listView.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(int position, SwipeMenu menu, int index) {
                //Information item = listData.get(position);
                switch (index) {
                    case 0:
                        // open
                        //open(item);
                        break;
                    case 1:
                        // delete
//                        delete(item);
                        listData.remove(position);
                        listAdapter.notifyDataSetChanged();
                        break;
                }
                return false;
            }
        });

        // set SwipeListener
        listView.setOnSwipeListener(new SwipeMenuListView.OnSwipeListener() {

            @Override
            public void onSwipeStart(int position) {
                // swipe start
            }

            @Override
            public void onSwipeEnd(int position) {
                // swipe end
            }
        });

        // other setting
        //listView.setCloseInterpolator(new BounceInterpolator());

        // test item long click
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view,
                                           int position, long id) {
                Crouton.makeText(MainListViewActivity.this, position + " long click", Style.INFO).show();
                return false;
            }
        });

    }

//    private void delete(Information item) {
//        // delete app
//        try {
//            Intent intent = new Intent(Intent.ACTION_DELETE);
//            intent.setData(Uri.fromParts("package", item.title, null));
//            startActivity(intent);
//        } catch (Exception e) { }
//    }

    public class MainListViewAdapter extends BaseAdapter {

        List<Information> data = Collections.emptyList();

        public MainListViewAdapter(List<Information> data) {
            this.data = data;
        }

//        public void delete(int i) {
//            data.remove(i);
//        }

        @Override
        public Information getItem(int position) {
            return data.get(position);
        }

        public String get(int i) {
            Information inf = data.get(i);
            return inf.title + "\n" + inf.desc;
        }

        @Override
        public int getCount() {
            return data == null ? 0 : data.size();
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public int getViewTypeCount() {
            // menu type count
            return 3;
        }

        @Override
        public int getItemViewType(int position) {
            // current menu type
            return position % 3;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = View.inflate(getApplicationContext(),
                        R.layout.single_row, null);
                new ViewHolder(convertView);
            }
            ViewHolder viewHolder = (ViewHolder) convertView.getTag();

            Information current = data.get(position);
            GradientDrawable icon_color = (GradientDrawable) viewHolder.icon.getBackground();
            icon_color.setColor(Color.parseColor(Utils.getColorOfTheListItem(current.letter)));
            viewHolder.title.setText(current.title);
            viewHolder.icon.setText(Character.toString(current.letter));
            if (!current.desc.isEmpty())
                viewHolder.desc.setText(current.desc);
            else viewHolder.desc.setVisibility(View.GONE);

            return convertView;
        }

        class ViewHolder {

            TextView title;
            TextView icon;
            TextView desc;

            public ViewHolder(View itemView) {
                title = (TextView) itemView.findViewById(R.id.list_title);
                icon = (TextView) itemView.findViewById(R.id.text_with_icon);
                desc = (TextView) itemView.findViewById(R.id.description);
                itemView.setTag(this);
            }
        }
    }


    private int dp2px(int dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp,
                getResources().getDisplayMetrics());
    }
}
