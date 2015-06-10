package com.discounty;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.discounty.discounty.Information;
import com.discounty.discounty.RecyclerAdapter;
import com.getbase.floatingactionbutton.AddFloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;
import de.keyboardsurfer.android.widget.crouton.Crouton;
import de.keyboardsurfer.android.widget.crouton.Style;

public class ActivityMain extends ActionBarActivity implements RecyclerAdapter.ClickListener {

    private RecyclerAdapter recyclerAdapter;

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Crouton.cancelAllCroutons();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.itemsList);


        recyclerAdapter = new RecyclerAdapter(getApplicationContext(), getData());
        recyclerView.setAdapter(recyclerAdapter);
        recyclerAdapter.setClickListener(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));


        SwipeableRecyclerViewTouchListener swipeTouchListener =
                new SwipeableRecyclerViewTouchListener(recyclerView,
                        new SwipeableRecyclerViewTouchListener.SwipeListener() {
                            @Override
                            public boolean canSwipe(int position) {
                                return true;
                            }

                            @Override
                            public void onDismissedBySwipeLeft(RecyclerView recyclerView, int[] reverseSortedPositions) {
                                for (int position : reverseSortedPositions) {
                                    recyclerAdapter.delete(position);
                                    recyclerAdapter.notifyItemRemoved(position);
                                }
                                recyclerAdapter.notifyDataSetChanged();
                            }

                            @Override
                            public void onDismissedBySwipeRight(RecyclerView recyclerView, int[] reverseSortedPositions) {
                                for (int position : reverseSortedPositions) {
                                    recyclerAdapter.delete(position);
                                    recyclerAdapter.notifyItemRemoved(position);
                                }
                                recyclerAdapter.notifyDataSetChanged();
                            }
                        });

        recyclerView.addOnItemTouchListener(swipeTouchListener);
        recyclerView.addItemDecoration(new SimpleDividerItemDecoration(getApplicationContext()));


        AddFloatingActionButton fabAdd = (AddFloatingActionButton) findViewById(R.id.fab_add);
        fabAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                final SweetAlertDialog dialog = new SweetAlertDialog(v.getContext(), SweetAlertDialog.WARNING_TYPE);
//                dialog
//                        .setTitleText("Are you sure?")
//                        .setContentText("Won't be able to recover this file!")
//                        .setCancelText("No,cancel plx!")
//                        .setConfirmText("Yes,delete it!")
//                        .showCancelButton(true)
//                        .setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
//                            @Override
//                            public void onClick(SweetAlertDialog sDialog) {
//                                sDialog.cancel();
//                            }
//                        })
//                        .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
//                            @Override
//                            public void onClick(SweetAlertDialog sweetAlertDialog) {
//                                dialog.cancel();
//                                new SweetAlertDialog(sweetAlertDialog.getContext(), SweetAlertDialog.SUCCESS_TYPE)
//                                        .setTitleText("Good job!")
//                                        .setContentText("You clicked the button!")
//                                        .show();
//                            }
//                        })
//                        .show();
                Crouton.makeText(ActivityMain.this, "You've just pressed the floating button:)", Style.INFO).show();
            }
        });
    }


//    private boolean shouldDelete = false;
//    public boolean onShowDeleteAlertDialog(View v){
//        final SweetAlertDialog dialog = new SweetAlertDialog(v.getContext(), SweetAlertDialog.WARNING_TYPE);
//        dialog
//                .setTitleText("Are you sure?")
//                .setContentText("Won't be able to recover this file!")
//                .setCancelText("No,cancel plx!")
//                .setConfirmText("Yes,delete it!")
//                .showCancelButton(true)
//                .setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
//                    @Override
//                    public void onClick(SweetAlertDialog sDialog) {
//                        sDialog.cancel();
//                    }
//                })
//                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
//                    @Override
//                    public void onClick(SweetAlertDialog sweetAlertDialog) {
//                        dialog.cancel();
//                        new SweetAlertDialog(sweetAlertDialog.getContext(), SweetAlertDialog.SUCCESS_TYPE)
//                                .setTitleText("Good job!")
//                                .setContentText("You clicked the button!")
//                                .show();
//                        shouldDelete = true;
//                    }
//                })
//                .show();
//        if(shouldDelete){
//            shouldDelete = false;
//            return true;
//        }
//        return false;
//    }


    public static List<Information> getData() {
        List<Information> data = new ArrayList<>();
        String[] titles = {"Hello1", "Title2", "Something3", "And4", "Привет", "цуйцпвапф", "zazazaaza"};
        String[] desc = {"Dummy description number 1...", "Lorem ipsum dolor sit amet, consectetur...", "", "heey! Thi is a dummy description!..."};

        for (int i = 0; i < 5; ++i) {
            Information current = new Information();
            current.letter = titles[i % titles.length].charAt(0);
            current.title = titles[i % titles.length];
            current.desc = desc[i % desc.length];
            data.add(current);
        }

        return data;
    }

    @Override
    public void itemClicked(View view, int position) {


        final SweetAlertDialog dialog = new SweetAlertDialog(ActivityMain.this, SweetAlertDialog.BUTTON_POSITIVE);
        dialog.setTitleText("Are you sure?");
        dialog.setContentText("Won't be able to recover this file!");
        dialog.setCancelText("No,cancel plx!");
        dialog.setConfirmText("Yes,delete it!");
        dialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
            @Override
            public void onClick(SweetAlertDialog sweetAlertDialog) {
                dialog.cancel();
                new SweetAlertDialog(sweetAlertDialog.getContext(), SweetAlertDialog.SUCCESS_TYPE)
                        .setTitleText("Good job!")
                        .setContentText("You clicked the button!")
                        .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                            @Override
                            public void onClick(SweetAlertDialog sweetAlertDialog) {
                                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                            }
                        })
                        .show();
            }
        });
        dialog.show();
    }
}
